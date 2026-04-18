package org.example;

public class Planet {
    private final Location loc;
    private final String name;

    public Planet(double x, double y, String name) {
        this.loc = new Location(x,y);
        this.name = name;
    }

    private class Location{
        protected final double x;
        protected final double y;

        public Location(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public String getName() {
        return this.name;
    }

    public double getX() {
        return this.loc.x;
    }

    public double getY() {
        return this.loc.y;
    }

    @Override
    public String toString() {
        String output = this.name;
        output += " at " + this.loc.x + ", " + this.loc.y;
        return output;
    }
}
