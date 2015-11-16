package com.mygdx.phong;

import com.badlogic.gdx.math.FloatCounter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.*;


public class VectorMath {

    // Odbija wektor vec symetrycznie do wektora normalnego
    public static Vector3 reflect(Vector3 vec, Vector3 normal) {
        normal.nor();
        return normal.scl(normal.dot(vec) * 2).sub(vec);
    }


    // Kąt pomiędzy dwoma wektorami
    public static float angle(Vector3 a, Vector3 b) {

        float aNorm = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        float bNorm = (float) Math.sqrt(b.x * b.x + b.y * b.y + b.z * b.z);

        float angle = (bakedAcos(a.dot(b) / (aNorm * bNorm)) * MathUtils.radiansToDegrees);

        return angle;

    }


    private static float bakedAcos(float value){

        int vInterpolated = (int)(value*1000);
        Float angle = backedCos.get(vInterpolated);

        if(angle != null){
            return angle;
        }
        else
        {
            angle = (float)Math.acos(value);
            backedCos.put(vInterpolated,angle);
            return angle;
        }

    }

    private static HashMap<Integer, Float> backedCos = new HashMap<Integer, Float>() ;

}
