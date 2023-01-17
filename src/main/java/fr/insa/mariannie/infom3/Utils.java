/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.scene.control.Alert;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author alexa
 */
public class Utils {
    
        public static void showErrorInAlert(String titre, String message, String detail) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(message);
        alert.setContentText(detail);
        alert.showAndWait();

    }

    static void showErrorInAlert(String pb, String localizedMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(localizedMessage);
        alert.showAndWait();
    }

        public static void addSimpleBorder(Region c) {
        addSimpleBorder(c, Color.BLACK, BorderWidths.DEFAULT.getTop());
    }
    
    public static void addSimpleBorder(Region c,Color couleur,double epaisseur) {
        c.setBorder(new Border(new BorderStroke(couleur,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(epaisseur))));
    }

}
