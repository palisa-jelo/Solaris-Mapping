package org.example;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class GalaxyDisplay extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane) setPlanets(Main.build());
        root.setStyle("-fx-alignment: center; -fx-padding: 5; -fx-background-color: black;");
        Scene scene = new Scene(root, 1200, 800);

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case ENTER -> {
                    PlanetPath path = PlanetPath.finish();

                    //Draw the path
                    if(path != null) {
                        System.out.println("Drawing line....");
                        for(int i = 0; i < path.size() - 1; i++) {
                            PlanetNode p1 = path.get(i);
                            PlanetNode p2 = path.get(i+1);
                            double x1 = p1.getScaledX() + 8;
                            double x2 = p2.getScaledX() + 8;
                            double y1 = p1.getScaledY() + 8;
                            double y2 = p2.getScaledY() + 8;
                            double midX = (x1 + x2) / 2;
                            double midY = (y1 + y2 / 2);

                            Line line = new Line(x1, y1,
                                    x2, y2);
                            line.setStyle("-fx-stroke: red;");
                            line.setStrokeWidth(4);
                            line.toFront();

                            /*
                            Text label = new Text("" + Planet.distance(p1.getPlanet(), p2.getPlanet()));
                            label.setLayoutX(midX);
                            label.setLayoutY(midY);
                            label.setFill(Color.WHITE);
                            label.setStyle("-fx-font-size: 12px;");
                             */

                            root.getChildren().add(line);
                            //root.getChildren().add(label);
                        }
                    }
                }
                case BACK_SPACE -> PlanetPath.delete();
                case ESCAPE -> PlanetPath.wipe();
                //more cases to be added
            }

        });

        stage.setTitle("Galaxy");
        stage.setScene(scene);
        stage.show();
    }

    public Parent setPlanets(Galaxy galaxy) {
        Pane map = new Pane();

        List<Planet> scaled = galaxy.scale(galaxy.getPlanets());
        for(int i = 0; i < galaxy.getPlanets().size(); i ++) {
            Planet p = galaxy.getPlanets().get(i);
            Planet pScaled = scaled.get(i);

            Circle circle = new PlanetNode(8, p, pScaled.getX(), pScaled.getY());
            Text label = new Text(p.getName());
            label.setFill(Color.WHITE);
            label.setStyle("-fx-font-size: 12px;");

            VBox node = new VBox(circle, label);
            node.setLayoutX(pScaled.getX());
            node.setLayoutY(pScaled.getY());
            map.getChildren().add(node);
        }

        return map;
    }
}
