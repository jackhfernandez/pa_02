package unprg.capa_presentacion;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import unprg.capa_logica.modelos.*;
import unprg.capa_logica.servicios.ReporteService;
import unprg.capa_presentacion.utils.UiHelper;

/**
 * Ventana de Reportes del Sistema
 * @author jackh
 */
public class JdReportes extends javax.swing.JDialog {

    private ReporteService reporteService;
    private JTabbedPane tabbedPane;
    
    // Tablas
    private JTable tablaMateriales;
    private JTable tablaProyectos;
    private JTable tablaPedidos;
    private JTable tablaGastosPorProyecto;
    private JTable tablaGastosPorMaterial;
    private JTable tablaGastosPorMes;
    
    // Modelos de tabla
    private DefaultTableModel modeloMateriales;
    private DefaultTableModel modeloProyectos;
    private DefaultTableModel modeloPedidos;
    private DefaultTableModel modeloGastosPorProyecto;
    private DefaultTableModel modeloGastosPorMaterial;
    private DefaultTableModel modeloGastosPorMes;
    
    // Sorters para filtrado
    private TableRowSorter<DefaultTableModel> sorterMateriales;
    private TableRowSorter<DefaultTableModel> sorterProyectos;
    private TableRowSorter<DefaultTableModel> sorterPedidos;
    
    // Labels de estadísticas (resumen por tab)
    private JLabel lblTotalGastos;
    private JLabel lblTotalMateriales;
    private JLabel lblTotalProyectos;
    private JLabel lblTotalPedidos;
    private JLabel lblValorInventario;
    
    // Labels de las cards de estadísticas generales
    private JLabel lblCardGastoTotal;
    private JLabel lblCardProyectos;
    private JLabel lblCardPedidos;
    private JLabel lblCardInventario;

    /**
     * Creates new form JdReportes
     */
    public JdReportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.reporteService = new ReporteService();
        configurarVentana();
        inicializarComponentes();
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializarComponentes() {
        // Limpiar el contenido y usar BorderLayout
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        
        // Crear TabbedPane principal
        tabbedPane = new JTabbedPane();
        UiHelper.estilarTabbedPane(tabbedPane);
        
        // Agregar tabs
        tabbedPane.addTab("📦 Materiales", crearPanelMateriales());
        tabbedPane.addTab("🏗️ Proyectos", crearPanelProyectos());
        tabbedPane.addTab("📋 Pedidos", crearPanelPedidos());
        tabbedPane.addTab("💰 Análisis de Gastos", crearPanelGastos());
        
        // Panel inferior con botón salir
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelInferior.setBackground(UiHelper.FONDO_OSCURO);
        
        JButton btnRefrescar = new JButton("🔄 Refrescar Datos");
        UiHelper.estilarBotonSecundario(btnRefrescar);
        btnRefrescar.addActionListener(e -> cargarDatos());
        
        JButton btnCerrar = new JButton("❌ Cerrar");
        UiHelper.estilarBotonPrincipal(btnCerrar);
        btnCerrar.addActionListener(e -> dispose());
        
        panelInferior.add(btnRefrescar);
        panelInferior.add(btnCerrar);
        
        // Agregar al contenedor
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
        
        revalidate();
        repaint();
    }

    // ========== TAB MATERIALES ==========
    private JPanel crearPanelMateriales() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        
        // Panel superior con filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFiltros.setBackground(UiHelper.FONDO_OSCURO);
        
        JLabel lblFiltro = new JLabel("🔍 Buscar:");
        lblFiltro.setForeground(UiHelper.TEXTO_PRIMARIO);
        
        JTextField txtBuscar = new JTextField(35);
        UiHelper.estilarCampoBusqueda(txtBuscar, "Buscar por nombre de material...");
        txtBuscar.setPreferredSize(new Dimension(450, 45));
        
        JComboBox<String> cbFiltroStock = new JComboBox<>(new String[]{"Todos", "Stock Bajo (≤100)", "Sin Stock"});
        UiHelper.estilarComboBox(cbFiltroStock);
        
        panelFiltros.add(lblFiltro);
        panelFiltros.add(txtBuscar);
        panelFiltros.add(new JLabel("   "));
        panelFiltros.add(new JLabel("📊 Filtrar:"));
        panelFiltros.add(cbFiltroStock);
        
        // Tabla
        String[] columnas = {"Producto", "Descripción", "Unidad", "Precio Unit.", "Stock", "Valor Total"};
        modeloMateriales = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales = new JTable(modeloMateriales);
        sorterMateriales = new TableRowSorter<>(modeloMateriales);
        tablaMateriales.setRowSorter(sorterMateriales);
        UiHelper.estilarTabla(tablaMateriales);
        
        JScrollPane scrollPane = new JScrollPane(tablaMateriales);
        UiHelper.estilarScrollPane(scrollPane);
        
        // Eventos de filtrado
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrarMateriales(txtBuscar.getText(), cbFiltroStock.getSelectedIndex()); }
            public void removeUpdate(DocumentEvent e) { filtrarMateriales(txtBuscar.getText(), cbFiltroStock.getSelectedIndex()); }
            public void changedUpdate(DocumentEvent e) { filtrarMateriales(txtBuscar.getText(), cbFiltroStock.getSelectedIndex()); }
        });
        
        cbFiltroStock.addActionListener(e -> filtrarMateriales(txtBuscar.getText(), cbFiltroStock.getSelectedIndex()));
        
        // Panel de resumen
        JPanel panelResumen = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelResumen.setBackground(UiHelper.FONDO_CARD);
        panelResumen.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        lblTotalMateriales = new JLabel("Total Materiales: 0");
        lblTotalMateriales.setForeground(UiHelper.TEXTO_PRIMARIO);
        lblTotalMateriales.setFont(lblTotalMateriales.getFont().deriveFont(Font.BOLD));
        
        lblValorInventario = new JLabel("Valor Inventario: S/ 0.00");
        lblValorInventario.setForeground(UiHelper.VERDE_EXITO);
        lblValorInventario.setFont(lblValorInventario.getFont().deriveFont(Font.BOLD));
        
        panelResumen.add(lblTotalMateriales);
        panelResumen.add(Box.createHorizontalStrut(50));
        panelResumen.add(lblValorInventario);
        
        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelResumen, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void filtrarMateriales(String texto, int filtroStock) {
        RowFilter<DefaultTableModel, Object> filtro = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String nombre = entry.getStringValue(0).toLowerCase();
                int stock = 0;
                try {
                    stock = Integer.parseInt(entry.getStringValue(4));
                } catch (NumberFormatException e) {}
                
                boolean coincideTexto = texto.isEmpty() || nombre.contains(texto.toLowerCase());
                boolean coincideStock = true;
                
                if (filtroStock == 1) coincideStock = stock <= 10;
                else if (filtroStock == 2) coincideStock = stock == 0;
                
                return coincideTexto && coincideStock;
            }
        };
        sorterMateriales.setRowFilter(filtro);
    }

    // ========== TAB PROYECTOS ==========
    private JPanel crearPanelProyectos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        
        // Panel superior con filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFiltros.setBackground(UiHelper.FONDO_OSCURO);
        
        JLabel lblFiltro = new JLabel("🔍 Buscar:");
        lblFiltro.setForeground(UiHelper.TEXTO_PRIMARIO);
        
        JTextField txtBuscar = new JTextField(35);
        UiHelper.estilarCampoBusqueda(txtBuscar, "Buscar por nombre de proyecto...");
        txtBuscar.setPreferredSize(new Dimension(450, 45));
        
        panelFiltros.add(lblFiltro);
        panelFiltros.add(txtBuscar);
        
        // Tabla
        String[] columnas = {"Proyecto", "Descripción", "Dirección", "Fecha Inicio", "Gasto Total"};
        modeloProyectos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProyectos = new JTable(modeloProyectos);
        sorterProyectos = new TableRowSorter<>(modeloProyectos);
        tablaProyectos.setRowSorter(sorterProyectos);
        UiHelper.estilarTabla(tablaProyectos);
        
        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        UiHelper.estilarScrollPane(scrollPane);
        
        // Evento de filtrado
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrarProyectos(txtBuscar.getText()); }
            public void removeUpdate(DocumentEvent e) { filtrarProyectos(txtBuscar.getText()); }
            public void changedUpdate(DocumentEvent e) { filtrarProyectos(txtBuscar.getText()); }
        });
        
        // Panel de resumen
        JPanel panelResumen = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelResumen.setBackground(UiHelper.FONDO_CARD);
        panelResumen.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        lblTotalProyectos = new JLabel("Total Proyectos: 0");
        lblTotalProyectos.setForeground(UiHelper.TEXTO_PRIMARIO);
        lblTotalProyectos.setFont(lblTotalProyectos.getFont().deriveFont(Font.BOLD));
        
        panelResumen.add(lblTotalProyectos);
        
        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelResumen, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void filtrarProyectos(String texto) {
        if (texto.isEmpty()) {
            sorterProyectos.setRowFilter(null);
        } else {
            sorterProyectos.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0, 1, 2));
        }
    }

    // ========== TAB PEDIDOS ==========
    private JPanel crearPanelPedidos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        
        // Panel superior con filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFiltros.setBackground(UiHelper.FONDO_OSCURO);
        
        JLabel lblFiltroProyecto = new JLabel("🏗️ Proyecto:");
        lblFiltroProyecto.setForeground(UiHelper.TEXTO_PRIMARIO);
        
        JComboBox<String> cbProyecto = new JComboBox<>();
        cbProyecto.addItem("Todos los proyectos");
        UiHelper.estilarComboBox(cbProyecto);
        cbProyecto.setPreferredSize(new Dimension(180, 30));
        
        JLabel lblFiltroMaterial = new JLabel("   📦 Material:");
        lblFiltroMaterial.setForeground(UiHelper.TEXTO_PRIMARIO);
        
        JTextField txtMaterial = new JTextField(25);
        UiHelper.estilarCampoBusqueda(txtMaterial, "Buscar material...");
        txtMaterial.setPreferredSize(new Dimension(350, 45));
        
        panelFiltros.add(lblFiltroProyecto);
        panelFiltros.add(cbProyecto);
        panelFiltros.add(lblFiltroMaterial);
        panelFiltros.add(txtMaterial);
        
        // Tabla
        String[] columnas = {"Proyecto", "Material", "Cantidad", "Precio Unit.", "Subtotal", "Fecha"};
        modeloPedidos = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPedidos = new JTable(modeloPedidos);
        sorterPedidos = new TableRowSorter<>(modeloPedidos);
        tablaPedidos.setRowSorter(sorterPedidos);
        UiHelper.estilarTabla(tablaPedidos);
        
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        UiHelper.estilarScrollPane(scrollPane);
        
        // Cargar proyectos al combo
        cargarProyectosEnCombo(cbProyecto);
        
        // Eventos de filtrado
        cbProyecto.addActionListener(e -> filtrarPedidos(cbProyecto.getSelectedItem().toString(), txtMaterial.getText()));
        
        txtMaterial.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrarPedidos(cbProyecto.getSelectedItem().toString(), txtMaterial.getText()); }
            public void removeUpdate(DocumentEvent e) { filtrarPedidos(cbProyecto.getSelectedItem().toString(), txtMaterial.getText()); }
            public void changedUpdate(DocumentEvent e) { filtrarPedidos(cbProyecto.getSelectedItem().toString(), txtMaterial.getText()); }
        });
        
        // Panel de resumen
        JPanel panelResumen = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelResumen.setBackground(UiHelper.FONDO_CARD);
        panelResumen.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        lblTotalPedidos = new JLabel("Total Pedidos: 0");
        lblTotalPedidos.setForeground(UiHelper.TEXTO_PRIMARIO);
        lblTotalPedidos.setFont(lblTotalPedidos.getFont().deriveFont(Font.BOLD));
        
        lblTotalGastos = new JLabel("Monto Total: S/ 0.00");
        lblTotalGastos.setForeground(UiHelper.VERDE_EXITO);
        lblTotalGastos.setFont(lblTotalGastos.getFont().deriveFont(Font.BOLD));
        
        panelResumen.add(lblTotalPedidos);
        panelResumen.add(Box.createHorizontalStrut(50));
        panelResumen.add(lblTotalGastos);
        
        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelResumen, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void cargarProyectosEnCombo(JComboBox<String> combo) {
        List<String> proyectos = reporteService.obtenerNombresProyectos();
        for (String p : proyectos) {
            combo.addItem(p);
        }
    }
    
    private void filtrarPedidos(String proyecto, String material) {
        RowFilter<DefaultTableModel, Object> filtro = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String proy = entry.getStringValue(0);
                String mat = entry.getStringValue(1).toLowerCase();
                
                boolean coincideProyecto = proyecto.equals("Todos los proyectos") || proy.equalsIgnoreCase(proyecto);
                boolean coincideMaterial = material.isEmpty() || mat.contains(material.toLowerCase());
                
                return coincideProyecto && coincideMaterial;
            }
        };
        sorterPedidos.setRowFilter(filtro);
    }

    // ========== TAB ANÁLISIS DE GASTOS ==========
    private JPanel crearPanelGastos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        
        // Panel superior con estadísticas generales
        JPanel panelEstadisticas = new JPanel(new GridLayout(1, 4, 15, 0));
        panelEstadisticas.setBackground(UiHelper.FONDO_OSCURO);
        panelEstadisticas.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JPanel cardGastoTotal = UiHelper.crearPanelEstadistica("Gasto Total", "Cargando...", UiHelper.VERDE_EXITO);
        JPanel cardProyectos = UiHelper.crearPanelEstadistica("Proyectos", "Cargando...", UiHelper.AZUL_INFO);
        JPanel cardPedidos = UiHelper.crearPanelEstadistica("Pedidos", "Cargando...", UiHelper.MORADO_PRINCIPAL);
        JPanel cardInventario = UiHelper.crearPanelEstadistica("Valor Inventario", "Cargando...", UiHelper.AMARILLO_ADVERTENCIA);
        
        lblCardGastoTotal = obtenerLabelValor(cardGastoTotal);
        lblCardProyectos = obtenerLabelValor(cardProyectos);
        lblCardPedidos = obtenerLabelValor(cardPedidos);
        lblCardInventario = obtenerLabelValor(cardInventario);
        
        panelEstadisticas.add(cardGastoTotal);
        panelEstadisticas.add(cardProyectos);
        panelEstadisticas.add(cardPedidos);
        panelEstadisticas.add(cardInventario);
        
        // TabbedPane interno para diferentes análisis
        JTabbedPane tabsGastos = new JTabbedPane();
        tabsGastos.putClientProperty("JTabbedPane.tabType", "underlined");
        tabsGastos.putClientProperty("JTabbedPane.tabHeight", 35);
        
        // Panel gastos por proyecto
        JPanel panelGastosProyecto = crearPanelGastosPorProyecto();
        tabsGastos.addTab("Por Proyecto", panelGastosProyecto);
        
        // Panel gastos por material
        JPanel panelGastosMaterial = crearPanelGastosPorMaterial();
        tabsGastos.addTab("Por Material", panelGastosMaterial);
        
        // Panel gastos por mes
        JPanel panelGastosMes = crearPanelGastosPorMes();
        tabsGastos.addTab("Por Mes/Año", panelGastosMes);
        
        panel.add(panelEstadisticas, BorderLayout.NORTH);
        panel.add(tabsGastos, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelGastosPorProyecto() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        String[] columnas = {"Proyecto", "Total Gastado", "% del Total"};
        modeloGastosPorProyecto = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaGastosPorProyecto = new JTable(modeloGastosPorProyecto);
        UiHelper.estilarTabla(tablaGastosPorProyecto);
        
        JScrollPane scrollPane = new JScrollPane(tablaGastosPorProyecto);
        UiHelper.estilarScrollPane(scrollPane);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelGastosPorMaterial() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        String[] columnas = {"Material", "Total Gastado", "% del Total"};
        modeloGastosPorMaterial = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaGastosPorMaterial = new JTable(modeloGastosPorMaterial);
        UiHelper.estilarTabla(tablaGastosPorMaterial);
        
        JScrollPane scrollPane = new JScrollPane(tablaGastosPorMaterial);
        UiHelper.estilarScrollPane(scrollPane);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelGastosPorMes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UiHelper.FONDO_OSCURO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        String[] columnas = {"Período", "Total Gastado", "% del Total"};
        modeloGastosPorMes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaGastosPorMes = new JTable(modeloGastosPorMes);
        UiHelper.estilarTabla(tablaGastosPorMes);
        
        JScrollPane scrollPane = new JScrollPane(tablaGastosPorMes);
        UiHelper.estilarScrollPane(scrollPane);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // ========== CARGA DE DATOS ==========
    private void cargarDatos() {
        cargarMateriales();
        cargarProyectos();
        cargarPedidos();
        cargarGastos();
        actualizarEstadisticasGenerales();
    }
    
    private void cargarMateriales() {
        modeloMateriales.setRowCount(0);
        List<Material> materiales = reporteService.obtenerTodosMateriales();
        double valorTotal = 0;
        
        for (Material m : materiales) {
            double valorMaterial = m.getStock() * m.getPrecioUnitario();
            valorTotal += valorMaterial;
            
            modeloMateriales.addRow(new Object[]{
                m.getNombProducto(),
                m.getDescripcion(),
                m.getUnidadMedida(),
                UiHelper.formatearMoneda(m.getPrecioUnitario()),
                m.getStock(),
                UiHelper.formatearMoneda(valorMaterial)
            });
        }
        
        lblTotalMateriales.setText("Total Materiales: " + materiales.size());
        lblValorInventario.setText("Valor Inventario: " + UiHelper.formatearMoneda(valorTotal));
    }
    
    private void cargarProyectos() {
        modeloProyectos.setRowCount(0);
        List<Proyecto> proyectos = reporteService.obtenerTodosProyectos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Proyecto p : proyectos) {
            double gastoProyecto = reporteService.calcularGastoTotalProyecto(p.getNombreProyecto());
            String fecha = p.getFechaInicio() != null ? sdf.format(p.getFechaInicio()) : "Sin fecha";
            
            modeloProyectos.addRow(new Object[]{
                p.getNombreProyecto(),
                p.getDescripcion(),
                p.getDireccion(),
                fecha,
                UiHelper.formatearMoneda(gastoProyecto)
            });
        }
        
        lblTotalProyectos.setText("Total Proyectos: " + proyectos.size());
    }
    
    private void cargarPedidos() {
        modeloPedidos.setRowCount(0);
        List<Pedido> pedidos = reporteService.obtenerTodosPedidos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double montoTotal = 0;
        
        for (Pedido p : pedidos) {
            String fecha = p.getFechaPedido() != null ? sdf.format(p.getFechaPedido()) : "Sin fecha";
            montoTotal += p.getSubtotal();
            
            modeloPedidos.addRow(new Object[]{
                p.getNombreProyecto(),
                p.getNombreMaterial(),
                p.getCantidad(),
                UiHelper.formatearMoneda(p.getCostoUnitarioAlMomento()),
                UiHelper.formatearMoneda(p.getSubtotal()),
                fecha
            });
        }
        
        lblTotalPedidos.setText("Total Pedidos: " + pedidos.size());
        lblTotalGastos.setText("Monto Total: " + UiHelper.formatearMoneda(montoTotal));
    }
    
    private void cargarGastos() {
        double gastoTotal = reporteService.calcularGastoTotal();
        
        // Gastos por proyecto
        modeloGastosPorProyecto.setRowCount(0);
        Map<String, Double> gastosPorProyecto = reporteService.obtenerGastosPorProyecto();
        for (Map.Entry<String, Double> entry : gastosPorProyecto.entrySet()) {
            double porcentaje = gastoTotal > 0 ? (entry.getValue() / gastoTotal) * 100 : 0;
            modeloGastosPorProyecto.addRow(new Object[]{
                entry.getKey(),
                UiHelper.formatearMoneda(entry.getValue()),
                String.format("%.1f%%", porcentaje)
            });
        }
        
        // Gastos por material
        modeloGastosPorMaterial.setRowCount(0);
        Map<String, Double> gastosPorMaterial = reporteService.obtenerGastosPorMaterial();
        for (Map.Entry<String, Double> entry : gastosPorMaterial.entrySet()) {
            double porcentaje = gastoTotal > 0 ? (entry.getValue() / gastoTotal) * 100 : 0;
            modeloGastosPorMaterial.addRow(new Object[]{
                entry.getKey(),
                UiHelper.formatearMoneda(entry.getValue()),
                String.format("%.1f%%", porcentaje)
            });
        }
        
        // Gastos por mes
        modeloGastosPorMes.setRowCount(0);
        Map<String, Double> gastosPorMes = reporteService.obtenerGastosPorMes();
        for (Map.Entry<String, Double> entry : gastosPorMes.entrySet()) {
            double porcentaje = gastoTotal > 0 ? (entry.getValue() / gastoTotal) * 100 : 0;
            modeloGastosPorMes.addRow(new Object[]{
                entry.getKey(),
                UiHelper.formatearMoneda(entry.getValue()),
                String.format("%.1f%%", porcentaje)
            });
        }
    }
    
    private void actualizarEstadisticasGenerales() {
        double gastoTotal = reporteService.calcularGastoTotal();
        int totalProyectos = reporteService.contarTotalProyectos();
        int totalPedidos = reporteService.contarTotalPedidos();
        double valorInventario = reporteService.obtenerValorInventario();

        lblCardGastoTotal.setText(UiHelper.formatearMoneda(gastoTotal));
        lblCardProyectos.setText(String.valueOf(totalProyectos));
        lblCardPedidos.setText(String.valueOf(totalPedidos));
        lblCardInventario.setText(UiHelper.formatearMoneda(valorInventario));
    }

    /**
     * Obtiene el JLabel de valor dentro de una card creada por UiHelper.crearPanelEstadistica.
     * La card tiene un JPanel (GridLayout) con [0]=título y [1]=valor.
     */
    private JLabel obtenerLabelValor(JPanel card) {
        for (java.awt.Component comp : card.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel contenido = (JPanel) comp;
                if (contenido.getComponentCount() >= 2) {
                    return (JLabel) contenido.getComponent(1);
                }
            }
        }
        return new JLabel();
    }

    private void configurarVentana() {
        //setTitle("📊 Reportes del Sistema - Gestión de Construcción");
        setSize(950, 700);
        setLocationRelativeTo(getOwner());
        setResizable(true);
        setMinimumSize(new Dimension(800, 600));
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
