package com.atarion.game.entidad.jugador.maquina;

import com.atarion.game.entidad.objeto.Escombro;
import com.atarion.game.entidad.jugador.Jugador;
import com.atarion.game.entidad.objeto.Laser;
import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import java.util.Iterator;
import java.util.LinkedList;

public class Claudius extends Maquina {

    private Laser laser = null;
    private boolean carga = false, colisionlaser = false;
    private Texture escombros, bordes, el, ninguno;

    private enum Modo {
        ESCOMBROS, BORDES, EL, NINGUNO
    }
    private Modo modo = Modo.NINGUNO;

    public Claudius(boolean batalla) {
        super(batalla);

        this.velocidad *= 4;
        this.textura = new Texture(Gdx.files.internal("textures/claudius.png"));
        this.tiempoactivo = 20;
        this.y = 300;

        this.escombros = new Texture(Gdx.files.internal("textures/endurancedebris.png"));
        this.bordes = new Texture(Gdx.files.internal("textures/enduranceborders.png"));
        this.el = new Texture(Gdx.files.internal("textures/endurancehimself.png"));
        this.ninguno = new Texture(Gdx.files.internal("textures/claudius.png"));
    }

    @Override
    public MensajeJSON enviarEstado() {
        MensajeJSON estado = super.enviarEstado();
        estado.escribirAtributo("carga", "" + this.carga);
        estado.escribirAtributo("colisionlaser", "" + this.colisionlaser);
        estado.escribirAtributo("modo", this.modo.toString());

        if (this.laser == null) {
            estado.escribirAtributo("laser", "null");
        } else {
            estado.escribirAtributo("laser", this.laser.enviarEstado().getMensaje());
        }

        return estado;
    }

    @Override
    public void recibirEstado(String estado) {
        /*super.recibirEstado(estado); 
        
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        if(objeto.getString("laser").equals("null"))
        { this.laser = null; }
        else
        {
            if(this.laser != null)
            { this.laser = new Laser(genesis,objeto.getString("proyectil")); }
        }
        this.colisionlaser = objeto.getBoolean("colisionlaser");*/
    }

    @Override
    public void actualizarEstado() {
        super.actualizarEstado();

        if (laser != null) {
            laser.actualizarEstado();
        }
    }

    @Override
    public void jugar(Camera camara, Escena escena) {
        super.jugar(camara, escena);

        if (laser != null) {
            this.enemigos.stream().forEach(enemigo -> {
                if (laser.overlaps(enemigo)) {
                    if (!this.colisionlaser) {
                        enemigo.setVida(enemigo.getVida() - 20);
                        Gdx.app.log("INFO", "Has recibido 50 del proyectil. Te quedan " + enemigo.getVida() + " vidas.");
                        this.colisionlaser = true;
                    }
                } else {
                    this.colisionlaser = false;
                }
            });
        }

        switch (modo) {
            case ESCOMBROS:
                this.enemigos.stream().forEach(enemigo -> {
                    enemigo.setInmune(false);
                });
                break;
            case BORDES:
                this.enemigos.stream().forEach(enemigo -> {
                    if (enemigo.getX() == 0
                            || enemigo.getX() == 1000
                            || enemigo.getY() == 0
                            || enemigo.getY() == 800) {
                        Gdx.app.log("INFO", "De repente, te hiciste vulnerable a los bordes. 200 puntos de daño y te quedan " + this.getVida() + "vidas");
                        enemigo.setVida(enemigo.getVida() - 200);
                    }
                });
                break;
            case EL:
                this.enemigos.stream().forEach(enemigo -> {
                    if (this.overlaps(enemigo)) {
                        Gdx.app.log("INFO", "De repente, te hiciste muy vulnerable a la bola. 200 puntos de daño y te quedan " + this.getVida() + "vidas");
                        enemigo.setVida(enemigo.getVida() - 200);
                    }
                });
                break;
        }
    }

    @Override
    public void agregarEnemigo(Jugador jugador) {
        this.enemigos.add(jugador);
    }

    public void esquivar(LinkedList<Escombro> escombros) {
        if (carga) {
            this.enemigos.stream().forEach(enemigo -> {
                if (enemigo.getX() > this.getX()) {
                    decision = 1;
                } else if (enemigo.getX() < this.getX()) {
                    decision = 2;
                } else {
                    decision = 0;
                }
            });
        } else {
            Iterator<Escombro> i = escombros.iterator();
            while (i.hasNext()) {
                Escombro escombro = i.next();

                if (escombro.getX() == this.x
                        && escombro.getY() - this.y <= 150) {
                    decision = 1;
                } else if (escombro.getX() > this.x
                        && escombro.getX() - this.x <= 150
                        && escombro.getY() - this.y <= 150) {
                    decision = 2;
                } else if (escombro.getX() < this.x
                        && this.x - escombro.getX() <= 150
                        && escombro.getY() - this.y <= 150) {
                    decision = 1;
                } else {
                    decision = 0;
                }
            }
        }
    }

    @Override
    protected void controlBordes(Escena escena) {
        if (this.y < 0) {
            this.y = escena.getTotalHeight() - 20;
        }

        if (this.y > escena.getTotalHeight() - 20) {
            this.y = 0;
        }

        if (this.x < 0) {
            this.x = escena.getTotalWidth() - 80;
        }

        if (this.x > escena.getTotalWidth() - 80) {
            this.x = 0;
        }
    }

    @Override
    protected void controlEspecial() {
        if (parado && activado) {
            this.modo = Modo.NINGUNO;
            this.textura = this.ninguno;
        }

        super.controlEspecial();
    }

    @Override
    public void activarEspecial() {
        this.carga = true;

        this.modo = Modo.NINGUNO;
        this.textura = this.ninguno;

        int probabilidad = (int) (Math.random() * 3 + 1);
        switch (probabilidad) {
            case 1:
                laser = new Laser(genesis, "textures/laserdebris.png", this);
                break;
            case 2:
                laser = new Laser(genesis, "textures/laserborder.png", this);
                break;
            case 3:
                laser = new Laser(genesis, "textures/laserhimself.png", this);
                break;
        }
    }

    @Override
    public void desactivarEspecial() {
        this.carga = false;
        this.laser = null;

        int probabilidad = (int) (Math.random() * 3 + 1);
        switch (probabilidad) {
            case 1:
                modo = Modo.ESCOMBROS;
                this.textura = this.escombros;
                break;
            case 2:
                modo = Modo.BORDES;
                this.textura = this.bordes;
                break;
            case 3:
                modo = Modo.EL;
                this.textura = this.el;
                break;
        }

        if (!modo.equals(Modo.ESCOMBROS)) {
            this.enemigos.stream().forEach(enemigo -> {
                enemigo.setInmune(true);
            });
        }
    }

    @Override
    public void setVida(int vida) {
        if (this.modo.equals(Modo.NINGUNO)) {
            super.setVida(vida);
        }
    }

    @Override
    protected void faseDos() {
    }

    @Override
    protected void faseTres() {
    }

    @Override
    protected void faseCuatro() {
    }

    @Override
    protected void faseCinco() {
    }

    @Override
    protected void faseSeis() {
    }

    @Override
    protected void faseSiete() {
    }

    @Override
    protected void faseOcho() {
    }

    @Override
    protected void faseNueve() {
    }

    @Override
    protected void faseDiez() {
    }
}
