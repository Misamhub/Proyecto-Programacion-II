
package Entities;


public class EquipoNacional extends Equipo {
    private String departamento;

    public EquipoNacional() {
    }

    public EquipoNacional(String Nombre,  String departamento) {
        super(Nombre);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
}
