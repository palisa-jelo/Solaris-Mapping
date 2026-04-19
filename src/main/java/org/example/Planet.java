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

        public double distanceTo(Location loc) {
            return Math.sqrt(Math.pow(this.x - loc.x,2) + Math.pow(this.y - loc.y, 2));
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

    public static int distance(Planet p1, Planet p2) {
        if(p1.equals(p2)) {
            return 1;
        }
        double distance = p1.loc.distanceTo(p2.loc);
        return (int) (distance / 0.2);
    }

    public static int warpDistance(Planet p1, Planet p2) {
        if(p1.equals(p2)) {
            return 1;
        }
        double distance = p1.loc.distanceTo(p2.loc);
        return (int) (distance / 0.6);
    }

    @Override
    public String toString() {
        String output = this.name;
        output += " at " + this.loc.x + ", " + this.loc.y;
        return output;
    }
}
