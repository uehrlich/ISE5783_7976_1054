package primitives;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 * @author Uri and david
 */
class VectorTests {

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
    @Test
    public void testLengthSqured(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,1,1);
        assertEquals("scale() wrong result pos scalar", v1.lengthSquared(),3 , 0.00001 );
        Vector v2 = new Vector(-1,1,-1);
        assertEquals("scale() wrong result pos scalar", v2.lengthSquared(),3 , 0.00001 );


    }
    @Test
    public void testScale(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,1,1);
        double t = 3 ;
        assertEquals("scale() wrong result pos scalar", v1.scale(t), new Vector(3,3,3));
        t = -3 ;
        assertEquals("sclae() wrong result", v1.scale(t), new Vector(-3,-3,-3));
        // =============== Boundary Values Tests ==================
        assertThrows("scale() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.scale(0));
    }
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));
    }
    @Test
    public void testAdd() {
        Vector v1 = new Vector(1,1,0);
        Vector v2 = new Vector(1,3,0);
        //  4 = sqrt(2) * SQRT(10) * cos(x)
        //  A = 50 CEL - zavit hada
        assertEquals("Add() wrong result", v1.add(v2) ,new Vector(2,4,0) );
        Vector v3 = new Vector(-1,-3,0);
        // zavit cheha - 129 cel
        assertEquals("Add() wrong result", v1.add(v3) ,new Vector(0,-2,0) );
        // =============== Boundary Values Tests ==================
        Vector vn1 = new Vector(1,1,1);
        v2 = new Vector(2,2,2);
        assertEquals("coliner vectors are not creating colinear vector  " , vn1.add(v2),new Vector(3,3,3));
        Vector vn2 = new Vector(-1,-1,-1);
        assertThrows("Add() cant create Zero Vector for oppiste vectors ",
                IllegalArgumentException.class,
                () -> vn1.add(vn2) );
        v1 = new Vector(1,0,0);
        v2 = new Vector(0,1,0);
        assertEquals("Normals Vectors Add() wrong result", v1.add(v2) ,new Vector(1,1,0) );
        assertEquals("Same Vectors Add() wrong result", v1.add(v1) ,new Vector(2,0,0) );


    }
}
