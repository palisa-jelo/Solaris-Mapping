package org.example;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class PlanetNode extends Circle {
    private final Planet planet;
    private final double scaledX;
    private final double scaledY;

    public PlanetNode(int size, Planet planet, double scaledX, double scaledY) {
        super(size);
        super.setStyle("-fx-fill: azure;");
        super.setStroke(Color.BLACK);
        this.planet = planet;
        this.scaledX = scaledX;
        this.scaledY = scaledY;

        super.setOnMouseClicked(clicked -> {
            if(clicked.isShiftDown() || !PlanetPath.getViewing()) {
                PlanetPath.pathTo(this);
            }
        });

        super.setOnMouseEntered(kama -> {
            super.setStyle("-fx-fill: green;");
        });

        super.setOnMouseExited(weka -> {
            if(PlanetPath.getViewing() || !PlanetPath.selected(this)) {
                super.setStyle("-fx-fill: azure;");
            }
        });
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public double getScaledX() {
        return this.scaledX;
    }

    public double getScaledY() {
        return scaledY;
    }
}
