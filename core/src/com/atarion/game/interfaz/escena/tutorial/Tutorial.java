package com.atarion.game.interfaz.escena.tutorial;


import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.maquina.Dummy;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public abstract class Tutorial extends Escena
{
    protected Texture insignia = null;
    protected BitmapFont titulo = new BitmapFont(), descripcion = new BitmapFont(), siguiente = new BitmapFont();
    protected String textotitulo = null, textodescripcion = null, textosiguiente = "PULSA  ENTER  PARA  SALTAR  A  LA  SIGUIENTE  CLASE";
    
    protected Tutorial()
    {
        super();
        this.batalla = true;
        
        titulo.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        titulo.getData().setScale(2f);
        
        descripcion.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        descripcion.getData().setScale(1f);
        
        siguiente.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        siguiente.getData().setScale(1f);
    }
    
    
    @Override
    public void entrar(ClaseHumano clase)
    {
        this.maquinasEnemigas.add(new Dummy(true));
        super.entrar(clase);
    }
    
    
    @Override
    public void render(float delta)
    {
        super.render(delta);
        this.controlarTeclado();

        genesis.begin();
        
        if(insignia != null)
        { genesis.draw(this.insignia,50,700); }
        
        titulo.draw(genesis,this.textotitulo,150,760);
        descripcion.draw(genesis,this.textodescripcion,50,700);
        siguiente.draw(genesis,this.textosiguiente,50,650);
        
        genesis.end();
    }
    public abstract void controlarTeclado();
}
