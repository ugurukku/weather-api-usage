package com.weather.controller;

import com.weather.service.WeatherInter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {


    @Autowired
    @Qualifier("weatherService")
    public WeatherInter weather;

    @CrossOrigin(origins = "http://localhost:8085")
    @GetMapping("/{cityName}")
    public ResponseEntity<JSONObject> conditions(@PathVariable("cityName") String cityName) {

        JSONObject weatherObject = new JSONObject();
        double value = weather.returnJSONObject(cityName);
        weatherObject.put("deyer",String.format("%s",value));

        return ResponseEntity.ok(weatherObject);
    }


}
