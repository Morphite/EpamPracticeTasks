package ua.nure.leonov.practice7.entity;

import java.util.Arrays;

public class Beer {

    private String name;
    private String type;
    private boolean al;
    private String manufacturer;
    private Ingredient[] ingredients;
    private int transparency;
    private boolean filter;
    private int nutritionalValue;
    private FillChar fillChar;

    Beer(String name, String type, boolean al, String manufacturer, Ingredient[] ingredients,
         int transparency, boolean filter, int nutritionalValue, FillChar fillChar) {
        this.name = name;
        this.type = type;
        this.al = al;
        this.manufacturer = manufacturer;
        this.ingredients = ingredients;
        this.transparency = transparency;
        this.filter = filter;
        this.nutritionalValue = nutritionalValue;
        this.fillChar = fillChar;
    }

    Beer(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAl() {
        return al;
    }

    public void setAl(boolean al) {
        this.al = al;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public FillChar getFillChar() {
        return fillChar;
    }

    public void setFillChar(FillChar fillChar) {
        this.fillChar = fillChar;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", al=" + al +
                ", manufacturer='" + manufacturer + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", transparency='" + transparency + '\'' +
                ", filter=" + filter +
                ", nutritionalValue='" + nutritionalValue + '\'' +
                ", fillChar=" + fillChar +
                '}';
    }
}
