package de.ramunno.mylittlefactory.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MitarbeiterISZ on 16.02.2017.
 */

public class Item {

    public String Name;
    public int ID;
    public List<Recipe> recipes;

    public Item(int ID, String Name){
        this.ID = ID;
        this.Name = Name;
        recipes = new ArrayList<Recipe>();
    }

    public void AddRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }
}
