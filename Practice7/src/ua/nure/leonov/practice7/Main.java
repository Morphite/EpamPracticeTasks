package ua.nure.leonov.practice7;

import ua.nure.leonov.practice7.parser.DomParser;
import ua.nure.leonov.practice7.parser.SaxParser;
import ua.nure.leonov.practice7.parser.StaxParser;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String inputXml = args[0];

        DomParser domParser = new DomParser(new File(inputXml));
        domParser.parseFile();
        domParser.sortBeers();

        SaxParser saxParser = new SaxParser(new File(inputXml));
        saxParser.parseFile();
        saxParser.sortBeers(saxParser.getBeers(), new File("output.sax.xml"));

        StaxParser staxParser = new StaxParser(new File(inputXml));
        staxParser.parseFile();
        staxParser.sortBeers(staxParser.getBeers(), new File("output.stax.xml"));
    }
}
