package geometries;

/**
 *  @author Uri and David
 */
public abstract class RadialGeometry extends Geometry {
    protected double radius;

    public RadialGeometry(double r){
        this.radius = r;
    }
}
