package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Proyectil;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Brutus extends Maquina
{
    private Proyectil proyectil;
    private boolean colisionproyectil = false;
    
    
    public Brutus(Batch genesis) 
    {
        super(genesis);
        
        this.textura = new Texture(Gdx.files.internal("brutus.png"));
        this.fuerza *= 4;
        this.velocidad /= 2;
    }
    
    
    @Override
    public void actualizarEstado()
    {
        super.actualizarEstado();
        
        if(proyectil != null)
        { proyectil.actualizarEstado(); }
    }
    
    
    @Override
    public void agregarEnemigo(Jugador jugador)
    {
        this.enemigo = jugador;
        this.perseguir();
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
                    Gdx.app.log("INFO", "Has recibido 10 del proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                    this.colisionproyectil = true;
                }
            }
            else if(proyectil.overlaps(this))
            {
                if(!this.colisionproyectil)
                {
                    this.vida -= proyectil.getDureza();
                    Gdx.app.log("INFO", "La bola ha recibido 10 de su medicina. Le quedan " + vida + " vidas.");
                    this.colisionproyectil = true;
                }
            }
            else
            { this.colisionproyectil = false; }
        }
    }
    private void perseguir()
    {
        if(enemigo.getX() > this.x 
                && (enemigo.getY() == this.y 
                || (enemigo.getY() >= 800 - 98 && this.y >= 800 - 98) 
                || (enemigo.getY() <= 98 && this.y <= 98)))
        { decision = 1; }
        else if(enemigo.getX() < this.x 
                && (enemigo.getY() == this.y 
                || (enemigo.getY() >= 800 - 98 && this.y >= 800 - 98) 
                || (enemigo.getY() <= 98 && this.y <= 98)))
        { decision = 2; }
        
        else if(enemigo.getY() > this.y 
                && (enemigo.getX() == this.x 
                || (enemigo.getX() >= 1000 - 98 && this.x >= 1000 - 98) 
                || (enemigo.getX() <= 98 && this.x <= 98)))
        { decision = 3; }
        else if(enemigo.getY() < this.y 
                && (enemigo.getX() == this.x 
                || (enemigo.getX() >= 1000 - 98 && this.x >= 1000 - 98) 
                || (enemigo.getX() <= 98 && this.x <= 98)))
        { decision = 4; }
        
        else if(enemigo.getX() > this.x && enemigo.getY() > this.y)
        { decision = 5; }
        else if(enemigo.getX() > this.x && enemigo.getY() < this.y)
        { decision = 6; }
        
        else if(enemigo.getX() < this.x && enemigo.getY() > this.y)
        { decision = 7; }
        else if(enemigo.getX() < this.x && enemigo.getY() < this.y)
        { decision = 8; }
    }
    
    
    @Override
    public void controlBordes() 
    {   
        if(this.y < 0) 
        {
            this.y = 0;
            this.perseguir();
        }
        
        if(this.y > 800 - 100)
        {
            this.y = 800 - 100;
            this.perseguir();
        }
        
        if(this.x < 0)
        {
            this.x = 0;
            this.perseguir();
        }
        
        if(this.x > 1000 - 100) 
        {
            this.x = 1000 - 100;
            this.perseguir();
        }
    }

    
    @Override
    public void activarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("hunt.png"));
        enemigo.setVelocidad(enemigo.getVelocidad() / 2);
        proyectil = new Proyectil(genesis,50,50,enemigo.getX(),enemigo.getY());
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("brutus.png"));
        enemigo.setVelocidad(enemigo.getVelocidad() * 2);
        proyectil = null;
        this.perseguir();
    } 
}
