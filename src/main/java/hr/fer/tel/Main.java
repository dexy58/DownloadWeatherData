package hr.fer.tel;

import hr.fer.tel.location.Locations;

import java.net.*;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) { //add input parameters for location (selected lan and lon from this input parameter)
        URL website = null;
        String yesterday = getYesterdayDateString();

        try {
            String url = String.format("https://worldmodel.csiro.au/gclimate?lat=%s&lon=%s&format=apsim&start=%s0101&stop=%s",
                    Locations.ZAGREB_LAT, Locations.ZAGREB_LON, getYear(0), yesterday); //date format: yyyyMMdd
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

    private static int getYear(int index) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.YEAR, index);
        return year.get(Calendar.YEAR);
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(yesterday());
    }
}
