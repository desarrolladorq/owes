package com.example.dev.owes.models;

/**
 * Created by dev on 05/01/17.
 */

public class ShoppingList {
    String listName;
    String owner;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }
}
