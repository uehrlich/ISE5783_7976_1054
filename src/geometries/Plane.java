package geometries;

import geometries.Geometry;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane
 *
 * @author Uri and David
 */
public class Plane extends Geometry {
    public final Point q0;
    private final Vector normal;

    public Plane(Point q1, Point q2, Point q3) {
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


    public Point getQ0() {
        return q0;
    }


    /**
     * @param ray, max distance
     * @return
     */


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        // The ray is contained in the plane
        if (isZero(ray.getDir().dotProduct(this.normal))) {
            return null;
        }

        // Ray origin is the head of the normal
        if (ray.getP0().equals(this.q0)) {
            return null;
        }

        double numerator = this.normal.dotProduct(this.q0.subtract(ray.getP0()));
        double denominator = this.normal.dotProduct(ray.getDir());
        if (isZero(denominator)) {
            throw new IllegalArgumentException("denominator cannot be zero");
        }
        double t = alignZero(numerator / denominator);

        // The ray starts from the plane
        if (t == 0) {
            return null;
        }

        if (t > 0 && alignZero(t - maxDistance) <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        return null;
    }

}
