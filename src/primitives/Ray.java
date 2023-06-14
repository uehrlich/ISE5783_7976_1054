package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 *represnts linear ray in the real numbers world
 *that contains point of start and dir - linear line !
 * @author Uri and David
 */
public class Ray {
    private final  Point p0;
    private final Vector dir;

    public Point getP0() {

        return this.p0;
    }

    public Vector getDir() {
        return this.dir;
    }

    public Ray(Point p0, Vector dir) { //simple constructor
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getPoint(double t){
        return this.p0.add(this.dir.scale(t));
    }

    @Override
    public boolean equals(Object obj) { // checks if the two equal
        return (obj instanceof Ray) && (((Ray)obj).p0.equals(this.p0) && ((Ray)obj).dir.equals(this.dir));
    }


    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points.isEmpty()) {
            return null;
        }
        GeoPoint closestPoint = points.get(0);
        double closestPointDistance = this.p0.distance(points.get(0).point);
        for (int i = 1; i < points.size(); i++) {
            if (this.p0.distance(points.get(i).point) < closestPointDistance) {
                closestPoint = points.get(i);
                closestPointDistance = this.p0.distance(points.get(i).point);
            }
        }
        return closestPoint;
    }

        @Override
    public String toString() {//simple command line
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}