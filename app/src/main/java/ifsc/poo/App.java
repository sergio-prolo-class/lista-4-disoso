package ifsc.poo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedHashSet;
import java.util.Set;

public class App {
    private Set<NaveEspacial> garagem = new LinkedHashSet<>();

    public static void main(String[] args) {
        App app = new App();
        app.inicializarNavesExemplo();
        app.executarMenu();
    }

    private void inicializarNavesExemplo() {
        garagem.add(new NaveMineradora(18, 5, 1000));
        garagem.add(new NaveExploradora(20, 3));
        garagem.add(new NaveCargueira(14, 10, 5000));
        garagem.add(new NaveSentinela(16));
    }

    private void executarMenu() {
        int opcao;
        do {
            exibirMenu();
            opcao = StdIn.readInt();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void exibirMenu() {
        StdOut.println("\n=== Sistema de Gerenciamento de Frota Espacial ===");
        StdOut.println("1 - Listar Naves");
        StdOut.println("2 - Testar Todas as Naves");
        StdOut.println("0 - Sair");
        StdOut.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                listarNaves();
                break;
            case 2:
                testarTodasNaves();
                break;
            case 0:
                StdOut.println("Encerrando o sistema...");
                break;
            default:
                StdOut.println("Opção inválida!");
        }
    }

    private void listarNaves() {
        StdOut.println("\n=== Naves na Garagem ===");
        garagem.forEach(nave -> {
            StdOut.printf("%s (ID#%04d) - Vel: %d/%d Mm/h | Trip: %d/%d%n",
                    nave.getClass().getSimpleName(),
                    nave.getId(),
                    nave.getVelocidadeAtual(),
                    nave.getVelocidadeMaxima(),
                    nave.getTripulacaoAtual(),
                    nave.getTripulacaoMaxima());
        });
    }

    private void testarTodasNaves() {
        StdOut.println("\n=== Testando Todas as Naves ===");
        garagem.forEach(this::testarNave);
    }

    private void testarNave(NaveEspacial nave) {
        StdOut.println("\n" + nave.getClass().getSimpleName() + " (ID#" + String.format("%04d", nave.getId()) + ")");

        // fazendo um teste basico de voo
        executarComando(nave::decolar);
        executarComando(() -> nave.acelerar(5));
        executarComando(() -> nave.acelerar(10));
        executarComando(() -> nave.frear(3));
        executarComando(nave::pousar);

        // fazendo teste de interfaces
        if (nave instanceof Tripulada) {
            executarComando(((Tripulada) nave)::controlarManual);
        }

        if (nave instanceof NaveMineradora) {
            testarNaveMineradora((NaveMineradora) nave);
        }

        if (nave instanceof NaveExploradora) {
            testarNaveExploradora((NaveExploradora) nave);
        }

        if (nave instanceof NaveCargueira) {
            testarNaveCargueira((NaveCargueira) nave);
        }

        if (nave instanceof NaveSentinela) {
            testarNaveSentinela((NaveSentinela) nave);
        }
    }

    private void executarComando(Runnable comando) {
        try {
            StdOut.println(comando.toString().split("::")[1] + ": " + ((java.util.function.Supplier<String>) comando).get());
        } catch (Exception e) {
            StdOut.println("Erro ao executar comando: " + e.getMessage());
        }
    }

    private void testarNaveMineradora(NaveMineradora nave) {
        executarComando(() -> nave.minerar(200));
        executarComando(() -> nave.recarregarLaser(300));
    }

    private void testarNaveExploradora(NaveExploradora nave) {
        executarComando(nave::ligarHolofotes);
        executarComando(nave::pousar); // Deve desligar holofotes
    }

    private void testarNaveCargueira(NaveCargueira nave) {
        executarComando(() -> nave.carregar(4000));
        executarComando(nave::ativarBlindagem);
        executarComando(nave::desativarBlindagem);
    }

    private void testarNaveSentinela(NaveSentinela nave) {
        executarComando(nave::ativarControleAutomatico);
        executarComando(nave::ligarRadar);
        executarComando(() -> nave.acelerar((int)(nave.getVelocidadeMaxima() * 0.95)));
        executarComando(nave::ligarRadar); // Deve falhar por alta velocidade
    }
}