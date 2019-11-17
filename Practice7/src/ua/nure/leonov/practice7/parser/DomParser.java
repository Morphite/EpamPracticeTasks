package ua.nure.leonov.practice7.parser;

import ua.nure.leonov.practice7.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomParser {

    private File inputFile;
    private DocumentBuilder documentBuilder;
    private Document document;
    private List<Beer> beers;

    public DomParser(File inputFile) {

        this.inputFile = inputFile;
        beers = new ArrayList<>();

    }

    public void parseFile() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            document = documentBuilder.parse(inputFile);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("Beer");

        for (int temp = 0; temp < nodeList.getLength(); temp++) {

            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String st = element.getAttribute("xsi:type");
                switch (st) {
                    case "AlcBeer":
                        beers.add(mapToEntity(element, mapToEntity(element)));
                        break;
                    case "NonAlcBeer":
                        beers.add(mapToEntity(element));
                        break;
                }
            }
        }
    }

    private Beer mapToEntity(Element element) {
        String name = element.getElementsByTagName("Name").item(0).getTextContent();
        String type = element.getElementsByTagName("Type").item(0).getTextContent();
        boolean al = Boolean.valueOf(element.getElementsByTagName("Al").item(0).getTextContent());
        String manufacturer = element.getElementsByTagName("Manufacturer").item(0).getTextContent();
        Ingredient[] ingredients = createIngredients(parseToArray(element.getElementsByTagName("Ingredients").
                item(0).getTextContent()));
        int transparency = Integer.parseInt(element.getElementsByTagName("Transparency").item(0).getTextContent());
        boolean filter = Boolean.valueOf(element.getElementsByTagName("Filter").item(0).getTextContent());
        int nutritionValue = Integer.parseInt(element.getElementsByTagName("NutritionalValue").item(0).getTextContent());
        FillChar fillChar = createFillChar(parseToArray(element.getElementsByTagName("FillChar").
                item(0).getTextContent()));

        return new NonAlcBeer(name, type, al, manufacturer,
                ingredients, transparency, filter, nutritionValue, fillChar);
    }

    private Beer mapToEntity(Element element, Beer beer) {
        String alcPercent = element.getElementsByTagName("alcPercent").item(0).getTextContent();

        return new AlcBeer(beer.getName(), beer.getType(), beer.isAl(), beer.getManufacturer(),
                beer.getIngredients(), beer.getTransparency(), beer.isFilter(), beer.getNutritionalValue(),
                beer.getFillChar(), alcPercent);
    }

    private List<String> parseToArray(String st) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+,\\d+)|(\\w+)");
        Matcher matcher = pattern.matcher(st);
        while (matcher.find()) {
            list.add(matcher.group(0));
        }
        return list;
    }

    private Ingredient[] createIngredients(List<String> list) {
        Ingredient[] ingredients = new Ingredient[list.size()];
        int count = 0;
        for (String e : list) {
            ingredients[count] = new Ingredient(e);
            count++;
        }
        return ingredients;
    }

    private FillChar createFillChar(List<String> list) {
        return new FillChar(list.get(0), list.get(1));
    }

    public void sortBeers() {
        beers.sort(Comparator.comparing(Beer::getName));
        beers.forEach(System.out::println);
        writeToFile(beers);
    }

    private void writeToFile(List<Beer> bs) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();

            Element beers = document.createElement("Beers");
            document.appendChild(beers);

            for (Beer b : bs) {

                Element beer = document.createElement("Beer");
                beers.appendChild(beer);

                Element name = document.createElement("Name");
                name.setTextContent(b.getName());
                beer.appendChild(name);

                Element type = document.createElement("Type");
                type.setTextContent(b.getType());
                beer.appendChild(type);

                Element al = document.createElement("Al");
                al.setTextContent(String.valueOf(b.isAl()));
                beer.appendChild(al);

                Element manufacturer = document.createElement("Manufacturer");
                manufacturer.setTextContent(b.getManufacturer());
                beer.appendChild(manufacturer);

                Element ingredients = document.createElement("Ingredients");
                beer.appendChild(ingredients);

                for (Ingredient i : b.getIngredients()) {
                    Element ingredient = document.createElement("Name");
                    ingredient.setTextContent(i.getName());
                    ingredients.appendChild(ingredient);
                }

                if (b instanceof AlcBeer) {
                    Element alcPercent = document.createElement("alcPercent");
                    alcPercent.setTextContent(((AlcBeer) b).getAlcPercent());
                    beer.appendChild(alcPercent);
                }

                Element transparency = document.createElement("Transparency");
                transparency.setTextContent(String.valueOf(b.getTransparency()));
                beer.appendChild(transparency);

                Element filter = document.createElement("Filter");
                filter.setTextContent(String.valueOf(b.isFilter()));
                beer.appendChild(filter);

                Element nutritionValue = document.createElement("NutritionalValue");
                nutritionValue.setTextContent(String.valueOf(b.getNutritionalValue()));
                beer.appendChild(nutritionValue);

                Element fillChar = document.createElement("FillChar");
                beer.appendChild(fillChar);

                Element volume = document.createElement("volume");
                volume.setTextContent(b.getFillChar().getVolume());
                fillChar.appendChild(volume);

                Element material = document.createElement("packageMaterial");
                material.setTextContent(b.getFillChar().getPackageMaterial());
                fillChar.appendChild(material);
            }
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("output.dom.xml"));
            transformer.transform(source, streamResult);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

}