package ua.nure.leonov.practice7.entity;

public class AlcBeer extends Beer {

    private String alcPercent;

    public AlcBeer(String name, String type, boolean al, String manufacturer, Ingredient[] ingredients,
                   int transparency, boolean filter, int nutritionalValue, FillChar fillChar, String alcPercent) {
        super(name, type, al, manufacturer, ingredients, transparency, filter, nutritionalValue, fillChar);
        this.alcPercent = alcPercent;
    }

    public AlcBeer(){}

    public String getAlcPercent() {
        return alcPercent;
    }

    public void setAlcPercent(String alcPercent) {
        this.alcPercent = alcPercent;
    }

    @Override
    public String toString() {
        return "AlcBeer{" +
                "alcPercent='" + alcPercent + '\''
                +super.toString()+
                '}';
    }
}
