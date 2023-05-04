package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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



    public Vector getNormal(Point point) {

        //  checks if it in the bases center to avoid zero vector
        if( point.equals(axisRay.getP0() ) ||
                point.equals(axisRay.getP0().add(this.axisRay.getDir().scale(height)) ) ){
            return  this.axisRay.getDir() ;
        }
        // checks if it is in the bases and if it is returns axises dir and if it not calc like tube
        double radiusSquared = this.radius * this.radius;
        Vector toOtherBase = this.axisRay.getDir().scale(this.height) ;
        Point p0Ver2 = this.axisRay.getP0().add(toOtherBase);
        double crossProductp01 =  this.axisRay.getDir().dotProduct(this.axisRay.getP0().subtract(point));
        double crossProductp02 =   this.axisRay.getDir().dotProduct(p0Ver2.subtract(point));
        return  (point.distanceSquared(this.axisRay.getP0()) <= radiusSquared  && crossProductp01  == 0) ||
                (point.distanceSquared(p0Ver2) <= radiusSquared && crossProductp02 == 0) ?
                this.axisRay.getDir() :
                super.getNormal(point) ;


    }

    @Override
    public List<Point> findIntersections(Ray ray){
        return null ;
    }
}
