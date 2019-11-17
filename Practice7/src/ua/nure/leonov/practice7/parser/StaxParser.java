package ua.nure.leonov.practice7.parser;

import ua.nure.leonov.practice7.entity.*;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StaxParser {

    private File file;
    private List<String> beerList;
    private List<Beer> beers;
    private int countOfEntities = 0;

    public StaxParser() {
    }

    public StaxParser(File file) {
        this.file = file;
        beerList = new ArrayList<>();
        beers = new ArrayList<>();
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void parseFile() {
        try {

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

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(file));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        System.out.println("Start Element :" + qName);


                        if (qName.equalsIgnoreCase("Beer")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String typeOfBeer = attributes.next().getValue();
                            beerList.add(typeOfBeer);
                        } else if (qName.equalsIgnoreCase("NAME")) {
                            name = true;
                        } else if (qName.equalsIgnoreCase("TYPE")) {
                            type = true;
                        } else if (qName.equalsIgnoreCase("AL")) {
                            al = true;
                        } else if (qName.equalsIgnoreCase("MANUFACTURER")) {
                            manufacturer = true;
                        } else if (qName.equalsIgnoreCase("INGREDIENTS")) {
                            ingredients = true;
                        } else if (qName.equalsIgnoreCase("ALCPERCENT")) {
                            alcPercent = true;
                        } else if (qName.equalsIgnoreCase("TRANSPARENCY")) {
                            transparency = true;
                        } else if (qName.equalsIgnoreCase("FILTER")) {
                            filter = true;
                        } else if (qName.equalsIgnoreCase("NUTRITIONALVALUE")) {
                            nutritionalValue = true;
                        } else if (qName.equalsIgnoreCase("FILLCHAR")) {
                            fillChar = true;
                        } else if (qName.equalsIgnoreCase("VOLUME")) {
                            fillChar = true;
                        } else if (qName.equalsIgnoreCase("PACKAGEMATERIAL")) {
                            fillChar = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (name) {
                            beerList.add(characters.getData());
                            System.out.println("Name : " + characters.getData());
                            name = false;
                        }

                        if (type) {
                            beerList.add(characters.getData());
                            System.out.println("Type : " + characters.getData());
                            type = false;
                        }

                        if (al) {
                            beerList.add(characters.getData());
                            System.out.println("Al : " + characters.getData());
                            al = false;
                        }

                        if (manufacturer) {
                            beerList.add(characters.getData());
                            System.out.println("Manufacturer : " + characters.getData());
                            manufacturer = false;
                        }

                        if (ingredients) {
                            System.out.println("Ingredients : " + characters.getData());
                            ingredients = false;
                        }

                        if (alcPercent) {
                            beerList.add(characters.getData());
                            System.out.println("alcPercent : " + characters.getData());
                            alcPercent = false;
                        }

                        if (transparency) {
                            beerList.add(characters.getData());
                            System.out.println("Transparency : " + characters.getData());
                            transparency = false;
                        }

                        if (filter) {
                            beerList.add(characters.getData());
                            System.out.println("Filter : " + characters.getData());
                            filter = false;
                        }

                        if (nutritionalValue) {
                            beerList.add(characters.getData());
                            System.out.println("NutritionalValue : " + characters.getData());
                            nutritionalValue = false;
                        }

                        if (fillChar) {
                            if (!characters.getData().contains("\n")) {
                                beerList.add(characters.getData());
                                System.out.println("FillChar : " + characters.getData());
                                fillChar = false;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("beer")) {
                            System.out.println("End Element :" + endElement);
                        }
                        break;
                }
            }

            mapData();
//            System.out.println(beers);

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }


    }


    public void sortBeers(List<Beer> beersToSortAndWrite, File outFile) {
        beersToSortAndWrite.sort(Comparator.comparing(Beer::getNutritionalValue));
        beersToSortAndWrite.forEach(System.out::println);
        writeToFile(beersToSortAndWrite, outFile);
    }

    public void writeToFile(List<Beer> bs, File outFile) {
        try {
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = xMLOutputFactory.createXMLStreamWriter(new FileOutputStream(outFile), "UTF-8");


            writer.writeStartDocument();
            writer.writeStartElement("Beers");

            for (Beer b : bs) {
                writer.writeStartElement("Beer");


                writer.writeStartElement("Name");
                writer.writeCharacters(b.getName());
                writer.writeEndElement();

                writer.writeStartElement("Type");
                writer.writeCharacters(b.getType());
                writer.writeEndElement();

                writer.writeStartElement("Al");
                writer.writeCharacters(String.valueOf(b.isAl()));
                writer.writeEndElement();

                writer.writeStartElement("Manufacturer");
                writer.writeCharacters(b.getManufacturer());
                writer.writeEndElement();

                writer.writeStartElement("Ingredients");
                for (Ingredient ingredient : b.getIngredients()) {
                    writer.writeStartElement("Name");
                    writer.writeCharacters(ingredient.getName());
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                if (b instanceof AlcBeer) {
                    writer.writeStartElement("alcPercent");
                    writer.writeCharacters(((AlcBeer) b).getAlcPercent());
                    writer.writeEndElement();
                }

                writer.writeStartElement("Transparency");
                writer.writeCharacters(String.valueOf(b.getTransparency()));
                writer.writeEndElement();

                writer.writeStartElement("Filter");
                writer.writeCharacters(String.valueOf(b.isFilter()));
                writer.writeEndElement();

                writer.writeStartElement("NutritionalValue");
                writer.writeCharacters(String.valueOf(b.getNutritionalValue()));
                writer.writeEndElement();

                writer.writeStartElement("FillChar");
                writer.writeStartElement("volume");
                writer.writeCharacters(b.getFillChar().getVolume());
                writer.writeEndElement();

                writer.writeStartElement("packageMaterial");
                writer.writeCharacters(b.getFillChar().getPackageMaterial());
                writer.writeEndElement();
                writer.writeEndElement();


                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();


        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
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
