package br.com.ucsal.olimpiadas.services.participanteServices;

import br.com.ucsal.olimpiadas.Participante;

import java.util.List;
import java.util.Scanner;

public class EscolherParticipante implements IEscolherParticipante {

    private final List<Participante> participantes;
    private final Scanner in;

    public EscolherParticipante(List<Participante> participantes, Scanner in) {
        this.participantes = participantes;
        this.in = in;
    }

    public Long escolherParticipante() {
        System.out.println("\nParticipantes:");
        for (var p : participantes) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = participantes.stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}
