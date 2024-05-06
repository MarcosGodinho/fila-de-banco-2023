package Lavacao;

public class Prato {
    private int id;

    public Prato(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prato " + id;
    }
}
