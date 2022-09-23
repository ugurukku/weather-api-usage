package com.weather.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class WeatherService implements WeatherInter {
    public static void main(String[] args) throws IOException, InterruptedException {
    }


    @Override
    public double returnJSONObject(String cityName) {

        try {

            if (cityName == null || cityName.isEmpty()) {
                cityName = "baku";
            }

            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=75ad125450974fe8baa134247222309&q=" + cityName + "&aqi=no");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //check if connect is made
            int response = connection.getResponseCode();
            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject parse = (JSONObject) parser.parse(String.valueOf((informationString)));
                JSONObject obj1 = (JSONObject) parse.get("current");
                double value = (double) obj1.get("temp_c");
                return value;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


}
