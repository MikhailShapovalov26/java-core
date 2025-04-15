package ru.netology;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

import static ru.netology.Converter.listToJson;
import static ru.netology.Converter.writeString;

public class XMLMain {

    public static void main(String[] args) throws Exception {
        List<Employee> list = parseXML("data.xml");
        for (Employee employee : list) {
            System.out.println(employee.firstName);
        }
        String json = listToJson(list);
        writeString(json, "./data2.json");
    }

    private static List<Employee> parseXML(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            NodeList nodeList = document.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Employee employee = new Employee(
                            Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("firstName").item(0).getTextContent(),
                            element.getElementsByTagName("lastName").item(0).getTextContent(),
                            element.getElementsByTagName("country").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));
                    employees.add(employee);
                    System.out.println(employee);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
