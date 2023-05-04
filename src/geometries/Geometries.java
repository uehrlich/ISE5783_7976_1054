package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import primitives.*;


public class Geometries implements Intersectable {
    private List<Intersectable> items;

    public Geometries(Intersectable... geometries) {
        items = new LinkedList<Intersectable>(List.of(geometries));
    }

    public void add(Intersectable... geometries) {
        items.addAll(List.of(geometries));
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}