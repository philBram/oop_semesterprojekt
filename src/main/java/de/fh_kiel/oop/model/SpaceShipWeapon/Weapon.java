package de.fh_kiel.oop.model.SpaceShipWeapon;

import java.util.Objects;

//Keine Fertige oder genutzte Klasse
public abstract class Weapon {

    private int ammunition;
    private double fireRate;

    public abstract void fire();
    public abstract void reload();

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return ammunition == weapon.ammunition &&
                Double.compare(weapon.fireRate, fireRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ammunition, fireRate);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "ammunition=" + ammunition +
                ", fireRate=" + fireRate +
                '}';
    }
}
