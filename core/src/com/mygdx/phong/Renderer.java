package com.mygdx.phong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import java.awt.*;
import java.util.Random;

public class Renderer extends ApplicationAdapter {

    @Override
    public void create() {

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        s = new ShapeRenderer();
        b = new SpriteBatch();
        c = new OrthographicCamera(W, H);
        c.position.set(W / 2f, H / 2f, 0);
        c.zoom = 1f;
        c.update();

        sphere = new Sphere();
        font = new BitmapFont();
    }

    int W;
    int H;

    ShapeRenderer s;
    SpriteBatch b;
    OrthographicCamera c;

    Sphere sphere;

    BitmapFont font;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.05f, 0.05f,0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        c.update();
        sphere.update();
        PhongModel.update();

        s.setColor(Color.WHITE);
        s.setProjectionMatrix(c.combined);
        s.begin(ShapeRenderer.ShapeType.Filled);

        sphere.draw2D(s);

        s.end();

        b.begin();
        font.setColor(Color.RED);

        font.draw(b, "FPS:" + Gdx.graphics.getFramesPerSecond(), 6, H-10);

        PhongModel.drawInfo(b, font,W,H);

        b.end();


    }
}
