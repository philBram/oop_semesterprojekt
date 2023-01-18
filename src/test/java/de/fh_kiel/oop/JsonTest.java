package de.fh_kiel.oop;

import com.fasterxml.jackson.databind.JsonNode;
import de.fh_kiel.oop.controller.Json;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("when running JsonTest")
class JsonTest {

    private static Json JSON;
    private static String str;
    private InputStream testInput;
    private JsonNode jsonNode;

    @BeforeAll
    static void beforeAll() {
        JSON = Json.getInstance();
        //Methode parse() aus Json Klasse benötigt InputStream => String -> InputStream parsen
        str = "{ \"title\": \"JsonTest\", \"thisUsed\": \"false\" }";
    }

    @BeforeEach
    void beforeEach() throws Exception {
        testInput = new ByteArrayInputStream(str.getBytes());
        jsonNode = JSON.parse(testInput);
    }

    @AfterEach
    void afterEach() throws Exception {
        testInput.close();
    }

    @Nested
    @DisplayName("when running parseTest")
    class parseTest {

        /*@Test
        @DisplayName("when testing not null")
        void testNotNull() throws Exception {
            assertNotNull(JSON.parse(new ByteArrayInputStream(str.getBytes())), "should return a JsonNode");
        }*/

        @Test
        @DisplayName("when testing Exception throw")
        void testThrows() {
            assertThrows(Exception.class, () -> JSON.parse(null),
                    "should throw a Exception when calling method with null as argument");
        }

        @Test
        @DisplayName("when testing JsonNode return String")
        void testEquals() {
            assertAll(
                    () -> assertEquals("JsonTest", jsonNode.get("title").asText()),
                    () -> assertEquals("false", jsonNode.get("thisUsed").asText())
            );
        }
    }

    @Nested
    @DisplayName("when running jsonToJavaObjectTest")
    class jsonToJavaObjectTest {

        /*@Test
        @DisplayName("when testing not null")
        void testNotNull() throws Exception {
            assertNotNull(JSON.jsonToJavaObject(jsonNode, TestClass.class),
                    "should return a JavaObject");
        }*/

        @Test
        @DisplayName("when testing Exception throw")
        void testThrows() {
            assertThrows(Exception.class, () -> JSON.jsonToJavaObject(jsonNode, null),
                    "should throw a Exception when calling method with null as argument");
        }

        @Test
        @DisplayName("when testing return Object String")
        void testEquals() throws Exception {
            TestClass testClass = JSON.jsonToJavaObject(jsonNode, TestClass.class);

            String expected = jsonNode.get("title").asText();
            String actual = testClass.getTitle();

            assertEquals(expected, actual, "should equal Attribute of passed class");
        }
    }
}