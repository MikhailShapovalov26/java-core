package ru.netology;

import com.opencsv.CSVWriter;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CSVMainTestParseCSV {
    File fileCSV = new File("src/test/resources/test.csv");
    String[] stringCSV = new String[]{"1", "Человек-Олег", "Непобедимый", "RU", "25"};

    Employee employeeTest = new Employee(
            1,
            "Человек-Олег",
            "Непобедимый",
            "RU", 25
    );
    List<Employee> employeeListTest = new ArrayList<>(Collections.singleton(employeeTest));


    @BeforeEach
    void setUp() {

        try (CSVWriter writer = new CSVWriter(new FileWriter(
                fileCSV,
                true));) {
            if (fileCSV.createNewFile()) {
                System.out.println("Create file");
            }

            writer.writeNext(stringCSV);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    void tearDown() {
        if (fileCSV.delete()) {
            System.out.println("Delete csv file");
        }
    }

    @Test
    void parseCSV() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> result = CSVMain.parseCSV(columnMapping, String.valueOf(fileCSV));
        assert result != null;
        result.forEach(employee ->
                System.out.printf(
                        "ID: %d, Name: %s %s, Country: %s, Age: %d%n",
                        employee.id,
                        employee.firstName,
                        employee.lastName,
                        employee.country,
                        employee.age
                )
        );
        System.out.println(employeeTest.firstName);
        for (int i = 0; i < result.size(); i++) {
            Assertions.assertEquals(employeeListTest.get(i).id, result.get(i).id);
            Assertions.assertEquals(employeeListTest.get(i).firstName, result.get(i).firstName);
            Assertions.assertEquals(employeeListTest.get(i).lastName, result.get(i).lastName);
            Assertions.assertEquals(employeeListTest.get(i).country, result.get(i).country);
            Assertions.assertEquals(employeeListTest.get(i).age, result.get(i).age);
        }

    }

}