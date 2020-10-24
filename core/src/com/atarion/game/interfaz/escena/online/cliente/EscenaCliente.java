package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EscenaCliente extends Escena {

    private Socket cliente;
    private MensajeJSON iniciales = null;
    private BufferedReader lector;

    public EscenaCliente() {
        super();

        try {
            this.cliente = new Socket("localhost", 20595);
            this.lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException ex) {
        }
    }

    @Override
    public void entrar(ClaseHumano clase) {
        this.tu = this.asignarClase(tu, clase, true);
        this.tu.setIdentificador(new MensajeJSON().recibir(lector).getJson().getString("identificador"));

        try {
            MensajeJSON estado = new MensajeJSON();
            estado.escribirAtributo("identificador", this.tu.getIdentificador());
            estado.escribirAtributo("tipo", this.tu.getClase().toString());
            estado.enviar(this.cliente.getOutputStream());

            this.iniciales = new MensajeJSON().recibir(lector);
            Gdx.app.log("INFO", this.iniciales.getMensaje());

            this.iniciales.getJson().getJSONArray("iniciales").forEach(
                    inicial
                    -> {
                MensajeJSON valor = new MensajeJSON().recibir(inicial.toString());

                if (!valor.getJson().getString("identificador").equals(this.tu.getIdentificador())) {
                    ClaseHumano clase2 = ClaseHumano.valueOf(valor.getJson().getString("tipo"));
                    humano = this.asignarClase(this.humano, clase2, false);

                    String identificador2 = valor.getJson().getString("identificador");
                    humano.setIdentificador(identificador2);
                }
            }
            );
        } catch (IOException ex) {
        }

        if (this.humano != null) {
            this.tu.agregarEnemigo(this.humano);
            this.humano.agregarEnemigo(this.tu);
        }
        if (this.maquina != null) {
            tu.agregarEnemigo(maquina);
            maquina.agregarEnemigo(tu);
        }

        Atarion.getInstance().setScreen(this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        new HiloCliente(this).start();
    }

    public void actualizarPartida(String estado) {
        MensajeJSON mensaje = new MensajeJSON().recibir(lector);

        mensaje.getJson().getJSONArray("estados").forEach(
                elemento
                -> {
            MensajeJSON valor = new MensajeJSON().recibir(elemento.toString());
            
            //Ojo: causa problemas de memoria en partidas largas
            //Gdx.app.log("INFO", "El server responde: " + valor.getMensaje());

            if (valor.getJson().getString("identificador").equals(this.tu.getIdentificador())) {
                tu.recibirEstado(valor.getMensaje());
            } else if (valor.getJson().getString("identificador").equals(this.humano.getIdentificador())) {
                humano.recibirEstado(valor.getMensaje());
            }
        }
        );
    }

    @Override
    public void dispose() {
        try {
            super.dispose();
            cliente.close();
        } catch (IOException ex) {
        }
    }

    public Socket getCliente() {
        return cliente;
    }

    public Humano getTu() {
        return tu;
    }

    public BufferedReader getLector() {
        return lector;
    }

    public Humano getHumano() {
        return humano;
    }
}
