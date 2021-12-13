package hr.fer.tel;

import hr.fer.tel.location.Locations;

import java.net.*;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {
    public static void main(String[] args) { //add input parameters for lat, lon, start, stop date
        URL website = null;
        try {
            String url = String.format("https://worldmodel.csiro.au/gclimate?lat=%s&lon=%s&format=apsim&start=20210101&stop=20210201",
                    Locations.ZAGREB_LAT, Locations.ZAGREB_LON); //date format: yyyyMMdd
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("C:\\text.met"); //change text file depending on location (npr. Zagreb.met if Zagreb is selected).
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

         } catch (MalformedURLException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
