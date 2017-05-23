package com.atarion.game.entidad.jugador.humano.cannon;

public class Fanatic extends Cannon
{   
    public Fanatic(Batch genesis)
    {
        super(genesis);
    }


    @Override
    public void activarEspecial()
    {
        int azar = (int) (Math.random() * 2 + 1);
        
        if(azar == 1)
        { this.vida = 0; }
        else if(azar == 2)
        { 
            this.vida = 1;
            this.recarga = 0;
        }
        
        this.habilidad = new HabilidadFanatic(genesis,this);
    }
    @Override
    public void desactivarEspecial()
    {}
}
