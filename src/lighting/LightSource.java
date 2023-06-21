package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * An interface that defines critical methods for all light sources
 */

public interface LightSource {

/**
 * get the intensity of the light that comes from a point
 *
 * @param point is the origin of the light
*/
public Color getIntensity(Point p);
/**
* get the light direction of the vector
* @param point is the starting point of the vector
* @return direction of light
*/
public Vector getL(Point p);
double getDistance(Point point);


}
