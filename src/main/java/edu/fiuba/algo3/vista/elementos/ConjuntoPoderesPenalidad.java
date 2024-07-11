package edu.fiuba.algo3.vista.elementos;

import edu.fiuba.algo3.modelo.AlgoHoot.Jugador;

public class ConjuntoPoderesPenalidad extends ConjuntoPoderes{
    public ConjuntoPoderesPenalidad() {
        super();
    }
    @Override
    public void reestablecer(Jugador jugador) {
        botonesPoderes.clear();
        jugador.porCadaPoderPenalidad(
                (a,d,t) -> {
                    botonesPoderes.add(new BotonAnulador(a));
                    botonesPoderes.add(new BotonDuplicador(d));
                    botonesPoderes.add(new BotonTriplicador(t));
                }
        );
        actualizarBotones();
    }
}
