package ifsc.poo;

import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    private Set<NaveEspacial> garagem = new LinkedHashSet<>();
    private Scanner scanner = new Scanner(System.in);

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
        int opcao = -1;
        do {
            exibirMenu();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                processarOpcao(opcao);
            } else {
                scanner.next();
                StdOut.println("Por favor, digite um número válido (0, 1 ou 2).");
            }
        } while (opcao != 0);
        scanner.close();
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

        executarComandoComRetorno(nave::decolar);
        executarComandoComRetorno(() -> nave.acelerar(5));
        executarComandoComRetorno(() -> nave.acelerar(10));
        executarComandoComRetorno(() -> nave.frear(3));
        executarComandoComRetorno(nave::pousar);

        if (nave instanceof Tripulada) {
            executarComandoComRetorno(((Tripulada) nave)::controlarManual);
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

    private void executarComandoComRetorno(java.util.function.Supplier<String> comando) {
        try {
            String resultado = comando.get();
            if (resultado != null && !resultado.isEmpty()) {
                StdOut.println(resultado);
            }
        } catch (Exception e) {
            StdOut.println("Erro ao executar comando: " + e.getMessage());
        }
    }

    private void testarNaveMineradora(NaveMineradora nave) {
        executarComandoComRetorno(() -> nave.minerar(200));
        executarComandoComRetorno(() -> nave.recarregarLaser(300));
    }

    private void testarNaveExploradora(NaveExploradora nave) {
        executarComandoComRetorno(nave::ligarHolofotes);
        executarComandoComRetorno(nave::pousar);
    }

    private void testarNaveCargueira(NaveCargueira nave) {
        executarComandoComRetorno(() -> nave.carregar(4000));
        executarComandoComRetorno(nave::ativarBlindagem);
        executarComandoComRetorno(nave::desativarBlindagem);
    }

    private void testarNaveSentinela(NaveSentinela nave) {
        executarComandoComRetorno(nave::ativarControleAutomatico);
        executarComandoComRetorno(nave::ligarRadar);
        executarComandoComRetorno(() -> nave.acelerar((int)(nave.getVelocidadeMaxima() * 0.95)));
        executarComandoComRetorno(nave::ligarRadar);
    }
}