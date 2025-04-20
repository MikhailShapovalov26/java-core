package ru.netology;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileReader;
import java.util.List;

import static ru.netology.Converter.*;


public class CSVMain{

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json, "./data.json");

    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try(CSVReader csvReader = new CSVReader(
                new FileReader(fileName)))
        {
            CsvToBeanBuilder<Object> csvToBean = new CsvToBeanBuilder<>(csvReader);
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            return new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build()
                    .parse();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
