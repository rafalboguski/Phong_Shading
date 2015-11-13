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

        this.R = 100;
        this.center = new Vector3(400, 400, 400);

        populateNormalVectors();

    }

    public Vector3 center;
    public float R;


    public void draw2D(ShapeRenderer s) {

        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {
                if (normalVectors[i][j] != null) {

                    Vector3 normal = normalVectors[i][j].cpy();
                    Vector3 pos = normal.cpy().add(400, 400, 400);
                    Vector3 reflected = VectorMath.reflect(
                            lightPos.cpy().sub(pos),
                            normal.cpy()
                    );


                    float alpha = PhongModel.Ambient()
                            + PhongModel.Diffuse(reflected, normal)
                            + PhongModel.Spectacular(s, normal.cpy(), pos.cpy(), reflected.cpy());

                    if (alpha > 1)
                        alpha = 1;

                    else
                    s.setColor(alpha, 0, 0, 1);
                    s.rect(j, i, 1, 1);
                }
            }
        }
        s.setColor(Color.RED);
        Vector2 map = new Vector2(lightPos.x / 30f + 40, +lightPos.y / 30f + 40)
                .sub(40 + 400 / 30f, 40 + 400 / 30f)
                .nor().scl(40)
                .add(40 + 400 / 30f, 40 + 400 / 30f);

        s.circle(40 + 400 / 30f, 40 + 400 / 30f, 5);
        s.circle(lightPos.x / 30f + 40, +lightPos.y / 30f + 40, 5);

        lightPos = StoC(new Vector3(600,0,an+=15)).add(center);

        bn++;

    }

    boolean b = false;
    float an = 0;
    float bn = 0;

    public void update(){
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


    private Vector3 normalVectors[][] = new Vector3[800][800];
    Ray ray = new Ray();
    Vector3 intersection = new Vector3();

    // calculates normal vectors for each pixel, those are constant so can be pre baked
    public void populateNormalVectors() {
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 800; j++) {

                float dst = Vector2.dst(j, i, center.x, center.y);

                if (dst < R) {
                    // todo: remove new
                    ray = new Ray(new Vector3(j, -1000, i), new Vector3(0, 1, 0));
                    Intersector.intersectRaySphere(ray, center, R, intersection);
                    normalVectors[i][j] = intersection.sub(center).cpy();

                } else {
                    normalVectors[i][j] = null;
                }
            }
        }
    }


    Vector3 lightPos = new Vector3(400, -1000, 400);


    public Vector3 StoC(Vector3 p) {

        float x = p.x * sin(p.y - 90) * cos(p.z);
        float y = p.x * sin(p.y - 90) * sin(p.z);
        float z = p.x * cos(p.y - 90);

        return new Vector3(-x, -y,z);
    }

    private float sin(float dec){
        return MathUtils.sinDeg(dec);
    }
    private float cos(float dec){
        return MathUtils.cosDeg(dec);
    }

}
