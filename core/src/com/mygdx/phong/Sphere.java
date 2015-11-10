package com.mygdx.phong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.sun.org.apache.xpath.internal.compiler.Keywords;


public class Sphere {

    public Sphere() {

        this.R = 150;
        this.pos2D = new Vector2(400, 400);

        populateNormalVectors();

    }

    public Vector2 pos2D;
    public float R;


    private Vector3 normalVectors[][] = new Vector3[800][800];

    public void populateNormalVectors() {
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {

                float dst = Vector2.dst(j, i, pos2D.x, pos2D.y);
                if (dst < R) {
                    normalVectors[i][j] = normalVector(j / (2 * R), i / (2 * R));
                } else {
                    normalVectors[i][j] = null;
                }

            }

        }
    }


    public void draw2D(ShapeRenderer s) {

        //long time = System.currentTimeMillis();
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {
                if (normalVectors[i][j] != null) {
                    float alpha = angle(cam, normalVectors[i][j]) / 180f;


                    s.setColor(alpha, alpha, alpha, 1);
                    s.rect(j, i, 1, 1);
                }
            }
        }

        //println(System.currentTimeMillis() - time);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            cam = cam.add(-50, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            cam = cam.add(50, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {

            cam = cam.add(0, 0, -50);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {

            cam = cam.add(0, 0, 50);
        }
    }

    static float max = 0f;


    private Vector3 normalVector(float x, float y) {

        x *= 2 * R;
        y *= 2 * R;

        ray = new Ray(new Vector3(x, -1000, y), new Vector3(0, 1000, 0));

        Intersector.intersectRaySphere(ray, new Vector3(400, 400, 400), R, intersection);
        intersection.sub(400, 400, 400);

        return intersection.cpy();

    }

    Ray ray = new Ray();
    Vector3 intersection = new Vector3();

    Vector3 cam = new Vector3(0, 1000, 0);

    private float angle(Vector3 a, Vector3 b) {

        float aNorm = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        float bNorm = (float) Math.sqrt(b.x * b.x + b.y * b.y + b.z * b.z);

        float angle = (float) (Math.acos(a.dot(b) / (aNorm * bNorm)) * MathUtils.radiansToDegrees);

        return angle;

    }


    private void println(Object o) {
        System.out.println(o);
    }

}
