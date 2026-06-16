package Vista;

import Entities.Equipo;
import Services.ServicioCampeonato;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author y
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());
// Atributo para conectar con la lógica
    private ServicioCampeonato controlador;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        // Mejoras esteticas
        // 1. Estilizar la cabecera de la tabla (Nombre, Puntos, PJ...)
        java.awt.Font fuenteCabecera = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        jTable1.getTableHeader().setFont(fuenteCabecera);
        jTable1.getTableHeader().setForeground(new java.awt.Color(25, 42, 86));
        jTable1.getTableHeader().setReorderingAllowed(false); // Evita que el usuario arrastre y desordene las columnas

        // 2. Estilizar las filas y celdas de los equipos
        jTable1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jTable1.setRowHeight(26); // Más altura para que los números respiren
        jTable1.setShowGrid(true); // Mostrar líneas divisorias sutiles
        jTable1.setGridColor(new java.awt.Color(220, 220, 220));

        // 3. Efecto "Verde Césped" al seleccionar un equipo
        jTable1.setSelectionBackground(new java.awt.Color(46, 204, 113)); // Verde deportivo
        jTable1.setSelectionForeground(java.awt.Color.WHITE); // Texto blanco al dar clic

        // 4. Centrar los números de las columnas automáticamente (Columna 1 a la 7)
        javax.swing.table.DefaultTableCellRenderer renderCentrado = new javax.swing.table.DefaultTableCellRenderer();
        renderCentrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        for (int i = 1; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(renderCentrado);
        }
        // Para que el logo se cuadre
        try {
            // 1. Apuntamos a la misma foto transparente que ya tienes guardada
            javax.swing.ImageIcon imagenOriginal = new javax.swing.ImageIcon(getClass().getResource("/Vista/logo.png"));

            // 2. Tomamos las medidas del nuevo Label que pusiste en la tabla
            int anchoLabel = lblLogo.getWidth();
            int altoLabel = lblLogo.getHeight();

            // 3. Escalamos la imagen para que se adapte a este nuevo tamaño
            java.awt.Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(anchoLabel, altoLabel, java.awt.Image.SCALE_SMOOTH);

            // 4. Se la pasamos al componente
            lblLogo.setIcon(new javax.swing.ImageIcon(imagenEscalada));

        } catch (Exception e) {
            System.out.println("Aviso: No se pudo cargar el logo en la ventana principal: " + e.getMessage());
        }

        //  También le ponemos el icono de FUTMJI a la barra de título de esta ventana
        try {
            setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Vista/logo.png")).getImage());
        } catch (Exception e) {
            System.out.println("No se pudo poner el icono en la barra superior");
        }
        this.controlador = new ServicioCampeonato();

        // Utilizado para estilizar la cabezera de la tabla
        jTable1.getTableHeader().setFont(fuenteCabecera);

        // Cambiar el color del texto de la cabecera (Azul oscuro deportivo)
        jTable1.getTableHeader().setForeground(new java.awt.Color(25, 42, 86));

        //  Mejoras esteticas
        this.setTitle("FutMJI - Organiza tu campeonato");
        this.setLocationRelativeTo(null); // asegura que salga en el centro

        // Forzar a la tabla a mostrar la cuadrícula desde el inicio
        jTable1.setShowGrid(true);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jTable1.setGridColor(java.awt.Color.LIGHT_GRAY); // Líneas gris claro elegantes

        // Activar ordenamiento automático al hacer clic en las columnas (Puntos, GF, etc.)
        jTable1.setAutoCreateRowSorter(true);
        // =======================================

        refrescarTabla();
    }

    public void refrescarTabla() {
        // 1. Definir los títulos de las columnas
        String[] columnas = {"Nombre", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "DG"};

        // 2. Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto hace que TODAS las celdas sean de solo lectura
            }
        };

        // 3. Obtener la lista de equipos del servicio
        ArrayList<Equipo> lista = controlador.getEquipos();

        // 4. Llenar el modelo con los datos de cada equipo
        for (Equipo e : lista) {
            Object[] fila = {
                e.getNombre(),
                e.getPuntos(),
                e.getJugados(),
                e.getGanados(),
                e.getEmpatados(),
                e.getPerdidos(),
                e.getGolesFavor(),
                e.getGolesContra(),
                (e.getGolesFavor() - e.getGolesContra()) // Diferencia de Goles
            };
            modelo.addRow(fila);
        }

        // 5. Asignar el modelo al JTable que arrastraste en el diseño
        jTable1.setModel(modelo);
        javax.swing.table.DefaultTableCellRenderer renderCentrado = new javax.swing.table.DefaultTableCellRenderer();
        renderCentrado.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        // 2. Aplicar el centrado a todas las columnas numéricas (de la 1 a la 8)
        for (int i = 1; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(renderCentrado);
        }

        // 3. Darle más anchura a la columna "Nombre" para que respire
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(160);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnRegistrarPartido = new javax.swing.JButton();
        btnRegistrarEquipo = new javax.swing.JButton();
        btnEliminarEquipo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        vistaTabla = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 246, 250));

        jLabel1.setText("jLabel1");

        panelMenu.setBackground(new java.awt.Color(25, 42, 86));

        btnInicio.setBackground(new java.awt.Color(25, 42, 86));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(205, 205, 205));
        btnInicio.setText("Inicio");
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnRegistrarPartido.setBackground(new java.awt.Color(25, 42, 86));
        btnRegistrarPartido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrarPartido.setForeground(new java.awt.Color(205, 205, 205));
        btnRegistrarPartido.setText("Registrar Partido");
        btnRegistrarPartido.setBorder(null);
        btnRegistrarPartido.setBorderPainted(false);
        btnRegistrarPartido.setContentAreaFilled(false);
        btnRegistrarPartido.addActionListener(this::btnRegistrarPartidoActionPerformed);

        btnRegistrarEquipo.setBackground(new java.awt.Color(25, 42, 86));
        btnRegistrarEquipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrarEquipo.setForeground(new java.awt.Color(204, 204, 204));
        btnRegistrarEquipo.setText("Registrar Equipo");
        btnRegistrarEquipo.setBorder(null);
        btnRegistrarEquipo.setBorderPainted(false);
        btnRegistrarEquipo.setContentAreaFilled(false);
        btnRegistrarEquipo.addActionListener(this::btnRegistrarEquipoActionPerformed);

        btnEliminarEquipo.setBackground(new java.awt.Color(25, 42, 86));
        btnEliminarEquipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarEquipo.setForeground(new java.awt.Color(204, 204, 204));
        btnEliminarEquipo.setText("Eliminar Equipo");
        btnEliminarEquipo.setBorder(null);
        btnEliminarEquipo.setBorderPainted(false);
        btnEliminarEquipo.setContentAreaFilled(false);
        btnEliminarEquipo.addActionListener(this::btnEliminarEquipoActionPerformed);

        btnSalir.setBackground(new java.awt.Color(25, 42, 86));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(204, 204, 204));
        btnSalir.setText("Cerrar sesion");
        btnSalir.setBorder(null);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarEquipo)
                            .addComponent(btnRegistrarEquipo)
                            .addComponent(btnRegistrarPartido)))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnInicio))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnSalir)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnInicio)
                .addGap(64, 64, 64)
                .addComponent(btnRegistrarPartido)
                .addGap(65, 65, 65)
                .addComponent(btnRegistrarEquipo)
                .addGap(65, 65, 65)
                .addComponent(btnEliminarEquipo)
                .addGap(65, 65, 65)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vistaTabla.setBackground(new java.awt.Color(245, 246, 250));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("FutMJI  —  TABLA DE CLASIFICACIÓN");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "POSICIONES CAMPEONATO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(25, 42, 86))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout vistaTablaLayout = new javax.swing.GroupLayout(vistaTabla);
        vistaTabla.setLayout(vistaTablaLayout);
        vistaTablaLayout.setHorizontalGroup(
            vistaTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaTablaLayout.createSequentialGroup()
                .addGroup(vistaTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vistaTablaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vistaTablaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        vistaTablaLayout.setVerticalGroup(
            vistaTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaTablaLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(vistaTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaTablaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vistaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vistaTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPartidoActionPerformed
        // 1. Instanciamos la ventana de registro que acabas de diseñar
        VentanaRegistrarPartido vr = new VentanaRegistrarPartido();

// 2. La centramos en la pantalla
        vr.setLocationRelativeTo(null);

// 3. La hacemos visible
        vr.setVisible(true);

// 4. Ocultamos esta ventana principal (la tabla) para darle paso a la otra
        this.dispose();
    }//GEN-LAST:event_btnRegistrarPartidoActionPerformed

    private void btnRegistrarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEquipoActionPerformed
        VentanaRegistrarEquipo registrarEquipo = new VentanaRegistrarEquipo();
        registrarEquipo.setVisible(true);
        this.dispose(); // Cierra la tabla de posiciones
    }//GEN-LAST:event_btnRegistrarEquipoActionPerformed

    private void btnEliminarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEquipoActionPerformed
        // 1. Obtener la fila seleccionada de la JTable (asumiendo que se llama tblPosiciones o jTable1)
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Por favor, selecciona un equipo de la tabla para eliminar.",
                    "Ningún equipo seleccionado", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el nombre del equipo que está en la primera columna (columna 0)
        String nombreEquipo = jTable1.getValueAt(filaSeleccionada, 0).toString();

        // 3. Preguntar confirmación (Por seguridad para que no borren por error)
        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas eliminar al equipo '" + nombreEquipo + "'? Esto borrará sus estadísticas.",
                "Confirmar Eliminación", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
            // 4. Llamar al servicio para borrarlo
            boolean exito = controlador.eliminarEquipo(nombreEquipo);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "Equipo eliminado con éxito.");

                // 5. Refrescar la tabla de la ventana principal para ver los cambios
                // Aquí llamas al método que ya tienes hecho para llenar o pintar tu tabla
                this.refrescarTabla();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo eliminar el equipo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarEquipoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Deseas salir de FutMJI?",
                "Cerrar Aplicación", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra por completo la máquina virtual de Java de forma limpia
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            // === INSTALAMOS EL DISEÑO FLATLAF (MODO CLARO MODERNO) ===
            // Si prefieres modo oscuro, puedes cambiar "FlatLightLaf" por "FlatDarkLaf"
            com.formdev.flatlaf.FlatLightLaf.setup();

        } catch (Exception ex) {
            System.err.println("No se pudo inicializar el diseño FlatLaf, usando diseño por defecto.");
        }

        // Código original de NetBeans que lanza la ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarEquipo;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnRegistrarEquipo;
    private javax.swing.JButton btnRegistrarPartido;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel vistaTabla;
    // End of variables declaration//GEN-END:variables
}
