package lighting;

import primitives.*;

public class AmbientLight {


    private final Color intensity;


    /**
     *
     * @param Ia Illumination light
     * @param Ka light factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity = Ia.scale(Ka);
    }

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    public  AmbientLight() {
        this.intensity = Color.BLACK;
    }

    public Color getIntensity() {
        return intensity;
    }
}


