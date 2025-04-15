package ru.netology;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Converter {

     public static String listToJson(List list) {

          Gson gson = new GsonBuilder()
                  .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY) // https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5/com/google/gson/FieldNamingPolicy.html
                  .setPrettyPrinting().create();

          return gson.toJson(list, new TypeToken<List<Employee>>() {}.getType());
     }

     public static void writeString(String json, String nameFile) {

          try {
               Path filePath = Paths.get(nameFile);
               filePath.toFile().createNewFile();
               Files.writeString(filePath, json, StandardOpenOption.WRITE);
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }

}
