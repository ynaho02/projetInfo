/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 *
 * @author alexa
 */

public class GraphUserInterface extends Application {
     @Override
    public void start(Stage primaryStage) throws Exception {
        
         //primaryStage.setTitle("JAVAFX YO!");
        MainPane mp=new MainPane();
//        Scene scene = new Scene( new Label("Hello"));
        Scene scene = new Scene(mp,1000,800);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}

