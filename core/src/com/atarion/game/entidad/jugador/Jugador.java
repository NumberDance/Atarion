package com.atarion.game.entidad.jugador;

import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.objeto.Objeto;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Jugador extends Entidad
{
    protected int vida = 100, fuerza = 10;
    
    protected float velocidad = 1f;
    protected Direccion direccion;
    
    private boolean colision = false;
    protected Jugador enemigo;
    
    protected float cronometro = 0f;
    protected int tiempoactivo = 5, activo = 5, tiemporecarga = 10, recarga = 0;
    protected boolean activado = false, parada = false;
    
    
    protected Jugador(Batch genesis)
    {
        this.x = 800 / 2 - 64 / 2;
        this.width = 80;
        this.height = 20;
        this.genesis = genesis;
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
    { return fuerza; }
    public int getVida() 
    { return vida; }
    public void setVida(int vida) 
    { this.vida = vida; }
    public Direccion getDireccion() 
    { return direccion; }
    public float getVelocidad() 
    { return velocidad; }
    public void setVelocidad(float velocidad) 
    { this.velocidad = velocidad; }
    public boolean isActivado() 
    { return activado; }   
}
