package br.com.ucsal.olimpiadas.services.tentativaServices;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.Questao;
import br.com.ucsal.olimpiadas.Resposta;
import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.services.participanteServices.IEscolherParticipante;
import br.com.ucsal.olimpiadas.services.provaServices.IEscolherProva;
import br.com.ucsal.olimpiadas.services.questaoServices.IImprimirTabuleiro;

import java.util.List;
import java.util.Scanner;

public class AplicarProva implements IAplicarProva {

    private final List<Tentativa>       tentativas;
    private final List<Participante>    participantes;
    private final List<Prova>           provas;
    private final List<Questao>         questoes;
    private final long[]                proximoId;
    private final Scanner               in;
    private final ICalcularNota         calculadorNota;
    private final IEscolherParticipante escolherParticipante;
    private final IEscolherProva        escolherProva;
    private final IImprimirTabuleiro    imprimirTabuleiro;

    public AplicarProva(List<Tentativa> tentativas, List<Participante> participantes,
                        List<Prova> provas, List<Questao> questoes,
                        long[] proximoId, Scanner in,
                        ICalcularNota calculadorNota,
                        IEscolherParticipante escolherParticipante,
                        IEscolherProva escolherProva,
                        IImprimirTabuleiro imprimirTabuleiro) {
        this.tentativas          = tentativas;
        this.participantes       = participantes;
        this.provas              = provas;
        this.questoes            = questoes;
        this.proximoId           = proximoId;
        this.in                  = in;
        this.calculadorNota      = calculadorNota;
        this.escolherParticipante = escolherParticipante;
        this.escolherProva       = escolherProva;
        this.imprimirTabuleiro   = imprimirTabuleiro;
    }

    @Override
    public void aplicarProva() {
        if (participantes.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (provas.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = escolherParticipante.escolherParticipante();
        if (participanteId == null)
            return;

        var provaId = escolherProva.escolherProva();
        if (provaId == null)
            return;

        var questoesDaProva = questoes.stream().filter(q -> q.getProvaId() == provaId).toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        var tentativa = new Tentativa();
        tentativa.setId(proximoId[0]++);
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        System.out.println("\n--- Início da Prova ---");

        for (var q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            imprimirTabuleiro.imprimirTabuleiroFen(q.getFenInicial());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            var r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativa.getRespostas().add(r);
        }

        tentativas.add(tentativa);

        int nota = calculadorNota.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }
}
