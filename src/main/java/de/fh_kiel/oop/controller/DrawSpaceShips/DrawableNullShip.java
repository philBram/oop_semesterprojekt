package de.fh_kiel.oop.controller.DrawSpaceShips;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import processing.core.PImage;

public class DrawableNullShip extends DrawableSpaceShip {

    public DrawableNullShip(PImage img, float xPos, float yPos) {
        super(img, xPos, yPos);
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void updatePos(float width, float height) {
    }

    @Override
    protected void genXPosChangeRate() {
    }

    @Override
    protected void genYPosChangeRate() {
    }

    @Override
    public String toString() {
        return "DrawableNullShip{" +
                "img=" + img +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", xPosChangeRate=" + xPosChangeRate +
                ", yPosChangeRate=" + yPosChangeRate +
                "} " + super.toString();
    }
}
