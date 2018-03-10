package com.atarion.game.interfaz.escena;


import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.maquina.Claudius;
import java.util.Iterator;
import java.util.LinkedList;


public class EscenaClaudius extends Escena
{
    private LinkedList<Escombro> escombros;
    private LinkedList<Boolean> colisiones;

    
    public EscenaClaudius() 
    {
        super();
        super.musica("finale.mp3"); 
    }
    
    
    @Override
    public void show()
    {
        super.show();
        maquina = new Claudius(genesis);
        
        humano.agregarEnemigo(maquina);
        maquina.agregarEnemigo(humano);
        
        escombros = new LinkedList<>();
        colisiones = new LinkedList<>();
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
            escombros.forEach(escombro -> escombro.actualizarEstado());
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
                else
                { colisiones.set(m,Boolean.FALSE); }
            }  
        }
    }
}
