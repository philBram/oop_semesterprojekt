package de.fh_kiel.oop.controller.Painter;

import com.fasterxml.jackson.databind.JsonNode;
import de.fh_kiel.oop.controller.Json;
import de.fh_kiel.oop.controller.DrawableSpaceObjects;
import de.fh_kiel.oop.controller.ISSCoordinateContainer;
import de.fh_kiel.oop.controller.SpaceObjVisitor.Visitor;
import de.fh_kiel.oop.model.VisibleSpaceObjects;
import processing.core.PApplet;
import processing.core.PImage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Objects;

public class ProcessingPainter extends CollisionHandler {

    private int lvlCounter;
    private int issUpdateCounter;
    private final PImage issImg;
    private ISSCoordinateContainer issCoordinateContainer;
    private URL issSrc;
    private PImage backGrdImg;

    public ProcessingPainter(PApplet sketch, int [] initInfo,
                             ArrayList<DrawableSpaceObjects> drawObjs,
                             ArrayList<VisibleSpaceObjects> visObjs,
                             URL issSrc, URL backGrdURL) {

        this.sketch = sketch;
        //InitHandler, initialisiert benötigte Bilder
        this.images = fillImage();
        this.visObjs = visObjs;
        this.drawObjs = drawObjs;
        //Singleton
        this.iVisitor = Visitor.getInstance();
        this.initInfo = initInfo;
        this.issSrc = issSrc;

        this.lvlCounter = 1;
        this.lvlScore = 0;
        this.totalScore = 0;
        this.issUpdateCounter = 0;
        this.issImg = sketch.loadImage("ISS.png");
        this.issSrc = issSrc;

        //NASA HinterGrundsBild
        nasaAPI(backGrdURL);
        //ISS Koordinaten
        issAPI();
        //InitHandler; sorgt dafür, die ArrayLists drawObjs und visObjs mit Asteroiden / Raumschiffen zu füllen
        init();
    }

    private void nasaAPI(URL backGrdURL) {
        //HintergrundsBild = Nasa Astronomy Picture of the Day, falls kein img Format = fallBack Img
        //https://api.nasa.gov/
        try {
            JsonNode node = Json.getInstance().parse(backGrdURL.openStream());
            //Nasa Astronomy Picture of the Day
            String imgUrl = node.findValue("hdurl").asText();
            String mediaType = node.findValue("media_type").asText();

            if ((backGrdImg = sketch.loadImage(imgUrl, "jpg")) == null || !mediaType.equals("image")) {
                throw new Exception();
            }
        }
        catch (Exception ignore) {
            backGrdImg = sketch.loadImage("fallback.jpg");
        }
    }

    private void issAPI() {
        //Erstellt ein neues JavaObject, mit den Koordinaten der ISS
        //https://api.wheretheiss.at/v1/satellites/25544
        try {
            JsonNode nodeIss = Json.getInstance().parse(issSrc.openStream());
            issCoordinateContainer = Json.getInstance().jsonToJavaObject(nodeIss,
                    ISSCoordinateContainer.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawBackGround() {
        if (elementsLeft() > 0) {
            //ImageMode ist Center
            sketch.image(backGrdImg, sketch.width / 2.0f, sketch.height / 2.0f, sketch.width, sketch.height);
            //Overlay für die Info texte, falls Astronomy Pic of the day weiß ist
            backGrdOverLay(0.015f, 0.22f, 0.025f, 0.16f);
            backGrdOverLay(0.82f, 0.99f, 0.025f, 0.13f);
        }
    }

    public void drawSpaceObjects() {
        if (elementsLeft() > 0) {
            ListIterator<DrawableSpaceObjects> drawItr = drawObjs.listIterator();
            ListIterator<VisibleSpaceObjects> visItr = visObjs.listIterator();

            while (drawItr.hasNext() && visItr.hasNext()) {
                DrawableSpaceObjects drawObj = drawItr.next();
                VisibleSpaceObjects visObj = visItr.next();

                PImage img = drawObj.getImg();
                float xPos = drawObj.getXPos();
                float yPos = drawObj.getYPos();
                float width = visObj.getWidth();
                float height = visObj.getHeight();

                sketch.image(img, xPos, yPos, width, height);
                //Verändert die Position der einzelnen DrawableSpaceObjects
                drawObj.updatePos(sketch.width, sketch.height);
                //CollisionHandler; Überprüft, ob zwei Asteroiden mit demselben z-Wert kollidiert sind
                setUpCollisionCheck(drawObj, visObj);
            }
        }
    }

    public void drawInfoText() {
        if (elementsLeft() > 0) {
            String str = "Level: " + lvlCounter
                    + "\nSpaceships left: " + elementsLeft()
                    + "\nScore: " + lvlScore;

            sketch.textSize(0.015f * sketch.width);
            sketch.text(str, sketch.width * 0.83f, sketch.height * 0.03f,
                    sketch.width * 0.2f, sketch.height * 0.2f);
        }
    }

    public void drawISS() {
        if (elementsLeft() > 0) {
            //Processing height 0 bis max => mit null multiplizieren unten gilt als 0 => kein Richtungswechsel
            float latitude = sketch.height - issCoordinateContainer.convertY() * sketch.height;
            float longitude = issCoordinateContainer.convertX() * sketch.width;

            sketch.image(issImg, longitude, latitude, 500, 500);
        }
    }

    public void drawISSInfoText() {
        if (elementsLeft() > 0) {
            float latitude = 0;
            float longitude = 0;
            float velocity = 0;

            if (issUpdateCounter == 100) {
                updateISSPosition();
                issUpdateCounter = -1;
            } else {
                latitude = issCoordinateContainer.getLatitude();
                longitude = issCoordinateContainer.getLongitude();
                velocity = issCoordinateContainer.getVelocity();
            }

            drawISSText(latitude, longitude, velocity);

            issUpdateCounter++;
        }
    }

    private void updateISSPosition() {
        //JsonNode mit neuen ISSPositionDaten wird erstellt, um neue Position der ISS zu darstellen zu können
        try {
            JsonNode nodeISS = Json.getInstance().parse(issSrc.openStream());

            issCoordinateContainer.setLatitude((float) nodeISS.findValue("latitude").asDouble());
            issCoordinateContainer.setLongitude((float) nodeISS.findValue("longitude").asDouble());
            issCoordinateContainer.setVelocity((float) nodeISS.findValue("velocity").asDouble());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawISSText(float latitude, float longitude, float velocity) {
        String str = "ISS INFOS :" +
                "\nLatitude: " +
                latitude +
                "\nLongitude: " +
                longitude +
                "\nVelocity: " +
                velocity +
                " km/h";

        sketch.text(str, sketch.width * 0.02f, sketch.height * 0.03f,
                sketch.width * 0.2f, sketch.height * 0.2f);
    }

    public void drawGameOver() {
        if (elementsLeft() <= 0) {
            String str0 = "Game over";
            String str1 = "Level: " + lvlCounter + " finished"
                    + "\n\nScore: " + lvlScore
                    + "\nTotal Score: " + totalScore;

            sketch.background(0);

            sketch.textSize(50);
            sketch.text(str0, sketch.width * 0.5f - sketch.textWidth(str0) / 2,
                    sketch.height * 0.3f);

            sketch.textSize(20);
            sketch.text(str1, sketch.width * 0.5f - sketch.textWidth(str1) / 2,
                    sketch.height * 0.5f);

            drawRestartButton();
        }
    }

    private void drawRestartButton() {
        String str = "Next Level";

        sketch.fill(0);
        sketch.stroke(255);
        sketch.strokeWeight(3);
        sketch.rect(sketch.width * 0.45f - sketch.textWidth(str) / 2,
                sketch.height * 0.82f - sketch.textWidth(str) / 2,
                sketch.textWidth(str) + sketch.width * 0.1f,
                sketch.textWidth(str) - sketch.height * 0.05f);

        sketch.fill(255);
        sketch.text(str, sketch.width * 0.5f - sketch.textWidth(str) / 2, sketch.height * 0.8f);
    }

    public void gameNextLvlCheck() {
        if (elementsLeft() <= 0) {
            String str = "Next Level";

            float minX = sketch.width * 0.45f - sketch.textWidth(str) / 2;
            float minY = sketch.height * 0.82f - sketch.textWidth(str) / 2;
            float maxX = minX + sketch.textWidth(str) + sketch.width * 0.1f;
            float maxY = minY + sketch.textWidth(str) - sketch.height * 0.05f;

            if (sketch.mouseX > minX && sketch.mouseX < maxX && sketch.mouseY > minY && sketch.mouseY < maxY) {
                gameNextLvl();
                lvlScore = 0;
                lvlCounter++;
            }
        }
    }

    private void gameNextLvl() {
        for (int i = 1, j = initInfo.length - 1; i < initInfo.length; i++, j--) {
            //initInfo[0] : Asteroid Anzahl, initInfo[1] : Ufo Anzahl, initInfo[2] : BattleShip Anzahl
            initInfo[i] += j;
            //Factory shipType: 0 = Ufo, 1 = battleship
            addSpaceShips(initInfo[i], i - 1);
        }
    }

    private int elementsLeft() {
        //initInfo[0] : Asteroid Anzahl, initInfo[1] : Ufo Anzahl, initInfo[2] : BattleShip Anzahl
        return drawObjs.size() - initInfo[0];
    }

    public void backGrdOverLay(float x1, float x2, float y1, float y2) {
        sketch.loadPixels();

        for (int x = (int) (sketch.width * x1); x < sketch.width * x2; x++) {

            for (int y = (int) (sketch.height * y1); y < sketch.height * y2; y++) {

                int tmp = x + y * sketch.width;
                sketch.pixels[tmp] = sketch.color(50);
            }
        }

        sketch.updatePixels();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProcessingPainter that = (ProcessingPainter) o;
        return issUpdateCounter == that.issUpdateCounter &&
                Objects.equals(issImg, that.issImg) &&
                Objects.equals(issSrc, that.issSrc) &&
                Objects.equals(issCoordinateContainer, that.issCoordinateContainer) &&
                Objects.equals(issSrc, that.issSrc) &&
                Objects.equals(backGrdImg, that.backGrdImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), issUpdateCounter, issImg, issSrc, issCoordinateContainer, issSrc, backGrdImg);
    }

    @Override
    public String toString() {
        return "ProcessingPainter{" +
                "issUpdateCounter=" + issUpdateCounter +
                ", issImg=" + issImg +
                ", issSrc='" + issSrc + '\'' +
                ", issCoordinateContainer=" + issCoordinateContainer +
                ", issPosition=" + issSrc +
                ", backGrdImg=" + backGrdImg +
                ", iVisitor=" + iVisitor +
                ", score=" + lvlScore +
                ", sketch=" + sketch +
                ", images=" + images +
                ", visObjs=" + visObjs +
                ", drawObjs=" + drawObjs +
                ", initInfo=" + Arrays.toString(initInfo) +
                "} " + super.toString();
    }
}
