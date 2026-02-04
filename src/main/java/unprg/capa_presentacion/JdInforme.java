/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package unprg.capa_presentacion;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import unprg.capa_datos.MaterialDAO;
import unprg.capa_datos.PedidoDAO;
import unprg.capa_datos.ProyectoDAO;
import unprg.capa_logica.modelos.Material;
import unprg.capa_logica.modelos.Pedido;
import unprg.capa_logica.modelos.Proyecto;
import unprg.capa_presentacion.utils.UiHelper;

/**
 *
 * @author mauricio
 */
public class JdInforme extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JdInforme.class.getName());
    private MaterialDAO materialDAO;
    private ProyectoDAO proyectoDAO;
    private PedidoDAO pedidoDAO;

    /**
     * Creates new form JdInforme
     */
    public JdInforme(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarEstilos();
        materialDAO = new MaterialDAO();
        proyectoDAO = new ProyectoDAO();
        pedidoDAO = new PedidoDAO();
        cargarDatos();
    }
    
    public void cargarEstilos (){
        UiHelper.establecerIcono(logo1, "icons/material.svg",30,30,Color.WHITE);
        UiHelper.establecerIcono(logo2, "icons/proyectos.svg",30,30,Color.WHITE);
        UiHelper.establecerIcono(logo3, "icons/pedidos.svg",30,30,Color.WHITE);
        
        UiHelper.estilarTabla(jTable1);
        UiHelper.estilarTabla(jTable2);
        UiHelper.estilarTabla(jTable3);
        
    }
    
    private void cargarDatos() {
        llenarTablaMaterialesConMasStock();
        llenarTablaProyectosRecientes();
        llenarTablaPedidosMasCaros();
    }
    
    private void llenarTablaMaterialesConMasStock() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modelo.addColumn("Material");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Tipo Medida");
        
        List<Material> materiales = materialDAO.listar();
        
        if (materiales != null && !materiales.isEmpty()) {
            materiales.sort((m1, m2) -> Integer.compare(m2.getStock(), m1.getStock()));
            
            int limit = Math.min(5, materiales.size());
            for (int i = 0; i < limit; i++) {
                Material m = materiales.get(i);
                modelo.addRow(new Object[]{
                    m.getNombProducto(),
                    m.getDescripcion(),
                    m.getPrecioUnitario(),
                    m.getStock(),
                    m.getUnidadMedida()
                });
            }
        }
        jTable1.setModel(modelo);
    }
    
    private void llenarTablaProyectosRecientes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modelo.addColumn("Proyecto");
        modelo.addColumn("Descripción");
        modelo.addColumn("Ubicación");
        modelo.addColumn("Fecha Inicio");
        
        List<Proyecto> proyectos = proyectoDAO.listar();
        
        if (proyectos != null && !proyectos.isEmpty()) {
            proyectos.sort((p1, p2) -> p2.getFechaInicio().compareTo(p1.getFechaInicio()));
            
            int limit = Math.min(5, proyectos.size());
            for (int i = 0; i < limit; i++) {
                Proyecto p = proyectos.get(i);
                modelo.addRow(new Object[]{
                    p.getNombreProyecto(),
                    p.getDescripcion(),
                    p.getDireccion(),
                    sdf.format(p.getFechaInicio())
                });
            }
        }
        jTable2.setModel(modelo);
    }
    
    private void llenarTablaPedidosMasCaros() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modelo.addColumn("Material");
        modelo.addColumn("Proyecto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Costo Unitario");
        modelo.addColumn("Subtotal");
        
        List<Pedido> pedidos = pedidoDAO.listar();
        
        if (pedidos != null && !pedidos.isEmpty()) {
            pedidos.sort((p1, p2) -> Double.compare(p2.getSubtotal(), p1.getSubtotal()));
            
            int limit = Math.min(5, pedidos.size());
            for (int i = 0; i < limit; i++) {
                Pedido p = pedidos.get(i);
                modelo.addRow(new Object[]{
                    p.getNombreMaterial(),
                    p.getNombreProyecto(),
                    p.getCantidad(),
                    p.getCostoUnitarioAlMomento(),
                    p.getSubtotal()
                });
            }
        }
        jTable3.setModel(modelo);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        logo1 = new javax.swing.JLabel();
        logo3 = new javax.swing.JLabel();
        logo2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Top Materiales más pedidos");

        jLabel2.setText("Top Proyectos mas Lucrativos");

        jLabel3.setText("Top Materiales con más stock");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 211, 252));
        jLabel7.setText("Informe de los servicios mas usados del sistema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JdInforme dialog = new JdInforme(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private javax.swing.JLabel logo3;
    // End of variables declaration//GEN-END:variables
}
