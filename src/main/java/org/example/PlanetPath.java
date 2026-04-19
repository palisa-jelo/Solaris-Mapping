package org.example;

import java.util.LinkedList;

public class PlanetPath extends LinkedList<PlanetNode> {
    private static PlanetPath path = null;
    private static boolean viewing = true;

    // Send to constructor
    public static void pathTo(PlanetNode p) {
        if(path == null) {
            PlanetPath.setPathing();
            path = new PlanetPath(p);
        } else {
            path.add(p);
        }
    }

    private PlanetPath(PlanetNode p) {
        this.add(p);
    }

    private double getDistance() {
        if(this.size() <= 1) {
            return 0;
        }

        double sum = 0;
        for(int i = 0; i < this.size() - 1; i++) {
            sum += Planet.distance(this.get(i).getPlanet(), this.get(i+1).getPlanet());
        }
        return sum;
    }

    public static void setPathing() {
        viewing = false;
    }

    public static boolean getViewing() {
        return viewing;
    }

    public static boolean selected(PlanetNode p) {
        return path != null && path.contains(p);
    }

    public static PlanetPath finish() {
        if(path == null) {
            return null;
        }
        System.out.println("Our path is: ");
        for(PlanetNode p : path) {
            p.setStyle("-fx-fill: red;");
            System.out.print(p.getPlanet() + " -> ");
        }
        System.out.println("\nIt's size is " + path.getDistance());
        PlanetPath temp = path;
        path = null;
        viewing = true;
        return temp;
    }

    public static void delete() {
        if(path == null || path.size() <= 1) {
            PlanetPath.finish();
        }
        PlanetNode remove = path.removeLast();
        if(!remove.equals(path.getLast())) {
            remove.setStyle("-fx-fill: azure;");
        }
    }

    public static void wipe() {
        if(path == null) {
            return;
        }

        for(PlanetNode p : path) {
            p.setStyle("-fx-fill: azure;");
        }

        path = null;
        viewing = true;
    }
}
