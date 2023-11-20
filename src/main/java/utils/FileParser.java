package utils;

import entityes.Address;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileParser {
    private static List<Address> addressList;

    public static void parse(String path) throws IOException, JDOMException {

        if (path.endsWith("csv")) {
            parserCsv(path);
        } else if (path.endsWith("xml")) {
            parserXml(path);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void parserXml(String input) throws IOException, JDOMException {
        File inputFile = new File(input);
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(inputFile);
        Element rootElement = document.getRootElement();
        List<Element> items = rootElement.getChildren("item");
        addressList = new ArrayList<>();
        Address address;
        for (Element item : items) {
            address = new Address(item.getAttributeValue("city"), item.getAttributeValue("street"),
                    item.getAttributeValue("house"), item.getAttributeValue("floor"));
            addressList.add(address);
        }
    }

    private static void parserCsv(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        addressList = new ArrayList<>();
        String line;
        bufferedReader.readLine();
        Address address;

        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(";");
            address = new Address(parts[0], parts[1], parts[2], parts[3]);
            addressList.add(address);
        }
    }

    public static List<Address> getAddressList() {
        return addressList;
    }
}

