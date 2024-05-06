package Lavacao;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    private Main() {}

    public static void main(String[] args) throws Exception {
        List<Prato> sujos = new ArrayList<>();
        Escorredor escorredor = new Escorredor(10);
        List<Prato> limpos = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            sujos.add(new Prato(i));
        }
        System.out.println("Sujos: " + sujos);
        System.out.println("Limpos: " + limpos);
        Lavador donald = new Lavador("Donald", sujos, escorredor);
        Enxugador huguinho = new Enxugador("Huguinho", escorredor, limpos);
        Enxugador zezinho = new Enxugador("Zezinho", escorredor, limpos);
        Enxugador luisinho = new Enxugador("Lusinho", escorredor, limpos);
        donald.setPriority(10); // pode ficar selfish
        huguinho.setPriority(7);
        luisinho.setPriority(1); // pode dar starvation
        // coloca pra ready
        donald.start();
        huguinho.start();
        zezinho.start();
        luisinho.start();
        // aguarda terminarem
        donald.join();
        huguinho.join();
        zezinho.join();
        luisinho.join();
        System.out.println("Sujos: " + sujos);
        System.out.println("Limpos: " + limpos);
        System.out.println(huguinho);
        System.out.println(zezinho);
        System.out.println(luisinho);
    }
}
