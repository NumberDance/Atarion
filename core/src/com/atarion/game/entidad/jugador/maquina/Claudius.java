package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.Laser;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Iterator;
import java.util.LinkedList;

public class Claudius extends Maquina
{
    private Laser laser = null;
    private boolean carga = false, colisionlaser = false;
    private Texture escombros, bordes, el, ninguno;
    
    private enum Modo
    { ESCOMBROS,BORDES,EL,NINGUNO }
    private Modo modo = Modo.NINGUNO;
    
    
    public Claudius(Batch genesis) 
    {
        super(genesis);
        
        this.velocidad *= 4;
        this.textura = new Texture(Gdx.files.internal("claudius.png"));
        this.tiempoactivo = 20;
        this.y = 300;
        
        this.escombros = new Texture(Gdx.files.internal("endurancedebris.png"));
        this.bordes = new Texture(Gdx.files.internal("enduranceborders.png"));
        this.el = new Texture(Gdx.files.internal("endurancehimself.png"));
        this.ninguno = new Texture(Gdx.files.internal("claudius.png"));
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { 
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("carga","" + this.carga,ParteMensaje.CUERPO);
        estado.escribirAtributo("colisionlaser","" + this.colisionlaser,ParteMensaje.CUERPO);
        estado.escribirAtributo("modo",this.modo.toString(),ParteMensaje.CUERPO);

        if(this.laser == null)
        { estado.escribirAtributo("laser","null",ParteMensaje.FINAL); }
        else
        { estado.escribirAtributo("laser",this.laser.enviarEstado().getMensaje().toString(),ParteMensaje.FINAL); }
        
        return estado; 
    }
    @Override
    public void recibirEstado(String estado)
    { 
        /*super.recibirEstado(estado); 
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        if(objeto.getString("laser").equals("null"))
        { this.laser = null; }
        else
        {
            if(this.laser != null)
            { this.laser = new Laser(genesis,objeto.getString("proyectil")); }
        }
        this.colisionlaser = objeto.getBoolean("colisionlaser");*/
    }
    
    
    @Override
    public void actualizarEstado()
    {
        super.actualizarEstado();
        
        if(laser != null)
        { laser.actualizarEstado(); }
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
                    Gdx.app.log("INFO", "Has recibido 50 del proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                    this.colisionlaser = true;
                }
            }
            else
            { this.colisionlaser = false; }
        }
        
        switch(modo)
        {
            case ESCOMBROS:
                this.enemigo.setInmune(false);
            break;
            case BORDES:
                if(this.enemigo.getX() == 0
                        || this.enemigo.getX() == 1000
                        || this.enemigo.getY() == 0
                        || this.enemigo.getY() == 800)
                {
                    Gdx.app.log("INFO","De repente, te hiciste vulnerable a los bordes. 200 puntos de daño y te quedan " + this.getVida() + "vidas");
                    this.enemigo.setVida(this.enemigo.getVida() - 200);
                }
            break;
            case EL:
                
                if(this.overlaps(this.enemigo))
                {
                    Gdx.app.log("INFO","De repente, te hiciste muy vulnerable a la bola. 200 puntos de daño y te quedan " + this.getVida() + "vidas");
                    this.enemigo.setVida(this.enemigo.getVida() - 200);
                }
            break;
        }
    }
    
    
    @Override
    public void agregarEnemigo(Jugador jugador) 
    { this.enemigo = jugador; }
    
    
    public void esquivar(LinkedList<Escombro> escombros)
    {
        if(carga)
        {   
            if(enemigo.getX() > this.getX())
            { decision = 1; }
            else if(enemigo.getX() < this.getX())
            { decision = 2; }
            else
            { decision = 0; }
        }
        else
        {
            Iterator<Escombro> i = escombros.iterator();
            while(i.hasNext())
            {
                Escombro escombro = i.next();
            
                if(escombro.getX() == this.x
                        && escombro.getY() - this.y <= 150 )
                { decision = 1; }
                else if(escombro.getX() > this.x 
                        && escombro.getX() - this.x <= 150 
                        && escombro.getY() - this.y <= 150 ) 
                { decision = 2; }
                else if(escombro.getX() < this.x 
                        && this.x - escombro.getX() <= 150 
                        && escombro.getY() - this.y <= 150)
                { decision = 1; }
                else
                { decision = 0; }
            }
        }
    }
    
    
    @Override
    protected void controlBordes() 
    {
        if(this.y < 0) 
        { this.y = 800 - 20; }
        
        if(this.y > 800 - 20)
        { this.y = 0; }
        
        if(this.x < 0)
        { this.x = 1000 - 80; }
        
        if(this.x > 1000 - 80) 
        { this.x = 0; }
    }
    @Override
    protected void controlEspecial()
    {
        if(parado && activado)
        {
            this.modo = Modo.NINGUNO;
            this.textura = this.ninguno;
        }
        
        super.controlEspecial();
    }
    
    
    @Override
    public void activarEspecial() 
    {
        this.carga = true;
        
        this.modo = Modo.NINGUNO;
        this.textura = this.ninguno;
        
        int probabilidad = (int) (Math.random() * 3 + 1);
        switch(probabilidad)
        {
            case 1:
                laser = new Laser(genesis,"laserdebris.png",this);
            break;
            case 2:
                laser = new Laser(genesis,"laserborder.png",this);
            break;
            case 3:
                laser = new Laser(genesis,"laserhimself.png",this);
            break;
        }
    }
    @Override
    public void desactivarEspecial() 
    {   
        this.carga = false;
        this.laser = null;
        
        int probabilidad = (int) (Math.random() * 3 + 1);
        switch(probabilidad)
        {
            case 1:
                modo = Modo.ESCOMBROS;
                this.textura = this.escombros;
            break;
            case 2:
                modo = Modo.BORDES;
                this.textura = this.bordes;
            break;
            case 3:
                modo = Modo.EL;
                this.textura = this.el;
            break;
        }
        
        if(!modo.equals(Modo.ESCOMBROS))
        { enemigo.setInmune(true); }
    } 
    
    
    @Override
    public void setVida(int vida)
    {
        if(this.modo.equals(Modo.NINGUNO))
        { super.setVida(vida); }
    }
}
