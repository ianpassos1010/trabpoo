package br.com.ucsal.olimpiadas.services.participanteServices;

import br.com.ucsal.olimpiadas.Participante;

import java.util.List;
import java.util.Scanner;

public class AdicionarParticipante implements IAdicionarParticipante {

    private final List<Participante> participantes;
    private final long[] proximoId;
    private final Scanner in;

    public AdicionarParticipante(List<Participante> participantes, long[] proximoId, Scanner in) {
        this.participantes = participantes;
        this.proximoId = proximoId;
        this.in = in;
    }

    public void criarParticipante() {
        System.out.print("Nome: ");
        var nome = in.nextLine();

        System.out.print("Email (opcional): ");
        var email = in.nextLine();

        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        var p = new Participante();
        p.setId(proximoId[0]++);
        p.setNome(nome);
        p.setEmail(email);

        participantes.add(p);
        System.out.println("Participante cadastrado: " + p.getId());
    }
}
