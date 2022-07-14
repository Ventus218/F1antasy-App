package main.controllers;


import javafx.fxml.FXML;
import main.Utils;

public class TabPaneController {

    @FXML
    private PaneSquadraController paneSquadraController;

    @FXML
    private PaneClassificheController paneClassificheController;

    public void initialize() {
        paneSquadraController.initialize();
    }
}
