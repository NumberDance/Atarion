package com.atarion.game.interfaz.menu;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.servidor.Clase;
import com.atarion.game.interfaz.menu.analisis.AnalisisCrasus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class MenuSeleccion extends Menu
{
    private Escena escena;
    private Texture vista;
    private BitmapFont numeros;
    private Texture templar,fanatic,anarchist;
    private Texture traveler,merchant,visionary;
    private Texture guardian,benefactor,avenger;
    
    public MenuSeleccion(Escena escena,Texture vista) 
    {
        super();    
        tema = Gdx.audio.newMusic(Gdx.files.internal("grabbed.mp3"));
        this.escena = escena;
        this.vista = vista;
        
        this.numeros = new BitmapFont();
        this.numeros.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.numeros.getData().setScale(1.5f);
        
        this.templar = new Texture(Gdx.files.internal("templar.png"));
        this.fanatic = new Texture(Gdx.files.internal("fanatic.png"));
        this.anarchist = new Texture(Gdx.files.internal("anarchist.png"));
        this.traveler = new Texture(Gdx.files.internal("traveler.png"));
        this.merchant = new Texture(Gdx.files.internal("merchant.png"));
        this.visionary = new Texture(Gdx.files.internal("visionary.png"));
        this.guardian = new Texture(Gdx.files.internal("guardian.png"));
        this.benefactor = new Texture(Gdx.files.internal("benefactor.png"));
        this.avenger = new Texture(Gdx.files.internal("avenger.png"));
    }
    
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        genesis.begin();
        
        this.numeros.draw(genesis,"ELIGE LA MENTE DE COMBATE",20,750);
        
        genesis.draw(this.templar,150,150);
        this.numeros.draw(genesis,"1 Templar",20,170);
        genesis.draw(this.fanatic,150,100);
        this.numeros.draw(genesis,"2 Fanatic",20,120);
        genesis.draw(this.anarchist,150,50);
        this.numeros.draw(genesis,"3 Anarchist",20,70);
        
        genesis.draw(this.traveler,400,150);
        this.numeros.draw(genesis,"4 Traveler",250,170);
        genesis.draw(this.merchant,400,100);
        this.numeros.draw(genesis,"5 Merchant",250,120);
        genesis.draw(this.visionary,400,50);
        this.numeros.draw(genesis,"6 Visionary",250,70);
        
        genesis.draw(this.guardian,670,150);
        this.numeros.draw(genesis,"7 Guardian",520,170);
        genesis.draw(this.benefactor,670,100);
        this.numeros.draw(genesis,"8 Benefactor",520,120);
        genesis.draw(this.avenger,670,50);
        this.numeros.draw(genesis,"9 Avenger",520,70);
        
        if(this.vista != null)
        { genesis.draw(this.vista,20,220); }
        
        genesis.end();
    }
    @Override
    protected void controlarTeclado()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
        { escena.entrar(Clase.TEMPLAR); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) 
        { escena.entrar(Clase.FANATIC); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) 
        { escena.entrar(Clase.ANARCHIST); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) 
        { escena.entrar(Clase.TRAVELER); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) 
        { escena.entrar(Clase.MERCHANT); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) 
        { escena.entrar(Clase.VISIONARY); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) 
        { escena.entrar(Clase.GUARDIAN); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) 
        { escena.entrar(Clase.BENEFACTOR); }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) 
        { escena.entrar(Clase.AVENGER); }
    }  
}
