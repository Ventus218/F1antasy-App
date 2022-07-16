package main.controllers;


import javafx.fxml.FXML;

public class TabPaneController {

    @FXML
    private PaneSquadraController paneSquadraController;

    @FXML
    private PaneGranPremiController paneGranPremiController;

    @FXML
    private PaneClassifichePrivateController paneClassifichePrivateController;

    @FXML
    private PaneClassificaGlobaleController paneClassificaGlobaleController;

    public void setup() {
        paneSquadraController.setup();
        paneGranPremiController.setup();
        paneClassifichePrivateController.setup();
    }
}
