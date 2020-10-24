package com.atarion.game.interfaz;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Interfaz implements Screen {

    protected OrthographicCamera camara = null;
    protected SpriteBatch genesis = null;
    protected Music tema = null;
    protected Interfaz control = null;
    protected Texture fondo = new Texture(Gdx.files.internal("fondo.jpg"));
    protected int cameraWidth = Gdx.graphics.getWidth(),
            cameraHeight = Gdx.graphics.getHeight();

    @Override
    public void show() {
        if (control == null || control == this) {
            Gdx.app.setLogLevel(Application.LOG_INFO);

            camara = new OrthographicCamera();
            camara.setToOrtho(false, this.cameraWidth, this.cameraHeight);

            genesis = new SpriteBatch();

            control = this;
        }
    }

    public void musica(String ruta) {
        this.tema = Gdx.audio.newMusic(Gdx.files.internal(ruta));

        if (tema != null) {
            tema.setLooping(true);
            tema.play();
        }
    }

    @Override
    public void hide() {
        if (control.equals(this)) {
            if (tema != null) {
                tema.stop();
            }

            control = null;
        }
    }

    @Override
    public void render(float delta) {
        camara.update();
        genesis.setProjectionMatrix(camara.combined);

        genesis.begin();
        genesis.draw(fondo, 0, 0, this.cameraWidth, this.cameraHeight);
        genesis.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        genesis.dispose();

        if (tema != null) {
            tema.dispose();
        }
    }
}
