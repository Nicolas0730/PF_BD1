package co.edu.uniquindio.proyectofinal.bd1.model;

public class Departamento {

    private int id;
    private String nombre;

    public Departamento(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
