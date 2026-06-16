
package Entities;

import java.util.ArrayList;
import java.util.Collections;


public class TablaPosiciones {
    private ArrayList<Equipo> equiposOrdenados;

    public TablaPosiciones() {
        equiposOrdenados= new ArrayList<>();
    }
    public void ordenarEquipos() {
        Collections.sort(equiposOrdenados, (Equipo e1, Equipo e2) -> {
            // 1. Puntos descendente
            if (e2.getPuntos() != e1.getPuntos()) {
                return Integer.compare(e2.getPuntos(), e1.getPuntos());
            }
            
            // 2. Diferencia de goles descendente
            int dif1 = e1.getGolesFavor() - e1.getGolesContra();
            int dif2 = e2.getGolesFavor() - e2.getGolesContra();
            if (dif2 != dif1) {
                return Integer.compare(dif2, dif1);
            }
            
            // 3. Goles a favor descendente
            if (e2.getGolesFavor() != e1.getGolesFavor()) {
                return Integer.compare(e2.getGolesFavor(), e1.getGolesFavor());
            }
            
            // 4. Orden alfabético ascendente
            return e1.getNombre().compareTo(e2.getNombre());
        });
    }

    public ArrayList<Equipo> getEquiposOrdenados() {
        return equiposOrdenados;
    }

    public void setEquiposOrdenados(ArrayList<Equipo> equiposOrdenados) {
        this.equiposOrdenados = equiposOrdenados;
    }
        
    
    
    
}
