package geometries;
import java.util.List;
import java.util.Objects;

import primitives.*;

public abstract class Intersectable {
    /**
     * to find intersections with shape(3d)
     *
     *
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected  abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


/**
     * inner class for help
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         *
         * @param point
         * @param geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.point = point;
            this.geometry = geometry;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return (o instanceof GeoPoint) && (((GeoPoint)o).point.equals(this.point) && (((GeoPoint)o).geometry.equals(this.geometry) ));
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
   /* public  List<GeoPoint> findGeoIntersections (Ray ray){
        //return findGeoIntersectionsHelper(ray);

    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper (Ray ray);

    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;
        public GeoPoint(Point point , Geometry geometry){
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object x){
            return x instanceof GeoPoint
                    && ((GeoPoint)x).geometry.equals(geometry) &&
                    ((GeoPoint)x).point.equals(point);
        }

    }*/



}
