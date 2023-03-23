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
        return null;
    }
}