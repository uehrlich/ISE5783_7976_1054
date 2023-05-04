package geometries;

import geometries.Geometry;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 *Plane
 *
 * @author Uri and David
 */
public class Plane implements Geometry {
    public final Point q0;
    private final Vector normal;

    public Plane(Point q1, Point q2, Point q3) { //constructor
        Vector v1 = new Vector(q1, q2); // two new vectors
        Vector v2 = new Vector(q2, q3);
        this.normal = v1.crossProduct(v2).normalize();
        this.q0 = q1;
    }

    public Plane(Point q0, Vector normal) { //simple constructor
        this.q0 = q0;
        this.normal = normal;
    }

    @Override
    public Vector getNormal(Point point) { //get
        return normal;
    }

    public Vector getNormal() { // get without the point
        return normal;
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        double nv = this.getNormal().dotProduct(ray.getDir());
        if(Util.isZero(nv)){
            return  null ;
        }
        else{
            if(q0.equals(ray.getP0())){
                return null;
            }
            double nqp =  this.getNormal().dotProduct(this.q0.subtract(ray.getP0()));
            double t = nqp / nv  ;
            if(Util.alignZero(t) > 0 ){
                return List.of(ray.getPoint(t));
            }
            else{
                return null ;
            }
        }

    }
}
