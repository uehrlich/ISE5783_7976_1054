package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import primitives.*;


public class Geometries implements Intersectable {
    private List<Intersectable> items;

    public Geometries() {
        items = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable... geometries) {
        items = new LinkedList<Intersectable>(List.of(geometries));
    }

    public void add(Intersectable... geometries) {
        items.addAll(List.of(geometries));
    }


    @Override
    public List<Point> findIntersections(Ray ray) {

            List<Point> intersections = null; // all intersections
            for (Intersectable geometry: this.items) {
                List<Point> geometryIntersections = geometry.findIntersections(ray); // intersections of each geometry
                if (geometryIntersections != null) { // there are intersections in this geometry
                    if (intersections == null) { // if there are not intersections yet - create new list
                        intersections = new LinkedList<>();
                    }
                    intersections.addAll(geometryIntersections);
                }
            }
            return intersections;
        }

    }
