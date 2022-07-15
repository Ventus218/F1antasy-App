package main.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.Utils;
import main.dto.GranPremioProgrammato;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class ListCellGranPremio extends ListCell<GranPremioProgrammato> {

    private Parent parent ;

    Label labelStato;
    Label labelNomeGranPremio;
    Label labelData;

    public ListCellGranPremio() {
        AnchorPane anchor = new AnchorPane();
        anchor.setPrefHeight(150);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        anchor.getChildren().add(hBox);

        ImageView imageView = new ImageView();
        imageView.setImage(new Image(Utils.class.getResourceAsStream("asset/white-finish-flag.png")));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        hBox.getChildren().add(imageView);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(15);
        HBox.setHgrow(vbox, Priority.ALWAYS);
        hBox.getChildren().add(vbox);

        VBox v1 = new VBox();
        v1.setSpacing(5);
        vbox.getChildren().add(v1);

        labelStato = new Label();
        labelStato.getStyleClass().add("title");
        v1.getChildren().add(labelStato);

        labelNomeGranPremio = new Label();
        labelNomeGranPremio.getStyleClass().add("title");
        v1.getChildren().add(labelNomeGranPremio);

        labelData = new Label();
        labelData.getStyleClass().add("title");
        vbox.getChildren().add(labelData);

        Separator separator = new Separator();
        AnchorPane.setRightAnchor(separator, 0.0);
        AnchorPane.setBottomAnchor(separator, -7.0);
        AnchorPane.setLeftAnchor(separator, 0.0);
        anchor.getChildren().add(separator);

        parent = anchor;
    }

    @Override
    protected void updateItem(GranPremioProgrammato gpp, boolean empty) {
        super.updateItem(gpp, empty);
        if (empty || gpp==null) {
            setGraphic(null);
        } else {

            labelStato.setText(gpp.getGranPremio().getStato());
            labelNomeGranPremio.setText(gpp.getGranPremio().getNome());

            LocalDate ld = gpp.getDataGranPremio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Locale l = Locale.ITALY ;
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate( FormatStyle.LONG ).withLocale( l );
            labelData.setText(ld.format(f));

            setGraphic(parent);
        }
    }
}
