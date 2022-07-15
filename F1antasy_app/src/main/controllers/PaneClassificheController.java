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
import main.dto.Classifica;
import main.dto.ClassificaPrivata;
import main.dto.UtenteInClassifica;
import main.model.PaneClassificheModel;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaneClassificheController implements Initializable {

    private PaneClassificheModel model = new PaneClassificheModel(User.loggedInUser.getUsername());

    @FXML
    public ListView<UtenteInClassificaConPosizionamento> listViewClassificaGlobale;
    @FXML
    public ComboBox<String> comboBoxClassificaPrivataSelezionata;
    @FXML
    public ListView<UtenteInClassificaConPosizionamento> listViewClassificaPrivata;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String c: model.getAvailableClassifichePrivate()) {
            comboBoxClassificaPrivataSelezionata.getItems().add(c);
        }

        // Disabling selection.
        listViewClassificaGlobale.setSelectionModel(new DisabledSelectionModel<UtenteInClassificaConPosizionamento>());
        listViewClassificaPrivata.setSelectionModel(new DisabledSelectionModel<UtenteInClassificaConPosizionamento>());


        listViewClassificaGlobale.getItems().addAll(model.getClassificaGlobale());
        listViewClassificaGlobale.setCellFactory(lv -> new ListCellClassifica());
    }

    @FXML
    public void comboBoxValueWasChanged(ActionEvent actionEvent) {
        model.setSelectedClassificaPrivata(Optional.of(comboBoxClassificaPrivataSelezionata.getValue()));
        listViewClassificaPrivata.getItems().removeAll(listViewClassificaPrivata.getItems());
        listViewClassificaPrivata.getItems().addAll(model.getClassificaPrivata().get());

        listViewClassificaPrivata.setCellFactory(lv -> new ListCellClassifica());
    }
}
