package co.edu.uniquindio.proyectofinal.bd1.model;

public class Especialidad {
    private int id;
    private String nombre;
    private String descripcion;
    private int categoria_id;
    private int cod_dane;

    public Especialidad(int i, String n, String d, int c, int cd){
        this.id=i;
        this.nombre=n;
        this.descripcion=d;
        this.categoria_id=c;
        this.cod_dane=cd;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCod_dane() {
        return cod_dane;
    }
}
