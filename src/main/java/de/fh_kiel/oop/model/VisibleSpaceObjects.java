package de.fh_kiel.oop.model;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;

import java.util.Objects;

public abstract class VisibleSpaceObjects {

    protected float width;
    protected float height;

    public VisibleSpaceObjects(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public abstract int accept(IVisitor visitor);

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisibleSpaceObjects that = (VisibleSpaceObjects) o;
        return Float.compare(that.width, width) == 0 &&
                Float.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "VisibleSpaceObjects{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
