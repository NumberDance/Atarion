package com.atarion.game.interfaz.escena.mundo;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.Yo;
import com.atarion.game.entidad.jugador.maquina.Brutus;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.Random;

public class Mundo extends Escena {

    Mundo arriba, abajo, izquierda, derecha;
    boolean moverArriba, moverAbajo, moverIzquierda, moverDerecha;

    final int bordeNavegacion = 50;
    BitmapFont textoNavegacion = new BitmapFont();

    public Mundo() {
        this.batalla = false;

        this.totalWidth += 400;
        this.totalHeight += 200;

        this.randomizarMundo();
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

        if (tu.x >= this.totalWidth - bordeNavegacion) {
            this.moverIzquierda = true;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de la izquierda",
                    tu.getX(),
                    tu.getY());
        } else if (tu.x <= bordeNavegacion) {
            this.moverDerecha = true;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de la derecha",
                    tu.getX(),
                    tu.getY());
        } else if (tu.y >= this.totalHeight - bordeNavegacion) {
            this.moverArriba = true;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ir a la zona de arriba",
                    tu.getX(),
                    tu.getY());
        } else if (tu.y <= bordeNavegacion) {
            this.moverAbajo = true;
            this.textoNavegacion.draw(genesis,
                    "Pulsa C para ira la zona de abajo",
                    tu.getX(),
                    tu.getY());
        } else {
            this.moverIzquierda = false;
            this.moverDerecha = false;
            this.moverArriba= false;
            this.moverAbajo = false;
            
            this.textoNavegacion = new BitmapFont();
        }

        this.genesis.end();

        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            if (this.moverDerecha) {
                derecha = new Mundo();
                derecha.setTu(tu);
                
                Atarion.getInstance().setScreen(this.derecha);
                this.textoNavegacion.dispose();
                this.dispose();
            } else if (this.moverIzquierda) {
                izquierda = new Mundo();
                izquierda.setTu(tu);
                
                Atarion.getInstance().setScreen(this.izquierda);
                this.textoNavegacion.dispose();
                this.dispose();
            } else if (this.moverArriba) {
                arriba = new Mundo();
                arriba.setTu(tu);
                
                Atarion.getInstance().setScreen(this.arriba);
                this.textoNavegacion.dispose();
                this.dispose();
            } else if (this.moverAbajo) {
                abajo = new Mundo();
                abajo.setTu(tu);
                
                Atarion.getInstance().setScreen(this.abajo);
                this.textoNavegacion.dispose();
                this.dispose();
            }
        }
    }
}
