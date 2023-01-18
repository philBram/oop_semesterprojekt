package de.fh_kiel.oop.controller.Painter;

import de.fh_kiel.oop.Main;
import de.fh_kiel.oop.controller.DrawableAsteroid;
import de.fh_kiel.oop.controller.DrawableSpaceObjects;
import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.controller.SpaceShipFactory;
import de.fh_kiel.oop.model.VisibleAsteroid;
import de.fh_kiel.oop.model.VisibleSpaceObjects;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

//Klasse ist Package Private
class InitHandler {

    protected PApplet sketch;
    protected ArrayList<ArrayList<PImage>> images;
    protected ArrayList<VisibleSpaceObjects> visObjs;
    protected ArrayList<DrawableSpaceObjects> drawObjs;
    protected int [] initInfo;

    protected void init() {
        //initInfo[0] : Asteroid Anzahl, initInfo[1] : Ufo Anzahl, initInfo[2] : BattleShip Anzahl
        addAsteroid(initInfo[0]);
        //Factory shipType: 0 = Ufo, 1 = battleship
        for (int i = 1; i < initInfo.length; i++) {
            addSpaceShips(initInfo[i], i - 1);
        }
    }

    protected void addAsteroid(int num) {
        //initInfo[0] : Asteroid Anzahl, initInfo[1] : Ufo Anzahl, initInfo[2] : BattleShip Anzahl
        for (int i = 0; i < num; i++) {
            //images 0: Explosion Bilder, images 1: Asteroid Bilder, images 2: Ufo Bilder, images 3: Kampfschiff Bilder
            PImage img = images.get(1).get(i % images.get(1).size());

            initAsteroidVisible();
            initAsteroidDrawable(img);
        }
    }

    protected void addSpaceShips(int num, int type) {
        //Factory => es können alle Raumschiffe mit einer verschachtelten For-Schleife und denselben Methoden
        //verwendet werden
        SpaceShipFactory drawShipFactory = SpaceShipFactory.getInstance();

        for (int i = 0; i < num; i++) {
            //Factory shipType: 0 = Ufo, 1 = battleship
            //images 0: Explosion Bilder, images 1: Asteroid Bilder, images 2: Ufo Bilder, images 3: Kampfschiff Bilder
            PImage img = images.get(type + 2).get(i % images.get(type + 2).size());

            initSpaceShipDrawable(drawShipFactory, img, type);
            initSpaceShipVisible(drawShipFactory, type);
        }
    }

    private void initAsteroidVisible() {
        float width = (float)(Math.random() * 26) + 25;
        float height = (float)(Math.random() * 26) + 25;

        visObjs.add(new VisibleAsteroid(width, height));
    }

    private void initAsteroidDrawable(PImage img) {

        float xPos = (float)(Math.random() * 1.5 * sketch.width);
        float yPos = (float)(Math.random() * 1.5 * sketch.height);
        float xPosChangeRate;
        float yPosChangeRate;

        do {
            xPosChangeRate = (float) (Math.random() * 151) - 75;
            yPosChangeRate = (float) (Math.random() * 151) - 75;
        } while (xPosChangeRate == 0 || yPosChangeRate == 0);

        int zPos = (int)(Math.random() * 3);

        drawObjs.add(new DrawableAsteroid(img, xPos, yPos, xPosChangeRate, yPosChangeRate, zPos));
    }

    private void initSpaceShipVisible(SpaceShipFactory factory, int type) {

        float radius = (float)(Math.random() * 51) + 150;

        visObjs.add( factory.createVisShip(radius, radius, type) );
    }

    private void initSpaceShipDrawable(SpaceShipFactory factory, PImage img, int type) {

        float xPos = (float)(Math.random() * 1.1 * sketch.width);
        float yPos = (float)(Math.random() * 1.1 * sketch.height);

        drawObjs.add( factory.createDrawShip(img, xPos, yPos, type) );
    }

    //Die folgenden Methoden werden gebraucht, um die benötigten Bilder kompakt in einer Liste vorfinden zu können
    protected ArrayList<ArrayList<PImage>> fillImage() {
        ArrayList<ArrayList<PImage>> images = new ArrayList<>();

        //images 0: Explosion Bilder, images 1: Asteroid Bilder, images 2: Ufo Bilder, images 3: Kampfschiff Bilder
        images.add(0, fillExplosionImages());
        images.add(1, fillAsteroidImages());
        images.add(2, fillUfoImages());
        images.add(3, fillBattleShipImages());

        return images;
    }

    private ArrayList<PImage> fillAsteroidImages() {
        int i = 0;
        PImage imgTemp;
        ArrayList<PImage> images = new ArrayList<>();

        //Fehlermeldung kann ignoriert werden, da asteroid_3.png nie genutzt wird
        while ((imgTemp = sketch.loadImage("asteroid_" + (i++) + ".png")) != null) {
            images.add(imgTemp);
        }

        return images;
    }

    private ArrayList<PImage> fillExplosionImages() {
        int i = 0;
        PImage imgTemp;
        ArrayList<PImage> images = new ArrayList<>();

        //Fehlermeldung kann ignoriert werden, da asteroid_3.png nie genutzt wird
        while ((imgTemp = sketch.loadImage("explosion_" + (i++) + ".png")) != null) {
            images.add(imgTemp);
        }

        return images;
    }

    private ArrayList<PImage> fillUfoImages() {
        int i = 0;
        PImage imgTemp;
        ArrayList<PImage> images = new ArrayList<>();

        //Fehlermeldung kann ignoriert werden, da asteroid_3.png nie genutzt wird
        while ((imgTemp = sketch.loadImage("ufo_" + (i++) + ".png")) != null) {
            images.add(imgTemp);
        }

        return images;
    }

    private ArrayList<PImage> fillBattleShipImages() {
        int i = 0;
        PImage imgTemp;
        ArrayList<PImage> images = new ArrayList<>();

        //Fehlermeldung kann ignoriert werden, da asteroid_3.png nie genutzt wird
        while ((imgTemp = sketch.loadImage("battleship_" + (i++) + ".png")) != null) {
            images.add(imgTemp);
        }

        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitHandler that = (InitHandler) o;
        return Objects.equals(sketch, that.sketch) &&
                Objects.equals(images, that.images) &&
                Objects.equals(visObjs, that.visObjs) &&
                Objects.equals(drawObjs, that.drawObjs) &&
                Arrays.equals(initInfo, that.initInfo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sketch, images, visObjs, drawObjs);
        result = 31 * result + Arrays.hashCode(initInfo);
        return result;
    }

    @Override
    public String toString() {
        return "InitHandler{" +
                "sketch=" + sketch +
                ", images=" + images +
                ", visObjs=" + visObjs +
                ", drawObjs=" + drawObjs +
                ", initInfo=" + Arrays.toString(initInfo) +
                '}';
    }
}
