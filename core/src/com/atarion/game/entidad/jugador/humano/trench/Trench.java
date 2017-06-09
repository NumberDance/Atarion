package com.atarion.game.entidad.jugador.humano.trench;

import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.objeto.ProyectilTrench;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Trench extends Humano
{   
    protected ProyectilTrench proyectil = null;
    protected boolean colisionproyectil = false;
    
    public Trench(Batch genesis,boolean tu)
    {
        super(genesis,tu);
        
        this.vida *= 2;
        this.velocidad /= 2;
        this.inmune = true;
    }
    
    @Override
    public void actualizarEstado()
    {
        super.actualizarEstado();
        
        if(proyectil != null)
        { proyectil.actualizarEstado(); }
    }
    @Override
    public void jugar(Camera camara)
    {
        super.jugar(camara);
        
        if(proyectil != null)
        {
            proyectil.lanzar();
            
            if(proyectil.overlaps(enemigo))
            {
                if(!this.colisionproyectil)
                {
                    enemigo.setVida(enemigo.getVida() - proyectil.getDureza());
                    Gdx.app.log("INFO", "El enemigo ha recibido 5 del proyectil. Le quedan " + enemigo.getVida() + " vidas.");
                    this.colisionproyectil = true;
                }
            }
            else if(proyectil.overlaps(this))
            {
                if(!this.colisionproyectil)
                {
                    enemigo.setVida(enemigo.getVida() - proyectil.getDureza());
                    Gdx.app.log("INFO", "Te has dado con tu proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                    this.colisionproyectil = true;
                }
            }
            else
            { this.colisionproyectil = false; }
            
            if(proyectil.isDestino())
            { proyectil = null; }
        }
    }
    @Override
    public void controlEspecial()
    {
        super.controlEspecial();
        
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT) && !controlado)
        {
            boolean disparar = true;
            
            float origenx = this.x + 15, origeny = this.y - 15;
            float destinox = 0,destinoy = 0;
            
            switch(this.direccion)
            {
                case ARRIBA:
                    origeny += 70;  
                    
                    destinox = this.x + 15;
                    destinoy = 800;
                break;
                case ABAJO:
                    origeny -= 70; 
                    
                    destinox = this.x + 15;
                    destinoy = 0;
                break;
                case DERECHA:
                    origenx += 100;
                    
                    destinox = 1000;
                    destinoy = this.y - 15; 
                break;
                case IZQUIERDA:
                    origenx -= 100;
                                        
                    destinox = 0;
                    destinoy = this.y - 15;
                break;
                default:
                    disparar = false;
                break;
            }
            
            if(disparar)
            { proyectil = new ProyectilTrench(genesis,origenx,origeny,destinox,destinoy); }
        }
    }
    
    @Override
    public void colisionObjeto(Objeto objeto)
    {
        if(this.inmune)
        { Gdx.app.log("INFO","Las barreras de la clase Trench pueden atravesar objetos."); }
        else
        {
            Gdx.app.log("INFO","De repente, te hiciste vulnerable a los objetos. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
            this.vida -= objeto.getDureza();
        }
    }
    
    
    @Override
    public abstract void activarEspecial();
    @Override
    public abstract void desactivarEspecial();
}
