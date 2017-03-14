package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Iterator;
import java.util.LinkedList;

public class Claudius extends Maquina
{
    public Claudius(Batch genesis) 
    {
        super(genesis);
        this.velocidad *= 2;
        this.textura = new Texture(Gdx.files.internal("claudius.png"));
        this.tiempoactivo = 20;
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        this.enemigo = jugador;
    }
    
    public void esquivar(LinkedList<Escombro> escombros)
    {
        if(this.y >= 400)
        {
            this.decision = 4;
        }
        
        Iterator<Escombro> i = escombros.iterator();
        while(i.hasNext())
        {
            Escombro escombro = i.next();
            
            if(escombro.getX() == this.x
                    && escombro.getY() - this.y <= 150 )
            {
                decision = 1;
            }
            else if(escombro.getX() > this.x 
                    && escombro.getX() - this.x <= 150 
                    && escombro.getY() - this.y <= 150 ) 
            {
                decision = 2;
            }
            else if(escombro.getX() < this.x 
                    && this.x - escombro.getX() <= 150 
                    && escombro.getY() - this.y <= 150)
            {
                decision = 1;
            }
            else
            {
                decision = 0;
            }
        }
        
        if(enemigo.getY() == this.y || Math.abs(enemigo.getY() - this.y) < 90)
        {
            if(enemigo.getX() > this.x)
            {
                decision = 1;
            }
            else if(enemigo.getX() < this.x)
            {
                decision = 2;
            }
            else
            {
                decision = 0;
            }
        }
        else if(enemigo.getX() == this.x || Math.abs(enemigo.getX() - this.x) < 90)
        {
            decision = 4;
        }
    }
    
    @Override
    protected void controlBordes() 
    {
        if(this.y < 0) 
        {
            this.y = 800 - 20;
        }
        if(this.y > 800 - 20)
        {
            this.y = 0;
        }
        if(this.x < 0)
        {
            this.x = 1000 - 80;
        }
        if(this.x > 1000 - 80) 
        {
            this.x = 0;
        }
    }
    
    @Override
    public void activarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("endurance.png"));
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("claudius.png"));
    } 
    
    @Override
    public void setVida(int vida)
    {
        if(!this.activado)
        {
            super.setVida(vida);
        }
    }
}
