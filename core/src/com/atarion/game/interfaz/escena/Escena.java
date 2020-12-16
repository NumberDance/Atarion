package com.atarion.game.interfaz.escena;

import com.atarion.game.Atarion;
import com.atarion.game.entidad.jugador.humano.ClaseHumano;
import com.atarion.game.entidad.jugador.humano.DummyGeneric;
import com.atarion.game.entidad.jugador.humano.Humano;
import com.atarion.game.entidad.jugador.humano.cannon.Anarchist;
import com.atarion.game.entidad.jugador.humano.cannon.DummyCannon;
import com.atarion.game.entidad.jugador.humano.cannon.Fanatic;
import com.atarion.game.entidad.jugador.humano.cannon.Templar;
import com.atarion.game.entidad.jugador.humano.trench.Avenger;
import com.atarion.game.entidad.jugador.humano.trench.Benefactor;
import com.atarion.game.entidad.jugador.humano.trench.DummyTrench;
import com.atarion.game.entidad.jugador.humano.trench.Guardian;
import com.atarion.game.entidad.jugador.humano.wheel.DummyWheel;
import com.atarion.game.entidad.jugador.humano.wheel.Merchant;
import com.atarion.game.entidad.jugador.humano.wheel.Traveler;
import com.atarion.game.entidad.jugador.humano.wheel.Visionary;
import com.atarion.game.entidad.jugador.maquina.Maquina;
import com.atarion.game.interfaz.Interfaz;
import com.atarion.game.interfaz.menu.MenuDerrota;
import com.atarion.game.interfaz.menu.MenuVictoria;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Escena extends Interfaz {

    protected Humano tu = null;

    protected List<Humano> humanosAliados = new LinkedList<>(),
            humanosEnemigos = new LinkedList<>();

    protected List<Maquina> maquinasAliadas = new LinkedList<>(),
            maquinasEnemigas = new LinkedList<>();

    protected boolean cmaquina = false, chumano = false;

    protected boolean batalla;

    public void entrar(ClaseHumano clase) {
        this.tu = this.asignarClase(clase, true);

        humanosEnemigos.forEach(humano -> {
            if (humano != null) {
                this.tu.agregarEnemigo(humano);
                humano.agregarEnemigo(this.tu);
            }
        });

        maquinasEnemigas.forEach(maquina -> {
            if (maquina != null) {
                tu.agregarEnemigo(maquina);
                maquina.agregarEnemigo(tu);
            }
        });

        Atarion.getInstance().setScreen(this);
    }

    protected Humano asignarClase(ClaseHumano clase, boolean tu) {
        Humano jugador = null;
        
        switch (clase) {
            case ANARCHIST:
                jugador = new Anarchist(tu, this.batalla);
                break;
            case FANATIC:
                jugador = new Fanatic(tu, this.batalla);
                break;
            case TEMPLAR:
                jugador = new Templar(tu, this.batalla);
                break;

            case AVENGER:
                jugador = new Avenger(tu, this.batalla);
                break;
            case BENEFACTOR:
                jugador = new Benefactor(tu, this.batalla);
                break;
            case GUARDIAN:
                jugador = new Guardian(tu, this.batalla);
                break;

            case MERCHANT:
                jugador = new Merchant(tu, this.batalla);
                break;
            case TRAVELER:
                jugador = new Traveler(tu, this.batalla);
                break;
            case VISIONARY:
                jugador = new Visionary(tu, this.batalla);
                break;

            case DUMMYGENERIC:
                jugador = new DummyGeneric();
                break;
            case DUMMYCANNON:
                jugador = new DummyCannon();
                break;
            case DUMMYWHEEL:
                jugador = new DummyWheel();
                break;
            case DUMMYTRENCH:
                jugador = new DummyTrench();
                break;
        }

        return jugador;
    }

    @Override
    public void show() {
        super.show();

        this.tu.setGenesis(genesis);

        this.humanosEnemigos.forEach(humano -> humano.setGenesis(genesis));
        this.humanosAliados.forEach(humano -> humano.setGenesis(genesis));

        this.maquinasEnemigas.forEach(maquina -> maquina.setGenesis(genesis));
        this.maquinasAliadas.forEach(maquina -> maquina.setGenesis(genesis));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        genesis.begin();

        this.tu.actualizarEstado();

        this.humanosEnemigos.forEach(humano -> humano.actualizarEstado());
        this.humanosAliados.forEach(humano -> humano.actualizarEstado());

        this.maquinasEnemigas.forEach(maquina -> maquina.actualizarEstado());
        this.maquinasAliadas.forEach(maquina -> maquina.actualizarEstado());

        genesis.end();

        this.tu.jugar(camara, this);

        this.humanosEnemigos.forEach(humano -> humano.jugar(camara, this));
        this.humanosAliados.forEach(humano -> humano.jugar(camara, this));

        this.maquinasEnemigas.forEach(maquina -> maquina.jugar(camara, this));
        this.maquinasAliadas.forEach(maquina -> maquina.jugar(camara, this));

        this.controlColisiones();
        this.controlResultado();
    }

    private void controlColisiones() {
        this.maquinasEnemigas.forEach(maquina -> {
            if (maquina.overlaps(this.tu)) {
                if (!cmaquina) {
                    this.tu.colisionJugador(maquina);
                    maquina.colisionJugador(this.tu);

                    cmaquina = true;
                }
            } else {
                cmaquina = false;
            }
        });

        this.maquinasAliadas.forEach(maquina -> {
            if (maquina.overlaps(this.tu)) {
                if (!cmaquina) {
                    this.tu.colisionJugador(maquina);
                    maquina.colisionJugador(this.tu);

                    cmaquina = true;
                }
            } else {
                cmaquina = false;
            }
        });

        this.humanosEnemigos.forEach(humano -> {
            if (humano.overlaps(tu)) {
                if (!this.chumano) {
                    tu.colisionJugador(humano);
                    humano.colisionJugador(tu);

                    this.chumano = true;
                }
            } else {
                this.chumano = false;
            }
        });

        this.humanosAliados.forEach(humano -> {
            if (humano.overlaps(tu)) {
                if (!this.chumano) {
                    tu.colisionJugador(humano);
                    humano.colisionJugador(tu);

                    this.chumano = true;
                }
            } else {
                this.chumano = false;
            }
        });
    }

    private void controlResultado() {
        if (this.batalla) {
            boolean derrota = tu.getVida() <= 0,
                    victoria = maquinasEnemigas.stream()
                            .map(maquina -> maquina.getVida())
                            .reduce(Integer::sum)
                            .get() <= 0;

            if (derrota) {
                new MenuDerrota().mostrar();
            } else if (victoria) {
                new MenuVictoria().mostrar();
            } else {
            }
        }
    }

    @Override
    public void dispose() {
        tu.dispose();

        this.humanosEnemigos.forEach(humano -> humano.dispose());
        this.humanosAliados.forEach(humano -> humano.dispose());

        this.maquinasEnemigas.forEach(maquina -> maquina.dispose());
        this.maquinasAliadas.forEach(maquina -> maquina.dispose());
    }
}
