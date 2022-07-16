package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import main.User;
import main.components.DisabledSelectionModel;
import main.components.ListCellClassifica;
import main.components.UtenteInClassificaConPosizionamento;
import main.model.PaneClassificaGlobaleModel;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaneClassificaGlobaleController implements Initializable {

    private PaneClassificaGlobaleModel model = new PaneClassificaGlobaleModel(User.loggedInUser.getUsername());

    @FXML
    public ListView<UtenteInClassificaConPosizionamento> listViewClassificaGlobale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disabling selection.
        listViewClassificaGlobale.setSelectionModel(new DisabledSelectionModel<UtenteInClassificaConPosizionamento>());


        listViewClassificaGlobale.getItems().addAll(model.getClassificaGlobale());
        listViewClassificaGlobale.setCellFactory(lv -> new ListCellClassifica());
    }
}
