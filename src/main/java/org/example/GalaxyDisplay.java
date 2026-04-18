package org.example;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class GalaxyDisplay extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane) setPlanets(Main.build());
        root.setStyle("-fx-alignment: center; -fx-padding: 20;");
        Scene scene = new Scene(root, 1000, 1000);
        stage.setTitle("Galaxy");
        stage.setScene(scene);
        stage.show();
    }

    public Parent setPlanets(Galaxy galaxy) {
        Pane map = new Pane();

        for(Planet p : galaxy.getPlanets()) {
          Button btn = new Button(p.getName());
          btn.setLayoutX(p.getX());
          btn.setLayoutY(p.getY());
          map.getChildren().add(btn);
        }

        return map;
    }
}
