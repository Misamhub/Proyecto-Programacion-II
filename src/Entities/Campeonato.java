
package Entities;

import java.util.ArrayList;


public class Campeonato {
    private ArrayList<Equipo> listaEquipos;
    private ArrayList<Partido> listaPartidos;

    public Campeonato() {
        listaEquipos = new ArrayList<>();
        listaPartidos = new ArrayList<>();
        
    }

    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public ArrayList<Partido> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(ArrayList<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }
    
    
   
    
}
