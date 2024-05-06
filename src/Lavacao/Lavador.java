package Lavacao;

import java.util.List;

// Producer
public class Lavador extends Thread {
    private List<Prato> sujos;
    private Escorredor escorredor;

    public Lavador(String nome, List<Prato> sujos, Escorredor escorredor) {
        super("Lavador " + nome);
        this.sujos = sujos;
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!sujos.isEmpty()) {
            Prato p = null;
            synchronized (sujos) {
                p = sujos.remove(0);
            }
            System.out.println(getName() + " lavando " + p);
            escorredor.colocar(p);
        }
        escorredor.setFim(true);
        System.out.println(getName() + " terminou de lavar");
    }
}
