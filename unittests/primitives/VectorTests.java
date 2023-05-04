
package primitives;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;
/**
 * Unit tests for primitives.Vector class
 * @author Uri and david
 */
public class VectorTests {

    /**
     * test the dotProduct (scalar duplication)
     */
    @Test
    public void testDotProduct(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,1,0);
        Vector v2 = new Vector(1,3,0);
        //  4 = sqrt(2) * SQRT(10) * cos(x)
        //  A = 50 CEL - zavit hada
        assertEquals(4, v1.dotProduct(v2) , 0.00001, "Add() wrong result");
        Vector v3 = new Vector(-1,-3,0);
        // zavit cheha - 129 cel
        assertEquals( v1.dotProduct(v3) ,-4 , 0.00001, "Add() wrong result" );
        // =============== Boundary Values Tests ==================
        Vector vn1 = new Vector(1,0,0);
        Vector vn2 = new Vector(0,1,0);
        assertTrue(isZero(vn1.dotProduct(vn2)),"dotProduct() result is not right");

    }
    @Test
    public void testNormalize(){//TODO
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,0,0);
        assertEquals(1 , v1.length(), 0.00001 ,"scale() wrong result pos scalar");
        Vector v2 = new Vector(-1,0,0);
        assertEquals(1, v2.length(), 0.00001, "scale() wrong result pos scalar");


    }

    /**
     * tests the length of the vector
     */
    @Test
    public void testLength(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,0,0);
        assertEquals( 1 ,v1.length(), 0.00001,"scale() wrong result pos scalar");
        Vector v2 = new Vector(-1,0,0);
        assertEquals(1 , v2.length(), 0.00001, "scale() wrong result pos scalar");


    }

    /**
     * tests the length squared
     */
    @Test
    public void testLengthSquared(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,1,1);
        assertEquals( 3 ,v1.lengthSquared(), 0.00001, "scale() wrong result pos scalar");
        Vector v2 = new Vector(-1,1,-1);
        assertEquals( 3 , v2.lengthSquared(), 0.00001 ,"scale() wrong result pos scalar");


    }

    /**
     * tests duplication with scalar
     */
    @Test
    public void testScale(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,1,1);
        double t = 3 ;
        assertEquals( new Vector(3,3,3), v1.scale(t), "scale() wrong result pos scalar");
        t = -3 ;
        assertEquals(new Vector(-3,-3,-3), v1.scale(t), "scale() wrong result");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),"scale() for parallel vectors does not throw an exception");
    }

    /**
     * tests cross product( vector duplication)
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( vr.length(),v1.length() * v2.length(),  0.00001 ,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
    }


    @Test
    public void testSubtract () { // subtract the two
        Vector v1 = new Vector(1, 1, 0);
        Vector v2 = new Vector(1, 3, 0);
        //  4 = sqrt(2) * SQRT(10) * cos(x)
        //  A = 50 CEL - zavit hada
        assertEquals(new Vector(0, -2, 0), v1.subtract(v2), "subtract() wrong result");
        Vector v3 = new Vector(-1, -3, 0);
        // zavit cheha - 129 cel
        assertEquals(new Vector(2, 4, 0), v1.subtract(v3), "subtract() wrong result");
        // =============== Boundary Values Tests ==================
        Vector vn1 = new Vector(1, 1, 1);
        v2 = new Vector(2, 2, 2);
        assertEquals(new Vector(-1, -1, -1), vn1.subtract(v2), "coliner vectors are not creating colinear vector  ");
        v1 = new Vector(1, 0, 0);
        assertThrows(IllegalArgumentException.class,() -> vn1.subtract(vn1),
                "subtract() cant create Zero Vector for oppiste vectors ");

        v2 = new Vector(0, 1, 0);
        assertEquals(new Vector(1, -1, 0), v1.subtract(v2), "Normals Vectors suctract() wrong result");

    }

        /**
         * tests add vector to another
         */
    @Test
    public void testAdd() {
        Vector v1 = new Vector(1,1,0);
        Vector v2 = new Vector(1,3,0);
        //  4 = sqrt(2) * SQRT(10) * cos(x)
        //  A = 50 CEL - zavit hada
        assertEquals(new Vector(2,4,0),  v1.add(v2) ,"Add() wrong result");
        Vector v3 = new Vector(-1,-3,0);
        // zavit cheha - 129 cel
        assertEquals( new Vector(0,-2,0), v1.add(v3) ,"Add() wrong result" );
        // =============== Boundary Values Tests ==================
        Vector vn1 = new Vector(1,1,1);
        v2 = new Vector(2,2,2);
        assertEquals(new Vector(3,3,3), vn1.add(v2), "coliner vectors are not creating colinear vector  " );
        Vector vn2 = new Vector(-1,-1,-1);
        v1 = new Vector(1,0,0);
        assertThrows( IllegalArgumentException.class,() -> vn1.add(vn2),
                "Add() cant create Zero Vector for oppiste vectors " );

        v2 = new Vector(0,1,0);
        assertEquals(new Vector(1,1,0), v1.add(v2) , "Normals Vectors Add() wrong result");
        assertEquals(new Vector(2,0,0), v1.add(v1) , "Same Vectors Add() wrong result" );


    }
}
