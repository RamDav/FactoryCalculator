package de.ramunno.mylittlefactory.factory;

/**
 * Created by MitarbeiterISZ on 20.02.2017.
 */

public class Part {
    public Item item;
    public Integer amount;

    public Part(Item item, Integer amount){
        this.item = item;
        this.amount = amount;
    }
}
