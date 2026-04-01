package br.com.ucsal.olimpiadas.entites;

public class Questao {
    private long id;
    private long provaId;

    private String enunciado;
    private String[] alternativas = new String[5];
    private char alternativaCorreta;

    private String fenInicial;

    public Questao(long id, long provaId, String enunciado, String[] alternativas, char alternativaCorreta, String fenInicial) {
        this.id = id;
        this.provaId = provaId;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.alternativaCorreta = alternativaCorreta;
        this.fenInicial = fenInicial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProvaId() {
        return provaId;
    }

    public void setProvaId(long provaId) {
        this.provaId = provaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public char getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public String getFenInicial() {
        return fenInicial;
    }

    public void setFenInicial(String fenInicial) {
        this.fenInicial = fenInicial;
    }
}
