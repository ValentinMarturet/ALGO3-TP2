package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("AlgoHoot");
        Image icono = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/icono.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);
        AlgoHoot a = AlgoHoot.getInstancia();
        a.agregarJugador(new Jugador("jugador1"));
        a.agregarJugador(new Jugador("jugador2"));

        VistaTableroJugadores tableroVF = new VistaTableroJugadores();
        a.obtenerJugadores().forEach(j -> tableroVF.agregarJugador(j.obtenerNombre(), j.obtenerPuntaje()));
        VistaPreguntaVF vistaVF = new VistaPreguntaVF(stage, 1280, 720,
                new PreguntaVF("los patos saben volar??????", "peces", "sos crak respondiste", new OpcionCorrecta("mas vale"), new OpcionIncorrecta("q van a volar los pajaros esos")),
                tableroVF);

        VistaTableroJugadores tableroOC = new VistaTableroJugadores();
        a.obtenerJugadores().forEach(j -> tableroOC.agregarJugador(j.obtenerNombre(), j.obtenerPuntaje()));
        VistaPreguntaOC vistaOC = new VistaPreguntaOC(stage, 1280, 720,
                new PreguntaOC("ordenar segun tengas ganas total no se verifica nada :p", "mesi", "hola", new Opcion("esta va primero"), new Opcion("esta va segundo"), new Opcion("tercera"), new Opcion("esta tambien va primera"), new Opcion("catorce")),
                tableroOC);

        VistaTableroJugadores tableroMC = new VistaTableroJugadores();
        a.obtenerJugadores().forEach(j -> tableroMC.agregarJugador(j.obtenerNombre(), j.obtenerPuntaje()));
        VistaPreguntaMC vistaMC = new VistaPreguntaMC(stage, 1280, 720,
                new PreguntaMC("seleccionar los argentinos del siguiente grupo de personajes", "cultura","en contra de la creencia popular, batman es uruguayo", new OpcionCorrecta("mesi"), new OpcionIncorrecta("batman"), new OpcionCorrecta("argentinaman"), new OpcionIncorrecta("el chapulin colorao"), new OpcionCorrecta("joe biden")),
                tableroMC);

        VistaTableroJugadores tableroGC = new VistaTableroJugadores();
        a.obtenerJugadores().forEach(j -> tableroGC.agregarJugador(j.obtenerNombre(), j.obtenerPuntaje()));
        VistaPreguntaGC vistaGC = new VistaPreguntaGC(stage, 1280, 720,
                new PreguntaGC("dividir en grupos de buenos y malos", "etica","swalalala",
                        new Grupo("buenos", new Opcion("robin el hijo d batman"), new Opcion("luke skywalker el hijo d anakin")),
                        new Grupo("malos", new Opcion("anakin el hijo d la fuerza"), new Opcion("el guason hijo d la sociedad corrupta"))),
                tableroGC);

        VistaConfigurarPartida vistaConfig = new VistaConfigurarPartida(stage, 1280, 720);

        VistaTableroJugadores tableroFin = new VistaTableroJugadores();
        a.obtenerJugadores().forEach(j -> {
            tableroFin.agregarJugador(j.obtenerNombre(), j.obtenerPuntaje());
        });
        VistaFinPregunta vistaFin = new VistaFinPregunta(stage, 1280, 720,
                "La torta no lleva ni suero no crema de leche: la masa se hace con yemas, harina y azúcar, el relleno es DDL, y arriba tiene merengue italiano. Chocotorta para la próxima",
                tableroFin);

        stage.setScene(vistaFin);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}