/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alexa
 */
public class CategoriesDAO {

    public static ObservableList<Cat> getCatList() {
        Cat transports = new Cat("TRAN", "Transports");
        Cat meubles = new Cat("MEUB", "Meubles");
        Cat habits = new Cat("HABI", "Habits");

        ObservableList<Cat> list //
                = FXCollections.observableArrayList(transports, meubles, habits);
        return list;
    }

}
