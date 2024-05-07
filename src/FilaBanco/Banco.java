package FilaBanco;

import java.util.LinkedList;
import java.util.List;

public class Banco {
    private List<Cliente> fila = new LinkedList<>();
    private Caixa[] caixas;

    public Banco(int numCaixas) {
        caixas = new Caixa[numCaixas];
        for (int i = 0; i < numCaixas; i++) {
            caixas[i] = new Caixa(fila, i + 1);
            new Thread(caixas[i]).start();
        }
    }

    public void adicionarCliente(Cliente cliente) {
        synchronized (fila) {
            fila.add(cliente);
            fila.notifyAll();
        }
    }

    public void fechar() {
        for (Caixa caixa : caixas) {
            caixa.interrupt();
            try {
                caixa.join(); // espera a thread caixa terminar
            } catch (InterruptedException e) {}
        }
    }

    public void imprimirEstatisticas() {
        int totalClientes = 0;
        long maxEspera = 0;
        long maxAtendimento = 0;
        long totalTempo = 0;

        for (Caixa caixa : caixas) {
            totalClientes += caixa.getClientesAtendidos();
            maxEspera = Math.max(maxEspera, caixa.getTempoMaxEspera());
            maxAtendimento = Math.max(maxAtendimento, caixa.getTempoMaxAtendimento());
            totalTempo += caixa.getTempoMedio() * caixa.getClientesAtendidos();
        }

        System.out.println("Total de clientes atendidos: " + totalClientes);
        System.out.println("Tempo máximo de espera: " + maxEspera);
        System.out.println("Tempo máximo de atendimento: " + maxAtendimento);
        System.out.println("Tempo médio no banco: " + totalTempo / totalClientes);
    }
}