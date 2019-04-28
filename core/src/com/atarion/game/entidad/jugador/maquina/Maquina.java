package com.atarion.game.entidad.jugador.maquina;


import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;


public abstract class Maquina extends Jugador
{
    protected int decision = 0, fase = 1;
    
    
    public Maquina(boolean batalla) 
    {
        super(batalla);
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
    { return super.enviarEstado().escribirAtributo("decision","" + this.decision); }
    
    @Override
    public void setVida(int vida)
    {
        super.setVida(vida);
        
        if(vida == (vidainicial * 9 / 10))
        {
            this.fase = 2;
            this.faseDos(); 
        }
        else if(vida == (vidainicial * 8 / 10))
        { 
            this.fase = 3;
            this.faseTres(); 
        }
        else if(vida == (vidainicial * 7 / 10))
        {
            this.fase = 4;
            this.faseCuatro(); 
        }
        else if(vida == (vidainicial * 6 / 10))
        { 
            this.fase = 5;
            this.faseCinco(); 
        }
        else if(vida == (vidainicial * 5 / 10))
        {
            this.fase = 6;
            this.faseSeis(); 
        }
        else if(vida == (vidainicial * 4 / 10))
        { 
            this.fase = 7;
            this.faseSiete(); 
        }
        else if(vida == (vidainicial * 3 / 10))
        { 
            this.fase = 8;
            this.faseOcho(); 
        }
        else if(vida == (vidainicial * 2 / 10))
        { 
            this.fase = 9;
            this.faseNueve(); 
        }  
        else if(vida == (vidainicial * 1 / 10))
        { 
            this.fase = 10;
            this.faseDiez(); 
        }
        
        Gdx.app.log("FASE","El carcelero esta en fase " + this.fase);
    }     
    
    protected abstract void faseDos();
    protected abstract void faseTres();
    protected abstract void faseCuatro();
    protected abstract void faseCinco();
    protected abstract void faseSeis();
    protected abstract void faseSiete();
    protected abstract void faseOcho();
    protected abstract void faseNueve();
    protected abstract void faseDiez();
}
