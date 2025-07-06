package ifsc.poo;

public abstract class NaveEspacial {
    private static int contadorId = 1;
    protected final int id;
    protected int velocidadeAtual;
    protected final int velocidadeMaxima;
    protected int tripulacaoAtual;
    protected final int tripulacaoMaxima;

    public NaveEspacial(int velocidadeMaxima, int tripulacaoMaxima) {
        this.id = contadorId++;
        this.velocidadeAtual = 0;
        this.velocidadeMaxima = validarVelocidadeMaxima(velocidadeMaxima);
        this.tripulacaoAtual = 0;
        this.tripulacaoMaxima = validarTripulacaoMaxima(tripulacaoMaxima);
    }

    protected int validarVelocidadeMaxima(int velocidade) {
        // definindo velocidade maxima das Naves Blindadas
        if (this instanceof Blindada) {
            return Math.min(velocidade, 14);
        }
        // definindo velocidade máxima das outras Naves
        return Math.min(velocidade, 20);
    }

    protected int validarTripulacaoMaxima(int tripulacao) {
        // definindo que as Naves Autonomas nao podem ter tripulantes
        if (this instanceof Autonoma) {
            return 0;
        }
        // definindo que as Naves Tripuladas devem ter entre 2 e 10 tripulantes
        if (this instanceof Tripulada) {
            return Math.max(2, Math.min(tripulacao, 10));
        }
        // definindo que as outras Naves devem ter no minimo 10 tripulantes
        return Math.max(tripulacao, 10);
    }

    public String acelerar(int intensidade) {
        int novaVelocidade = velocidadeAtual + intensidade;
        if (novaVelocidade > velocidadeMaxima) {
            velocidadeAtual = velocidadeMaxima;
            return formatarMensagem("velocidade máxima alcançada (%d Mm/h)", velocidadeMaxima);
        }
        velocidadeAtual = novaVelocidade;
        return formatarMensagem("acelerando para %d Mm/h", velocidadeAtual);
    }

    public String frear(int intensidade) {
        int novaVelocidade = velocidadeAtual - intensidade;
        if (novaVelocidade < 0) {
            velocidadeAtual = 0;
            return formatarMensagem("parada completa");
        }
        velocidadeAtual = novaVelocidade;
        return formatarMensagem("freando para %d Mm/h", velocidadeAtual);
    }

    public String pousar() {
        if (velocidadeAtual == 0) {
            return formatarMensagem("pousando");
        }
        return formatarMensagem("não é possível pousar em movimento (%d Mm/h)", velocidadeAtual);
    }

    public String decolar() {
        return formatarMensagem("decolando");
    }

    protected String formatarMensagem(String formato, Object... args) {
        return String.format("%s(ID#%04d): " + formato,
                getClass().getSimpleName(),
                id,
                args);
    }

    // getters
    public int getId() { return id; }
    public int getVelocidadeAtual() { return velocidadeAtual; }
    public int getVelocidadeMaxima() { return velocidadeMaxima; }
    public int getTripulacaoAtual() { return tripulacaoAtual; }
    public int getTripulacaoMaxima() { return tripulacaoMaxima; }
}