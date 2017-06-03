package com.atarion.game.entidad.jugador.humano;

import com.atarion.game.entidad.habilidad.Habilidad;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Humano extends Jugador
{    
    private boolean invertido = false;
    protected Habilidad habilidad = null;

    
    public Humano(Batch genesis)
    {
        super(genesis);
        this.y = 30;
    }
    
    
    @Override
    public void actualizarEstado()
    {
       super.actualizarEstado();
        
       if(habilidad != null)
       { habilidad.actualizarEstado(); }
    }
    
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    { enemigo = jugador; }
    @Override
    public void jugar(Camera camara) 
    {   
        super.jugar(camara);
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && !controlado) 
        {
            if(invertido)
            {
                this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.ABAJO;
            }
            else
            {
                this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.ARRIBA;
                
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_DERECHO_ARRIBA;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_IZQUIERDO_ARRIBA;
                }
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && !controlado)
        {
            if(invertido)
            {
                this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.ARRIBA;
            }
            else
            {
                this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.ABAJO;
                
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_DERECHO_ABAJO;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_IZQUIERDO_ABAJO;
                }
            }
        }      
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !controlado)
        {
            if(invertido)
            {
                this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.IZQUIERDA;
            }
            else
            {
                this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.DERECHA;
            
                if(Gdx.input.isKeyPressed(Input.Keys.UP))
                {
                    this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_DERECHO_ARRIBA;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                {
                    this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_DERECHO_ABAJO;
                }
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !controlado) 
        {
            if(invertido)
            {
                this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.DERECHA;
            }
            else
            {
                this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                direccion = Direccion.IZQUIERDA;
            
                if(Gdx.input.isKeyPressed(Input.Keys.UP))
                {
                    this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_IZQUIERDO_ARRIBA;
                }
                else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                {
                    this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                    direccion = Direccion.LATERAL_IZQUIERDO_ABAJO;
                }
            }
        }
        else
        { direccion = Direccion.PARADO; }
    }   
    @Override
    protected void controlEspecial()
    {
        cronometro += Gdx.graphics.getDeltaTime();
        if(cronometro >= 1.0f)
        {
            if(activado)
            {
                if(activo == 0)
                {
                    this.desactivarEspecial();
                    this.activo = this.tiempoactivo;
                    Gdx.app.log("INFO","El modulo se agoto.");
                    activado = false;
                }
                else if(parado)
                {
                    this.desactivarEspecial();
                    
                    this.parado = false;
                    activado = false;
                }
                else
                {
                    activo--;
                    Gdx.app.log("INFO","El modulo se agotara en " + tiempoactivo + " segundos.");
                } 
            }
            
            if(recarga > 0)
            {
                recarga--;
                Gdx.app.log("INFO","El modulo estara disponible en " + recarga + " segundos.");
            }
            else
            { Gdx.app.log("INFO","El modulo ya esta disponible."); }
            
            cronometro = 0.0f;
        }
           
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !controlado)
        {
            if(recarga == 0)
            {
                activarEspecial();
                
                recarga = tiemporecarga;
                activado = true;
            }
        }
    }
    protected void controlBordes()
    {
        if(this.y < 0) 
        { this.y = 0; }
        
        if(this.y > 800 - 20)
        { this.y = 800 - 20; }
        
        if(this.x < 0)
        { this.x = 0; }
        
        if(this.x > 1000 - 80) 
        { this.x = 1000 - 80; }
    }
    
    
    @Override
    public void colisionObjeto(Objeto objeto) 
    {}
    @Override
    public void colisionJugador(Jugador jugador) 
    {
        vida -= jugador.getFuerza();
        
        Gdx.app.log
        (
            "INFO",
            "El jugador te ha hecho " 
            + jugador.getFuerza() 
            + " puntos de daño. Te quedan " 
            + vida 
            + " vidas."
        );
    }

    
    public void setInvertido(boolean invertido) 
    { this.invertido = invertido; }
}
