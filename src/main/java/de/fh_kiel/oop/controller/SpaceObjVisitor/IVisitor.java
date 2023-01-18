package de.fh_kiel.oop.controller.SpaceObjVisitor;

import de.fh_kiel.oop.controller.DrawableAsteroid;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableNullShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableBattleShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableUfo;
import de.fh_kiel.oop.model.VisibleAsteroid;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleNullShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleBattleShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleUfo;

public interface IVisitor {

    int visit(DrawableAsteroid asteroid);
    int visit(DrawableUfo ufo);
    int visit(DrawableBattleShip battleShip);
    int visit(DrawableNullShip nullShip);

    int visit(VisibleAsteroid asteroid);
    int visit(VisibleUfo ufo);
    int visit(VisibleBattleShip battleShip);
    int visit(VisibleNullShip nullShip);
}
