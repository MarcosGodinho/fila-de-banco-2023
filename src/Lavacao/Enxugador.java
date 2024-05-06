package Lavacao;

import java.util.List;

// Consumer
public class Enxugador extends Thread {
    private Escorredor escorredor;
    private List<Prato> limpos;
    private int pratosSecados;

    public Enxugador(String nome, Escorredor escorredor, List<Prato> limpos) {
        super("Enxugador " + nome);
        this.escorredor = escorredor;
        this.limpos = limpos;
    }

    @Override
    public void run() {
        while (true) {
            Prato p = this.escorredor.retirar();
            if (p != null) {
                System.out.println(getName() + " enxugando " + p);
                pratosSecados++;
                synchronized (limpos) {
                    limpos.add(p);
                }
            } else {
                System.out.println(getName() + " acabou de enxugar");
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Enxugador " + getName() + " : " + pratosSecados;
    }
}