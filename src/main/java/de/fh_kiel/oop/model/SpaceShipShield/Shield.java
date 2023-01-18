package de.fh_kiel.oop.model.SpaceShipShield;

import java.util.Objects;

//Keine Fertige oder genutzte Klasse
public abstract class Shield {

    protected double shieldHP;
    protected double regenerationRate;

    public abstract void regenerate();
    public abstract double absorb(double dmg);

    public double getShieldHP() {
        return shieldHP;
    }

    public void setShieldPower(double shieldHP) {
        this.shieldHP = shieldHP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shield shield = (Shield) o;
        return Double.compare(shield.shieldHP, shieldHP) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shieldHP);
    }

    @Override
    public String toString() {
        return "Shield{" +
                "shieldPower=" + shieldHP +
                '}';
    }
}
