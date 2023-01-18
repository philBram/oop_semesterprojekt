package de.fh_kiel.oop.controller;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import processing.core.PImage;

import java.util.Objects;

public class DrawableAsteroid extends DrawableSpaceObjects {

    protected int zPos;

    public DrawableAsteroid(PImage img, float xPos, float yPos, float xPosChangeRate, float yPosChangeRate, int zPos) {
        super(img, xPos, yPos);

        this.xPosChangeRate = xPosChangeRate;
        this.yPosChangeRate = yPosChangeRate;
        this.zPos = zPos;
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void updatePos(float outSideWidth, float outSideHeight) {
        updateXPos(outSideWidth);
        updateYPos(outSideHeight);
    }

    private void updateXPos(float outSideWidth) {
        xPos += xPosChangeRate;
        //Prallt vom erweiterten Processingfensterrand ab
        if (xPos > 1.5 * outSideWidth) {
            xPos = 1.5f * outSideWidth;
            xPosChangeRate *= -1;
        }
        else if (xPos < -0.5 * outSideWidth) {
            xPos = -0.5f * outSideWidth;
            xPosChangeRate *= -1;
        }
    }

    private void updateYPos(float outSideHeight) {
        yPos += yPosChangeRate;
        //Prallt vom erweiterten Processingfensterrand ab
        if (yPos > 1.5 * outSideHeight) {
            yPos = 1.5f * outSideHeight;
            yPosChangeRate *= -1;
        }
        else if (yPos < -0.5 * outSideHeight) {
            yPos = -0.5f * outSideHeight;
            yPosChangeRate *= -1;
        }
    }

    public boolean checkXPos(DrawableAsteroid drawObj, float dist, float radiusX) {
        //Für CollisionCheck => true falls übergebener Asteroid mit this in X Richtung kollidiert
        return this.zPos == drawObj.getZPos() && this != drawObj && dist <= radiusX;
    }

    public boolean checkYPos(DrawableAsteroid drawObj, float dist, float radiusY) {
        //Für CollisionCheck => true falls übergebener Asteroid mit this in Y Richtung kollidiert
        return this.zPos == drawObj.getZPos() && this != drawObj && dist <= radiusY;
    }

    public int getZPos() {
        return zPos;
    }

    public void setZPos(int zPos) {
        this.zPos = zPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DrawableAsteroid asteroid = (DrawableAsteroid) o;
        return zPos == asteroid.zPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), zPos);
    }

    @Override
    public String toString() {
        return "DrawableAsteroid{" +
                "img=" + img +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", xPosChangeRate=" + xPosChangeRate +
                ", yPosChangeRate=" + yPosChangeRate +
                "} " + super.toString();
    }
}
