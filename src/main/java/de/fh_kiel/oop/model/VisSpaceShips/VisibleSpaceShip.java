package de.fh_kiel.oop.model.VisSpaceShips;

import de.fh_kiel.oop.model.VisibleSpaceObjects;

import java.util.Objects;

public abstract class VisibleSpaceShip extends VisibleSpaceObjects {
    protected int health;

    public VisibleSpaceShip(float width, float height) {
        super(width, height);
    }

    public boolean hit() {
        health -= 100;

        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VisibleSpaceShip that = (VisibleSpaceShip) o;
        return health == that.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), health);
    }

    @Override
    public String toString() {
        return "VisibleSpaceShip{" +
                "health=" + health +
                "} " + super.toString();
    }
}
