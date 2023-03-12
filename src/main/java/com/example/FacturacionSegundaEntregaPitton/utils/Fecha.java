package com.example.FacturacionSegundaEntregaPitton.utils;


import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;

public class Fecha {
    private String $id;
    private static String currentDateTime;
    private String utcOffset;
    private Boolean isDayLightSavingsTime;

    public Fecha() {
    }

    public Fecha(String $id, String currentDateTime, String utcOffset, Boolean isDayLightSavingsTime) {
        this.$id = $id;
        this.currentDateTime = currentDateTime;
        this.utcOffset = utcOffset;
        this.isDayLightSavingsTime = isDayLightSavingsTime;
    }

    public static String getApi(){
        try{
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://worldclockapi.com/api/json/utc/now";
        Fecha fecha = restTemplate.getForObject(url, Fecha.class);

          String fechaAMostrar= fecha.getCurrentDateTime();

          return fechaAMostrar;
    }catch (Exception e){ //en caso de que se pierda la conexion con la API se aplica la fecha local
            Date fechaActual= Date.from(Instant.now());
            return String.valueOf(fechaActual);
        }

    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Boolean getDayLightSavingsTime() {
        return isDayLightSavingsTime;
    }

    public void setDayLightSavingsTime(Boolean dayLightSavingsTime) {
        isDayLightSavingsTime = dayLightSavingsTime;
    }
}