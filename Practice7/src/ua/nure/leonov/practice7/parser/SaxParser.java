package ua.nure.leonov.practice7.parser;

import ua.nure.leonov.practice7.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SaxParser {

    private File file;
    private List<String> beerList;
    private List<Beer> beers;
    private int countOfEntities = 0;

    public SaxParser(File file) {
        this.file = file;
        beerList = new ArrayList<>();
        beers = new ArrayList<>();
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void parseFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean name = false;
                boolean type = false;
                boolean al = false;
                boolean manufacturer = false;
                boolean ingredients = false;
                boolean alcPercent = false;
                boolean transparency = false;
                boolean filter = false;
                boolean nutritionalValue = false;
                boolean fillChar = false;
                boolean volume = false;
                boolean packageMaterial = false;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) {

                    System.out.println("Start Element: " + qName);

                    if (qName.equalsIgnoreCase("Beer")) {
                        String typeOfBeer = attributes.getValue("xsi:type");
                        beerList.add(typeOfBeer);
                    }

                    if (qName.equalsIgnoreCase("NAME")) {
                        name = true;
                    }

                    if (qName.equalsIgnoreCase("TYPE")) {
                        type = true;
                    }

                    if (qName.equalsIgnoreCase("AL")) {
                        al = true;
                    }

                    if (qName.equalsIgnoreCase("MANUFACTURER")) {
                        manufacturer = true;
                    }

                    if (qName.equalsIgnoreCase("INGREDIENTS")) {
                        ingredients = true;
                    }

                    if (qName.equalsIgnoreCase("ALCPERCENT")) {
                        alcPercent = true;
                    }

                    if (qName.equalsIgnoreCase("TRANSPARENCY")) {
                        transparency = true;
                    }

                    if (qName.equalsIgnoreCase("FILTER")) {
                        filter = true;
                    }

                    if (qName.equalsIgnoreCase("NUTRITIONALVALUE")) {
                        nutritionalValue = true;
                    }

                    if (qName.equalsIgnoreCase("FILLCHAR")) {
                        fillChar = true;
                    }

                    if (qName.equalsIgnoreCase("VOLUME")) {
                        fillChar = true;
                    }

                    if (qName.equalsIgnoreCase("PACKAGEMATERIAL")) {
                        fillChar = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) {

                    if (qName.equalsIgnoreCase("Beer")) {
                        System.out.println("End Element :" + qName);
                        System.out.println();
                    }
                }

                public void characters(char ch[], int start, int length) {

                    if (name) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Name : " + new String(ch, start, length));
                        name = false;
                    }

                    if (type) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Type : " + new String(ch, start, length));
                        type = false;
                    }

                    if (al) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Al : " + new String(ch, start, length));
                        al = false;
                    }

                    if (manufacturer) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Manufacturer : " + new String(ch, start, length));
                        manufacturer = false;
                    }

                    if (ingredients) {
                        System.out.println("Ingredients : " + new String(ch, start, length));
                        ingredients = false;
                    }

                    if (alcPercent) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("alcPercent : " + new String(ch, start, length));
                        alcPercent = false;
                    }

                    if (transparency) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Transparency : " + new String(ch, start, length));
                        transparency = false;
                    }

                    if (filter) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("Filter : " + new String(ch, start, length));
                        filter = false;
                    }

                    if (nutritionalValue) {
                        beerList.add(new String(ch, start, length));
                        System.out.println("NutritionalValue : " + new String(ch, start, length));
                        nutritionalValue = false;
                    }

                    if (fillChar) {
                        if (!new String(ch, start, length).contains("\n")) {
                            beerList.add(new String(ch, start, length));
                            System.out.println("FillChar : " + new String(ch, start, length));
                            fillChar = false;
                        }
                    }
                }

            };

            saxParser.parse(file, handler);
            mapData();
//            System.out.println(beers);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sortBeers(List<Beer> beersToSortAndWrite, File outFile) {
        beersToSortAndWrite.sort(Comparator.comparing(Beer::getTransparency));
        beersToSortAndWrite.forEach(System.out::println);
        new StaxParser().writeToFile(beersToSortAndWrite, outFile);
    }

    private void mapData() {

        for (int count = 0; count < beerList.size(); count++) {

            switch (beerList.get(count)) {
                case "AlcBeer":
                    AlcBeer alcBeer = new AlcBeer();
                    alcBeer.setName(beerList.get(count += 1));
                    alcBeer.setType(beerList.get(count += 1));
                    alcBeer.setAl(Boolean.valueOf(beerList.get(count += 1)));
                    alcBeer.setManufacturer(beerList.get(count += 1));
                    alcBeer.setIngredients(new Ingredient[]{new Ingredient(beerList.get(count += 1)),
                            new Ingredient(beerList.get(count += 1)), new Ingredient(beerList.get(count += 1))});
                    alcBeer.setAlcPercent(beerList.get(count += 1));
                    alcBeer.setTransparency(Integer.parseInt(beerList.get(count += 1)));
                    alcBeer.setFilter(Boolean.valueOf(beerList.get(count += 1)));
                    alcBeer.setNutritionalValue(Integer.parseInt(beerList.get(count += 1)));
                    alcBeer.setFillChar(new FillChar(beerList.get(count += 1), beerList.get(count += 1)));
                    beers.add(alcBeer);

                    break;
                case "NonAlcBeer":
                    NonAlcBeer nonAlcBeer = new NonAlcBeer();
                    nonAlcBeer.setName(beerList.get(count += 1));
                    nonAlcBeer.setType(beerList.get(count += 1));
                    nonAlcBeer.setAl(Boolean.valueOf(beerList.get(count += 1)));
                    nonAlcBeer.setManufacturer(beerList.get(count += 1));
                    nonAlcBeer.setIngredients(new Ingredient[]{new Ingredient(beerList.get(count += 1)),
                            new Ingredient(beerList.get(count += 1)), new Ingredient(beerList.get(count += 1))});
                    nonAlcBeer.setTransparency(Integer.parseInt(beerList.get(count += 1)));
                    nonAlcBeer.setFilter(Boolean.valueOf(beerList.get(count += 1)));
                    nonAlcBeer.setNutritionalValue(Integer.parseInt(beerList.get(count += 1)));
                    nonAlcBeer.setFillChar(new FillChar(beerList.get(count += 1), beerList.get(count += 1)));
                    beers.add(nonAlcBeer);

                    break;
            }

        }
    }


}
