package de.ramunno.mylittlefactory.factory;

import java.util.List;

/**
 * Created by MitarbeiterISZ on 16.02.2017.
 */

public class Recipe {

    public String Name;
    public int ID;
    public Integer Period_Ticks;

    public List<Part> products;
    public List<Part> ingredients;

    public Recipe(Integer ID, String Name, List<Part> ingredients, List<Part> products, Integer Period_Ticks){
        this.ID = ID;
        this.Name = Name;
        this.ingredients = ingredients;
        this.products = products;
        this.Period_Ticks = Period_Ticks;
    }


}
