package Services;

import Entities.Equipo;
import Entities.EquipoNacional;
import Entities.EquipoInternacional;
import Entities.Partido;
import Entities.TablaPosiciones;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ServicioCampeonato {

    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    // Nombres de los archivos físicos como constantes del controlador
    private static final String ARCHIVO_EQUIPOS = "equipos.properties";
    private static final String ARCHIVO_CAMPEONATO = "campeonato.properties";

    public ServicioCampeonato() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();

        // Al iniciar el controlador, aseguramos los archivos y cargamos los datos
        asegurarArchivosFisicos();
        this.cargarEquiposDesdeArchivo();
    }

    // Metodo para obligar a Windows a crear los archivos si no existen
    private void asegurarArchivosFisicos() {
        try {
            File fEquipos = new File(ARCHIVO_EQUIPOS);
            if (!fEquipos.exists()) {
                fEquipos.createNewFile();
            }
            File fCamp = new File(ARCHIVO_CAMPEONATO);
            if (!fCamp.exists()) {
                fCamp.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al inicializar archivos: " + e.getMessage());
        }
    }

    //  LOGICA DE LECTURA 
    private void cargarEquiposDesdeArchivo() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(ARCHIVO_EQUIPOS)) {
            prop.load(fis);

            String nombresRaw = prop.getProperty("lista.nombres", "");
            if (nombresRaw.isEmpty()) {
                return;
            }

            String[] nombres = nombresRaw.split(",");
            for (String nom : nombres) {
                String tipo = prop.getProperty(nom + ".tipo");
                Equipo equipo;

                // Reconstrucción polimórfica según la herencia
                if ("Nacional".equals(tipo)) {
                    String depto = prop.getProperty(nom + ".departamento", "No especificado");
                    equipo = new EquipoNacional(nom, depto);
                } else {
                    String pais = prop.getProperty(nom + ".pais", "No especificado");
                    equipo = new EquipoInternacional(nom, pais);
                }

                // Restaurar estadísticas encapsuladas
                equipo.setPuntos(Integer.parseInt(prop.getProperty(nom + ".puntos", "0")));
                equipo.setJugados(Integer.parseInt(prop.getProperty(nom + ".jugados", "0")));
                equipo.setGanados(Integer.parseInt(prop.getProperty(nom + ".ganados", "0")));
                equipo.setEmpatados(Integer.parseInt(prop.getProperty(nom + ".empatados", "0")));
                equipo.setPerdidos(Integer.parseInt(prop.getProperty(nom + ".perdidos", "0")));
                equipo.setGolesFavor(Integer.parseInt(prop.getProperty(nom + ".golesFavor", "0")));
                equipo.setGolesContra(Integer.parseInt(prop.getProperty(nom + ".golesContra", "0")));

                this.equipos.add(equipo);
            }
            this.actualizarTabla(); // Ordenar la tabla si cargó datos previos
            System.out.println(" Datos cargados directamente en el controlador.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Aviso al cargar datos: " + e.getMessage());
        }
    }

    //  LOGICA DE ESCRITURA 
    public boolean eliminarEquipo(String nombreEquipo) {
        // 1. Buscamos si el equipo existe en nuestra lista
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombreEquipo)) {
                // 2. Lo removemos de la colección
                equipos.remove(e);

                // 3. Reordenamos la tabla por si cambian posiciones
                this.actualizarTabla();

                // 4. Reescribimos el archivo físico equipos.properties con el equipo ya fuera
                this.guardarEquiposEnArchivo();
                return true; // Retorna true si se eliminó con éxito
            }
        }
        return false; // Retorna false si no se encontró
    }

    public void guardarEquiposEnArchivo() {
        Properties prop = new Properties();
        StringBuilder nombresRaw = new StringBuilder();

        for (int i = 0; i < equipos.size(); i++) {
            Equipo e = equipos.get(i);
            nombresRaw.append(e.getNombre());
            if (i < equipos.size() - 1) {
                nombresRaw.append(",");
            }

            // Mapear estadísticas
            prop.setProperty(e.getNombre() + ".puntos", String.valueOf(e.getPuntos()));
            prop.setProperty(e.getNombre() + ".jugados", String.valueOf(e.getJugados()));
            prop.setProperty(e.getNombre() + ".ganados", String.valueOf(e.getGanados()));
            prop.setProperty(e.getNombre() + ".empatados", String.valueOf(e.getEmpatados()));
            prop.setProperty(e.getNombre() + ".perdidos", String.valueOf(e.getPerdidos()));
            prop.setProperty(e.getNombre() + ".golesFavor", String.valueOf(e.getGolesFavor()));
            prop.setProperty(e.getNombre() + ".golesContra", String.valueOf(e.getGolesContra()));

            // Mapear datos específicos por herencia
            if (e instanceof EquipoNacional) {
                prop.setProperty(e.getNombre() + ".tipo", "Nacional");
                prop.setProperty(e.getNombre() + ".departamento", ((EquipoNacional) e).getDepartamento());
            } else if (e instanceof EquipoInternacional) {
                prop.setProperty(e.getNombre() + ".tipo", "Internacional");
                prop.setProperty(e.getNombre() + ".pais", ((EquipoInternacional) e).getPais());
            }
        }

        prop.setProperty("lista.nombres", nombresRaw.toString());

        try (FileOutputStream fos = new FileOutputStream(ARCHIVO_EQUIPOS)) {
            prop.store(fos, "Persistencia del Campeonato - Controlada desde el Servicio");
            System.out.println(" Archivo '" + ARCHIVO_EQUIPOS + "' guardado desde el controlador.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Metodos Controlador
    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void registrarEquipo(Equipo nuevoEquipo) {
        this.equipos.add(nuevoEquipo);
        this.actualizarTabla();

        // Guarda directamente usando el método interno
        this.guardarEquiposEnArchivo();
    }

    public void validarPartido(Equipo local, Equipo visitante) throws Exception {
        if (local.getNombre().equalsIgnoreCase(visitante.getNombre())) {
            throw new Exception("Un equipo no puede jugar contra sí mismo.");
        }
        for (Partido p : partidos) {
            if (p.getEquipoLocal().getNombre().equalsIgnoreCase(local.getNombre())
                    && p.getEquipoVisitante().getNombre().equalsIgnoreCase(visitante.getNombre())) {
                throw new Exception("Este partido ya fue registrado anteriormente.");
            }
        }
    }

    public void registrarResultado(Partido partido) throws Exception {
        Equipo local = partido.getEquipoLocal();
        Equipo visitante = partido.getEquipoVisitante();

        validarPartido(local, visitante);

        int golesL = partido.getGolesLocal();
        int golesV = partido.getGolesVisitante();

        local.setJugados(local.getJugados() + 1);
        visitante.setJugados(visitante.getJugados() + 1);

        local.setGolesFavor(local.getGolesFavor() + golesL);
        local.setGolesContra(local.getGolesContra() + golesV);

        visitante.setGolesFavor(visitante.getGolesFavor() + golesV);
        visitante.setGolesContra(visitante.getGolesContra() + golesL);

        if (golesL > golesV) {
            local.setPuntos(local.getPuntos() + 3);
            local.setGanados(local.getGanados() + 1);
            visitante.setPerdidos(visitante.getPerdidos() + 1);
        } else if (golesL < golesV) {
            visitante.setPuntos(visitante.getPuntos() + 3);
            visitante.setGanados(visitante.getGanados() + 1);
            local.setPerdidos(local.getPerdidos() + 1);
        } else {
            local.setPuntos(local.getPuntos() + 1);
            visitante.setPuntos(visitante.getPuntos() + 1);
            local.setEmpatados(local.getEmpatados() + 1);
            visitante.setEmpatados(visitante.getEmpatados() + 1);
        }

        this.partidos.add(partido);
        this.actualizarTabla();

        // Guarda el estado de los equipos con sus nuevos puntos y goles
        this.guardarEquiposEnArchivo();
    }

    public void actualizarTabla() {
        TablaPosiciones logicaOrden = new TablaPosiciones();
        logicaOrden.setEquiposOrdenados(this.equipos);
        logicaOrden.ordenarEquipos();
        this.equipos = logicaOrden.getEquiposOrdenados();
    }
}
