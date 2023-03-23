package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 *Limited Tube
 *
 * @author Uri and David
 */
public class Cylinder extends Tube{
    private final double height;

    public Cylinder(Ray axisRay, double radius, double height ) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null ;
    }

}
