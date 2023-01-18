package de.fh_kiel.oop.model.VisSpaceShips;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;

public class VisibleNullShip extends VisibleSpaceShip {

    public VisibleNullShip(float width, float height) {
        super(width, height);

        this.health = 0;
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VisibleNullShip{" +
                "health=" + health +
                ", width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
