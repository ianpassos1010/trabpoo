package br.com.ucsal.olimpiadas.services.provaServices;

import br.com.ucsal.olimpiadas.Prova;

import java.util.List;
import java.util.Scanner;

public class EscolherProva {

    private final List<Prova> provas;
    private final Scanner in;

    public EscolherProva(List<Prova> provas, Scanner in) {
        this.provas = provas;
        this.in = in;
    }

    public Long escolherProva() {
        System.out.println("\nProvas:");
        for (var p : provas) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = provas.stream().anyMatch(p -> p.getId() == id);
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
