package ru.netology;


import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class ConverterTest {


    private static final String EXAMPLE_JSON = "[\n" +
            "  \"Article 1\",\n" +
            "  \"Article 2\",\n" +
            "  \"Article 3\"\n" +
            "]";
    public List<String> list = Arrays.asList("Article 1", "Article 2", "Article 3");

    @Description("Тестирование преобразования Array в JSON объект и сравнения их")
    @ParameterizedTest
    @ValueSource(strings = {EXAMPLE_JSON})
    void listToJson(String string) {
        String result = Converter.listToJson(list);
        Assertions.assertEquals(string, result);

    }

    @Description("Hamcrest")
    @Test
    void listToJsonHamcrest() {
        String result = Converter.listToJson(list);
        assertThat(result, equalTo(
                EXAMPLE_JSON
        ));

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS) //https://www.baeldung.com/java-beforeall-afterall-non-static
    class JsonFileTests {
        File fileJOSN = new File("src/test/resources/test.json");

        @BeforeAll
        @Test
        void createJSONFile() throws IOException {
            if (fileJOSN.createNewFile()) {
                System.out.println("Create file");
            }
        }

        @Test
        void writeString() throws IOException {
            Converter.writeString(Converter.listToJson(list), fileJOSN.toString());
            System.out.println(Files.readString(Path.of(fileJOSN.toURI()))); // для наглядности
            Assertions.assertEquals(EXAMPLE_JSON, Files.readString(Path.of(fileJOSN.toURI())));
        }

        @AfterAll
        void deleteJsonFile() {
            if (fileJOSN.delete()) {
                System.out.println("Delete file json");
            }
        }
    }


}