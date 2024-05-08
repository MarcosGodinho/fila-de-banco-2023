package FilaBanco;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int numCaixas = 1; numCaixas <= 5; numCaixas++) {
            System.out.println("Simulação com " + numCaixas + " caixas:");
            Banco banco = new Banco(numCaixas); // número de caixas

            long inicio = System.currentTimeMillis();
            while (System.currentTimeMillis() - inicio < 720000) { // 5 minutos
                Thread.sleep((int)(Math.random() * 45000 + 5000)); // intervalo de chegada
                banco.adicionarCliente(new Cliente(System.currentTimeMillis(), (int)(Math.random() * 90000 + 30000))); // tempo de atendimento
            }

            banco.fechar();
            banco.imprimirEstatisticas();
            System.out.println();
        }
    }
}