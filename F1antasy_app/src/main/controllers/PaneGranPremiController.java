package main.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import main.F1antasyDB;
import main.User;
import main.Utils;
import main.components.ListCellGranPremio;
import main.dto.*;
import main.model.PaneGranPremiModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaneGranPremiController {

    private PaneGranPremiModel model;

    @FXML public ListView<GranPremioProgrammato> listViewGranPremi;
    @FXML public Label labelMessage;
    @FXML public Label labelPunteggioOttenuto;
    @FXML public HBox hBoxPunteggio;
    @FXML public Label labelNomePilota1;
    @FXML public Label labelPrezzoPilota1;
    @FXML public Label labelNomePilota2;
    @FXML public Label labelPrezzoPilota2;
    @FXML public Label labelNomePilota3;
    @FXML public Label labelPrezzoPilota3;
    @FXML public Label labelNomePilota4;
    @FXML public Label labelPrezzoPilota4;
    @FXML public Label labelNomeMotorizzazione;
    @FXML public Label labelPrezzoMotorizzazione;

    private List<Label> _labelsNomiPiloti = new ArrayList();
    private List<Label> _labelsPrezziPiloti = new ArrayList();;
    private List<Button> _buttonsPiloti = new ArrayList();;

    public void setup() {
        this.model = new PaneGranPremiModel(User.loggedInUser.getUsername());

        listViewGranPremi.setCellFactory(lv -> new ListCellGranPremio());
        listViewGranPremi.getItems().addAll(model.getGranPremi()); // OCCHIOO

        listViewGranPremi.getSelectionModel().getSelectedItems().addListener((ListChangeListener) change -> updateModelAndUI());

        _labelsNomiPiloti.add(labelNomePilota1);
        _labelsNomiPiloti.add(labelNomePilota2);
        _labelsNomiPiloti.add(labelNomePilota3);
        _labelsNomiPiloti.add(labelNomePilota4);
        _labelsPrezziPiloti.add(labelPrezzoPilota1);
        _labelsPrezziPiloti.add(labelPrezzoPilota2);
        _labelsPrezziPiloti.add(labelPrezzoPilota3);
        _labelsPrezziPiloti.add(labelPrezzoPilota4);

        updateUI();
    }

    private void updateModelAndUI() {
        updateModel();
        updateUI();
    }

    private void updateModel() {
        if (listViewGranPremi.getSelectionModel().getSelectedItems().size() == 1) {
            model.selectGranPremio(listViewGranPremi.getSelectionModel().getSelectedItem());
        }
    }

    private void updateUI() {
        Optional<GranPremioProgrammato> selectedGP = model.getSelectedGranPremio();
        Optional<List<PilotaConPrezzo>> piloti = model.getSelectedGranPremioPilotiConPrezzo();
        if (selectedGP.isPresent()) {
            if (piloti.isPresent()) {
                labelNomeMotorizzazione.setText(model.getSelectedGranPremioSquadra().get().getNomeMotorizzazione().getNome());
                labelPrezzoMotorizzazione.setText(model.getSelectedGranPremioPrezzoMotorizzazione().get().toString());

                for (int i = 0; i < _labelsNomiPiloti.size(); i++) {
                    PilotaConPrezzo p = piloti.get().get(i);
                    _labelsNomiPiloti.get(i).setText(p.getPilota().getNome() + " " + p.getPilota().getCognome());
                    _labelsPrezziPiloti.get(i).setText(p.getPrezzo().toString());
                }
                if (selectedGP.get().getConcluso()) {
                    labelMessage.setText("Punteggio Ottenuto");
                    Integer punteggio = model.getSelectedGPPunteggioOttenuto().get();
                    labelPunteggioOttenuto.setText(punteggio.toString());
                    hBoxPunteggio.setOpacity(1);
                } else {
                    labelMessage.setText("Questo Gran Premio è in fase di svolgimento");
                    hBoxPunteggio.setOpacity(0);
                }
            } else {
                labelNomeMotorizzazione.setText("???");
                labelPrezzoMotorizzazione.setText("???");

                for (int i = 0; i < _labelsNomiPiloti.size(); i++) {
                    _labelsNomiPiloti.get(i).setText("???");
                    _labelsPrezziPiloti.get(i).setText("???");
                }
                if (selectedGP.get().getConcluso()) {
                    labelMessage.setText("Quando si è svolto questo Gran Premio non avevi ancora scelto una Squadra.");
                } else {
                    labelMessage.setText("Vedrai la squadra quando questo Gran Premio sarà concluso o in svolgimento.");
                }
                hBoxPunteggio.setOpacity(0);
            }
        } else {
            labelNomeMotorizzazione.setText("???");
            labelPrezzoMotorizzazione.setText("???");

            for (int i = 0; i < _labelsNomiPiloti.size(); i++) {
                _labelsNomiPiloti.get(i).setText("???");
                _labelsPrezziPiloti.get(i).setText("???");
            }

            labelMessage.setText("Seleziona un Gran Premio per vedere la squadra con cui hai partecipato.");
            hBoxPunteggio.setOpacity(0);
        }
    }
}
