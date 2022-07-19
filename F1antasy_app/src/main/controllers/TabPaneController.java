package main.controllers;


import javafx.fxml.FXML;

public class TabPaneController {

    @FXML
    private PaneSquadraController paneSquadraController;

    @FXML
    private PaneGranPremiController paneGranPremiController;

    @FXML
    private PaneMercatoController paneMercatoController;

    @FXML
    private PaneClassifichePrivateController paneClassifichePrivateController;

    @FXML
    private PaneClassificaGlobaleController paneClassificaGlobaleController;

    public void setup() {
        paneSquadraController.setup();
        paneGranPremiController.setup();
        paneMercatoController.setup();
        paneClassifichePrivateController.setup();
    }
}
