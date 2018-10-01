package com.outreach.interviews.map.builder;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.outreach.interviews.map.models.GeoCode;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class OwenInterview {

    private CloseableHttpClient httpclient;

    private final String getAPIKey() {
        return "AIzaSyAW0D4KOwyWK51x-Awks59RhQKuX9X3Fu4";
    }

    public OwenInterview() {
        httpclient = HttpClients.createDefault();
    }

    public GeoCode getGeoCodeByCityName(String cityName) throws ClientProtocolException, IOException {
        String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s", cityName,
                getAPIKey());

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpclient.execute(httpGet);

        JsonObject result = null;
        try {
            HttpEntity entity = response.getEntity();
            String resultStr = IOUtils.toString(entity.getContent(), "UTF-8");
            result = new JsonParser().parse(resultStr).getAsJsonObject();
        } finally {
            response.close();
        }

        JsonObject outp = result.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
        double lat = outp.get("lat").getAsDouble();
        double lng = outp.get("lng").getAsDouble();

        return new GeoCode(lat, lng);
    }

}