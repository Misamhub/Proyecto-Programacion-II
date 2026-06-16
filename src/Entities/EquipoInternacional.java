
package Entities;


public class EquipoInternacional extends Equipo{
    private String pais;

    public EquipoInternacional() {
    }

    public EquipoInternacional( String Nombre, String pais) {
        super(Nombre);
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
}
