package com.atarion.game.entidad.jugador;


import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.graphics.Camera;
import org.json.JSONObject;
import org.json.JSONTokener;


public abstract class Jugador extends Entidad
{
    protected int vida = 100, fuerza = 10;
    
    protected float velocidad = 1f;
    protected Direccion direccion = Direccion.PARADO;
    
    private boolean colision = false;
    protected Jugador enemigo;
    
    protected float cronometro = 0f;
    protected int tiempoactivo = 5, activo = 5, tiemporecarga = 10, recarga = 0;
    protected boolean activado = false, inmune = false;
    protected boolean parado = false, controlado = false, invertido = false;
    
    
    protected Jugador()
    {
        this.x = 800 / 2 - 64 / 2;
        this.width = 80;
        this.height = 20;   
    }
    
    
    @Override
    public MensajeJSON enviarEstado()
    {
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("vida","" + vida);
        estado.escribirAtributo("fuerza","" + fuerza);
        estado.escribirAtributo("velocidad","" + velocidad);
        estado.escribirAtributo("direccion","" + direccion);
        estado.escribirAtributo("colision","" + colision);
        estado.escribirAtributo("cronometro","" + cronometro);
        estado.escribirAtributo("tiempoactivo","" + tiempoactivo);
        estado.escribirAtributo("activo","" + activo);
        estado.escribirAtributo("tiemporecarga","" + tiemporecarga);
        estado.escribirAtributo("recarga","" + recarga);
        estado.escribirAtributo("activado","" + activado);
        estado.escribirAtributo("parado","" + parado);
        estado.escribirAtributo("controlado","" + controlado);
        estado.escribirAtributo("inmune","" + inmune);
        estado.escribirAtributo("invertido","" + invertido);
        
        if(this.enemigo != null)
        { estado.escribirAtributo("enemigo",enemigo.getIdentificador()); }
        
        return estado;
    }
    @Override
    public void recibirEstado(String estado)
    {
        super.recibirEstado(estado);
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.vida = objeto.getInt("vida");
        this.fuerza = objeto.getInt("fuerza");
        this.velocidad = objeto.getFloat("velocidad");
        this.direccion = Direccion.valueOf(objeto.getString("direccion"));
        this.colision = objeto.getBoolean("colision");
        this.cronometro = objeto.getFloat("cronometro");
        this.tiempoactivo = objeto.getInt("tiempoactivo");
        this.activo = objeto.getInt("activo");
        this.tiemporecarga = objeto.getInt("tiemporecarga");
        this.recarga = objeto.getInt("recarga");
        
        boolean act = objeto.getBoolean("activado");
        if(act && !this.activado)
        { this.activarEspecial(); }
        this.activado = act;
        
        this.parado = objeto.getBoolean("parado");
        this.controlado = objeto.getBoolean("controlado");
        this.inmune = objeto.getBoolean("inmune");
        this.invertido = objeto.getBoolean("invertido");
    }
    
    
    public abstract void agregarEnemigo(Jugador jugador);
    protected void jugar(Camera camara)
    {
        if(!controlado)
        {
            this.controlMovimiento();
            this.controlEspecial();
            this.controlBordes();
        }
    }
    
    
    protected abstract void colisionObjeto(Objeto objeto);
    protected abstract void colisionJugador(Jugador jugador);
    
    
    protected abstract void controlMovimiento();
    protected abstract void controlBordes();
    protected abstract void controlEspecial();
    public void pararEspecial()
    {
        this.parado = true;
        this.desactivarEspecial();
    }
    
    
    public abstract void activarEspecial();
    public abstract void desactivarEspecial();
    

    public boolean isColision()
    { return colision; }
    public Jugador getEnemigo()
    { return enemigo; }
    public float getCronometro()
    { return cronometro; }
    public int getTiempoactivo()
    { return tiempoactivo; }
    public int getActivo()
    { return activo; }
    public int getTiemporecarga()
    { return tiemporecarga; }
    public boolean isParado()
    { return parado; }
    public int getRecarga()
    { return recarga; }
    public int getFuerza()
    { return fuerza; }
    public float getVelocidad()
    { return velocidad; }
    public int getVida()
    { return vida; }
    public Direccion getDireccion()
    { return direccion; }
    public boolean isActivado()
    { return activado; }
    
    
    public void setVida(int vida)
    { this.vida = vida; }
    public void setFuerza(int fuerza)
    { this.fuerza = fuerza; }
    public void setVelocidad(float velocidad)
    { this.velocidad = velocidad; }
    public void setDireccion(Direccion direccion)
    { this.direccion = direccion; }
    public void setColision(boolean colision)
    { this.colision = colision; }
    public void setEnemigo(Jugador enemigo)
    { this.enemigo = enemigo; }
    public void setCronometro(float cronometro)
    { this.cronometro = cronometro; }
    public void setTiempoactivo(int tiempoactivo)
    { this.tiempoactivo = tiempoactivo; }
    public void setActivo(int activo)
    { this.activo = activo; }
    public void setTiemporecarga(int tiemporecarga)
    { this.tiemporecarga = tiemporecarga; }
    public void setRecarga(int recarga)
    { this.recarga = recarga; }
    public void setActivado(boolean activado)
    { this.activado = activado; }
    public void setParada(boolean parado)
    { this.parado = parado; }
    public boolean isControlado()
    { return controlado; }
    public void setControlado(boolean controlado)
    { this.controlado = controlado; }
    public void setInmune(boolean inmune) 
    { this.inmune = inmune; }
}
