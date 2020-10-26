package com.atarion.game.interfaz.escena.online.cliente;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        this.tu = this.asignarClase(clase, true);
        this.tu.setIdentificador(new MensajeJSON().recibir(lector).getJson().getString("identificador"));

        try {
            MensajeJSON estado = new MensajeJSON();
            estado.escribirAtributo("identificador", this.tu.getIdentificador());
            estado.escribirAtributo("tipo", this.tu.getClase().toString());
            estado.enviar(this.cliente.getOutputStream());

            this.iniciales = new MensajeJSON().recibir(lector);
            Gdx.app.log("INFO", this.iniciales.getMensaje());

            this.iniciales.getJson().getJSONArray("iniciales").forEach(inicial -> {
                MensajeJSON valorEstadoInicial = new MensajeJSON().recibir(inicial.toString());

                if (!valorEstadoInicial.getJson().getString("identificador").equals(this.tu.getIdentificador())) {
                    Humano enemigo = this.asignarClase(ClaseHumano.valueOf(valorEstadoInicial.getJson().getString("tipo")),
                            false);
                    enemigo.setIdentificador(valorEstadoInicial.getJson().getString("identificador"));
                    
                    this.humanosEnemigos.add(enemigo);
                }
            });
        } catch (IOException ex) {
        }

        this.humanosEnemigos.stream().forEach(humano -> {
            this.tu.agregarEnemigo(humano);
            humano.agregarEnemigo(this.tu);
        });

        this.maquinasEnemigas.stream().forEach(maquina -> {
            tu.agregarEnemigo(maquina);
            maquina.agregarEnemigo(tu);
        });

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
            MensajeJSON valorEstado = new MensajeJSON().recibir(elemento.toString());

            //Ojo: causa problemas de memoria en partidas largas
            //Gdx.app.log("INFO", "El server responde: " + valorEstado.getMensaje());
            if (valorEstado.getJson().getString("identificador").equals(this.tu.getIdentificador())) {
                tu.recibirEstado(valorEstado.getMensaje());
            } else {
                this.recibirEstadoSiMatch(this.humanosEnemigos.stream()
                        .map(humano -> (Jugador) humano), valorEstado);
                this.recibirEstadoSiMatch(this.humanosAliados.stream()
                        .map(humano -> (Jugador) humano), valorEstado);

                this.recibirEstadoSiMatch(this.maquinasEnemigas.stream()
                        .map(maquina -> (Jugador) maquina), valorEstado);
                this.recibirEstadoSiMatch(this.maquinasAliadas.stream()
                        .map(maquina -> (Jugador) maquina), valorEstado);
            }
        }
        );
    }

    private void recibirEstadoSiMatch(Stream<Jugador> jugadores, MensajeJSON valorEstado) {
        List<Jugador> matches = jugadores
                .filter(jugador -> valorEstado.getJson().getString("identificador")
                .equals(jugador.getIdentificador()))
                .collect(Collectors.toList());

        Gdx.app.log("matches:", Arrays.toString(matches.toArray()));

        if (!matches.isEmpty()) {
            matches.get(0).recibirEstado(valorEstado.getMensaje());
        }
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
}
