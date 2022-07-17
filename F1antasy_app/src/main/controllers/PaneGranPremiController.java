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
        Optional<List<PilotaConPrezzo>> piloti = model.getSelectedGranPremioPilotiConPrezzo();
        for (int i = 0; i < _labelsNomiPiloti.size(); i++) {
            if (piloti.isPresent()) {
                PilotaConPrezzo p = piloti.get().get(i);
                _labelsNomiPiloti.get(i).setText(p.getPilota().getNome() + " " + p.getPilota().getCognome());
                _labelsPrezziPiloti.get(i).setText(p.getPrezzo().toString());
            } else {
                _labelsNomiPiloti.get(i).setText("???");
                _labelsPrezziPiloti.get(i).setText("???");
            }
        }

        Optional<GranPremioProgrammato> gp = model.getSelectedGranPremio();
        if (gp.isPresent()) {
            if (gp.get().getConcluso()) {
                labelNomeMotorizzazione.setText(model.getSelectedGranPremioSquadra().get().getNomeMotorizzazione().getNome());
                labelPrezzoMotorizzazione.setText(model.getSelectedGranPremioPrezzoMotorizzazione().get().toString());

                labelMessage.setText("Punteggio Ottenuto");
                try {
                    labelPunteggioOttenuto.setText(F1antasyDB.getPunteggioOttenutoGranPremioConcluso(model.getUsername(), gp.get().getCampionato().getAnno(), gp.get().getDataGranPremio()).toString());
                } catch (SQLException e) {
                    Utils.crashWithMessage(e.toString());
                }
                hBoxPunteggio.setOpacity(1);
            } else {
                labelNomeMotorizzazione.setText("???");
                labelPrezzoMotorizzazione.setText("???");

                labelMessage.setText("Questo Gran Premio non è ancora concluso.");
                hBoxPunteggio.setOpacity(0);
            }
        } else {
            labelNomeMotorizzazione.setText("???");
            labelPrezzoMotorizzazione.setText("???");

            labelMessage.setText("Seleziona un Gran Premio per vedere la squadra con cui hai partecipato.");
            hBoxPunteggio.setOpacity(0);
        }
    }
}
