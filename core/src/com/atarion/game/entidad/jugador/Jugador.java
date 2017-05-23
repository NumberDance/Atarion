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
    protected boolean activado = false, parado = false, controlado = false;
    
    
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
        this.parado = true;
        this.desactivarEspecial();
    }
    
    
    public abstract void activarEspecial();
    public abstract void desactivarEspecial();
    

    public boolean isColision()
    { return colision; }
    public Jugador getEnemigo()
    { return enemigo; }
    public float getCronometro()
    { return cronometro; }
    public int getTiempoactivo()
    { return tiempoactivo; }
    public int getActivo()
    { return activo; }
    public int getTiemporecarga()
    { return tiemporecarga; }
    public boolean isParado()
    { return parado; }
    public int getRecarga()
    { return recarga; }
    public int getFuerza()
    { return fuerza; }
    public float getVelocidad()
    { return velocidad; }
    
    
    public void setVida(int vida)
    { this.vida = vida; }
    public void setFuerza(int fuerza)
    { this.fuerza = fuerza; }
    public void setVelocidad(float velocidad)
    { this.velocidad = velocidad; }
    public void setDireccion(Direccion direccion)
    { this.direccion = direccion; }
    public void setColision(boolean colision)
    { this.colision = colision; }
    public void setEnemigo(Jugador enemigo)
    { this.enemigo = enemigo; }
    public void setCronometro(float cronometro)
    { this.cronometro = cronometro; }
    public void setTiempoactivo(int tiempoactivo)
    { this.tiempoactivo = tiempoactivo; }
    public void setActivo(int activo)
    { this.activo = activo; }
    public void setTiemporecarga(int tiemporecarga)
    { this.tiemporecarga = tiemporecarga; }
    public void setRecarga(int recarga)
    { this.recarga = recarga; }
    public void setActivado(boolean activado)
    { this.activado = activado; }
    public void setParada(boolean parado)
    { this.parado = parado; }
    public boolean isControlado()
    { return controlado; }
    public void setControlado(boolean controlado)
    { this.controlado = controlado; }
}
