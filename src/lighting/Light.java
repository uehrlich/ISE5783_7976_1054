package lighting;

import primitives.Color;



/**
 *An abstract class that represents all types of light sources in the scene
 */
 abstract class Light {
     private Color intensity;

    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter
     * @return
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
