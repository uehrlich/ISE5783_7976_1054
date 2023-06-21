package geometries;
import java.util.Arrays;
import java.util.LinkedList;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static java.lang.Math.sqrt;
import static primitives.Util.*;

import java.util.List;

import static primitives.Util.alignZero;

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


    /**
     * @param
     * @return
     */



//    public List<Point> findIntersections(Ray rayC) {
//
//            Vector u;
//            double tm ;
//            double d ;
//            if(center.equals(rayC.getP0())){
//                tm = 0 ;
//                d =0 ;
//            }
//            else {
//                u = this.center.subtract(rayC.getP0());
//                tm = rayC.getDir().dotProduct(u);
//                d  = Math.sqrt(u.lengthSquared() - (tm*tm) );
//            }
//
//
//            if( d >= this.radius ){
//                return null;
//            }
//            double th = Math.sqrt(this.radius * this.radius - d * d);
//            double t1 = tm + th ;
//            double t2 = tm - th ;
//            if(t1 < 0 && t2 < 0 ){
//                return null ;
//            }
//            LinkedList<Point> arr = new LinkedList<>();
//            if(t1 > 0  ){
//                arr.add(rayC.getPoint(t1));
//            }
//            if(t2 > 0 ){
//                arr.add(rayC.getPoint(t2));
//            }
//            return arr;
//        }



    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        Vector u = this.center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double d = sqrt(u.lengthSquared() - tm * tm);

        if (d > this.radius) {
            return null;
        }

        double th = sqrt(this.radius * this.radius - d * d);
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 > 0 &&
                t2 > 0 &&
                alignZero(t1 - maxDistance) <= 0 &&
                alignZero(t2 - maxDistance) <= 0 &&
                t1 != t2) {

            return List.of(new GeoPoint(this, ray.getPoint(t1)),
                    new GeoPoint(this, ray.getPoint(t2)));
        }

        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {

            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        }

        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {

            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        }

        return null;
    }



}

