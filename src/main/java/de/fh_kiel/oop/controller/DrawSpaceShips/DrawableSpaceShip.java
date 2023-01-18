package de.fh_kiel.oop.controller.DrawSpaceShips;

import de.fh_kiel.oop.controller.DrawableSpaceObjects;
import processing.core.PImage;

public abstract class DrawableSpaceShip extends DrawableSpaceObjects {

    public DrawableSpaceShip(PImage img, float xPos, float yPos) {
        super(img, xPos, yPos);
    }

    protected abstract void genXPosChangeRate();
    protected abstract void genYPosChangeRate();

    //Es werden beide Methoden gebraucht, da keine Pointer übergeben werden können
    protected void updateXPos(float outSideWidth) {
        xPos += xPosChangeRate;
        //Prallt vom erweiterten Processingfensterrand ab
        if (xPos > 1.1 * outSideWidth) {
            xPos = 1.1f * outSideWidth;
            xPosChangeRate *= -1;
        }
        else if (xPos < -0.1 * outSideWidth) {
            xPos = -0.1f * outSideWidth;
            xPosChangeRate *= -1;
        }
    }

    protected void updateYPos(float outSideHeight) {
        yPos += yPosChangeRate;
        //Prallt vom erweiterten Processingfensterrand ab
        if (yPos > 1.1 * outSideHeight) {
            yPos = 1.1f * outSideHeight;
            yPosChangeRate *= -1;
        }
        if (yPos < -0.1 * outSideHeight) {
            yPos = -0.1f * outSideHeight;
            yPosChangeRate *= -1;
        }
    }

    @Override
    public String toString() {
        return "DrawableSpaceShip{" +
                "img=" + img +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", xPosChangeRate=" + xPosChangeRate +
                ", yPosChangeRate=" + yPosChangeRate +
                "} " + super.toString();
    }
}
