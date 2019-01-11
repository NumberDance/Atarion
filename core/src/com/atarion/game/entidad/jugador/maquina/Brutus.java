package com.atarion.game.entidad.jugador.maquina;


import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.ProyectilBrutus;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Brutus extends Maquina
{
    private ProyectilBrutus proyectil;
    private boolean colisionproyectil = false;
    
    
    public Brutus() 
    {
        super();
        
        this.textura = new Texture(Gdx.files.internal("brutus.png"));
        this.fuerza *= 4;
        this.velocidad /= 2;
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    { 
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("colisionproyectil","" + this.colisionproyectil);
        
        if(this.proyectil != null)
        { estado.escribirAtributo("proyectil",this.proyectil.enviarEstado().getMensaje().toString()); }
        else
        { estado.escribirAtributo("proyectil","null"); }
        
        return estado; 
    }
    @Override
    public void recibirEstado(String estado)
    { 
        super.recibirEstado(estado); 
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        if(objeto.getString("proyectil").equals("null"))
        { this.proyectil = null; }
        else
        {
            if(this.proyectil != null)
            { this.proyectil = new ProyectilBrutus(genesis,objeto.getString("proyectil")); }
        }
        this.colisionproyectil = objeto.getBoolean("colisionproyectil");
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
                    this.setVida(this.vida - proyectil.getDureza());
                    Gdx.app.log("INFO", "La bola ha recibido 10 de su medicina. Le quedan " + vida + " vidas.");
                    
                    this.colisionproyectil = true;
                    this.perseguir();
                }
            }
            else
            { this.colisionproyectil = false; }
        }
    }
    private void perseguir()
    {
        float ccentrox = this.getX() - (this.enemigo.getWidth() / 2);
        float ccentroy = this.getY() - (this.enemigo.getHeight() / 2);
        
        float ecentrox = this.enemigo.getX() - (this.enemigo.getWidth() / 2);
        float ecentroy = this.enemigo.getY() - (this.enemigo.getHeight() / 2);
        
        /*if (Math.abs(ccentroy - ecentroy) < this.enemigo.getWidth()
                && Math.abs(ccentrox - ecentrox) < this.enemigo.getHeight())
        {
            if(enemigo.getX() > this.x && enemigo.getY() > this.y)
            { decision = 5; }
            else if(enemigo.getX() > this.x && enemigo.getY() < this.y)
            { decision = 6; }
            else if(enemigo.getX() < this.x && enemigo.getY() > this.y)
            { decision = 7; }
            else if(enemigo.getX() < this.x && enemigo.getY() < this.y)
            { decision = 8; }
        }
        else if(Math.abs(ccentroy - ecentroy) < this.enemigo.getWidth())
        {
            if(enemigo.getX() > this.x)
            { 
                if(this.proyectil != null
                        && (Math.abs(this.getY() - this.proyectil.getY()) < this.proyectil.getHeight())
                        && (this.proyectil.getX() < this.enemigo.getX())
                        && (this.proyectil.getX() > this.getX()))
                { decision = 2; }
                else
                { decision = 1; }
            
            }
            else if(enemigo.getX() < this.x)
            { 
                if(this.proyectil != null
                        && (Math.abs(this.getY() - this.proyectil.getY()) < this.proyectil.getHeight())
                        && (this.proyectil.getX() > this.enemigo.getX())
                        && (this.proyectil.getX() < this.getX()))
                { decision = 1; }
                else
                { decision = 2; }
            }
        }
        else if(Math.abs(ccentrox - ecentrox) < this.enemigo.getHeight())
        {
            if(enemigo.getY() > this.y)
            { 
                if(this.proyectil != null
                        && (Math.abs(this.getX() - this.proyectil.getX()) < this.proyectil.getWidth())
                        && (this.proyectil.getY() < this.enemigo.getX())
                        && (this.proyectil.getY() > this.getY()))
                { decision = 4; }
                else
                { decision = 3; } 
            }
            else if(enemigo.getY() < this.y)
            { 
                if(this.proyectil != null
                        && (Math.abs(this.getX() - this.proyectil.getX()) < this.proyectil.getWidth())
                        && (this.proyectil.getY() > this.enemigo.getX())
                        && (this.proyectil.getY() < this.getY()))
                { decision = 3; }
                else
                { decision = 4; } 
            }
        }
        else*/ if(enemigo.getX() > this.x && enemigo.getY() > this.y)
        { decision = 5; }
        else if(enemigo.getX() > this.x && enemigo.getY() < this.y)
        { decision = 6; }
        else if(enemigo.getX() < this.x && enemigo.getY() > this.y)
        { decision = 7; }
        else if(enemigo.getX() < this.x && enemigo.getY() < this.y)
        { decision = 8; }
        else if(enemigo.getX() > this.x)
        { decision = 1; }
        else if(enemigo.getX() < this.x)
        { decision = 2; }
        else if(enemigo.getY() > this.y)
        { decision = 3; }
        else if(enemigo.getY() < this.y)
        { decision = 4; }
    }
    
    
    @Override
    public void controlBordes() 
    {   
        if(this.y < 0) 
        {
            this.y = 0;
            this.perseguir();
        }
        else if(this.y > 800 - 105)
        {
            this.y = 800 - 110;
            this.perseguir();
        }
        
        if(this.x < 0)
        {
            this.x = 0;
            this.perseguir();
        }
        else if(this.x > 1000 - 105) 
        {
            this.x = 1000 - 110;
            this.perseguir();
        }
        
        if(this.fase == 10)
        { this.perseguir(); }
    }

    
    @Override
    public void activarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("hunt.png"));
        proyectil = new ProyectilBrutus(genesis,50,50,enemigo.getX(),enemigo.getY());
        
        this.velocidad += 0.1;
        this.enemigo.setVelocidad(this.enemigo.getVelocidad() - 0.1f);
    }
    @Override
    public void desactivarEspecial() 
    {
        this.textura = new Texture(Gdx.files.internal("brutus.png"));
        proyectil = null;
        
        this.perseguir();
    } 

    
    @Override
    protected void faseDos()
    {}
    @Override
    protected void faseTres()
    {}
    @Override
    protected void faseCuatro()
    {}
    @Override
    protected void faseCinco()
    {}
    @Override
    protected void faseSeis()
    {}
    @Override
    protected void faseSiete()
    {}
    @Override
    protected void faseOcho()
    {}
    @Override
    protected void faseNueve()
    {}
    @Override
    protected void faseDiez()
    {}
}
