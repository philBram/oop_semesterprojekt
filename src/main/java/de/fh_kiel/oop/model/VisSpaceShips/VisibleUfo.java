package de.fh_kiel.oop.model.VisSpaceShips;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.model.SpaceShipShield.Shield;

public class VisibleUfo extends VisibleSpaceShip {

    private Shield shield;

    public VisibleUfo(float width, float height) {
        super(width, height);

        this.health = 100;
    }

    //Dependency Injection
    //Dieser Konstruktor wird in diesem Projekt noch nicht verwendet
    public VisibleUfo(float width, float height, Shield shield) {
        this(width, height);

        this.shield = shield;
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VisibleUfo{" +
                "health=" + health +
                ", width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
