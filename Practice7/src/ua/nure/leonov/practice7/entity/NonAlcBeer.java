package ua.nure.leonov.practice7.entity;

public class NonAlcBeer extends Beer {

    public NonAlcBeer(String name, String type, boolean al, String manufacturer, Ingredient[] ingredients,
                      int transparency, boolean filter, int nutritionalValue, FillChar fillChar) {
        super(name, type, al, manufacturer, ingredients, transparency, filter, nutritionalValue, fillChar);
    }

    public NonAlcBeer(){}

}
