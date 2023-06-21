package geometries;
import static  primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 *Triangle
 *
 * @author Uri and David
 */
public class Triangle extends Polygon {
    public Triangle(Point p1 , Point p2 , Point p3) {
        super(p1,p2,p3);
    }



    @Override
    public boolean equals(Object obj) {//checks if equals
        if (this == obj) return true;
        return (obj instanceof Triangle) && super.equals(obj);
    }




    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Check if the point inside the area of the triangle
        Vector v1 = this.vertices.get(0).subtract(ray.getP0());
        Vector v2 = this.vertices.get(1).subtract(ray.getP0());
        Vector v3 = this.vertices.get(2).subtract(ray.getP0());
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        if ((ray.getDir().dotProduct(n1) < 0 &&
                ray.getDir().dotProduct(n2) < 0 &&
                ray.getDir().dotProduct(n3) < 0) ||
                (ray.getDir().dotProduct(n1) > 0 &&
                        ray.getDir().dotProduct(n2) > 0 &&
                        ray.getDir().dotProduct(n3) > 0)) {

            double numerator = this.plane.getNormal().dotProduct(this.plane.getQ0().subtract(ray.getP0()));
            double denominator = this.plane.getNormal().dotProduct(ray.getDir());
            if (isZero(denominator)) {
                throw new IllegalArgumentException("denominator cannot be zero");
            }
            double t = alignZero(numerator / denominator);

            // The ray starts from the triangle
            if (t == 0) {
                return null;
            }

            if (t > 0 && alignZero(t - maxDistance) <= 0) {
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        return null;
    }

//    protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray ){
//
//        List<GeoPoint> lst = this.plane.findGeoIntersections(ray);
//        if(lst == null)
//            return  null;
//
//        Point p0 = lst.get(0).point;
//
//    // Check if the point inside the area of the triangle
//        Vector v1 = this.vertices.get(0).subtract(p0);
//        Vector v2 = this.vertices.get(1).subtract(p0);
//        Vector v3 = this.vertices.get(2).subtract(p0);
//        Vector n1 = v1.crossProduct(v2).normalize();
//        Vector n2 = v2.crossProduct(v3).normalize();
//        Vector n3 = v3.crossProduct(v1).normalize();
//
//        if ((ray.getDir().dotProduct(n1) < 0 &&
//                ray.getDir().dotProduct(n2) < 0 &&
//                ray.getDir().dotProduct(n3) < 0) ||
//                (ray.getDir().dotProduct(n1) > 0 &&
//                        ray.getDir().dotProduct(n2) > 0 &&
//                        ray.getDir().dotProduct(n3) > 0)) {
//
//            double numerator = this.plane.getNormal().dotProduct(this.plane.getQ0().subtract(ray.getP0()));
//            double denominator = this.plane.getNormal().dotProduct(ray.getDir());
//            if (isZero(denominator)) {
//                throw new IllegalArgumentException("denominator cannot be zero");
//            }
//            double t = alignZero(numerator / denominator);
//
//            // The ray starts from the triangle
//            if (t == 0) {
//                return null;
//            }
//
//            if (t > 0) {
//                return List.of(new GeoPoint(this, ray.getPoint(t)));
//            }
 //       }

  //      return null;

 //   }
    }



