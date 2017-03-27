package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.Guardian;
import com.atarion.game.entidad.objeto.Laser;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Iterator;
import java.util.LinkedList;

public class Claudius extends Maquina
{
    private Laser laser = null;
    private boolean colisionlaser = false;
    private boolean carga = false;
    
    private enum TipoLaser 
    {
        ESCOMBROS,
        BORDES,
        EL
    }
    private TipoLaser enuso = null;
    
    public Claudius(Batch genesis) 
    {
        super(genesis);
        this.velocidad *= 2;
        this.textura = new Texture(Gdx.files.internal("claudius.png"));
        this.tiempoactivo = 20;
        this.y = 300;
    }
    
    @Override
    public void actualizarEstado()
    {
        super.actualizarEstado();
        
        if(laser != null)
        {
            laser.actualizarEstado();
        }
    }
    @Override
    public void jugar(Camera camara)
    {
        super.jugar(camara);
        
        if(laser != null)
        {   
            if(laser.overlaps(enemigo))
            {
                if(!this.colisionlaser)
                {
                    this.enemigo.setVida(this.enemigo.getVida() - 20);
                    Gdx.app.log("INFO", "Has recibido 20 del proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                    this.colisionlaser = true;
                }
            }
            else
            {
                this.colisionlaser = false;
            }
            
            switch(enuso)
            {
                case ESCOMBROS:
                    ((Guardian)this.enemigo).setInmune(false);
                break;
                case BORDES:
                    if(this.enemigo.getX() == 0
                            || this.enemigo.getX() == 1000
                            || this.enemigo.getY() == 0
                            || this.enemigo.getY() == 800)
                    {
                        Gdx.app.log("INFO","De repente, te hiciste vulnerable a los bordes. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
                        this.enemigo.setVida(this.enemigo.getVida() - 100);
                    }
                break;
                case EL:
                    if(this.overlaps(this.enemigo))
                    {
                        Gdx.app.log("INFO","De repente, te hiciste muy vulnerable a la bola. 100 puntos de daño y te quedan " + this.getVida() + "vidas");
                        this.enemigo.setVida(this.enemigo.getVida() - 100);
                    }
                break;
            }
        }
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        this.enemigo = jugador;
    }
    
    public void esquivar(LinkedList<Escombro> escombros)
    {
        if(carga)
        {
            if(enemigo.getX() > this.getX())
            {
                decision = 1;
            }
            else if(enemigo.getX() < this.getX())
            {
                decision = 2;
            }
        }
        else
        {
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
        
        int probabilidad = (int) (Math.random() * 3 + 1);
        switch(probabilidad)
        {
            case 1:
                laser = new Laser(genesis,"laserdebris.png",this);
                enuso = TipoLaser.ESCOMBROS;
            break;
            case 2:
                laser = new Laser(genesis,"laserborder.png",this);
                enuso = TipoLaser.BORDES;
            break;
            case 3:
                laser = new Laser(genesis,"laserhimself.png",this);
                enuso = TipoLaser.EL;
            break;
        }
        
        this.carga = true;
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("claudius.png"));
        
        this.laser = null;
        if(enuso.equals(TipoLaser.ESCOMBROS))
        {
            ((Guardian)enemigo).setInmune(true);
        }
       
        this.carga = false;
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
