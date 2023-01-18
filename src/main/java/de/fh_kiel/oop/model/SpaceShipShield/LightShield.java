package de.fh_kiel.oop.model.SpaceShipShield;

//Keine Fertige oder genutzte Klasse
public class LightShield extends Shield {

    public LightShield() {
        this.shieldHP = 50;
        this.regenerationRate = 0.5;
    }

    @Override
    public void regenerate() {
        if (shieldHP < 50) {
            shieldHP += regenerationRate;
        }
    }

    @Override
    public double absorb(double dmg) {
        if (shieldHP >= dmg * 0.05) {
            shieldHP -= dmg * 0.05;
        }
        else {
            shieldHP = 0;
        }
        //5 % Absorb
        return dmg * 0.05;
    }

    @Override
    public String toString() {
        return "LightShield{" +
                "shieldHP=" + shieldHP +
                "} " + super.toString();
    }
}
