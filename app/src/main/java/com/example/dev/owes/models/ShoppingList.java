package com.example.dev.owes.models;



import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

/**
 * Created by dev on 05/01/17.
 */

public class ShoppingList {
    String listName;
    String owner;
    HashMap<String, Object> timeLastChanged;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;

        //Date last changed will always be set to ServerValue.TIMESTAMP
        HashMap<String, Object> timeLastChangedObj= new HashMap<String, Object>();
        timeLastChangedObj.put("date", ServerValue.TIMESTAMP);
        this.timeLastChanged = timeLastChangedObj;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<String, Object> getTimeLastChanged() {
        return timeLastChanged;
    }
    @Exclude
    public long getTimeLastChangedLong(){
        return (long)timeLastChanged.get("date");
    }

}
