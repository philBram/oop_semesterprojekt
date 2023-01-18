package de.fh_kiel.oop.controller.Painter;

import de.fh_kiel.oop.controller.DrawableAsteroid;
import de.fh_kiel.oop.controller.DrawableSpaceObjects;
import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleSpaceShip;
import de.fh_kiel.oop.model.VisibleSpaceObjects;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Objects;

//Klasse ist Package Private
class CollisionHandler extends InitHandler {

    protected IVisitor iVisitor;
    protected int lvlScore;
    protected int totalScore;

    public void spaceShipHitCheck() {
        ListIterator<DrawableSpaceObjects> drawItr = drawObjs.listIterator();
        ListIterator<VisibleSpaceObjects> visItr = visObjs.listIterator();


        while (drawItr.hasNext() && visItr.hasNext()) {
            DrawableSpaceObjects drawObj = drawItr.next();
            VisibleSpaceObjects visObj = visItr.next();

            //Visitor returns: Draw/Vis Asteroid = 0, Draw/Vis Ufo = 1, Draw/Vis battleship = 2, Draw/Vis NullShip = -1
            if (drawObj.accept(iVisitor) != 0 && visObj.accept(iVisitor) != 0) {
                //Iteratoren müssen übergeben werden, da Elemente aus den Listen entfernt werden
                checkSingleShip(drawItr, visItr, drawObj, visObj, sketch.mouseX, sketch.mouseY);
            }
        }
    }

    private void checkSingleShip(ListIterator<DrawableSpaceObjects> drawItr, ListIterator<VisibleSpaceObjects> visItr,
                     DrawableSpaceObjects drawObj, VisibleSpaceObjects visObj, float mouseX, float mouseY) {

        //ImageMode ist Center
        boolean minX = mouseX > drawObj.getXPos() - visObj.getWidth() / 2;
        boolean maxX = mouseX < drawObj.getXPos() + visObj.getWidth() / 2;
        boolean minY = mouseY > drawObj.getYPos() - visObj.getHeight() / 2;
        boolean maxY = mouseY < drawObj.getYPos() + visObj.getHeight() / 2;

        if (minX && maxX && minY && maxY) {
            //images 0: Explosion Bilder, images 1: Asteroid Bilder, images 2: Ufo Bilder, images 3: Kampfschiff Bilder
            int ranImg = (int)(Math.random() * images.get(0).size());
            PImage explosionImg = images.get(0).get(ranImg);
            sketch.image(explosionImg, drawObj.getXPos(), drawObj.getYPos(), 300, 300);
            //Überprüft ob health der VisibleSpaceShips bei 0 liegt
            if ( ((VisibleSpaceShip) visObj).hit() ) {
                //Passt den Score and, je nachdem was für ein SpaceShip getroffen wurde
                shipTypeHit( ((VisibleSpaceShip) visObj));

                drawItr.remove();
                visItr.remove();
            }
        }
    }

    private void shipTypeHit(VisibleSpaceShip visObj) {
        //Visitor returns: Draw/Vis Asteroid = 0, Draw/Vis Ufo = 1, Draw/Vis battleship = 2, Draw/Vis NullShip = -1
        switch (visObj.accept(iVisitor)) {
            case 1:
                lvlScore += 100;
                totalScore += 100;
                break;
            case 2:
                lvlScore += 300;
                totalScore += 300;
                break;
            default:
                break;
        }
    }

    protected void setUpCollisionCheck(DrawableSpaceObjects singleDrawObj, VisibleSpaceObjects singleVisObj) {
        //Visitor returns: Draw/Vis Asteroid = 0, Draw/Vis Ufo = 1, Draw/Vis battleship = 2, Draw/Vis NullShip = -1
        if (singleDrawObj.accept(iVisitor) == 0) {
            //Es wird die gesamte Liste der SpaceObjects durchgegangen
            for (int i = 0; i < drawObjs.size(); i++) {
                setUpAsteroidCollisionCheck(singleDrawObj, singleVisObj, i);
            }
        }
    }

    private void setUpAsteroidCollisionCheck(DrawableSpaceObjects singleDrawObj,
                                             VisibleSpaceObjects singleVisObj, int i) {
        //Falls es sich bei dem Element aus der Liste um einen Asteroiden handelt,
        // werden Entfernung etc verglichen
        if (drawObjs.get(i).accept(iVisitor) == 0) {
            //von zwei Asteroiden wird die Entfernung zueinander ermittelt; ausgehend vom Mittelpunkt des Bildes
            float dist = PApplet.dist(singleDrawObj.getXPos(), singleDrawObj.getYPos(),
                    drawObjs.get(i).getXPos(), drawObjs.get(i).getYPos());

            float radiusX = singleVisObj.getWidth() / 2 + visObjs.get(i).getWidth() / 2;
            float radiusY = singleVisObj.getHeight() / 2 + visObjs.get(i).getHeight() / 2;

            asteroidCollisionCheck(singleDrawObj, i, dist, radiusX, radiusY);
        }
    }

    private void asteroidCollisionCheck(DrawableSpaceObjects singleDrawObj, int i,
                                     float dist, float radiusX, float radiusY) {

        //images 0: Explosion Bilder, images 1: Asteroid Bilder, images 2: Ufo Bilder, images 3: Kampfschiff Bilder
        int ranImg = (int)(Math.random() * images.get(0).size());
        PImage explosionImg = images.get(0).get(ranImg);
        //Zwei Asteroiden dessen Koordinaten verglichen werden sollen
        DrawableAsteroid singleDrawAsteroid = (DrawableAsteroid) singleDrawObj;
        DrawableAsteroid drawAsteroid = (DrawableAsteroid) drawObjs.get(i);

        collisionCheckX(singleDrawAsteroid, drawAsteroid, explosionImg, dist, radiusX);
        collisionCheckY(singleDrawAsteroid, drawAsteroid, explosionImg, dist, radiusY);
    }

    private void collisionCheckX(DrawableAsteroid singleDrawAsteroid, DrawableAsteroid drawAsteroid,
                                 PImage explosionImg, float dist, float radiusX) {

        //checkXPos gibt true zurück, falls die beiden übergebenen Asteroiden in X Richtung kollidiert sind
        if (singleDrawAsteroid.checkXPos(drawAsteroid, dist, radiusX)) {
            sketch.image(explosionImg, drawAsteroid.getXPos(), drawAsteroid.getYPos(), 200, 200);

            singleDrawAsteroid.setXPosChangeRate(singleDrawAsteroid.getXPosChangeRate() * -1);
            drawAsteroid.setXPosChangeRate(drawAsteroid.getXPosChangeRate() * -1);
            drawAsteroid.setZPos((int) (Math.random() * 3));
        }
    }

    private void collisionCheckY(DrawableAsteroid singleAsteroid, DrawableAsteroid drawAsteroid,
                                 PImage explosionImg, float dist, float radiusY) {

        //checkYPos gibt true zurück, falls die beiden übergebenen Asteroiden in Y Richtung kollidiert sind
        if (singleAsteroid.checkYPos(drawAsteroid, dist, radiusY)) {
            sketch.image(explosionImg, drawAsteroid.getXPos(), drawAsteroid.getYPos(), 200, 200);

            singleAsteroid.setYPosChangeRate(singleAsteroid.getYPosChangeRate() * -1);
            drawAsteroid.setYPosChangeRate(drawAsteroid.getYPosChangeRate() * -1);
            drawAsteroid.setZPos((int) (Math.random() * 3));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CollisionHandler that = (CollisionHandler) o;
        return lvlScore == that.lvlScore &&
                Objects.equals(iVisitor, that.iVisitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), iVisitor, lvlScore);
    }

    @Override
    public String toString() {
        return "CollisionHandler{" +
                "iVisitor=" + iVisitor +
                ", score=" + lvlScore +
                ", sketch=" + sketch +
                ", images=" + images +
                ", visObjs=" + visObjs +
                ", drawObjs=" + drawObjs +
                ", initInfo=" + Arrays.toString(initInfo) +
                "} " + super.toString();
    }
}
