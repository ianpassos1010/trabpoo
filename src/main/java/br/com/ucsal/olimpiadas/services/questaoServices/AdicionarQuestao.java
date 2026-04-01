package br.com.ucsal.olimpiadas.services.questaoServices;

import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.services.provaServices.EscolherProva;

import java.util.List;
import java.util.Scanner;

public class AdicionarQuestao implements IAdicionarQuestao {

    private final List<Questao> questoes;
    private final List<Prova> provas;
    private final long[] proximoId;
    private final Scanner in;

    public AdicionarQuestao(List<Questao> questoes, List<Prova> provas, long[] proximoId, Scanner in) {
        this.questoes = questoes;
        this.provas = provas;
        this.proximoId = proximoId;
        this.in = in;
    }

    public void criarQuestao() {
        if (provas.isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        var provaId = new EscolherProva(provas, in).escolherProva();
        if (provaId == null)
            return;

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var q = new Questao();
        q.setId(proximoId[0]++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}
