package org.example;

import org.json.*;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    private final List<Planet> planetList;

    public static String getFile(String file) throws Exception {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(file);
        if(is == null) {
            throw new FileNotFoundException();
        }
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    public Galaxy(String file) {
        JSONObject object = null;
        try {
            object = new JSONObject(getFile(file));
        } catch(Exception e) {
            throw new RuntimeException("Error occured in fetching galaxy: " + e);
        }
        JSONArray planetsJSON = object.getJSONObject("galaxy")
                .getJSONArray("stars");

        List<Planet> planets = new ArrayList<>();
        for(Object planet : planetsJSON) {
            JSONObject pl = (((JSONObject) planet));
            Planet p = new Planet(pl.getJSONObject("location").getDouble("x"),
                pl.getJSONObject("location").getDouble("y"),
                pl.getString("name"));
            planets.add(p);
        }

        planetList = scale(planets);
    }

    public List<Planet> scale(List<Planet> planets) {
        double maxX = -1000;
        double minX = 1000;
        double maxY = -1000;
        double minY = 1000;

        for(Planet p : planets) {
            if(p.getX() > maxX) {
                maxX = p.getX();
            }
            if(p.getX() < minX) {
                minX = p.getX();
            }
            if(p.getY() > maxY) {
                maxY = p.getY();
            }
            if(p.getY() < minY) {
                minY = p.getY();
            }
        }

        double xDistance = maxX - minX;
        double yDistance = maxY - minY;

        List<Planet> planetsScaled = new ArrayList<>();
        for(Planet p : planets) {
            Planet p2 = new Planet(950.0*(p.getX() - minX)/xDistance, 950.0*(p.getY() - minY)/yDistance, p.getName());
            planetsScaled.add(p2);
        }
        return planetsScaled;
    }

    @Override
    public String toString() {
        String output = "The galaxy is full of many. Here are its planets:\n";
        for(Planet planet : planetList) {
            output += planet + "\n";
        }
        return output;
    }

    public List<Planet> getPlanets() {
        return this.planetList;
    }
}
