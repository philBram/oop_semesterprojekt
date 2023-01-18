package de.fh_kiel.oop.model;

import de.fh_kiel.oop.controller.SpaceObjVisitor.IVisitor;
import de.fh_kiel.oop.controller.SpaceObjVisitor.Visitor;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleBattleShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleNullShip;
import de.fh_kiel.oop.model.VisSpaceShips.VisibleUfo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("when running VisibleSpaceObjectsTest")
class VisibleSpaceObjectsTest {

    private static VisibleAsteroid testAsteroid;
    private static VisibleUfo testUfo;
    private static VisibleBattleShip testBattleShip;
    private static VisibleNullShip testNullShip;
    private static IVisitor iVisitor;

    @BeforeAll
    static void beforeAll() {
        iVisitor = Visitor.getInstance();

        testAsteroid = new VisibleAsteroid(0, 0);
        testUfo = new VisibleUfo(0, 0);
        testBattleShip = new VisibleBattleShip(0, 0);
        testNullShip = new VisibleNullShip(0, 0);
    }

    @Test
    @DisplayName("when testing accept method")
    void testAccept() {
        assertAll(
                () -> assertEquals(0, testAsteroid.accept(iVisitor),
                "visitor should return 0 when VisibleSpaceObject is a VisibleSpaceObject"),

                () -> assertEquals(1, testUfo.accept(iVisitor),
                "visitor should return 1 when VisibleSpaceObject is a VisibleUfo"),

                () -> assertEquals(2, testBattleShip.accept(iVisitor),
                "visitor should return 2 when VisibleSpaceObject is a VisibleBattleShip"),

                () -> assertEquals(-1, testNullShip.accept(iVisitor),
                "visitor should return 1 when VisibleSpaceObject is a VisibleNullShip")
        );
    }

    @Test
    @DisplayName("when testing hit method")
    void testUfoHit() {
        assertAll(
                () -> assertEquals(100, testUfo.getHealth()),
                () -> assertTrue(testUfo.hit()),

                () -> assertEquals(300, testBattleShip.getHealth()),
                () -> assertFalse(testBattleShip.hit()),
                () -> assertFalse(testBattleShip.hit()),
                () -> assertTrue(testBattleShip.hit()),

                () -> assertEquals(0, testNullShip.getHealth()),
                () -> assertTrue(testNullShip.hit())
        );
    }
}