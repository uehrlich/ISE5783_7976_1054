
package geometries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import geometries.*;
import primitives.*;

import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * Testing triangles
 *
 * @author Uri and david
 *
 */
public class TriangleTests {


    Point p1 = new Point(0, 0, 0);
    Point p2 = new Point(5, 0, 0);
    Point p3 = new Point(0, 4, 0);
    Triangle triangle = new Triangle(p1, p2, p3);

    Point p4 = new Point(1, 1, 0);  // A point inside the area of the triangle

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }


        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Triangle(new Point(0, 0, 1),  new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a Triangle with vertex on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 0, 1)),
                "Constructed a Triangle with  vertice on a side");


    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        assertEquals(new Vector(0,0, 1), pl.getNormal(new Point(0, 1, 0)), "Bad normal to trinagle");//checks on the triangle
    }


//    @Test
//    void testFindIntersections() {
//        Triangle p1 = new Triangle(new Point(1, 2, 3), new Point(-1, -2, 4), new Point(4, 2, 1));
//
//        // ============ Equivalence Partitions Tests ==============
//        //TC01: Ray intersect inside Triangle
//
//        Ray r1 = new Ray(new Point(-1, -1, 1), new Vector(1, 1, 1));
//      assertEquals(List.of(new Point(0.9473684210526312, 0.9473684210526312, 2.947368421052631)), p1.findIntersections(r1),
//                "findIntersections() wrong result");
//
//        //TC02: Ray intersect outside Triangle against edge
//        Ray r2 = new Ray(new Point(-1, -1, 1), new Vector(1, 1, 4));
//        assertNull(p1.findIntersections(r2),
//                "findIntersections() wrong result");
//
//        //TC03: Ray intersect outside Triangle against vertex
//        Ray r3 = new Ray(new Point(-1, -1, 1), new Vector(7, 4, -1));
//        assertNull(p1.findIntersections(r3),
//                "findIntersections() wrong result");
//
//        // =============== Boundary Values Tests ==================
//        //TC11: Ray intersect on edge
//        Ray r4 = new Ray(new Point(-1, -1, 1), new Vector(2.6, 3, 1.6));
//        assertNull(p1.findIntersections(r4),
//                "findIntersections() Ray intersect on edge wrong result");
//
//        //TC12: Ray intersect in vertex
//        Ray r5 = new Ray(new Point(-1, -1, 1), new Vector(0, -1, 3));
//        assertNull(p1.findIntersections(r5),
//                "findIntersections() Ray intersect in vertex wrong result");
//
//        //TC13: Ray intersect on edge's continuation
//        Ray r6 = new Ray(new Point(-1, -1, 1), new Vector(3, 5, 1.5));
//        assertNull(p1.findIntersections(r6),
//                "findIntersections() Ray intersect on edge's continuation wrong result");
//    }


    @Test
    void testFindIntersections() {

        Ray ray1 = new Ray(new Point(0, 0, -3), new Vector(1, 1, 1));
        Ray ray2 = new Ray(new Point(1, 1, -3), new Vector(-4, -4, 7));
        Ray ray3 = new Ray(new Point(0, 0, -3), new Vector(1, 1, 8));
        Ray ray4 = new Ray(new Point(0, 1, -1), new Vector(0, 0, 1));
        Ray ray5 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        Ray ray6 = new Ray(new Point(0, 0, -1), new Vector(10, -1, 1));
        Ray ray7 = new Ray(new Point(-1, -1, 0), new Vector(1, 1, 0));
        Ray ray8 = new Ray(new Point(1, 1, 0), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============

        // First Equivalence Partition - No intersection points
        // TC1: The ray does not intersect the triangle - the ray passes against the side
        assertNull(triangle.findIntersections(ray1), "findIntersections() is incorrect");
        // TC2: The ray does not intersect the triangle - the ray passes against the vertex
        assertNull(triangle.findIntersections(ray2), "findIntersections() is incorrect");

        // Second Equivalence Partition - One intersection point
        // TC3: The ray intersects the triangle at one point
        assertEquals(List.of(new Point(0.375, 0.375, 0)), triangle.findIntersections(ray3), "findIntersections() is incorrect");
        assertEquals(1, triangle.findIntersections(ray3).size(), "findIntersections() is incorrect");

        // =============== Boundary Values Tests ==================
        // TC4: The ray intersects the triangle's side
        assertNull(triangle.findIntersections(ray4), "findIntersections() is incorrect");
        // TC5: The ray intersects the triangle's vertex
        assertNull(triangle.findIntersections(ray5), "findIntersections() is incorrect");
        // TC6: The ray intersects the triangle's side continuation
        assertNull(triangle.findIntersections(ray6), "findIntersections() is incorrect");
        // TC7: The ray parallel to the triangle plane
        assertNull(triangle.findIntersections(ray7), "findIntersections() is incorrect");
        // TC8: The ray starts from the surface of the triangle
        assertNull(triangle.findIntersections(ray8), "findIntersections() is incorrect");
    }


}
