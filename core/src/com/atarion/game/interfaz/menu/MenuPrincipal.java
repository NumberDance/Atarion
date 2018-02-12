package com.atarion.game.interfaz.menu;


import com.atarion.game.Atarion;
import com.atarion.game.interfaz.escena.online.cliente.EscenaCliente;
import com.atarion.game.interfaz.escena.tutorial.TutorialTeclas;
import com.atarion.game.interfaz.menu.analisis.AnalisisBrutus;
import com.atarion.game.interfaz.menu.analisis.AnalisisClaudius;
import com.atarion.game.interfaz.menu.analisis.AnalisisCrasus;
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
    }
    
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        
        texto = new BitmapFont();
        
        genesis.begin();
        
        genesis.draw(logo, 300, 650);
        texto.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        texto.getData().setScale(2f);
        texto.draw(genesis, "A: Batalla de fuerza. \nB: Batalla de velocidad.\nC: Batalla de resistencia. \nX: Tutorial.", 370, 550);
        
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
        { Atarion.getInstance().setScreen(new AnalisisCrasus()); }
        else if(Gdx.input.isKeyPressed(Input.Keys.B))
        { Atarion.getInstance().setScreen(new AnalisisBrutus()); }
        else if(Gdx.input.isKeyPressed(Input.Keys.C))
        { Atarion.getInstance().setScreen(new AnalisisClaudius()); }
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
        { Atarion.getInstance().setScreen(new EscenaCliente()); }
        else if(Gdx.input.isKeyPressed(Input.Keys.X))
        { Atarion.getInstance().setScreen(new TutorialTeclas()); }
    }
}
