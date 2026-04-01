package br.com.ucsal.olimpiadas.services.tentativaServices;

import br.com.ucsal.olimpiadas.Tentativa;

import java.util.List;

public class ListarTentativas implements IListarTentativas {

    private final List<Tentativa> tentativas;
    private final ICalcularNota   calculadorNota;

    public ListarTentativas(List<Tentativa> tentativas, ICalcularNota calculadorNota) {
        this.tentativas     = tentativas;
        this.calculadorNota = calculadorNota;
    }

    public void listarTentativas() {
        System.out.println("\n--- Tentativas ---");
        for (var t : tentativas) {
            int nota = calculadorNota.calcularNota(t);
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(), nota, t.getRespostas().size());
        }
    }
}
