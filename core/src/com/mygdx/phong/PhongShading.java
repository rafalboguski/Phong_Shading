package com.mygdx.phong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import java.util.Random;

public class PhongShading extends ApplicationAdapter {

    @Override
    public void create() {

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        s = new ShapeRenderer();

        c = new OrthographicCamera(W, H);
        c.position.set(W / 2f, H / 2f, 0);
        c.zoom = 1.2f;
        c.update();
        sphere = new Sphere();

    }

    int W;
    int H;

    ShapeRenderer s;
    OrthographicCamera c;

    Sphere sphere;


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        c.update();

        s.setColor(Color.WHITE);
        s.setProjectionMatrix(c.combined);
        s.begin(ShapeRenderer.ShapeType.Point);

        sphere.draw2D(s);


        s.end();


    }
}
