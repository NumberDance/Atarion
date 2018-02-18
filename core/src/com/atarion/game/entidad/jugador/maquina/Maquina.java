package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Maquina extends Jugador
{
    protected int decision = 0;
    
    
    public Maquina(Batch genesis) 
    {
        super(genesis);
        this.y = 600;
        velocidad = 1.5f;
        this.tiemporecarga = 20;
        
        this.width = 100;
        this.height = 100;
    }
    
    
    @Override
    public void jugar(Camera camara) 
    {  
        super.jugar(camara);
        
        if(!controlado)
        { this.controlMovimiento();}
    }
    
    
    @Override
    protected final void controlMovimiento()
    {
        switch(decision)
        {
            case 0:
                this.direccion = Direccion.PARADO;
            break;
            case 1:
                this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.DERECHA;
            break;
            case 2:
                this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.IZQUIERDA;
            break;
            case 3:
                this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.ARRIBA;
            break;
            case 4:
                this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.ABAJO;
            break;
            case 5:
                this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.LATERAL_DERECHO_ARRIBA;
            break;
            case 6:
                this.x += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.LATERAL_DERECHO_ABAJO;
            break;
            case 7:
                this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.y += 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.LATERAL_IZQUIERDO_ARRIBA;
            break;
            case 8:
                this.x -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.y -= 200 * Gdx.graphics.getDeltaTime() * this.velocidad;
                this.direccion = Direccion.LATERAL_IZQUIERDO_ABAJO;
            break;
        }
    }
    @Override
    protected abstract void controlBordes();
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
                    activado = false;
                }
                else if(parado)
                {
                    this.desactivarEspecial();
                    this.parado = false;
                    activado = false;
                }
                else
                { activo--; } 
            }
            
            if(recarga > 0)
            { recarga--; }
            else if(recarga == 0 && !controlado)
            {
                activarEspecial();
                recarga = tiemporecarga;
                activado = true;
            }
            
            cronometro = 0.0f;
        }
    }
    
    
    @Override
    public void colisionObjeto(Objeto objeto) 
    {}
    @Override
    public void colisionJugador(Jugador objeto) 
    {}
    
    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo("decision","" + this.decision,ParteMensaje.CUERPO); }
}
