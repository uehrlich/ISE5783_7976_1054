package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

import java.awt.*;
import java.util.LinkedList;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries;

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

  /*  private Scene(SceneBuilder sceneBuilder) {
        this.name = sceneBuilder.name;
        this.background = sceneBuilder.background;
        //this.ambientLight = sceneBuilder.ambientLight;
        this.geometries = sceneBuilder.geometries;
    }

     /*public static class SceneBuilder {
        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight;
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }

       public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

       /* public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }*/


//        public SceneBuilder setGeometries(Geometries geometries) {
//            this.geometries = geometries;
//            return this;
//        }
//
//        public Scene build() {
//            return new Scene(this);
//        }
//    }

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

