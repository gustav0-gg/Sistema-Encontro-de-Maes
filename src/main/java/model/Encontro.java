package model;

import java.time.LocalDate;

public class Encontro {

    private int id_encontro;
    private LocalDate data_encontro;
    private int realizado; // 1 = realizado, 0 = não realizado

    public Encontro(int id_encontro, LocalDate data_encontro, int realizado) {
        this.id_encontro = id_encontro;
        this.data_encontro = data_encontro;
        this.realizado = realizado;
    }

    public int getId_encontro() { return id_encontro; }
    public void setId_encontro(int id_encontro) { this.id_encontro = id_encontro; }

    public LocalDate getData_encontro() { return data_encontro; }
    public void setData_encontro(LocalDate data_encontro) { this.data_encontro = data_encontro; }

    public int getRealizado() { return realizado; }
    public void setRealizado(int realizado) { this.realizado = realizado; }
}
