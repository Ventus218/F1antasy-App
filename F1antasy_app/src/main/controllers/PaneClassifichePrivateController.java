package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.User;
import main.Utils;
import main.components.DisabledSelectionModel;
import main.components.ListCellClassifica;
import main.components.UtenteInClassificaConPosizionamento;
import main.model.PaneClassifichePrivateModel;

public class PaneClassifichePrivateController {

    private PaneClassifichePrivateModel model;

    @FXML public ComboBox<String> comboBoxClassificaPrivataSelezionata;
    @FXML public ListView<UtenteInClassificaConPosizionamento> listViewClassificaPrivata;
    @FXML public TextField textFieldCreaClassificaPrivata;
    @FXML public Button buttonCreaClassificaPrivata;
    @FXML public TextField textFieldUniscitiClassificaPrivata;
    @FXML public Button buttonUniscitiClassificaPrivata;
    @FXML public ComboBox<String> comboBoxEsciClassificaPrivata;
    @FXML public Button buttonEsciClassificaPrivata;

    public void setup() {
        model = new PaneClassifichePrivateModel(User.loggedInUser.getUsername());

        listViewClassificaPrivata.setCellFactory(lv -> new ListCellClassifica());
        listViewClassificaPrivata.setSelectionModel(new DisabledSelectionModel<UtenteInClassificaConPosizionamento>());

        resetUI();
    }

    private void resetUI() {
        listViewClassificaPrivata.getItems().clear();

        buttonCreaClassificaPrivata.setDisable(true);
        buttonUniscitiClassificaPrivata.setDisable(true);
        buttonEsciClassificaPrivata.setDisable(true);

        textFieldCreaClassificaPrivata.clear();
        textFieldUniscitiClassificaPrivata.clear();

        fillComboBoxClassificaPrivataSelezionata();
        fillComboBoxEsciClassificaPrivata();
    }

    private void updateList() {
        listViewClassificaPrivata.getItems().clear();
        if (model.getSelectedClassificaPrivata().isPresent()) {
            listViewClassificaPrivata.getItems().addAll(model.getClassificaPrivata().get());
        }
    }

    private void fillComboBoxClassificaPrivataSelezionata() {
        comboBoxClassificaPrivataSelezionata.getItems().clear();
        comboBoxClassificaPrivataSelezionata.getItems().addAll(model.getAvailableClassifichePrivate());
    }

    private void fillComboBoxEsciClassificaPrivata() {
        comboBoxEsciClassificaPrivata.getItems().clear();
        comboBoxEsciClassificaPrivata.getItems().addAll(model.getAvailableClassifichePrivate());
        buttonEsciClassificaPrivata.setDisable(true);
    }

    @FXML public void comboBoxClassificaPrivataSelezionataValueWasChanged(ActionEvent actionEvent) {
        String nome = comboBoxClassificaPrivataSelezionata.getValue();
        if (nome != null) {
            if (nome.isEmpty()) { System.err.println("nome was empty"); }
            model.selectClassificaPrivata(nome);
            updateList();
        }
    }

    @FXML public void comboBoxEsciClassificaPrivataValueWasChanged(ActionEvent actionEvent) {
        if (comboBoxEsciClassificaPrivata.getValue() != null) {
            if (comboBoxEsciClassificaPrivata.getValue().isEmpty()) { System.err.println("nome was empty"); }
            buttonEsciClassificaPrivata.setDisable(comboBoxEsciClassificaPrivata.getValue().isEmpty());
        }
    }

    @FXML public void anyTextFieldValueWasChanged(KeyEvent actionEvent) {
        buttonCreaClassificaPrivata.setDisable(textFieldCreaClassificaPrivata.getText().isEmpty());
        buttonUniscitiClassificaPrivata.setDisable(textFieldUniscitiClassificaPrivata.getText().isEmpty());
    }

    @FXML public void buttonCreaWasPressed(ActionEvent actionEvent) {
        if (model.createClassificaPrivata(textFieldCreaClassificaPrivata.getText())) {
            resetUI();
        } else {
            Utils.showError("Qualcosa è andato storto creando la Classifica Privata");
        }
    }

    @FXML public void buttonUniscitiWasPressed(ActionEvent actionEvent) {
        if (model.joinClassificaPrivata(textFieldUniscitiClassificaPrivata.getText())) {
            resetUI();
        } else {
            Utils.showError("Qualcosa è andato storto durante l'unione alla Classifica Privata");
        }
    }

    @FXML public void buttonEsciWasPressed(ActionEvent actionEvent) {
        if (model.leaveClassificaPrivata(comboBoxEsciClassificaPrivata.getValue())) {
            resetUI();
        } else {
            Utils.showError("Qualcosa è andato storto uscendo dalla Classifica Privata");
        }
    }
}
