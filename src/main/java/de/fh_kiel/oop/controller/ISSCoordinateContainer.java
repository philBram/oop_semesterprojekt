package de.fh_kiel.oop.controller;

import java.util.Objects;

public class ISSCoordinateContainer {

    private float latitude;
    private float longitude;
    private float velocity;

    //Nur die jsonToJavaObject Methode aus der Json Klasse ist in der Lage, diese Klasse zu instanziieren
    private ISSCoordinateContainer() {}

    public float convertX() {
        return (longitude + 180) / 360;
    }

    public float convertY() {
        return (latitude + 90) / 180;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISSCoordinateContainer that = (ISSCoordinateContainer) o;
        return Float.compare(that.latitude, latitude) == 0 &&
                Float.compare(that.longitude, longitude) == 0 &&
                Float.compare(that.velocity, velocity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, velocity);
    }

    @Override
    public String toString() {
        return "ISSCoordinateContainer{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", velocity=" + velocity +
                '}';
    }
}
