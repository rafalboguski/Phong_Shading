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

    // phong model


    private float Ia = 1f;
    private float Ka = 0.1f;

    private float Ii = 1f;

    private float Kd = 0.4f;

    private int N = 50;
    private float Ks = 0.5f;


    private float Ambient() {

        return Ia * Ka;
    }

    private float Diffuse(Vector3 reflected, Vector3 normal) {

        float beta = angle(reflected, normal);

        float Id = Kd * Ii * MathUtils.cosDeg(beta);

        return (Id > 0) ? Id : 0;
    }

    private float Spectacular(ShapeRenderer s, Vector3 normal, Vector3 position, Vector3 reflected) {


        Vector3 V = new Vector3(400, -1000, 400).sub(position);

        float alfa = angle(V.cpy(), reflected.cpy());

        if (N % 2 == 0)
            N = (N + 1);

        // funkcja wygaszania odlelosci
        float F = 1;
//        float F = (float) ((Math.pow(position.dst(lightPos),2)/100000000));
//        if(F>1)
//            F=1;
//        F=1f-F;

        float I = (float) (Ks * Ii * F * Math.pow(MathUtils.cosDeg(alfa), N));


        return (I > 0) ? I : 0;
    }


    public void draw2D(ShapeRenderer s) {

        //long time = System.currentTimeMillis();
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {
                if (normalVectors[i][j] != null) {

                    Vector3 normal = normalVectors[i][j].cpy();
                    Vector3 pos = normal.cpy().add(400, 400, 400);
                    Vector3 reflected = reflect(
                            lightPos.cpy().sub(pos),
                            normal.cpy()
                    );


                    float alpha = Ambient() + Diffuse(reflected,normal) + Spectacular(s, normal.cpy(), pos.cpy(), reflected.cpy());

                    if (alpha > 1)
                        alpha = 1;

                    s.setColor(alpha, alpha, alpha, 1);

                    s.rect(j, i, 1, 1);

                }
            }

        }
        s.setColor(Color.RED);
        s.circle(400 + 400 / 30f, 400 + 400 / 30f, 5);
        s.circle(lightPos.x / 30f + 400, +lightPos.y / 30f + 400, 5);


        //println(System.currentTimeMillis() - time);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            lightPos = lightPos.add(-moveSpeed, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            lightPos = lightPos.add(moveSpeed, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {

            lightPos = lightPos.add(0, 0, -moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {

            lightPos = lightPos.add(0, 0, moveSpeed);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {

            lightPos = lightPos.add(0, -moveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {

            lightPos = lightPos.add(0, moveSpeed, 0);
        }


    }


    private int moveSpeed = -200;

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

    Vector3 lightPos = new Vector3(400, -1000, 400);


    private Vector3 reflect(Vector3 vec, Vector3 normal) {
        normal.nor();
        return normal.scl((float) (normal.dot(vec) * 2)).sub(vec);
    }


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
