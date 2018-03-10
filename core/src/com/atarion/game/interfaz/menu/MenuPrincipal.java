package com.atarion.game.interfaz.menu;


import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.interfaz.escena.EscenaBrutus;
import com.atarion.game.interfaz.escena.EscenaClaudius;
import com.atarion.game.interfaz.escena.EscenaCrasus;
import com.atarion.game.interfaz.escena.online.cliente.EscenaCliente;
import com.atarion.game.interfaz.escena.tutorial.TutorialTeclas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class MenuPrincipal extends Menu
{
    private Texture logo;
    private BitmapFont texto;

    
    public MenuPrincipal() 
    {
        super();
        
        tema = null;//Gdx.audio.newMusic(Gdx.files.internal("exploring.mp3"));
        //tema.setLooping(true);
                
        logo = new Texture(Gdx.files.internal("logo.png"));
        texto = new BitmapFont();
    }
    
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        genesis.begin();
        
        genesis.draw(logo, 300, 650);
        texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        texto.getData().setScale(2f);
        
        StringBuilder menu = new StringBuilder();
        menu.append("A -> Campaña: Region Occipital.").append("\n");
        menu.append("B -> Campaña: Región Central.").append("\n");
        menu.append("C -> Campaña: Región Parietal.").append("\n");
        menu.append("D -> PVP online.").append("\n");
        menu.append("X -> Tutorial.").append("\n");
        
        texto.draw(genesis,menu.toString(),300,550);
        
        genesis.end();
    }
    
    
    @Override
    public void dispose()
    {
        logo.dispose();
        texto.dispose();
    }

    
    @Override
    protected void controlarTeclado() 
    {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) 
        {
            Texture analisis = new Texture(Gdx.files.internal("crasusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaCrasus(),analisis)); 
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.B))
        {
            Texture analisis = new Texture(Gdx.files.internal("brutusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaBrutus(),analisis)); 
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C))
        {
            Texture analisis = new Texture(Gdx.files.internal("claudiusanalysis.png"));
            Atarion.getInstance().setScreen(new MenuSeleccion(new EscenaClaudius(),analisis)); 
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
        { 
            Texture analisis = new Texture(Gdx.files.internal("logo.png"));
            /*Atarion.getInstance().setScreen(new MenuSeleccion(*/new EscenaCliente().entrar(ClaseHumano.TRAVELER);/*,analisis));*/
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.X))
        { new TutorialTeclas().entrar(ClaseHumano.DUMMYGENERIC); }
    }
}
