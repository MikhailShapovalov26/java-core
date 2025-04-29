package ru.netology;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import ru.netology.JsonParser.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = yamlParser("parameters.yaml");

        JsonParser parser = new JsonParser();
        Cat[] cat;
        try {
            cat = parser.parseJson(CloseableHttpClient(url));
            for (Cat cat1 : cat) {
                if (cat1.getUpvotes() != null && Integer.parseInt(cat1.getUpvotes()) > 0) {
                    System.out.println(cat1.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String CloseableHttpClient(String url) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            CloseableHttpResponse response = httpClient.execute(request);
            return new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String yamlParser(String nameFile) {
        Yaml yaml = new Yaml();
        InputStream inputStream = Main.class
                .getClassLoader()
                .getResourceAsStream(nameFile);
        Map<String, Map<String, String>> obj = yaml.load(inputStream);

        return obj.get("connect").get("url");

    }

}