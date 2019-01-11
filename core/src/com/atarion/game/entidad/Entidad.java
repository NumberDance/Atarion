package com.atarion.game.entidad;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.json.JSONTokener;


@Getter
@Setter
public abstract class Entidad extends Rectangle
{
    protected Batch genesis;
    protected Texture textura;
    
    protected String identificador = "NONE";
    protected boolean interaccion = false;
    
    
    public void actualizarEstado()
    { genesis.draw(textura,x,y); }
    public void dispose()
    { textura.dispose(); }
    
    
    public MensajeJSON enviarEstado()
    {
        MensajeJSON estado = new MensajeJSON();
        estado.escribirAtributo("identificador",this.identificador);
        estado.escribirAtributo("x","" + this.x);
        estado.escribirAtributo("y","" + this.y);
        return estado;
    }
    protected void recibirEstado(String estado)
    {
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.identificador = objeto.getString("identificador");
        this.x = objeto.getFloat("x");
        this.y = objeto.getFloat("y");
    }
}
