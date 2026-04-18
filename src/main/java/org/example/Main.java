package org.example;

import static javafx.application.Application.launch;

public class Main {
    public static Galaxy build() {
        Galaxy galaxy = new Galaxy("galaxy.txt");
        return galaxy;
    }

    public static void main(String[] args) { launch(GalaxyDisplay.class); }
}