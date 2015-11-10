package com.mygdx.phong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;


public class Pixel {


    public Vector3 normalVector;

    public Color color = new Color(0,0,0,1);


    public Pixel(Vector3 normalVector, Color color) {
        this.normalVector = normalVector;
        this.color = color;
    }
}
