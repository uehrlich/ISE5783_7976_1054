package geometries;


import primitives.Point;
import primitives.Vector;
/**
 *interface
 *
 * @author Uri and David
 */
public interface Geometry extends Intersectable {
    public Vector getNormal (Point point );


}


