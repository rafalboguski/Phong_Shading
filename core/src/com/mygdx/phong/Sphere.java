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

        this.R = 400;
        this.pos2D = new Vector2(400, 400);

    }

    public Vector2 pos2D;
    public float R;


    public void draw2D(ShapeRenderer s) {


        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {
                float dst = new Vector2(j, i).dst(pos2D);
                if (dst < R) {
                    float alpha = calc(j / 800f, i / 800f);
                    if (alpha > max)
                        max = alpha;

                    s.setColor(new Color(alpha, alpha, alpha, 1));
                    s.point(j, i, 0);

                }
               // println(max);
            }

        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            println("sdf");
            cam = cam.add(-10,0,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            println("sdf");
            cam = cam.add(10,0,0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            println("sdf");
            cam = cam.add(0,-10,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            println("sdf");
            cam = cam.add(0,10,0);
        }
    }

    static float max = 0f;

    private float calc(float x, float y) {
        com.badlogic.gdx.math.collision.Sphere sp = new com.badlogic.gdx.math.collision.Sphere(new Vector3(0, 0, 0), R);

        x *= 800;
        y *= 800;

        Vector3 intersection = new Vector3();
        Ray ray = new Ray(new Vector3(x, -10000, y), new Vector3(0, 1000, 0));


        ray.getEndPoint(intersection, 100);

        Intersector.intersectRaySphere(ray, new Vector3(400, 400, 400), 400, intersection);
        intersection.sub(400, 400, 400);

        //Vector3 cam = new Vector3(1000, 1000, 0);
        return angle(cam,intersection)/180f;

        //return intersection.len() / 1043.5583f;


    }

    Vector3 cam = new Vector3(0, 1000, 0);

    private float angle(Vector3 a, Vector3 b) {

        double aNorm = Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        double bNorm = Math.sqrt(b.x * b.x + b.y * b.y + b.z * b.z);

        double angle = Math.acos(a.dot(b) / (aNorm * bNorm)) * MathUtils.radiansToDegrees;

        return (float) angle;

    }


    private void println(Object o) {
        System.out.println(o);
    }

}
