package ifsc.poo;

public class NaveExploradora extends NaveEspacial implements Tripulada {
    private boolean holofotesLigados;

    public NaveExploradora(int velocidadeMaxima, int tripulacaoMaxima) {
        super(velocidadeMaxima, tripulacaoMaxima);
        this.holofotesLigados = false;
    }

    public String ligarHolofotes() {
        holofotesLigados = true;
        return formatarMensagem("holofotes ligados");
    }

    public String desligarHolofotes() {
        holofotesLigados = false;
        return formatarMensagem("holofotes desligados");
    }

    @Override
    public String pousar() {
        String mensagem = super.pousar();
        if (velocidadeAtual == 0 && holofotesLigados) {
            desligarHolofotes();
        }
        return mensagem;
    }

    @Override
    public String controlarManual() {
        return formatarMensagem("controle manual ativado");
    }

    public boolean isHolofotesLigados() { return holofotesLigados; }
}