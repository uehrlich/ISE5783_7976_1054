
package geometries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing sphere
 *
 * @author Uri and david
 *
 */
public class SphereTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Sphere(new Point(0,0,0), 1  );
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct sphere");
        }
        // =============== Boundary Values Tests ==================
    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere  pl = new Sphere(new Point(0, 0, 0), 1);
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 1)), "Bad normal to Sphere"); // check for point on the sphere
    }
}
