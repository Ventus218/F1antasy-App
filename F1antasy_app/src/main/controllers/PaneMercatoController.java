package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.components.DisabledSelectionModel;
import main.components.ListCellAcquisto;
import main.dto.MotorizzazioneConPrezzo;
import main.dto.PilotaConPrezzo;
import main.model.PaneMercatoModel;


public class PaneMercatoController {

    private PaneMercatoModel model;

    @FXML
    public ListView<PilotaConPrezzo> listViewPiloti;
    @FXML
    public ListView<MotorizzazioneConPrezzo> listViewMotorizzazioni;

    public void setup() {
        model = new PaneMercatoModel();

        listViewPiloti.setSelectionModel(new DisabledSelectionModel<PilotaConPrezzo>());
        listViewPiloti.getItems().addAll(model.getAvailablePiloti());
        listViewPiloti.setCellFactory(lv -> new ListCellAcquisto());

        listViewMotorizzazioni.setSelectionModel(new DisabledSelectionModel<MotorizzazioneConPrezzo>());
        listViewMotorizzazioni.getItems().addAll(model.getAvailableMotorizzazioni());
        listViewMotorizzazioni.setCellFactory(lv -> new ListCellAcquisto());
    }
}
