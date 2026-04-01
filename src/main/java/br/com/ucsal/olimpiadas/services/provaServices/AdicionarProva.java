package br.com.ucsal.olimpiadas.services.provaServices;

import br.com.ucsal.olimpiadas.Prova;

import java.util.List;
import java.util.Scanner;

public class AdicionarProva {

    private final List<Prova> provas;
    private final long[] proximoId;
    private final Scanner in;

    public AdicionarProva(List<Prova> provas, long[] proximoId, Scanner in) {
        this.provas = provas;
        this.proximoId = proximoId;
        this.in = in;
    }

    public void criarProva() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        var prova = new Prova();
        prova.setId(proximoId[0]++);
        prova.setTitulo(titulo);

        provas.add(prova);
        System.out.println("Prova criada: " + prova.getId());
    }
}
