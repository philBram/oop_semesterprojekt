package de.fh_kiel.oop.controller.DrawSpaceShips;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import processing.core.PImage;

public class DrawableUfo extends DrawableSpaceShip {

    public DrawableUfo(PImage img, float xPos, float yPos) {
        super(img, xPos, yPos);

        genXPosChangeRate();
        genYPosChangeRate();
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    //updatePos aller SpaceShips ist sehr ähnlich und man hätte auch einen zusätzlichen Parameter nutzen können. Dann
    //wäre jedoch in der drawSpaceObject Methode des ProcessingPainters eine Abfrage erfolgen müssen, um welches
    //SpaceShip es sich handelt. Fand diese Herangehensweise schöner.
    @Override
    public void updatePos(float outSideWidth, float outSideHeight) {
        //10 % Wahrscheinlichkeit Richtung in x / y zu ändern
        if (Math.random() < 0.1) {
            genXPosChangeRate();
        }
        if (Math.random() < 0.1) {
            genYPosChangeRate();
        }

        updateXPos(outSideWidth);
        updateYPos(outSideHeight);
    }

    @Override
    protected void genXPosChangeRate() {
        do {
            xPosChangeRate = (float) (Math.random() * 51) - 25;
        } while (xPosChangeRate == 0);
    }

    @Override
    protected void genYPosChangeRate() {
        do {
            yPosChangeRate = (float) (Math.random() * 51) - 25;
        } while (yPosChangeRate == 0);
    }

    @Override
    public String toString() {
        return "DrawableUfo{" +
                "img=" + img +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", xPosChangeRate=" + xPosChangeRate +
                ", yPosChangeRate=" + yPosChangeRate +
                "} " + super.toString();
    }
}
