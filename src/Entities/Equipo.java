
package Entities;


public class Equipo {
    private String Nombre;
    private int Puntos, Jugados, Ganados,Empatados,Perdidos;
    private int golesFavor,  golesContra;

    public Equipo() {
    }

    public Equipo(String Nombre) {
        this.Nombre = Nombre;
    }
    

    public Equipo(String Nombre, int Puntos, int Jugados, int Ganados, int Empatados, int Perdidos, int golesFavor, int golesContra) {
        this.Nombre = Nombre;
        this.Puntos = Puntos;
        this.Jugados = Jugados;
        this.Ganados = Ganados;
        this.Empatados = Empatados;
        this.Perdidos = Perdidos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int Puntos) {
        this.Puntos = Puntos;
    }

    public int getJugados() {
        return Jugados;
    }

    public void setJugados(int Jugados) {
        this.Jugados = Jugados;
    }

    public int getGanados() {
        return Ganados;
    }

    public void setGanados(int Ganados) {
        this.Ganados = Ganados;
    }

    public int getEmpatados() {
        return Empatados;
    }

    public void setEmpatados(int Empatados) {
        this.Empatados = Empatados;
    }

    public int getPerdidos() {
        return Perdidos;
    }

    public void setPerdidos(int Perdidos) {
        this.Perdidos = Perdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }
    
    
}
