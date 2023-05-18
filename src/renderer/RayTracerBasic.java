package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.getGeometries().findIntersections(ray);
        if (intersections == null) {
            return this.scene.getBackground();
        }
        return this.calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * returns ambient light
     * @param point
     * @return
     */
    private Color calcColor(Point point) {
        return this.scene.ambientLight.getIntensity();
    }
}
