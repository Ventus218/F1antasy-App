package main.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ListCellClassifica extends ListCell<UtenteInClassificaConPosizionamento> {

    private Parent parent ;

    Label labelPunteggio;
    Label labelUsername;
    Label labelPosizione;

    public ListCellClassifica() {
        AnchorPane anchor = new AnchorPane();
        anchor.setPrefHeight(60);

        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        leftSide.setSpacing(30.0);
        AnchorPane.setTopAnchor(leftSide, 0.0);
        AnchorPane.setBottomAnchor(leftSide, 0.0);
        AnchorPane.setLeftAnchor(leftSide, 0.0);
        anchor.getChildren().add(leftSide);

        VBox v1 = new VBox();
        v1.setAlignment(Pos.CENTER);
        leftSide.getChildren().add(v1);

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.getStyleClass().add("title");
        v1.getChildren().add(h1);

        labelPosizione = new Label("1");
        Label labelo = new Label("°");
        h1.getChildren().add(labelPosizione);
        h1.getChildren().add(labelo);

        labelUsername = new Label("Username");
        labelUsername.getStyleClass().add("title");
        leftSide.getChildren().add(labelUsername);


        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        AnchorPane.setTopAnchor(rightSide, 0.0);
        AnchorPane.setBottomAnchor(rightSide, 0.0);
        AnchorPane.setRightAnchor(rightSide, 0.0);
        anchor.getChildren().add(rightSide);

        HBox h = new HBox();
        h.setAlignment(Pos.CENTER_RIGHT);
        h.getStyleClass().add("title");
        VBox.setVgrow(h, Priority.NEVER);
        rightSide.getChildren().add(h);

        labelPunteggio = new Label("0");
        Label labelPt = new Label("pt");
        h.getChildren().add(labelPunteggio);
        h.getChildren().add(labelPt);


        Separator separator = new Separator();
        AnchorPane.setRightAnchor(separator, 0.0);
        AnchorPane.setBottomAnchor(separator, -7.0);
        AnchorPane.setLeftAnchor(separator, 0.0);
        anchor.getChildren().add(separator);

        parent = anchor;
    }

    @Override
    protected void updateItem(UtenteInClassificaConPosizionamento u, boolean empty) {
        super.updateItem(u, empty);
        if (empty || u==null) {
            setGraphic(null);
        } else {
            labelPosizione.setText(u.getPosizionamento().toString());
            labelUsername.setText(u.getUtenteInClassifica().getUsername());
            labelPunteggio.setText(u.getUtenteInClassifica().getPunteggio().toString());

            setGraphic(parent);
        }
    }
}
