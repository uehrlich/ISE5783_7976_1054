package primitives;
/**
 *represents linear point in the real numbers world
 *
 * @author Uri and David
 */
public class Point {
    protected final Double3 xyz;
    public Point(double d1 , double d2 , double d3 ) {//simple constructor
        this.xyz = new Double3(d1,d2,d3);
    }
    protected Point(Double3 xyz){this.xyz = xyz ; }//simple constructor
    @Override
    public boolean equals(Object obj) {//checks if equals
        if (this == obj) return true;
        return (obj instanceof Point) && (((Point)obj).xyz.equals(this.xyz) );
    } // check if the same reference

    /**
     * returns the duplicates of the differences of the two points
     * @param pt
     * @return
     */
    public double distanceSquared(Point pt){
        return (pt.xyz.d1 - this.xyz.d1)*(pt.xyz.d1 - this.xyz.d1) +
                (pt.xyz.d2 - this.xyz.d2)*(pt.xyz.d2 - this.xyz.d2) +
                (pt.xyz.d3 - this.xyz.d3)*(pt.xyz.d3 - this.xyz.d3);
    }

    /**
     * // gets a point and return the distance between the points by square root
     * @param pt
     * @return
     */

    public double distance(Point pt){
        return Math.sqrt(this.distanceSquared(pt));
    }
    @Override
    public String toString() { // prints double3D
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * adds a vector to the points and returns and returns new point
     * @param v
     * @return
     */
    public Point add (Vector v) { //
        return new Point (v.xyz.d1+ this.xyz.d1 ,
                v.xyz.d2 + this.xyz.d2 ,
                v.xyz.d3 + this.xyz.d3);
    }

    /**
     * // subtract two points and returns a new points
     * @param v
     * @return
     */
    public Vector subtract (Point v) {
        return new Vector (this.xyz.d1 - v.xyz.d1 ,
                this.xyz.d2  - v.xyz.d2 ,
                this.xyz.d3  - v.xyz.d3);
    }

}
