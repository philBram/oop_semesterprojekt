package de.fh_kiel.oop.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import processing.core.PImage;

import java.util.Objects;

public abstract class DrawableSpaceObjects {
    protected PImage img;
    protected float xPos;
    protected float yPos;
    protected float xPosChangeRate;
    protected float yPosChangeRate;

    public DrawableSpaceObjects(PImage img, float xPos, float yPos) {
        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public abstract int accept(IVisitor visitor);
    public abstract void updatePos(float width, float height);

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public PImage getImg() {
        return img;
    }

    public float getXPosChangeRate() {
        return xPosChangeRate;
    }

    public float getYPosChangeRate() {
        return yPosChangeRate;
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
    }

    public void setXPosChangeRate(float xPosChangeRate) {
        this.xPosChangeRate = xPosChangeRate;
    }

    public void setYPosChangeRate(float yPosChangeRate) {
        this.yPosChangeRate = yPosChangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawableSpaceObjects that = (DrawableSpaceObjects) o;
        return Float.compare(that.xPos, xPos) == 0 &&
                Float.compare(that.yPos, yPos) == 0 &&
                Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos, img);
    }

    @Override
    public String toString() {
        return "DrawableSpaceObjects{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", pImage=" + img +
                '}';
    }
}
