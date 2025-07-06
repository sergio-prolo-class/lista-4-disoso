package ifsc.poo;

public class NaveCargueira extends NaveEspacial implements Blindada {
    private final int capacidadeMaxima;
    private int cargaAtual;
    private boolean blindagemAtiva;

    public NaveCargueira(int velocidadeMaxima, int tripulacaoMaxima, int capacidadeMaxima) {
        super(velocidadeMaxima, tripulacaoMaxima);
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargaAtual = 0;
        this.blindagemAtiva = false;
    }

    public String carregar(int peso) {
        int novaCarga = cargaAtual + peso;
        if (novaCarga > capacidadeMaxima) {
            cargaAtual = capacidadeMaxima;
            return formatarMensagem("carga máxima atingida (%d/%d)", cargaAtual, capacidadeMaxima);
        }
        cargaAtual = novaCarga;

        if (cargaAtual > 0.7 * capacidadeMaxima && !blindagemAtiva) {
            ativarBlindagem();
        }

        return formatarMensagem("carregando %d unidades (%d/%d)", peso, cargaAtual, capacidadeMaxima);
    }

    public String descarregar(int peso) {
        int novaCarga = cargaAtual - peso;
        if (novaCarga < 0) {
            cargaAtual = 0;
            return formatarMensagem("carga totalmente descarregada");
        }
        cargaAtual = novaCarga;
        return formatarMensagem("descarregando %d unidades (%d/%d)", peso, cargaAtual, capacidadeMaxima);
    }

    @Override
    public String ativarBlindagem() {
        blindagemAtiva = true;
        return formatarMensagem("blindagem ativada");
    }

    @Override
    public String desativarBlindagem() {
        if (velocidadeAtual == 0) {
            blindagemAtiva = false;
            return formatarMensagem("blindagem desativada");
        }
        return formatarMensagem("não é possível desativar blindagem em movimento");
    }

    // getters
    public int getCargaAtual() { return cargaAtual; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public boolean isBlindagemAtiva() { return blindagemAtiva; }
}