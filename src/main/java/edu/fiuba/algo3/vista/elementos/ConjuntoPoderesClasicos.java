package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;

public class ConjuntoPoderesClasicos extends ConjuntoPoderes{
    public ConjuntoPoderesClasicos() {
        super();
    }

    @Override
    public void reestablecer(Jugador jugador) {
        botonesPoderes.clear();
        jugador.porCadaPoderClasico(
                (a,e) -> {
                    botonesPoderes.add(new BotonAnulador(a));
                    botonesPoderes.add(new BotonExclusividad(e));
                }
        );
        actualizarBotones();
    }
}
