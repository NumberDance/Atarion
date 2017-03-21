package com.atarion.game;

import com.atarion.game.escena.Escena;
import com.atarion.game.escena.EscenaBrutus;
import com.atarion.game.escena.analisis.AnalisisBrutus;
import com.atarion.game.escena.analisis.AnalisisClaudius;
import com.atarion.game.escena.analisis.AnalisisCrasus;
import com.atarion.game.escena.EscenaClaudius;
import com.atarion.game.escena.EscenaCrasus;
import com.atarion.game.escena.menu.MenuPrincipal;
import com.atarion.game.escena.menu.MenuVictoria;
import com.atarion.game.escena.menu.MenuDerrota;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;

public class Atarion extends Game
{
    private boolean seleccionado = false;
    private boolean jugando = false;
    
    private char batalla = ' ';
    private Music tema;
    private Escena escena;
    
    @Override
    public void create() 
    {   
        this.setScreen(new MenuPrincipal());
    }
    
    @Override
    public void render()
    {
        super.render();
        
        if(jugando)
        {
            if(escena.getEstado() == 0)
            {
                this.setScreen(new MenuVictoria());
                jugando = false;
                seleccionado = false;
                tema.stop();
                tema = Gdx.audio.newMusic(Gdx.files.internal("victory.mp3"));
                tema.play();
            }
            else if(escena.getEstado() == 1)
            {
                this.setScreen(new MenuDerrota());
                jugando = false;
                seleccionado = false;
                tema.stop();
            }
        }
        else
        {
            if(seleccionado)
            {
                if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) 
                {
                    switch(batalla)
                    {
                        case 'A':
                            escena = new EscenaCrasus();
                            this.setScreen(escena);
                        break;
                        case 'B':
                            escena = new EscenaBrutus();
                            this.setScreen(escena);
                        break;
                        case 'C':
                            escena = new EscenaClaudius();
                            this.setScreen(escena);
                    }
                
                    jugando = true;
                }
            }
            else
            {
                if(Gdx.input.isKeyPressed(Input.Keys.A)) 
                {
                    batalla = 'A';
                    this.setScreen(new AnalisisCrasus());
                    seleccionado = true;
                    tema = Gdx.audio.newMusic(Gdx.files.internal("zombies.mp3"));
                    tema.setLooping(true);
                    tema.play();
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.B))
                {
                    batalla = 'B';
                    this.setScreen(new AnalisisBrutus());
                    seleccionado = true;
                    tema = Gdx.audio.newMusic(Gdx.files.internal("commodus.mp3"));
                    tema.setLooping(true);
                    tema.play();
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.C))
                {
                    batalla = 'C';
                    this.setScreen(new AnalisisClaudius());
                    seleccionado = true;
                    tema = Gdx.audio.newMusic(Gdx.files.internal("finale.mp3"));
                    tema.setLooping(true);
                    tema.play();
                }
            }
        }
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
    }
}
