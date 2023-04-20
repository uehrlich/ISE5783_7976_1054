package geometries;

import primitives.Point;
import primitives.Vector;
/**
 *Sphere
 *
 * @author Uri and David
 */
public class Sphere extends RadialGeometry {
    final Point center;

    public Sphere(Point center, double radius) {//simple constructor
        super(radius);
        this.center = center;

    }


    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }


    public boolean equals(Object obj) {//checks if equals
        return (obj instanceof Sphere) && this.center.equals(((Sphere) obj).center) && this.radius == ((Sphere) obj).radius;
    }
}

