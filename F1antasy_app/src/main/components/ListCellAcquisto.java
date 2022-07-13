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
import main.dto.UtenteInClassifica;

public class ListCellAcquisto<T extends Acquistabile> extends ListCell<Acquistabile> {

    private Parent parent ;

    Label labelNome;
    Label labelPrezzo;

    public ListCellAcquisto() {
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

        labelNome = new Label("Nome Acquisto");
        labelNome.getStyleClass().add("title");
        v1.getChildren().add(labelNome);

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

        labelPrezzo = new Label("0");
        Label label$ = new Label("$");
        h.getChildren().add(labelPrezzo);
        h.getChildren().add(label$);


        Separator separator = new Separator();
        AnchorPane.setRightAnchor(separator, 0.0);
        AnchorPane.setBottomAnchor(separator, -7.0);
        AnchorPane.setLeftAnchor(separator, 0.0);
        anchor.getChildren().add(separator);

        parent = anchor;
    }

    @Override
    protected void updateItem(Acquistabile a, boolean empty) {
        super.updateItem(a, empty);
        if (empty || a==null) {
            setGraphic(null);
        } else {
            labelNome.setText(a.getNomeAcquisto());
            labelPrezzo.setText(a.getPrezzoAcquisto().toString());

            setGraphic(parent);
        }
    }
}
