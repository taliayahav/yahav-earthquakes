package yahav.geojson;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadGeoJson {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_day.geojson");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        Gson gson = new Gson();

        InputStreamReader reader = new InputStreamReader(inputStream);
        GeoJsonFeed feed = gson.fromJson(reader, GeoJsonFeed.class);
//        System.out.println(feed.features.size());

        GeoJsonFeed.Feature largest = feed.features.get(0);
        for(GeoJsonFeed.Feature feature : feed.features){
            if (feature.properties.mag > largest.properties.mag){
                largest = feature;
            }
        }
        System.out.printf("%s, %f, %d",
        largest.properties.place,
                largest.properties.mag,
                largest.properties.time);
    }
}
