package primitives;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for primitives.Point class
 * @author Uri and david
 */
public class PointTests {
    /**
     * Test method for .
     */

        @Test
        public void  testDistanceSquared(){//returns the duplicates of the difference of the two points
            // ============ Equivalence Partitions Tests ==============
            Point p1 = new Point(1,1,1);
            Point p2 = new Point(2,2,2);
            assertEquals(3 , p1.distanceSquared(p2), 0.00001 ,"distanceSquared() wrong result pos scalar");
            assertEquals( 3 , p2.distanceSquared(p1), 0.00001, "distanceSquared() wrong result pos scalar");

            assertEquals( 0 , p1.distanceSquared(p1),0.00001, "distanceSquared() wrong result pos scalar");
            // =============== Boundary Values Tests ==================
            p1  = new Point(1,0,0);
            p2 = new Point(2,0,0);
            assertEquals(1 ,p2.distanceSquared(p1), 0.00001, "distanceSquared() wrong result pos scalar");
            p1  = new Point(0,1,0);
            p2 = new Point(0,2,0);
            assertEquals( 1 , p2.distanceSquared(p1),0.00001,"distanceSquared() wrong result pos scalar");
            p1  = new Point(0,0,1);
            p2 = new Point(0,0,2);
            assertEquals(1 ,p2.distanceSquared(p1), 0.00001,"distanceSquared() wrong result pos scalar");



        }
        @Test
        public void testDistance(){ // square root
            // ============ Equivalence Partitions Tests ==============
            Point p1 = new Point(1,1,1);
            Point p2 = new Point(2,2,2);
            assertEquals( Math.sqrt(3) ,p1.distance(p2), 0.00001,"distance() wrong result pos scalar");
            assertEquals(Math.sqrt(3) , p2.distance(p1), 0.00001,"distance() wrong result pos scalar");

            //assertEquals(0 , 0.00001,"distance() wrong result pos scalar" );
            // =============== Boundary Values Tests ==================
            p1  = new Point(1,0,0);
            p2 = new Point(2,0,0);
            assertEquals(1 ,p2.distance(p1), 0.00001,"distance() wrong result pos scalar" );
            p1  = new Point(0,1,0);
            p2 = new Point(0,2,0);
            assertEquals(1 ,  p2.distance(p1),0.00001,"distance() wrong result pos scalar" );
            p1  = new Point(0,0,1);
            p2 = new Point(0,0,2);
            assertEquals(1 , p2.distance(p1),0.00001,"distance() wrong result pos scalar" );
        }
        @Test
        public void testAdd () {
            // ============ Equivalence Partitions Tests ==============
            Vector v1 = new Vector(1,0,0);
            Point p1 = new Point(1,1,1);
            assertEquals( new Point(2,1,1), p1.add(v1),"add() wrong result pos scalar");

        }
        @Test
        public void testSubtract () { // subtract the two
            // ============ Equivalence Partitions Tests ==============
            Point p1 = new Point(1,1,1);
            Point p2 = new Point(1,0,0);
            assertEquals(new Vector(0,1,1), p1.subtract(p2), "subtract() wrong result pos scalar" );
            // =============== Boundary Values Tests ==================
            assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),"subtract() for parallel vectors does not throw an exception" );
        }



    }
