package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.elementos.VistaTableroJugadores;
import edu.fiuba.algo3.vista.escenas.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppTests extends Application {


    @Override
    public void start(Stage stage) {

        AlgoHoot a = AlgoHoot.getInstancia();
        a.agregarJugador(new Jugador("jugador1"));
        a.agregarJugador(new Jugador("jugador2"));

        VistaTableroJugadores tableroVF = new VistaTableroJugadores(a.obtenerJugadores(),368,640);
        VistaPreguntaVF vistaVF = new VistaPreguntaVF(stage, 1280, 720,
                new PreguntaVF("los patos saben volar??????", "peces", "sos crak respondiste", new OpcionCorrecta("mas vale"), new OpcionIncorrecta("creo q no")),
                tableroVF);

        VistaTableroJugadores tableroOC = new VistaTableroJugadores(a.obtenerJugadores(),368,640);
        VistaPreguntaOC vistaOC = new VistaPreguntaOC(stage, 1280, 720,
                new PreguntaOC("ordenar segun tengas ganas total no se verifica nada :p", "mesi", "hola", new Opcion("esta va primero"), new Opcion("esta va segundo"), new Opcion("tercera"), new Opcion("esta tambien va primera"), new Opcion("catorce")),
                tableroOC);

        VistaTableroJugadores tableroMC = new VistaTableroJugadores(a.obtenerJugadores(),368,640);
        VistaPreguntaMC vistaMC = new VistaPreguntaMC(stage, 1280, 720,
                new PreguntaMC("seleccionar los argentinos del siguiente grupo de personajes", "cultura","en contra de la creencia popular, batman es uruguayo", new OpcionCorrecta("mesi"), new OpcionIncorrecta("batman"), new OpcionCorrecta("argentinaman"), new OpcionIncorrecta("el chapulin colorao"), new OpcionCorrecta("joe biden")),
                tableroMC);

        VistaTableroJugadores tableroGC = new VistaTableroJugadores(a.obtenerJugadores(),368,640);
        VistaPreguntaGC vistaGC = new VistaPreguntaGC(stage, 1280, 720,
                new PreguntaGC("dividir en grupos de buenos y malos", "etica","swalalala",
                        new Grupo("buenos", new Opcion("robin el hijo d batman"), new Opcion("luke skywalker el hijo d anakin")),
                        new Grupo("malos", new Opcion("anakin el hijo d la fuerza"), new Opcion("el guason hijo d la sociedad corrupta"))),
                tableroGC);

        VistaConfigurarPartida vistaConfig = new VistaConfigurarPartida(stage, 1280, 720);

        VistaTableroJugadores tableroFin = new VistaTableroJugadores(a.obtenerJugadores(),368,640);
        VistaFinPregunta vistaFin = new VistaFinPregunta(stage, 1280, 720,
                "La torta no lleva ni suqwero no crema de leche: la masa se hace con yemas, harina y azúcar, el relleno es DDL, y arriba tiene merengue italiano. Chocotorta para la próxima",
                tableroFin);


        stage.setScene(vistaMC);
        stage.setTitle("tests");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
