package de.fh_kiel.oop.controller;

import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableBattleShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableNullShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableUfo;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleBattleShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleNullShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleUfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("when running SpaceShipFactoryTest")
class SpaceShipFactoryTest {

    private static SpaceShipFactory shipFactory;

    @BeforeAll
    static void beforeAll() {
        shipFactory = SpaceShipFactory.getInstance();
    }

    @Nested
    @DisplayName("when running DrawableSpaceShipFactoryTest")
    class DrawableSpaceShipFactoryTest {

        @Test
        @DisplayName("when testing createDrawShip and createVisShip")
        void testCreateShip() {
            assertAll(
                    () -> assertEquals(shipFactory.createDrawShip(null, 0, 0, 0).getClass(),
                            DrawableUfo.class),

                    () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 1).getClass(),
                            DrawableUfo.class),

                    () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 2).getClass(),
                            DrawableUfo.class)
            );

            assertAll(
                () -> assertEquals(shipFactory.createDrawShip(null, 0, 0, 1).getClass(),
                        DrawableBattleShip.class),

                () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 0).getClass(),
                        DrawableBattleShip.class),

                () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 2).getClass(),
                        DrawableBattleShip.class)
            );

            assertAll(
                    () -> assertEquals(shipFactory.createDrawShip(null, 0, 0, 2).getClass(),
                            DrawableNullShip.class),

                    () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 0).getClass(),
                            DrawableNullShip.class),

                    () -> assertNotEquals(shipFactory.createDrawShip(null, 0, 0, 1).getClass(),
                            DrawableNullShip.class)
            );

            assertAll(
                    () -> assertEquals(shipFactory.createVisShip(0, 0, 0).getClass(),
                            VisibleUfo.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 1).getClass(),
                            VisibleUfo.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 2).getClass(),
                            VisibleUfo.class)
            );

            assertAll(
                    () -> assertEquals(shipFactory.createVisShip(0, 0, 1).getClass(),
                            VisibleBattleShip.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 0).getClass(),
                            VisibleBattleShip.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 2).getClass(),
                            VisibleBattleShip.class)
            );

            assertAll(
                    () -> assertEquals(shipFactory.createVisShip(0, 0, 2).getClass(),
                            VisibleNullShip.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 0).getClass(),
                            VisibleNullShip.class),

                    () -> assertNotEquals(shipFactory.createVisShip(0, 0, 1).getClass(),
                            VisibleNullShip.class)
            );
        }
    }
}