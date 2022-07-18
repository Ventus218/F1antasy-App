package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.FXMLResource;
import main.User;
import main.Utils;
import main.components.Acquistabile;
import main.controllers.delegates.PaneExchangeDelegate;
import main.dto.*;
import main.model.PaneSquadraModel;

import java.util.ArrayList;
import java.util.List;

public class PaneSquadraController implements PaneExchangeDelegate {

    private PaneSquadraModel model;

    @FXML public Label labelPunteggioAttuale;
    @FXML public Label labelBudgetRimanente;
    @FXML public Label labelValoreSquadra;
    @FXML public Label labelScambi;
    @FXML public Label labelNomePilota1;
    @FXML public Label labelPrezzoPilota1;
    @FXML public Button buttonScambiaPilota1;
    @FXML public Label labelNomePilota2;
    @FXML public Label labelPrezzoPilota2;
    @FXML public Button buttonScambiaPilota2;
    @FXML public Label labelNomePilota3;
    @FXML public Label labelPrezzoPilota3;
    @FXML public Button buttonScambiaPilota3;
    @FXML public Label labelNomePilota4;
    @FXML public Label labelPrezzoPilota4;
    @FXML public Button buttonScambiaPilota4;
    @FXML public Label labelNomeMotorizzazione;
    @FXML public Label labelPrezzoMotorizzazione;
    @FXML public Button buttonScambiaMotorizzazione;

    private List<Label> _labelsNomiPiloti = new ArrayList();
    private List<Label> _labelsPrezziPiloti = new ArrayList();;
    private List<Button> _buttonsPiloti = new ArrayList();;

    public void setup() {
        this.model = new PaneSquadraModel(User.loggedInUser.getUsername());

        _labelsNomiPiloti.add(labelNomePilota1);
        _labelsNomiPiloti.add(labelNomePilota2);
        _labelsNomiPiloti.add(labelNomePilota3);
        _labelsNomiPiloti.add(labelNomePilota4);
        _labelsPrezziPiloti.add(labelPrezzoPilota1);
        _labelsPrezziPiloti.add(labelPrezzoPilota2);
        _labelsPrezziPiloti.add(labelPrezzoPilota3);
        _labelsPrezziPiloti.add(labelPrezzoPilota4);
        _buttonsPiloti.add(buttonScambiaPilota1);
        _buttonsPiloti.add(buttonScambiaPilota2);
        _buttonsPiloti.add(buttonScambiaPilota3);
        _buttonsPiloti.add(buttonScambiaPilota4);

        updateUI();
    }

    private void updateUI() {
        labelPunteggioAttuale.setText(model.getPunteggioAttuale().toString());
        labelBudgetRimanente.setText(model.getSquadra().getBudgetRimanente().toString());
        labelValoreSquadra.setText(model.getValoreSquadra().toString());
        labelScambi.setText(model.getSquadra().getScambiEffettuati().toString());

        List<Pilota> piloti = model.getPiloti();
        for (int i = 0; i < 4; i++) {
            Pilota p = piloti.get(i);
            _labelsNomiPiloti.get(i).setText(p.getNome() + " " + p.getCognome());
            _labelsPrezziPiloti.get(i).setText(model.getPrezzoPilota(p).toString());
            if (model.getSquadra().getScambiEffettuati() >= Squadra.MASSIMO_SCAMBI) {
                _buttonsPiloti.get(i).setDisable(true);
            } else {
                _buttonsPiloti.get(i).setDisable(false);
            }
        }

        Motorizzazione m = model.getSquadra().getNomeMotorizzazione();
        labelNomeMotorizzazione.setText(m.getNome());
        labelPrezzoMotorizzazione.setText(model.getPrezzoMotorizzazione(m).toString());
        if (model.getSquadra().getScambiEffettuati() >= Squadra.MASSIMO_SCAMBI) {
            buttonScambiaMotorizzazione.setDisable(true);
        } else {
            buttonScambiaMotorizzazione.setDisable(false);
        }
    }

    private void startExchange(Acquistabile a) {
        Stage newWindow = Utils.getNewStage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setMinHeight(720);
        newWindow.setMinWidth(700);
        newWindow.setMaxWidth(700);
        newWindow.setTitle("F1antasy - Scambio");

        FXMLResource r = Utils.loadResource("views/PaneExchange.fxml");
        newWindow.setScene(new Scene(r.getParent()));
        PaneExchangeController controller = (PaneExchangeController) r.getController();
        controller.setDelegate(this);
        controller.initializeForExchange(a);

        newWindow.show();
    }

    @FXML
    public void exchangePilotaButtonWasPressed(ActionEvent actionEvent) {
        for (int i = 0; i < _buttonsPiloti.size(); i++) {
            if (_buttonsPiloti.get(i) == actionEvent.getSource()) {
                Pilota p = model.getPiloti().get(i);
                startExchange(new PilotaConPrezzo(p, model.getPrezzoPilota(p)));
            }
        }
    }

    @FXML
    public void exchangeMotorizzazioneButtonWasPressed(ActionEvent actionEvent) {
        Motorizzazione m = model.getSquadra().getNomeMotorizzazione();
        startExchange(new MotorizzazioneConPrezzo(m, model.getPrezzoMotorizzazione(m)));
    }

    @Override
    public void exchangeWasCompleted() {
        model.refreshData();
        updateUI();
    }
}
