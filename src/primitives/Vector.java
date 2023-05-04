
package primitives;

import java.awt.*;

/**
 * represnts linear vector in the real numbers world
 *
 * @author Uri and David
 */
public class Vector extends Point {

    public Vector(Point pt1, Point pt2) { //from pt1 to pt2  -> pt1pt2 vector
        super(pt2.xyz.d1 - pt1.xyz.d1,
                pt2.xyz.d2 - pt1.xyz.d2,
                pt2.xyz.d3 - pt1.xyz.d3);
        if (this.xyz.equals(Double3.ZERO))//gets a point
            throw new IllegalArgumentException("cant enter the zero vector");
    }

    public Vector(double d1, double d2, double d3) { //simple constructor
        super(d1, d2, d3);
        if (this.xyz.equals(Double3.ZERO))//gets a point
            throw new IllegalArgumentException("cant enter the zero vector");

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector) && (((Vector) obj).xyz.equals(this.xyz));
    }

    @Override
    public String toString() { // prints the point
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * // adding all values for each parameter and returns a new vector
     *
     * @param vc
     * @return
     */
    public Vector add(Vector vc) {
        return new Vector(
                this.xyz.d1 + vc.xyz.d1,
                this.xyz.d2 + vc.xyz.d2,
                this.xyz.d3 + vc.xyz.d3);
    }

    /**
     * // subtract all values for each parameter and returns a new vector
     *
     * @param vc
     * @return
     */
    public Vector subtract(Vector vc) {
        return new Vector(this.xyz.d1 - vc.xyz.d1,
                this.xyz.d2 - vc.xyz.d2,
                this.xyz.d3 - vc.xyz.d3);
    }

    /**
     * // duplicate by scale
     *
     * @param sc
     * @return
     */
    public Vector scale(double sc) {
        return new Vector(sc * this.xyz.d1,
                sc * this.xyz.d2,
                sc * this.xyz.d3
        );
    }

    /**
     * // gets a new vectors and returns the scalar duplication
     *
     * @param vc
     * @return
     */
    public double dotProduct(Vector vc) {
        return this.xyz.d1 * vc.xyz.d1 +
                this.xyz.d2 * vc.xyz.d2 +
                this.xyz.d3 * vc.xyz.d3;

    }

    /**
     * returns the length of the vector squared
     *
     * @return
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * // returns the length of the vector
     *
     * @return
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * // normalizes the vector
     *
     * @return
     */
    public Vector normalize() {
        return this.scale(1 / this.length());
    }
    /*
     * (x1,y1,z1) ( x2,y2,z2)
     *
     * x1 | y1  z1 x1 y1
     * x2 |  y2 z2 x2 y2
     * */

    /**
     * // gets a vector and returns the cross product of the two vectors
     *
     * @param vc
     * @return
     */
    public Vector crossProduct(Vector vc) {
        return new Vector((this.xyz.d2 * vc.xyz.d3 - this.xyz.d3 * vc.xyz.d2),
                (this.xyz.d3 * vc.xyz.d1 - this.xyz.d1 * vc.xyz.d3),
                (this.xyz.d1 * vc.xyz.d2 - this.xyz.d2 * vc.xyz.d1));

    }

}



