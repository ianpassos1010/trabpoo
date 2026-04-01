package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.menu.Comando;

import br.com.ucsal.olimpiadas.services.participanteServices.AdicionarParticipante;
import br.com.ucsal.olimpiadas.services.provaServices.AdicionarProva;
import br.com.ucsal.olimpiadas.services.questaoServices.AdicionarQuestao;
import br.com.ucsal.olimpiadas.services.tentativaServices.AplicarProva;
import br.com.ucsal.olimpiadas.services.tentativaServices.ListarTentativas;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    static final long[] proximoParticipanteId = {1};
    static final long[] proximaProvaId        = {1};
    static final long[] proximaQuestaoId      = {1};
    static final long[] proximaTentativaId    = {1};

    static final List<Participante> participantes = new ArrayList<>();
    static final List<Prova>        provas        = new ArrayList<>();
    static final List<Questao>      questoes      = new ArrayList<>();
    static final List<Tentativa>    tentativas    = new ArrayList<>();

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        seed();


        Map<String, Comando> comandos = new LinkedHashMap<>();
        comandos.put("1", () -> new AdicionarParticipante(participantes, proximoParticipanteId, in).criarParticipante());
        comandos.put("2", () -> new AdicionarProva(provas, proximaProvaId, in).criarProva());
        comandos.put("3", () -> new AdicionarQuestao(questoes, provas, proximaQuestaoId, in).criarQuestao());
        comandos.put("4", () -> new AplicarProva(tentativas, participantes, provas, questoes, proximaTentativaId, in).aplicarProva());
        comandos.put("5", () -> new ListarTentativas(tentativas).listarTentativas());

        while (true) {
            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão (A–E) em uma prova");
            System.out.println("4) Aplicar prova (selecionar participante + prova)");
            System.out.println("5) Listar tentativas (resumo)");
            System.out.println("0) Sair");
            System.out.print("> ");

            String opcao = in.nextLine();

            if ("0".equals(opcao)) {
                System.out.println("tchau");
                return;
            }

            Comando cmd = comandos.get(opcao);
            if (cmd != null) {
                cmd.executar();
            } else {
                System.out.println("opção inválida");
            }
        }
    }

    static void seed() {
        var prova = new Prova();
        prova.setId(proximaProvaId[0]++);
        prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
        provas.add(prova);

        var q1 = new Questao();
        q1.setId(proximaQuestaoId[0]++);
        q1.setProvaId(prova.getId());

        q1.setEnunciado("""
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """);

        q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");
        q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });
        q1.setAlternativaCorreta('C');

        questoes.add(q1);
    }
}