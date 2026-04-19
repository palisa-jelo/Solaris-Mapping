package org.example;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
                        System.out.println("Not implemented yet!");
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

        for(Planet p : galaxy.getPlanets()) {
            Circle circle = new PlanetNode(8, p);
            Text label = new Text(p.getName());
            label.setFill(Color.WHITE);
            label.setStyle("-fx-font-size: 12px;");

            VBox node = new VBox(circle, label);
            node.setLayoutX(p.getX());
            node.setLayoutY(p.getY());
            map.getChildren().add(node);
        }

        return map;
    }
}
