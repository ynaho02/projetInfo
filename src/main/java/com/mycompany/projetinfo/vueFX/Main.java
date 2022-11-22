/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetinfo.vueFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author ynaho01
 */

    
    public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new Label("coucou"));
//        Scene sc = new Scene(new TestFx());
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(sc);
        stage.setTitle("AmourFx");
          stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
    

