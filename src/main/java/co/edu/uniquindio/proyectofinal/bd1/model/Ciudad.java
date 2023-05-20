package co.edu.uniquindio.proyectofinal.bd1.model;

public class Ciudad {

    private int id;
    private String nombre;
    private String codigoDane;
//    private Departamento departamento= new Departamento();

    public Ciudad(int id,String nombre, String codigoDane, String codigo_Dep){
        this.id=id;
        this.nombre=nombre;
        this.codigoDane=codigoDane;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigoDane;
    }

    public void setCodigo(String codigo) {
        this.codigoDane = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
