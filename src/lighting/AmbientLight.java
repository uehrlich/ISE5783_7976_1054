package lighting;

import primitives.*;

public class AmbientLight extends Light {

    /**
     *
     * @param Ia Illumination light
     * @param Ka light factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {

        super(Ia.scale(Ka));
    }

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    public  AmbientLight() {
        super(Color.BLACK);
    }

}


