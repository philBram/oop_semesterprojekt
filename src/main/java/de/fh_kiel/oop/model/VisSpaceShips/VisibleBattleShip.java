package de.fh_kiel.oop.model.VisSpaceShips;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.model.SpaceShipShield.Shield;
import de.fh_kiel.oop.model.SpaceShipWeapon.Weapon;

public class VisibleBattleShip extends VisibleSpaceShip {

    private Shield shield;
    private Weapon weapon;

    public VisibleBattleShip(float width, float height) {
        super(width, height);

        this.health = 300;
    }

    //Dependency Injection
    //Dieser Konstruktor wird in diesem Projekt noch nicht verwendet
    public VisibleBattleShip(float width, float height, Shield shield, Weapon weapon) {
        this(width, height);

        this.shield = shield;
        this.weapon = weapon;
    }

    @Override
    public int accept(IVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VisibleBattleShip{" +
                "health=" + health +
                ", width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
