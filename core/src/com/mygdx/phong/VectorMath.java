package com.mygdx.phong;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;


public class VectorMath {

    // Odbija wektor vec symetrycznie do wektora normalnego
    public static Vector3 reflect(Vector3 vec, Vector3 normal) {
        normal.nor();
        return normal.scl((float) (normal.dot(vec) * 2)).sub(vec);
    }


    // Kąt pomiędzy dwoma wektorami
    public static float angle(Vector3 a, Vector3 b) {

        float aNorm = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        float bNorm = (float) Math.sqrt(b.x * b.x + b.y * b.y + b.z * b.z);

        float angle = (float) (Math.acos(a.dot(b) / (aNorm * bNorm)) * MathUtils.radiansToDegrees);

        return angle;

    }

}
