package de.fh_kiel.oop.controller.SpaceObjVisitor;

import de.fh_kiel.oop.controller.DrawableAsteroid;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableNullShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableBattleShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableUfo;
import de.fh_kiel.oop.model.VisibleAsteroid;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleNullShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleBattleShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleUfo;

public class Visitor implements IVisitor {

    private static Visitor visitor = null;

    private Visitor() {
    }

    //Singleton
    public static Visitor getInstance() {
        if (visitor == null) {
            visitor = new Visitor();
        }

        return visitor;
    }

    @Override
    public int visit(DrawableAsteroid asteroid) {
        return 0;
    }

    @Override
    public int visit(DrawableUfo ufo) {
        return 1;
    }

    @Override
    public int visit(DrawableBattleShip battleShip) {
        return 2;
    }

    @Override
    public int visit(DrawableNullShip nullShip) {
        return -1;
    }

    @Override
    public int visit(VisibleAsteroid asteroid) {
        return 0;
    }

    @Override
    public int visit(VisibleUfo ufo) {
        return 1;
    }

    @Override
    public int visit(VisibleBattleShip battleShip) {
        return 2;
    }

    @Override
    public int visit(VisibleNullShip nullShip) {
        return -1;
    }
}
