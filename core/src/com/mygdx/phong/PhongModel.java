package com.mygdx.phong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

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


}
