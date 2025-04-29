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
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class Main {

    private static final ParseJson parseJson = new ParseJson();
    private static String url = null;

    public static void main(String[] args) throws IOException {
        String fullUrl = yamlParser("connect.yaml").get("url") +
                yamlParser("connect.yaml").get("api_key");

        String responseNasa = null;

        System.out.println(fullUrl);
        try {
            responseNasa = CloseableHttpClient(fullUrl);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(responseNasa);

        if (Boolean.parseBoolean(yamlParser("connect.yaml").get("high_quality"))) {
            url = parseJson.parseJson(responseNasa).getHdurl();
        } else {
            url = parseJson.parseJson(responseNasa).getUrl();
        }
        downloadImageNasa(url);

    }

    private static void downloadImageNasa(String url) {
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get(url.substring(url.lastIndexOf('/') + 1)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Map<String, String> yamlParser(String nameFile) {
        Yaml yaml = new Yaml();
        InputStream inputStream = Main.class
                .getClassLoader()
                .getResourceAsStream(nameFile);
        Map<String, Map<String, String>> obj = yaml.load(inputStream);

        return obj.get("connect");

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

}