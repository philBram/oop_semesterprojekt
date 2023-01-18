package de.fh_kiel.oop;

import de.fh_kiel.oop.controller.Painter.ProcessingPainter;
import processing.core.PApplet;

import java.net.URL;
import java.util.ArrayList;

public class Main extends PApplet {

    private ProcessingPainter painter;

    public void setup() {
        imageMode(CENTER);

        //Asteroiden bleiben konstant, ufo- und battleShip-Anzahl nehmen währen des Spiels zu
        final int asteroid = 20;
        final int ufo = 2;
        final int battleShip = 1;

        //https://wheretheiss.at/w/developer
        final String issSrc = "https://api.wheretheiss.at/v1/satellites/25544";

        //Astronomy Picture of the day. Wird wenn möglich als HintergrundsBild verwendet
        //https://api.nasa.gov/
        final String API_KEY = "n0gRqUeQJC5sR3BnQSckexWXVhI8PQMk1xYTaZUH";
        final String URL_STRING = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY;

        //Dependency Injection
        try {
            painter = new ProcessingPainter(
                    this,
                    new int[] {asteroid, ufo, battleShip},
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new URL(issSrc),
                    new URL(URL_STRING));
        } catch (Exception ignore) {
            exit();
        }
    }

    public void settings() {
        size(1280, 900);
    }

    public void draw() {
        painter.drawBackGround();
        painter.drawISS();
        painter.drawSpaceObjects();
        painter.drawInfoText();
        painter.drawISSInfoText();
        painter.drawGameOver();
    }

    public void mousePressed() {
        painter.spaceShipHitCheck();
        painter.gameNextLvlCheck();
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Semester Projekt"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }
}
