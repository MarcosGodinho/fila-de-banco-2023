package Lavacao;
import java.util.ArrayList;
import java.util.List;

//
public class Escorredor {
    private int capacidade;
    private List<Prato> fila;
    private boolean fim;

    public Escorredor(int capacidade) {
        this.capacidade = capacidade;
        this.fila = new ArrayList<>(capacidade);
    }

    public boolean isFim() {
        return fim;
    }

    public void setFim(boolean fim) {
        this.fim = fim;
    }

    public int getQtdPratos() {
        return this.fila.size();
    }

    public synchronized void colocar(Prato p) {
        while (fila.size() >= capacidade) {
            try {
                System.out.println(Thread.currentThread().getName() + " Escorredor cheio");
                wait(1500);
            } catch (InterruptedException e) {}
        }
        this.fila.add(p);
        notifyAll();
    }

    public synchronized Prato retirar() {
        while (fila.isEmpty() && !fim) {
            try {
                System.out.println(Thread.currentThread().getName() + " Escorredor vazio");
                wait(1500);
            } catch (InterruptedException e) {}
        }
        if (fim) {
            return null;
        }
        notifyAll();
        return this.fila.remove(0);
    }
}
