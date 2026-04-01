package br.com.ucsal.olimpiadas.services.tentativaServices;

import br.com.ucsal.olimpiadas.Tentativa;

import java.util.List;

public class ListarTentativas {

    private final List<Tentativa> tentativas;

    public ListarTentativas(List<Tentativa> tentativas) {
        this.tentativas = tentativas;
    }

    public void listarTentativas() {
        System.out.println("\n--- Tentativas ---");
        for (var t : tentativas) {
            int nota = new CalcularNota().calcularNota(t);
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(), nota, t.getRespostas().size());
        }
    }
}
