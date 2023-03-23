package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
/**
 *Tube
 *
 * @author Uri and David
 */
public class Tube extends RadialGeometry {

    protected Ray axisRay;

    public Tube(Ray axisRay, double radius) {//simple constructor
        super(radius);
        this.axisRay = axisRay;

    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
