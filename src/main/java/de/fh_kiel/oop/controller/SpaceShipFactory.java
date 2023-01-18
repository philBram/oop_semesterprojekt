package de.fh_kiel.oop.controller;

import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableNullShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableBattleShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableSpaceShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableUfo;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleNullShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleBattleShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleSpaceShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleUfo;
import processing.core.PImage;

public class SpaceShipFactory {

    private static SpaceShipFactory factory = null;

    private SpaceShipFactory() {
    }

    //Singleton
    public static SpaceShipFactory getInstance() {
        if (factory == null) {
            factory = new SpaceShipFactory();
        }

        return factory;
    }

    public DrawableSpaceShip createDrawShip(PImage img, float xPos, float yPos, int type) {
        if (type == 0) {
            return new DrawableUfo(img, xPos, yPos);
        }
        else if (type == 1) {
            return new DrawableBattleShip(img, xPos, yPos);
        }
        else {
            return new DrawableNullShip(img, xPos, yPos);
        }
    }

    public VisibleSpaceShip createVisShip(float width, float height, int type) {
        if (type == 0) {
            return new VisibleUfo(width, height);
        }
        else if (type == 1) {
            return new VisibleBattleShip(width, height);
        }
        else {
            return new VisibleNullShip(width, height);
        }
    }
}
