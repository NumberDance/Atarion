package com.atarion.game.interfaz.escena;

import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.humano.Guardian;
import com.atarion.game.entidad.jugador.maquina.Claudius;
import com.badlogic.gdx.Gdx;
import java.util.Iterator;
import java.util.LinkedList;

public class EscenaClaudius extends Escena
{
    private LinkedList<Escombro> escombros;
    private LinkedList<Boolean> colisiones;

    
    public EscenaClaudius() 
    { super(Gdx.audio.newMusic(Gdx.files.internal("finale.mp3"))); }
    
    
    @Override
    public void show()
    {
        super.show();
        
        humano = new Guardian(genesis);
        maquina = new Claudius(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
        
        escombros = new LinkedList<Escombro>();
        colisiones = new LinkedList<Boolean>();
    } 
    @Override
    public void render(float delta)
    {
        super.render(delta);
       
        int oportunidad = (int) (Math.random() * 150 + 0);
        
        if(oportunidad == 0)
        {
            this.escombros.add(new Escombro(genesis));
            this.colisiones.add(Boolean.FALSE);
        }
        
        if(!escombros.isEmpty())
        {
            genesis.begin();
            
            Iterator<Escombro> i = escombros.iterator();
            while(i.hasNext())
            {
                Escombro escombro = i.next();
                escombro.actualizarEstado();
            }
            
            genesis.end();
            
            Iterator <Escombro> j = escombros.iterator();
            while(j.hasNext())
            {
                Escombro escombro = j.next();
                
                if(escombro.isDestino())
                {
                    escombro.dispose();
                    escombros.remove(escombro);
                }
                else
                {
                    escombro.caer();
                    ((Claudius)maquina).esquivar(escombros);
                }
            }
            
            for(int m = 0; m < escombros.size(); m++)
            {
                Escombro escombro = escombros.get(m);
                
                if(escombro.overlaps(humano))
                {
                    if(colisiones.get(m).equals(Boolean.FALSE))
                    {
                        humano.colisionObjeto(escombro);
                        colisiones.set(m,Boolean.TRUE);
                    }
                }
                else if(escombro.overlaps(maquina))
                {
                    if(colisiones.get(m).equals(Boolean.FALSE))
                    {
                        maquina.setVida(maquina.getVida() - escombro.getDureza());
                        Gdx.app.log("INFO", "La bola ha sido vilmente golpeada por un pedrusco. Le quedan " + maquina.getVida() + " vidas.");
                        colisiones.set(m,Boolean.TRUE);
                    }
                }
                else
                { colisiones.set(m,Boolean.FALSE); }
            }  
        }
    }
}
