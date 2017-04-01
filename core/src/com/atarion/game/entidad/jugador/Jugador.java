package com.atarion.game.entidad.jugador;

import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.objeto.Objeto;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Jugador extends Entidad
{
    protected int vida;
    protected int fuerza;
    protected float velocidad;
    private boolean colision;
    protected Jugador enemigo;
    protected Direccion direccion;
    
    protected int tiempoactivo = 5;
    protected int activo = 5;
    protected int tiemporecarga = 10;
    protected int recarga = 0;
    protected boolean activado = false;
    protected float cronometro = 0f;
    protected boolean parada = false;
    
    protected Jugador(Batch genesis)
    {
        this.x = 800 / 2 - 64 / 2;
        this.width = 80;
        this.height = 20;
        
        this.genesis = genesis;
        
        this.vida = 100;
        this.fuerza = 10;
        this.velocidad = 1f;
        this.colision = false;
    }
    
    public abstract void agregarEnemigo(Jugador jugador);
    protected void jugar(Camera camara)
    {
        this.controlEspecial();
        this.controlBordes();
    }
    
    protected abstract void colisionObjeto(Objeto objeto);
    protected abstract void colisionJugador(Jugador jugador);
    
    protected abstract void controlBordes();
    protected abstract void controlEspecial();
    public void pararEspecial()
    {
        this.parada = true;
        this.desactivarEspecial();
    }
    
    public abstract void activarEspecial();
    public abstract void desactivarEspecial();
    
    public int getFuerza() 
    {
        return fuerza;
    }
    public int getVida() 
    {
        return vida;
    }
    public void setVida(int vida) 
    {
        this.vida = vida;
    }
    public Direccion getDireccion() 
    {
        return direccion;
    }
    public float getVelocidad() 
    {
        return velocidad;
    }
    public void setVelocidad(float velocidad) 
    {
        this.velocidad = velocidad;
    }
    public boolean isActivado() 
    {
        return activado;
    }   
}
