package geometries;
import static  primitives.Util.*;
import primitives.Point;
import primitives.Util;
import primitives.Vector;
import primitives.Ray;

import java.util.List;
import java.util.LinkedList;

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
        double t  =  axisRay.getDir().dotProduct(
                point.subtract(
                        axisRay.getP0()));
        Point O = axisRay.getP0().add(
                axisRay.getDir().scale(t));
        return point.subtract(O);
    }

//    public List<Point> findIntersections(Ray ray) {
//        Vector dir = ray.getDir();
//        Vector v = axisRay.getDir();
//        double dirV = dir.dotProduct(v);
//
//        if (ray.getP0().equals(axisRay.getP0())) { // In case the ray starts on the p0.
//            if (Util.isZero(dirV))
//                return List.of(ray.getPoint(radius));
//
//            if (dir.equals(v.scale(dir.dotProduct(v))))
//                return null;
//
//            return List.of(ray.getPoint(
//                    Math.sqrt(radius * radius / dir.subtract(v.scale(dir.dotProduct(v)))
//                            .lengthSquared())));
//        }
//
//        Vector deltaP = ray.getP0().subtract(axisRay.getP0());
//        double dpV = deltaP.dotProduct(v);
//
//        double a = 1 - dirV * dirV;
//        double b = 2 * (dir.dotProduct(deltaP) - dirV * dpV);
//        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;
//
//        if (Util.isZero(a)) {
//            if (Util.isZero(b)) { // If a constant equation.
//                return null;
//            }
//            return List.of(ray.getPoint(-c / b)); // if it's linear, there's a solution.
//        }
//
//        double discriminant = Util.alignZero(b * b - 4 * a * c);
//
//        if (discriminant < 0) // No real solutions.
//            return null;
//
//        double t1 = Util.alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
//        double t2 = Util.alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.
//
//        if (discriminant <= 0) // No real solutions.
//            return null;
//
//        if (t1 > 0 && t2 > 0) {
//            List<Point> _points = new LinkedList<Point>();
//            _points.add(ray.getPoint(t1));
//            _points.add(ray.getPoint(t2));
//            return _points;
//        }
//        else if (t1 > 0) {
//            List<Point> _points = new LinkedList<Point>();
//            _points.add(ray.getPoint(t1));
//            return  _points;
//        }
//        else if (t2 > 0) {
//            List<Point> _points = new LinkedList<Point>();
//            _points.add(ray.getPoint(t2));
//            return _points;
//        }
//        return null;
//    }
//
//    protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
//                /*if (this.axisRay.getDirection().isParallel(ray.getDirection())) {
//            return null;
//        }*/
//        return null;
//
//    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }



}
