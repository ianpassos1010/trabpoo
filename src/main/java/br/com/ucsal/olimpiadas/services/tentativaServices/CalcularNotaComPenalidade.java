package br.com.ucsal.olimpiadas.services.tentativaServices;

import br.com.ucsal.olimpiadas.Tentativa;

/**
 * Estratégia alternativa de cálculo: cada erro cancela um acerto.
 * Nota mínima = 0. Substituível por CalcularNota sem alterar os callers (LSP).
 */
public class CalcularNotaComPenalidade implements ICalcularNota {

    @Override
    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        int erros   = 0;
        for (var r : tentativa.getRespostas()) {
            if (r.isCorreta()) acertos++;
            else               erros++;
        }
        return Math.max(0, acertos - erros);
    }
}
