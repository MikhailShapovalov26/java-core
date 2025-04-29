package ru.netology;


import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONMain {
    public static void main(String[] args) {
        String json = readString("data2.json");

        List<Employee> list = jsonToList(json);
        list.forEach(employee -> System.out.printf(
                "ID: %d, Name: %s %s, Country: %s, Age: %d%n",
                employee.id,
                employee.firstName,
                employee.lastName,
                employee.country,
                employee.age
        ));
    }

    private static List<Employee> jsonToList(String json) {
        Gson gson = new Gson();

        Type employeeListType = new TypeToken<List<Employee>>(){}.getType();

        return gson.fromJson(json, employeeListType);
    }

    private static String readString(String fileJson) {
        String line = null;
        StringBuilder lineall = new StringBuilder();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileJson));

            while ((line = buf.readLine()) != null){
                lineall.append(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lineall.toString();
    }
}
