package de.ramunno.mylittlefactory;

import android.app.Application;

import java.util.HashMap;

import de.ramunno.mylittlefactory.factory.Item;
import de.ramunno.mylittlefactory.factory.ItemFactory;
import de.ramunno.mylittlefactory.factory.Recipe;
import de.ramunno.mylittlefactory.factory.RecipeFactory;

/**
 * Created by MitarbeiterISZ on 20.02.2017.
 */

public class MyApplication extends Application {
    public HashMap<Integer, Item> Items = null;
    public HashMap<Integer, Recipe> Recipes = null;

    public void Update(){
        if(null == Items)
        {
            if(null == Items) {
                ItemFactory itemFactory = new ItemFactory();
                Items = itemFactory.generate(MyApplication.this);

                RecipeFactory recipeFactory = new RecipeFactory(Items);

                if (null != Items) {
                    Recipes = recipeFactory.generate(MyApplication.this);

                }
            }

        }
    }
}
