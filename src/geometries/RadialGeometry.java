package geometries;

/**
 *  @author Uri and David
 */
public abstract class RadialGeometry implements Geometry {
    protected double radius;

    public RadialGeometry(double r){
        this.radius = r;
    }
}
