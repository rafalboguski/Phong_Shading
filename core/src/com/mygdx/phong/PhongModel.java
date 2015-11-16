package com.mygdx.phong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.xpath.internal.operations.String;

import static com.mygdx.phong.VectorMath.angle;


public class PhongModel {

    private static float Ia = 1f;
    private static float Ka = 0.1f;

    private static float Ii = 1f;

    private static float Kd = 0.4f;

    private static int N = 50;
    private static float Ks = 0.5f;


    public static float Ambient() {

        return Ia * Ka;
    }

    public static float Diffuse(Vector3 reflected, Vector3 normal) {

        float beta = angle(reflected, normal);

        float Id = Kd * Ii * MathUtils.cosDeg(beta);

        return (Id > 0) ? Id : 0;
    }

    public static float Spectacular(ShapeRenderer s, Vector3 normal, Vector3 position, Vector3 reflected) {


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


    public static void update(){

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            N+=2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            N-=2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            Ka+=0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            Ka-=0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            Kd+=0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            Kd-=0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
            Ks+=0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            Ks-=0.01f;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            Ia+=0.1f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            Ia-=0.1f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            Ii+=0.1f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            Ii-=0.1f;
        }

        if (N < 0)
            N = 0;
        if (Ka < 0)
            Ka = 0;
        if (Kd < 0)
            Kd = 0;
        if (Ks < 0)
            Ks = 0;

        if (Ka > 1)
            Ka = 1;
        if (Kd > 1)
            Kd = 1;
        if (Ks > 1)
            Ks = 1;

        if (Ia < 0)
            Ia = 0;
        if (Ii < 0)
            Ii = 0;
    }

    public static void drawInfo(SpriteBatch b, BitmapFont font, int W, int H) {


        font.draw(b,"N = "+N+"   Ka = "+Ka+"   Kd = "+Kd+"   Ks = "+Ks+"      Ia = "+Ia+"   Ii = "+Ii+"   Keys   R->O   F->L"

                ,100,H-10);
    }
}
