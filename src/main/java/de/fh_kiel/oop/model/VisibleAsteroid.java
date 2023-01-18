package de.fh_kiel.oop.model;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;

public class VisibleAsteroid extends VisibleSpaceObjects {

    public VisibleAsteroid(float width, float height) {
        super(width, height);
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VisibleAsteroid{" +
                "width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
