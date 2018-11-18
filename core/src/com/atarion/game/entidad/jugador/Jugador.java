package com.atarion.game.entidad.jugador;


import com.atarion.game.entidad.Entidad;
import com.atarion.game.entidad.objeto.Objeto;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.graphics.Camera;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.json.JSONTokener;


@Getter
@Setter
public abstract class Jugador extends Entidad
{
    protected int vidainicial = 100, vida = 100, fuerza = 10;
    
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
}
