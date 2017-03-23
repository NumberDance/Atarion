package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.objeto.Bomba;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.HashSet;
import java.util.Iterator;

public class Crasus extends Maquina
{
    private boolean colision;
    private boolean colisionbomba;
    private float temporizador;
    private HashSet<Bomba> bombas;
    
    public Crasus(Batch genesis) 
    {
        super(genesis);
        this.vida *= 2;
        this.textura = new Texture(Gdx.files.internal("crasus.png"));
        this.colision = false;
        this.temporizador = 0f;
        bombas = new HashSet<Bomba>();
    }

    @Override
    public void jugar(Camera camara)
    {
        super.jugar(camara);
        
        if(!bombas.isEmpty())
        {
            temporizador += Gdx.graphics.getDeltaTime();
            if(temporizador >= 1.0f)
            {
                Iterator<Bomba> i = bombas.iterator();
                while(i.hasNext())
                {
                    i.next().cuentaAtras();
                }
                
                temporizador = 0f;
            }
            
            Iterator<Bomba> j = bombas.iterator();
            while(j.hasNext())
            {
                Bomba bomba = j.next();
                
                if(bomba.overlaps(enemigo) && bomba.getDureza() > 0)
                {
                    if(!this.colisionbomba)
                    {
                        enemigo.setVida(enemigo.getVida() - bomba.getDureza());
                        Gdx.app.log("INFO", "La bomba te hace 50 puntos de daño. Te quedan " + enemigo.getVida() + " vidas.");
                        this.colisionbomba = true;
                    }
                }
                else
                {
                    this.colisionbomba = false;
                }
            }
        }
    }
    @Override
    public void actualizarEstado()
    {
        super.actualizarEstado();
        
        if(!bombas.isEmpty())
        {
            Iterator<Bomba> i = bombas.iterator();
            while(i.hasNext())
            {
                i.next().actualizarEstado();
            }
        }
    }
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    {
        this.enemigo = jugador;
        this.huir();
    }

    private void huir() 
    {   
        if(x < 0 + 100 + 100)
        {
            decision = 1;
        }
        else if(x > 1000 - 100 - 100)
        {
            decision = 2;
        }
        else if(y < 0 + 100 + 100)
        {
            decision = 3;
        }
        else if(y > 800 - 100 - 100)
        {
            decision = 4;
        }
        else
        {
            if(enemigo.getX() > this.x && enemigo.getY() > this.y)
            {
                decision = 8;
            }
            else if(enemigo.getX() > this.x && enemigo.getY() > this.y)
            {
                decision = 6;
            }
            else if(enemigo.getX() > this.x && enemigo.getY() < this.y)
            {
                decision = 7;
            }
            else if(enemigo.getX() < this.x && enemigo.getY() < this.y)
            {
                decision = 5;
            }
            else if(enemigo.getX() < this.x)
            {
                decision = 1;
            }
            else if(enemigo.getX() > this.x)
            {
                decision = 2;
            }
            else if(enemigo.getY() < this.y)
            {
                decision = 3;
            }
            else if(enemigo.getY() > this.y)
            {
                decision = 4;
            }
        }
    }
    private void comprobarColision()
    {
        if(!colision)
        {
            this.vida -= 10;
            Gdx.app.log("INFO: ", "a la bola le quedan " + this.vida + " vidas.");
            this.colision = true;
        }
    }
    
    @Override
    public void controlBordes() 
    {   
        if(this.y < 0) 
        {
            this.y = 800 - 150;
            comprobarColision();
            decision = 5;
        } 
        else if(this.y > 800 - 100)
        {
            this.y = 0 + 150;
            comprobarColision();
            decision = 7;
        }
        else if(this.x < 0)
        {
            this.x = 1000 - 150;
            comprobarColision();
            decision = 8;
        }
        else if(this.x > 1000 - 100) 
        {
            this.x = 0 + 150;
            comprobarColision();
            decision = 6;
        }
        else
        {
            this.colision = false;
        }
        
        this.huir();
    }
    
    @Override
    public void activarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("control.png"));
        
        //A veces te amaga y no te invierte los controles 
        int probabilidad = (int) (Math.random() * 4 + 1);
        if(probabilidad > 1)
        {
            ((Humano)this.enemigo).setInvertido(true);
        }
        
        int posiciones = (int) (Math.random() * 2 + 1);
        switch(posiciones)
        {
            case 1:
                bombas.add(new Bomba(genesis,0 + 200,800 - 200 + 50));
                bombas.add(new Bomba(genesis,0 + 200,600 - 200));
                bombas.add(new Bomba(genesis,0 + 200,0 + 200 - 50));
                
                bombas.add(new Bomba(genesis,1000 - 200,800 - 200 + 50));
                bombas.add(new Bomba(genesis,1000 - 200,600 - 200));
                bombas.add(new Bomba(genesis,1000 - 200,0 + 200 - 50));
            break;
            case 2:
                bombas.add(new Bomba(genesis,0 + 200,800 - 200 + 50));
                bombas.add(new Bomba(genesis,700 - 200,800 - 200 + 50));
                bombas.add(new Bomba(genesis,1000 - 200,800 - 200 + 50));
                
                bombas.add(new Bomba(genesis,0 + 200,0 + 200 - 50));
                bombas.add(new Bomba(genesis,700 - 200,0 + 200 - 50));
                bombas.add(new Bomba(genesis,1000 - 200,0 + 200 - 50));
            break;
        }
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("crasus.png"));
        ((Humano)this.enemigo).setInvertido(false);
        bombas.clear();
    } 
}
