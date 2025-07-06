package ifsc.poo;

public class NaveSentinela extends NaveEspacial implements Autonoma {
    private boolean radarAtivo;

    public NaveSentinela(int velocidadeMaxima) {
        super(velocidadeMaxima, 0);
        this.radarAtivo = false;
    }

    public String ligarRadar() {
        if (velocidadeAtual > 0.9 * velocidadeMaxima) {
            return formatarMensagem("velocidade muito alta para ativar radar");
        }
        radarAtivo = true;
        return formatarMensagem("radar ativado");
    }

    public String desligarRadar() {
        radarAtivo = false;
        return formatarMensagem("radar desligado");
    }

    @Override
    public String ativarControleAutomatico() {
        return formatarMensagem("controle automático ativado");
    }

    public boolean isRadarAtivo() { return radarAtivo; }
}