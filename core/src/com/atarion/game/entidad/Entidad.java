package com.atarion.game.entidad;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import org.json.JSONObject;
import org.json.JSONTokener;


public abstract class Entidad extends Rectangle
{
    protected Batch genesis;
    protected Texture textura;
    protected String identificador = "NONE";
    
    
    public void actualizarEstado()
    { genesis.draw(textura,x,y); }
    public void dispose()
    { textura.dispose(); }
    
    
    public MensajeJSON enviarEstado()
    {
        MensajeJSON estado = new MensajeJSON();
        estado.escribirAtributo("identificador",this.identificador, ParteMensaje.PRINCIPIO);
        estado.escribirAtributo("x","" + this.x,ParteMensaje.CUERPO);
        estado.escribirAtributo("y","" + this.y,ParteMensaje.CUERPO);
        return estado;
    }
    protected void recibirEstado(String estado)
    {
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.identificador = objeto.getString("identificador");
        this.x = objeto.getFloat("x");
        this.y = objeto.getFloat("y");
    }

    
    public String getIdentificador()
    { return identificador; }
    
    
    public void asignarGenesis(Batch genesis)
    {  this.genesis = genesis; }
    public void setTextura(Texture textura)
    { this.textura = textura; }
    public void setIdentificador(String identificador)
    { this.identificador = identificador; }
}
