package de.fh_kiel.oop.controller;

import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableBattleShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableNullShip;
import de.fh_kiel.oop.controller.DrawSpaceShips.DrawableUfo;
import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.controller.SpaceObjVisitor.Visitor;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("when running DrawableSpaceObjectTest")
class DrawableSpaceObjectsTest {

    private static DrawableAsteroid testAsteroid;
    private static DrawableUfo testUfo;
    private static DrawableBattleShip testBattleShip;
    private static DrawableNullShip testNullShip;
    private static IVisitor iVisitor;

    @BeforeAll
    static void beforeAll() {
        testAsteroid = new DrawableAsteroid(null, 0, 0, 1, 1, 0);
        testUfo = new DrawableUfo(null, 0, 0);
        testBattleShip = new DrawableBattleShip(null, 0, 0);
        testNullShip = new DrawableNullShip(null, 0, 0);
        iVisitor = Visitor.getInstance();
    }

    @Test
    @DisplayName("when testing accept methods")
    void testAccept() {
        assertAll(
                () -> assertEquals(0, testAsteroid.accept(iVisitor),
                "visitor should return 0 when DrawableSpaceObject is a DrawableAsteroid"),

                () -> assertEquals(1, testUfo.accept(iVisitor),
                "visitor should return 1 when DrawableSpaceObject is a DrawableUfo"),

                () -> assertEquals(2, testBattleShip.accept(iVisitor),
                "visitor should return 2 when DrawableSpaceObject is a DrawableBattleShip"),

                () -> assertEquals(-1, testNullShip.accept(iVisitor),
                "visitor should return -1 when DrawableSpaceObject is a DrawableNullShip")
        );
    }

    @Nested
    @DisplayName("when running DrawableAsteroidTest")
    class DrawableAsteroidTest {

        @Test
        @DisplayName("when testing updatePos method")
        void testUpdatePos() {
            assertAll(
                    () -> testAsteroid.updatePos(100.50f, 50.50f),

                    () -> assertEquals(1, testAsteroid.getXPos()),
                    () -> assertEquals(1, testAsteroid.getYPos()),
                    () -> assertEquals(1, testAsteroid.getXPosChangeRate()),
                    () -> assertEquals(1, testAsteroid.getYPosChangeRate()),

                    () -> testAsteroid.setXPos(100.50f),
                    () -> testAsteroid.setYPos(50.50f),

                    () -> testAsteroid.updatePos(100.50f / 2, 50.50f / 2),

                    () -> assertEquals(100.50f / 2 * 1.5f, testAsteroid.getXPos()),
                    () -> assertEquals(50.50f / 2 * 1.5f, testAsteroid.getYPos()),
                    () -> assertEquals(-1, testAsteroid.getXPosChangeRate()),
                    () -> assertEquals(-1, testAsteroid.getYPosChangeRate()),

                    () -> testAsteroid.setXPos(-100.50f),
                    () -> testAsteroid.setYPos(-50.50f),

                    () -> testAsteroid.updatePos(-100.50f / 2, -50.50f / 2),

                    () -> assertEquals(-100.50f / 2 * -0.5f, testAsteroid.getXPos()),
                    () -> assertEquals(-50.50f / 2 * -0.5f, testAsteroid.getYPos()),
                    () -> assertEquals(1, testAsteroid.getXPosChangeRate()),
                    () -> assertEquals(1, testAsteroid.getYPosChangeRate())
            );
        }

        @Test
        @DisplayName("when testing checkXPos and checkYPos methods")
        void testCheckPos() {
            DrawableAsteroid testAsteroid_1 = new DrawableAsteroid(
                    null, 0.0f, 0.0f, 0.0f, 0.0f, 0);

            assertAll (
                    () -> assertFalse(testAsteroid.checkXPos(testAsteroid, 0, 0)),
                    () -> assertFalse(testAsteroid.checkYPos(testAsteroid, 0, 0)),

                    () -> assertFalse(testAsteroid.checkXPos(testAsteroid, 10, 0)),
                    () -> assertFalse(testAsteroid.checkYPos(testAsteroid, 10, 0)),

                    () -> assertTrue(testAsteroid.checkXPos(testAsteroid_1, 0, 0)),
                    () -> assertTrue(testAsteroid.checkYPos(testAsteroid_1, 0, 0)),

                    () -> assertFalse(testAsteroid.checkXPos(testAsteroid_1, 10, 0)),
                    () -> assertFalse(testAsteroid.checkYPos(testAsteroid_1, 10, 0)),

                    () -> testAsteroid.setZPos(1),

                    () -> assertFalse(testAsteroid.checkXPos(testAsteroid_1, 0, 0)),
                    () -> assertFalse(testAsteroid.checkYPos(testAsteroid_1, 0, 0)),

                    () -> assertFalse(testAsteroid.checkXPos(testAsteroid_1, 10, 0)),
                    () -> assertFalse(testAsteroid.checkYPos(testAsteroid_1, 10, 0))
            );
        }
    }

    @Nested
    @DisplayName("when running DrawableSpaceShipTest")
    class DrawableSpaceShipTest {

        @Test
        @DisplayName("when testing updatePos method")
        void testUpdatePos() {
            assertAll(
                    () -> testUfo.setXPos(1000.50f),
                    () -> testUfo.setYPos(500.50f),

                    () -> testUfo.updatePos(100.50f / 2, 50.50f / 2),

                    () -> assertEquals(100.50f / 2 * 1.1f, testUfo.getXPos()),
                    () -> assertEquals(50.50f / 2 * 1.1f, testUfo.getYPos()),

                    () -> testUfo.setXPos(-1000.50f),
                    () -> testUfo.setYPos(-500.50f),

                    () -> testUfo.updatePos(-100.50f / 2, -50.50f / 2),

                    () -> assertEquals(-100.50f / 2 * -0.1f, testUfo.getXPos()),
                    () -> assertEquals(-50.50f / 2 * -0.1f, testUfo.getYPos())
            );
        }
    }
}