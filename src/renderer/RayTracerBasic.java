package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Find the intersection and the scene’s geometries
     * If there is no intersection, return the background color
     * Find the closest intersection point
     * Find the color of the intersection point
     * @param ray
     * @return
     */
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * returns ambient light
     *
     * @param gp
     * @return
     */

    private Color calcColor(GeoPoint gp, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission(), calcLocalEffects(gp, ray)
                );// add calculated light contribution   from all light sources,
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return color;
        }
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(geoPoint.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }


    private Double3 calcDiffusive(Material material, double nl) {
        return material.getKd().scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2).scale(nl)).normalize();
        if (-alignZero(r.dotProduct(v)) <= 0) {
            return Double3.ZERO;
        }
        return material.getKs().scale(Math.pow(-alignZero(r.dotProduct(v)), material.getnShininess()));
    }

}
