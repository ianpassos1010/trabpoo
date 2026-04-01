package br.com.ucsal.olimpiadas.services.tentativaServices;

import br.com.ucsal.olimpiadas.Tentativa;

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
