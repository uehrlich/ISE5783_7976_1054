package renderer;

import primitives.*;
import scene.Scene;

/**
 *
  */
public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * method of scannig rays through scene
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);
}
