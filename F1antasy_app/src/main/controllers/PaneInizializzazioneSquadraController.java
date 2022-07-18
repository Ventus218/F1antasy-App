package main.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import main.User;
import main.Utils;
import main.components.LimitedSelectionModel;
import main.components.ListCellAcquisto;
import main.controllers.delegates.InizializzazioneSquadraDelegate;
import main.dto.MotorizzazioneConPrezzo;
import main.dto.PilotaConPrezzo;
import main.model.PaneInizializzazioneSquadraModel;


public class PaneInizializzazioneSquadraController {

    private PaneInizializzazioneSquadraModel model;
    private InizializzazioneSquadraDelegate delegate;

    @FXML
    public Label labelBudgetRimanente;
    @FXML
    public ListView<PilotaConPrezzo> listViewPiloti;
    @FXML
    public ListView<MotorizzazioneConPrezzo> listViewMotorizzazioni;
    @FXML
    public Button buttonConferma;

    public void setDelegate(InizializzazioneSquadraDelegate d) {
        this.delegate = d;
    }

    public void setup() {
        model = new PaneInizializzazioneSquadraModel(User.loggedInUser.getUsername());

        listViewPiloti.setSelectionModel(new LimitedSelectionModel(listViewPiloti.getSelectionModel(), 4));
        listViewPiloti.getItems().addAll(model.getAvailablePiloti());
        listViewPiloti.setCellFactory(lv -> new ListCellAcquisto());
        listViewPiloti.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                updateModelAndUI();
            }
        });


        listViewMotorizzazioni.getItems().addAll(model.getAvailableMotorizzazioni());
        listViewMotorizzazioni.setCellFactory(lv -> new ListCellAcquisto());
        listViewMotorizzazioni.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                updateModelAndUI();
            }
        });


        updateModelAndUI();
    }

    private void updateModelAndUI() {
        updateModel();
        updateUI();
    }

    private void updateModel() {
        model.selectPiloti(listViewPiloti.getSelectionModel().getSelectedItems());
        if (listViewMotorizzazioni.getSelectionModel().getSelectedItems().size() == 1) {
            model.selectMotorizzazione(listViewMotorizzazioni.getSelectionModel().getSelectedItems().stream().findFirst().get());
        }
    }

    private void updateUI() {
        labelBudgetRimanente.setText(model.getBudgetRimanente().toString() + " $");

        if (model.canCreateSquadra()) {
            buttonConferma.setDisable(false);
        } else {
            buttonConferma.setDisable(true);
        }
    }

    @FXML
    public void textFieldValueWasChanged(KeyEvent keyEvent) {
        updateModelAndUI();
    }

    @FXML
    public void buttonConfermaWasPressed(ActionEvent actionEvent) {
        if (model.createSquadra()) {
            delegate.squadraWasSuccessfullyInitialized();
        } else {
            Utils.showError("Qualcosa è andato storto durante l'inizializzazione della squadra.");
        }
    }
}
