package geometries;

import primitives.Material;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface
 *
 * @author Uri and David
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

//    public Geometry() {
//        this.emission = Color.BLACK;
//    }
//    public Geometry(Color emission) {
//        this.emission = emission;
//    }
//

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public abstract Vector getNormal(Point point);

    /**
     * emission getter
     *
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * emission setter
     *
     * @return this geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}


