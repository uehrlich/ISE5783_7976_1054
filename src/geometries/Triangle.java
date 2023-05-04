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

    //@Override
   // public Vector getNormal(Point point) {
     //   return null;
    //}

    @Override
    public boolean equals(Object obj) {//checks if equals
        if (this == obj) return true;
        return (obj instanceof Triangle) && super.equals(obj);
    }


    @Override
    public List<Point> findIntersections(Ray ray){
        /**
         * Triangle Area = abs(b-a) * abs(c-a) * sin(theta) / 2
         * Triangle Area = abs((b-a) x (c-a)) / 2
         *
         */
        try{
            List<Point> intersections = this.plane.findIntersections(ray);
            Point P = intersections.get(0);
            Point A = this.vertices.get(0);
            Point B = this.vertices.get(1);
            Point C = this.vertices.get(2);
            Vector CA = C.subtract(A);
            Vector BA = B.subtract(A);
            Vector PA;
            PA = P.subtract(A);
            double capArea = CA.crossProduct(PA).lengthSquared();
            double abcArea = CA.crossProduct(BA).lengthSquared();
            double abpArea = BA.crossProduct(PA).lengthSquared();
            double bcpArea;
            bcpArea = (B.subtract(P)).crossProduct(C.subtract(P)).lengthSquared();
            double uSquared = capArea / abcArea ;
            double vSquared = abpArea / abcArea ;
            double wSqaured = bcpArea / abcArea ;
            double u = Math.sqrt(uSquared);
            double v = Math.sqrt(vSquared);
            double w = Math.sqrt(wSqaured);
            if(u < 1 && v< 1 && w < 1 && !Util.isZero(u) && !Util.isZero(v)  && !Util.isZero(w) &&  Util.isZero(w+v+u-1)){
                return List.of(P) ;
            }
            return null ;
        }
        catch(Exception e) {
            return null ;
        }

    }

}

