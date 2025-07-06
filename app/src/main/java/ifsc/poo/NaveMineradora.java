package ifsc.poo;

public class NaveMineradora extends NaveEspacial implements Tripulada {
    private final int energiaLaserMaxima;
    private int energiaLaserAtual;

    public NaveMineradora(int velocidadeMaxima, int tripulacaoMaxima, int energiaLaserMaxima) {
        super(velocidadeMaxima, tripulacaoMaxima);
        this.energiaLaserMaxima = energiaLaserMaxima;
        this.energiaLaserAtual = energiaLaserMaxima;
    }

    public String minerar(int custo) {
        if (velocidadeAtual > (0.1 * velocidadeMaxima)) {
            return formatarMensagem("velocidade muito alta para minerar");
        }
        if (energiaLaserAtual < custo) {
            return formatarMensagem("energia insuficiente para minerar");
        }
        energiaLaserAtual -= custo;
        return formatarMensagem("minerando com custo de %d unidades de energia", custo);
    }

    public String recarregarLaser(int quantidade) {
        energiaLaserAtual = Math.min(energiaLaserAtual + quantidade, energiaLaserMaxima);
        return formatarMensagem("laser recarregado para %d/%d", energiaLaserAtual, energiaLaserMaxima);
    }

    @Override
    public String controlarManual() {
        return formatarMensagem("controle manual ativado");
    }

    // Getters específicos
    public int getEnergiaLaserAtual() { return energiaLaserAtual; }
    public int getEnergiaLaserMaxima() { return energiaLaserMaxima; }
}