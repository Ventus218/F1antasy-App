package main.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.User;
import main.Utils;
import main.components.Acquistabile;
import main.components.ListCellAcquisto;
import main.controllers.delegates.PaneExchangeDelegate;
import main.model.PaneExchangeModel;


public class PaneExchangeController<T extends Acquistabile> {

    private PaneExchangeModel<T> model;
    private PaneExchangeDelegate delegate;

    @FXML
    public Label labelOldPilotaName;
    @FXML
    public Label labelOldPilotaPrezzo;
    @FXML
    public Label labelNewPilotaName;
    @FXML
    public Label labelNewPilotaPrezzo;
    @FXML
    public Label labelBudgetRimanentePrezzo;
    @FXML
    public ListView<T> listView;
    @FXML
    public Button buttonConferma;


    public void initializeForExchange(Acquistabile oldAcquistabile) {
        this.model = new PaneExchangeModel(User.loggedInUser.getUsername(), oldAcquistabile);

        listView.setCellFactory(lv -> new ListCellAcquisto());
        listView.getItems().addAll(model.getAvailableAcquistabili());

        listView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {
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
        if (listView.getSelectionModel().getSelectedItems().size() == 1) {
            model.setNewAcquistabile(listView.getSelectionModel().getSelectedItems().stream().findFirst().get());
        }
    }

    private void updateUI() {
        labelOldPilotaName.setText(model.getOldAcquistabile().getNomeAcquisto());
        labelOldPilotaPrezzo.setText(model.getOldAcquistabile().getPrezzoAcquisto().toString());

        if (model.getNewAcquistabile().isPresent()) {
            labelNewPilotaName.setText(model.getNewAcquistabile().get().getNomeAcquisto());
            labelNewPilotaPrezzo.setText(model.getNewAcquistabile().get().getPrezzoAcquisto().toString());
        } else {
            labelNewPilotaName.setText("???????????");
            labelNewPilotaPrezzo.setText("????????? ");
        }

        labelBudgetRimanentePrezzo.setText(model.getBudgetRimanente().toString());

        if (model.getNewAcquistabile().isPresent() && model.getBudgetRimanente() >= 0) {
            buttonConferma.setDisable(false);
        } else {
            buttonConferma.setDisable(true);
        }
    }

    @FXML
    public void buttonAnnullaWasPressed(ActionEvent actionEvent) {
        closeStage();
    }

    @FXML
    public void buttonConfermaWasPressed(ActionEvent actionEvent) {
        if (model.makeExchange()) {
            delegate.exchangeWasCompleted();
        } else {
            Utils.showError("Qualcosa è andato storto effettuando lo scambio.");
        }
        closeStage();
    }

    private void closeStage() {
        ((Stage) listView.getScene().getWindow()).close();
    }

    public PaneExchangeDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(PaneExchangeDelegate delegate) {
        this.delegate = delegate;
    }
}
