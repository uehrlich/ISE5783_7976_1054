package scene;


import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import java.util.List;

import java.awt.*;
import java.util.LinkedList;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries;
    public List<LightSource> lights =  new LinkedList<>();


    public Scene(String name) {
        this.name = name;
        this.geometries = new Geometries();

    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public String getName() {
        return this.name;
    }

    public Color getBackground() {
        return this.background;
    }

    public AmbientLight getAmbientLight() {
        return this.ambientLight;
    }

    public Geometries getGeometries() {
        return this.geometries;
    }

    }

