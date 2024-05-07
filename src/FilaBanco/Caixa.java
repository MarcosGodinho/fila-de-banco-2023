package FilaBanco;

import java.util.List;
import java.util.concurrent.BlockingQueue;

class Caixa extends Thread {
    private List<Cliente> fila;
    private int id;
    private long tempoMaxAtendimento = 0;
    private long tempoMaxEspera = 0;
    private long tempoTotal = 0;
    private int clientesAtendidos = 0;

    public Caixa(List<Cliente> fila, int id) {
        this.fila = fila;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cliente cliente;
                synchronized (fila) {
                    while (fila.isEmpty()) {
                        fila.wait();
                    }
                    cliente = fila.remove(0);
                }
                atualizarTempoEspera(cliente);
                atenderCliente(cliente);
                atualizarTempoTotal(cliente);
            }
        } catch (InterruptedException e) {
            System.out.println("Caixa " + id + " terminou o atendimento.");
        }
    }

    public long getTempoMaxAtendimento() {
        return tempoMaxAtendimento / 1000;
    }

    public long getTempoMaxEspera() {
        return tempoMaxEspera / 1000;
    }

    public long getTempoMedio() {
        return (tempoTotal / clientesAtendidos) / 1000;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    private void atualizarTempoEspera(Cliente cliente) {
        long tempoEspera = System.currentTimeMillis() - cliente.tempoChegada;
        tempoMaxEspera = Math.max(tempoMaxEspera, tempoEspera);
    }

    private void atenderCliente(Cliente cliente) throws InterruptedException {
        Thread.sleep(cliente.tempoAtendimento);
        tempoMaxAtendimento = Math.max(tempoMaxAtendimento, cliente.tempoAtendimento);
    }

    private void atualizarTempoTotal(Cliente cliente) {
        tempoTotal += System.currentTimeMillis() - cliente.tempoChegada;
        clientesAtendidos++;
    }
}