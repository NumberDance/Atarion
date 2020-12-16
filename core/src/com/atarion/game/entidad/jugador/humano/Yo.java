/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Yo extends Templar {

    public Yo(boolean tu, boolean batalla) {
        super(tu, batalla);
        this.textura = new Texture(Gdx.files.internal("me.png"));
    }
}
