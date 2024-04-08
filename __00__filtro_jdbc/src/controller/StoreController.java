package controller;

import entity.Store;
import model.StoreModel;

import java.util.List;

public class StoreController {
    private StoreModel objStoreModel;

    //
    public StoreController (){
        //Crear una instancia del model
        this.objStoreModel = new StoreModel();
    }

    //Método para listar todas las tiendas
    public static String getAll(List<Object> listObject){
        String list = "Stores list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Store objStore = (Store) obj;

            //Concatenamos la información
            list += objStore.toString() + "\n";
        }

        return list;
    }


    public static StoreModel instanceModel(){
        return new StoreModel();
    }


}
