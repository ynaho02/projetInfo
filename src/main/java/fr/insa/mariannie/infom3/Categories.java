/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.application.Application;
import javafx.scene.control.ComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author alexa
 */
public class Categories extends ComboBox<Cat> {

    public Categories() {
        ObservableList<Cat> list = CategoriesDAO.getCatList();

        this.setItems(list);
        this.getSelectionModel().select(0) ;
                //a quoi sert cette ligne

    }

}
