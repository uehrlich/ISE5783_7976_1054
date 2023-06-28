package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera {

    private Point p0; // camera location
    private Vector vUp, vTo, vRight;
    private double height, width, distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int numOfSamples =5;


    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("The vectors must be orthogonal");
        }
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    // Method Chaining
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    // Method Chaining
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    // Method Chaining
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    // Method Chaining
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }
        /**
         * Creating a ray from the camera to the center of a specific pixel on the view plane
         * @param nX Pixels number on the x-axis in the view plane (columns)
         * @param nY Pixels number on the y-axis in the view plane (rows)
         * @param j index of column for a specific pixel
         * @param i index of row for a specific pixel
         * @return Creating a ray from the camera to the center of a specific pixel on the view plane
         */
        public Ray constructRay ( int nX, int nY, int j, int i){

            // pCenter is the point in the center of the view plane
            Point pCenter = this.p0.add(this.vTo.scale(this.distance));

            // pixels size
            double ratioX = this.width / nX;
            double ratioY = this.height / nY;

            // the center of P[i,j] pixel
            Point pIJ = pCenter;                            // In case that pCenter is exactly P[i,j] pixel
            double yI = -(i - (nY - 1) / 2d) * ratioY;        // The distance from pCenter to p[i,j] pixel's center in the y-axis
            double xJ = (j - (nX - 1) / 2d) * ratioX;         // The distance from pCenter to p[i,j] pixel's center in the x-axis


            if (!isZero(xJ)) {
                pIJ = pIJ.add(this.vRight.scale(xJ));
            }
            if (!isZero(yI)) {
                pIJ = pIJ.add(this.vUp.scale(yI));
            }

            Vector vIJ = pIJ.subtract(this.p0); // vector to the center of the pixel

            return new Ray(this.p0, vIJ);
        }

    /**
     * constract multiple rays through pixel
     * @param nX Pixels number on the x-axis in the view plane (columns)
     * @param nY Pixels number on the y-axis in the view plane (rows)
     * @param j index of column for a specific pixel
     * @param i index of row for a specific pixel
     * @param numSamples number of samples
     * @return
     */
    public LinkedList<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, int numSamples) {
        LinkedList<Ray> rays = new LinkedList<>();
        Point Pc = p0.add(vTo.scale(distance)); // the center of the view plane
        double Ry = height / nY;
        double Rx = width / nX;
        double subPixelSizeX = Rx / numSamples; // size of sub pixels in x axis
        double subPixelSizeY = Ry / numSamples; // size of sub pixels in y axis

        for (int s = 0; s < numSamples; s++) {
            for (int t = 0; t < numSamples; t++) {
                // Calculate the sub-pixel offset within the sub-pixel
                double subPixelOffsetX = (Math.random() + t) * subPixelSizeX;
                double subPixelOffsetY = (Math.random() + s) * subPixelSizeY;

                double yi = -(i - (nY - 1) / 2d + subPixelOffsetY) * Ry; // the y coordinate of the sub-pixel
                double xj = (j - (nX - 1) / 2d + subPixelOffsetX) * Rx; // the x coordinate of the sub-pixel

                Point Pij = Pc; // the point on the view plane

                if (!isZero(xj))
                    Pij = Pij.add(vRight.scale(xj)); // add x-coordinate offset to the view plane point

                if (!isZero(yi))
                    Pij = Pij.add(vUp.scale(yi)); // add y-coordinate offset to the view plane point

                Vector Vij = Pij.subtract(p0); // the vector from the camera to the point on the view plane
                rays.add(new Ray(p0, Vij)); // add the ray to the list of rays
            }
        }

        return rays;
    }



    public Camera setSamples(int num){
        if(num < 0){
            throw new IllegalArgumentException("Samples can't be negative!!!");
        }
        this.numOfSamples = num;
        return this;
    }

        public Point getP0 () {
            return p0;
        }

        public Vector getvUp () {
            return vUp;
        }

        public Vector getvTo () {
            return vTo;
        }

        public Vector getvRight () {
            return vRight;
        }


    public Camera renderImage() {
        try {
            if (this.p0 == null) {
                throw new MissingResourceException("Missing resource value", Point.class.getName(), "");
            }
            if (this.vUp == null || this.vRight == null || this.vTo == null) {
                throw new MissingResourceException("Missing resource value", Vector.class.getName(), "");
            }
            if (this.imageWriter == null) {
                throw new MissingResourceException("missing resource value", ImageWriter.class.getName(), "");
            }
            if (this.rayTracer == null) {
                throw new MissingResourceException("missing resource value", RayTracerBase.class.getName(), "");
            }

            // IMAGE RENDERING
            // Pass a ray from the camera through each pixel in the view plane and set the color
            for (int i = 0; i < this.imageWriter.getNy(); i++) {
                for (int j = 0; j < this.imageWriter.getNx(); j++) {
                    Color  pixelColor = castRays(this, j,i);

                    this.imageWriter.writePixel(j, i, pixelColor);

                }
            }

        } catch (MissingResourceException exception) {
            throw new UnsupportedOperationException("The fields must not be null ----> " + exception.getClassName());
        }
        return  this;
    }
     private Color castRay(Camera camera , int j,int i){
         Ray ray = this.constructRay(this.imageWriter.getNx(), this.imageWriter.getNy(), j, i);
          return  this.rayTracer.traceRay(ray);

     }

    /**
     * cast ray for  few rays
     * @param pixX
     * @param pixY
     * @return
     */
    private Color castRays(Camera camera, int pixX, int pixY) {
        if(this.numOfSamples<=1){
            return castRay( this, pixX,pixY);
        }
        LinkedList<Ray> rays = constructRaysThroughPixel(imageWriter.getNx(), imageWriter.getNx(), pixX, pixY, this.numOfSamples);
        Color color = Color.BLACK;
        for (Ray ray: rays) {
            color = color.add(this.rayTracer.traceRay(ray));
        }
        return color.reduce(rays.size());
    }
    public Camera writeToImage() {
        if (this.imageWriter == null) {
            throw new MissingResourceException("missing resource value", ImageWriter.class.getName(), "");
        }
        this.imageWriter.writeToImage();
        return this;
    }

    public void printGrid(int interval, Color color) {

        if (this.imageWriter == null) {
            throw new MissingResourceException("missing resource value", ImageWriter.class.getName(), "");
        }

        for (int i = 0; i < this.imageWriter.getNy(); i++) {
            for (int j = 0; j < this.imageWriter.getNx(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
    }



}



