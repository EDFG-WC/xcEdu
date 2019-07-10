package com.batch.testspringbatch.buildertest;

import static com.batch.testspringbatch.buildertest.NyPizza.Size.SMALL;
import static com.batch.testspringbatch.buildertest.Pizza.Topping.*;

public class Test {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
        //new NutritionFacts(new NutritionFacts.Builder())

        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();
    }
}
