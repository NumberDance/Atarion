package com.atarion.game.interfaz.escena.mundo;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.Direccion;
import com.atarion.game.entidad.jugador.humano.Yo;
import com.atarion.game.entidad.jugador.maquina.Brutus;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mundo extends Escena {

    protected Direccion sentidoNavegacion = Direccion.PARADO;

    protected final int bordeNavegacion = 50;
    protected BitmapFont textoNavegacion = new BitmapFont();

    protected List<Lugar> lugares = new ArrayList<>();
    protected List<Direccion> navegacion;

    @Getter
    class Lugar {

        String nombre, fondo;
        Ruta[] ruta;
        Entidad[] jugadoresAliados;
    }

    @Getter
    @AllArgsConstructor
    class Ruta {

        Direccion direccion;
    }
    
    @Getter
    @AllArgsConstructor
    class Entidad {

        Class clase;
        float x,y;
        boolean batalla;
        int ratio;
        String [] conversacion;
    }

    public Mundo() {
        this.batalla = false;

        this.totalWidth += 400;
        this.totalHeight += 200;

        try {
            File file = Gdx.files.internal("data/lugares.json").file();
            this.lugares = Arrays.asList(new Gson()
                    .fromJson(new String(Files.readAllBytes(file.toPath())), Lugar[].class));
        } catch (IOException ex) {
            Logger.getLogger(Mundo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void randomizarMundo() {
        Random random = new Random();

        if (random.ints(1, 11).findFirst().getAsInt() < 4) {
            Yo yo = new Yo(false, false);
            yo.getConversacion().add("Hola, este es el mundo abierto de Atarion");
            yo.getConversacion().add("Salgo con el 30% de  probabilidad, soy raro");
            yo.getConversacion().add("Un consejo, no te tomes a ti mismo en serio demasiado. Adios");
            yo.y += 300;

            this.humanosAliados.add(yo);
        }

        if (random.ints(1, 11).findFirst().getAsInt() < 8) {
            Maquina brutus = new Brutus(false);
            brutus.getConversacion().add("Salgo con el 70% de  probabilidad, soy comun");
            brutus.getConversacion().add("Sus via crujir viiivos");
            brutus.getConversacion().add("A toos");
            brutus.x += 300;

            this.maquinasEnemigas.add(brutus);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        this.genesis.begin();

        if (tu.x >= this.totalWidth - bordeNavegacion - 50) {
            this.sentidoNavegacion = Direccion.DERECHA;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de la derecha",
                    tu.getX(),
                    tu.getY());
        } else if (tu.x <= bordeNavegacion) {
            this.sentidoNavegacion = Direccion.IZQUIERDA;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de la izquierda",
                    tu.getX(),
                    tu.getY());
        } else if (tu.y >= this.totalHeight - bordeNavegacion) {
            this.sentidoNavegacion = Direccion.ARRIBA;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ir a la zona de arriba",
                    tu.getX(),
                    tu.getY());
        } else if (tu.y <= bordeNavegacion) {
            this.sentidoNavegacion = Direccion.ABAJO;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de abajo",
                    tu.getX(),
                    tu.getY());
        } else {
            this.sentidoNavegacion = null;
            this.textoNavegacion = new BitmapFont();
        }

        this.genesis.end();
        this.navegar();
    }

    private void navegar() {
        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            switch (this.sentidoNavegacion) {
                case DERECHA:
                case IZQUIERDA:
                case ARRIBA:
                case ABAJO:
                    this.navegarLocalizacion(this.sentidoNavegacion);
                    break;
            }
        }
    }

    private void navegarLocalizacion(Direccion direccion) {
        this.navegacion = navegacion == null
                ? new ArrayList<>()
                : navegacion;
        this.navegacion.add(direccion);
        
        //Gdx.app.log("NAV", Arrays.toString(this.navegacion.toArray()));

        List<Lugar> triangulado = lugares.stream()
                .filter(lugar -> {
                    List<Direccion> direccionesRuta = Arrays.asList(lugar.getRuta()).stream()
                            .map(ruta -> ruta.getDireccion())
                            .collect(Collectors.toList());

                    return navegacion.equals(direccionesRuta);
                })
                .collect(Collectors.toList());

        //TODO: El entrar() reemplaza el estado por una copia nueva, cambiarlo.
        if (triangulado.isEmpty()) {
            Mundo random = new Mundo();
            random.setTu(tu);
            random.entrar(tu.getClase());
            random.setNavegacion(navegacion);
            random.randomizarMundo();

            Atarion.getInstance().setScreen(random);
        } else {
            Mundo localizacion = new Mundo();
            localizacion.setTu(tu);
            localizacion.entrar(tu.getClase());
            localizacion.setNuevoFondo(new Texture(Gdx.files.internal(triangulado.get(0).getFondo())));
            localizacion.setFondoMostrado(new Texture(Gdx.files.internal(triangulado.get(0).getFondo())));

            Atarion.getInstance().setScreen(localizacion);
        }

        this.textoNavegacion.dispose();
        this.dispose();
    }
}
