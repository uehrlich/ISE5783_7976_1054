package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

import java.util.List;

public class CameraIntegrationsTest {

    //List<Point> allPoints = null;

    Camera camera1 = new Camera(
            new Point(0, 0, 0),
            new Vector(0, 0, -1),
            new Vector(0, 1, 0))
            .setVPDistance(1)
            .setVPSize(3, 3);

    Camera camera2 = new Camera(
            new Point(0, 0, 0.5),
            new Vector(0, 0, -1),
            new Vector(0, 1, 0))
            .setVPDistance(1)
            .setVPSize(3, 3);


    private int intersectionsNum(Camera camera, Intersectable shape, int nX, int nY) {
        int sumOfIntersectionPoints = 0;
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                List<Point> lst = shape.findIntersections(camera.constructRay(nX, nY, j, i));
                if (lst != null) {
                    sumOfIntersectionPoints += lst.size();
                }
            }
        }
        return sumOfIntersectionPoints;
    }

    @Test
    void testSphereWithCamera() {

        // TC1: Ray through one pixel intersects the sphere two times
        Sphere sphere1 = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, this.intersectionsNum(camera1, sphere1,3,3), "Integration of sphere is incorrect");

        // TC2: Ray through 9 pixels intersects the sphere 18 times
        Sphere sphere2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, this.intersectionsNum(camera2, sphere2,3,3), "Integration of sphere is incorrect");

        // TC3: Ray through 5 pixels intersects the sphere 10 times
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, this.intersectionsNum(camera2, sphere3,3,3), "Integration of sphere is incorrect");

        // TC4: Ray through 9 pixels intersects the sphere 9 times
        Sphere sphere4 = new Sphere(new Point(0, 0, -2.5), 50);
        assertEquals(9, this.intersectionsNum(camera2, sphere4,3,3), "Integration of sphere is incorrect");

        // TC5: Ray does not intersect the sphere
        Sphere sphere5 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, this.intersectionsNum(camera2, sphere5,3,3), "Integration of sphere is incorrect");
    }

    @Test
    void testPlaneWithCamera() {

        // TC1: Ray through 9 pixels intersects the plane 9 times
        Plane plane1 = new Plane(new Point(0,0,-5), new Point(1,1,-5), new Point(-2,-6,-5));
        assertEquals(9, this.intersectionsNum(camera1, plane1,3,3), "Integration of plane is incorrect");

        // TC2: Ray through 9 pixels intersects the plane 9 times
        Plane plane2 = new Plane(new Point(0,1.5,-1), new Point(0,0,-2), new Point(1,0,-2));
        assertEquals(9, this.intersectionsNum(camera1, plane2,3,3), "Integration of plane is incorrect");

        // TC3: Ray through 6 pixels intersects the plane 6 times
        Plane plane3 = new Plane(new Point(0,0.5,-1), new Point(0,0,-2), new Point(1,0,-2));
        assertEquals(6, this.intersectionsNum(camera1, plane3,3,3), "Integration of plane is incorrect");

    }

    @Test
    void testTriangleWithCamera() {

        // TC1: Ray through one pixel intersects the triangle one time
        Triangle triangle1 = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(1, this.intersectionsNum(camera1, triangle1,3,3), "Integration of plane is incorrect");

        // TC2: Ray through two pixels intersects the triangle two times
        Triangle triangle2 = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(2, this.intersectionsNum(camera1, triangle2,3,3), "Integration of plane is incorrect");

    }


}