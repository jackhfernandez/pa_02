package unprg.capa_presentacion.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import unprg.capa_datos.MaterialDAO;
import unprg.capa_logica.modelos.Material;

/**
 *
 * @author jackh
 */
public class UiHelper {
    
    private static MaterialDAO materialDAO = new MaterialDAO();

    // Paleta de colores basada en el diseño de la interfaz
    public static final Color CYAN_BORDE = new Color(0, 211, 252);           // Bordes y acentos principales
    public static final Color MORADO_PRINCIPAL = new Color(0, 211, 252);      // Para compatibilidad (ahora es cyan)
    public static final Color FONDO_PRINCIPAL = new Color(13, 27, 42);        // Fondo principal azul oscuro/navy
    public static final Color FONDO_CARD = new Color(27, 40, 56);             // Fondo de tarjetas
    public static final Color FONDO_OSCURO = new Color(20, 33, 48);           // Fondo alternativo oscuro
    public static final Color FONDO_CLARO = new Color(35, 50, 68);            // Fondo más claro para tablas
    public static final Color BOTON_AMARILLO = new Color(240, 192, 64);       // Botones principales amarillo/dorado
    public static final Color TEXTO_PRIMARIO = new Color(255, 255, 255);      // Texto principal blanco
    public static final Color TEXTO_SECUNDARIO = new Color(160, 175, 190);    // Texto secundario gris azulado
    public static final Color VERDE_EXITO = new Color(40, 167, 69);
    public static final Color ROJO_ALERTA = new Color(255, 107, 53);          // Naranja/rojo para alertas
    public static final Color AMARILLO_ADVERTENCIA = new Color(255, 193, 7);
    public static final Color AZUL_INFO = new Color(0, 211, 252);             // Cyan info

    static {
        UIManager.put("TextComponent.arc", 999);
        UIManager.put("Component.focusColor", MORADO_PRINCIPAL);
        UIManager.put("Component.innerFocusWidth", 1);
    }

    /**
     * Aplica estilos globales a toda la aplicación.
     * Llamar UNA vez después de configurar el Look and Feel.
     */
    public static void aplicarEstiloGlobal() {
        // 1. Tooltips estilizados (fondo oscuro, borde cyan, texto blanco)
        UIManager.put("ToolTip.background", FONDO_CARD);
        UIManager.put("ToolTip.foreground", TEXTO_PRIMARIO);
        UIManager.put("ToolTip.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CYAN_BORDE, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));

        // 3. Scrollbars delgadas y estilizadas
        UIManager.put("ScrollBar.width", 8);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new java.awt.Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.track", FONDO_PRINCIPAL);
        UIManager.put("ScrollBar.thumb", CYAN_BORDE.darker());
        UIManager.put("ScrollBar.hoverThumbColor", CYAN_BORDE);
        UIManager.put("ScrollBar.showButtons", false);

        // 5. Menú contextual / PopupMenu estilizado
        UIManager.put("PopupMenu.background", FONDO_CARD);
        UIManager.put("PopupMenu.foreground", TEXTO_PRIMARIO);
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(CYAN_BORDE, 1));
        UIManager.put("MenuItem.background", FONDO_CARD);
        UIManager.put("MenuItem.foreground", TEXTO_PRIMARIO);
        UIManager.put("MenuItem.selectionBackground", FONDO_CLARO);
        UIManager.put("MenuItem.selectionForeground", CYAN_BORDE);
    }



    public static void estilarFecha(JDateChooser chooser) {
        JButton btnCalendar = chooser.getCalendarButton();
        btnCalendar.putClientProperty("JButton.buttonType", "roundRect");
        btnCalendar.setBackground(BOTON_AMARILLO);
        btnCalendar.setForeground(FONDO_PRINCIPAL);
        JTextField editor = (JTextField) chooser.getDateEditor().getUiComponent();
        editor.putClientProperty("JTextField.arc", 999);
        editor.setForeground(TEXTO_PRIMARIO);
        editor.setCaretColor(TEXTO_PRIMARIO);
        editor.putClientProperty("FlatLaf.style",
                "background: " + colorToHex(FONDO_CARD) + ";"
                        + "foreground: " + colorToHex(TEXTO_PRIMARIO) + ";"
                        + "borderColor: " + colorToHex(CYAN_BORDE) + ";"
                        + "focusedBorderColor: " + colorToHex(CYAN_BORDE));
        chooser.setBorder(null);
        chooser.setOpaque(false);

        // Re-aplicar color blanco cada vez que se selecciona una fecha
        chooser.addPropertyChangeListener("date", evt -> {
            editor.setForeground(TEXTO_PRIMARIO);
            editor.setCaretColor(TEXTO_PRIMARIO);
        });
    }

    public static void estilarBotonSidebar(JButton boton, String iconPath) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "borderless");
        boton.putClientProperty("JComponent.arc", 20);
        boton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        boton.setIconTextGap(15);

        boton.putClientProperty("FlatLaf.style",
                "background: " + colorToHex(FONDO_PRINCIPAL) + ";"
                        + "foreground: " + colorToHex(TEXTO_SECUNDARIO) + ";"
                        + "hoverForeground: #ffffff;"
                        + "hoverBackground: " + colorToHex(FONDO_CARD) + ";"
                        + "borderColor: " + colorToHex(CYAN_BORDE) + ";");

        if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 22, 22);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> CYAN_BORDE));
            boton.setIcon(icon);
        }
    }

        public static void estilarBoton(JButton boton, String iconPath, Color bgColor, Color iconColor) {
            boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            boton.putClientProperty("JButton.buttonType", "borderless");
            boton.putClientProperty("JComponent.arc", 20);
            boton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            boton.setIconTextGap(15);

            // Determinar si el fondo es claro para usar texto negro, o texto claro si es oscuro
            double luminancia = (0.299 * bgColor.getRed() + 0.587 * bgColor.getGreen() + 0.114 * bgColor.getBlue()) / 255.0;
            Color textoColor = luminancia > 0.5 ? FONDO_PRINCIPAL : TEXTO_SECUNDARIO;

            boton.putClientProperty("FlatLaf.style",
                "background: " + colorToHex(bgColor) + ";"
                    + "foreground: " + colorToHex(textoColor) + ";"
                    + "hoverForeground: #ffffff;"
                    + "hoverBackground: " + colorToHex(FONDO_CARD) + ";"
                    + "borderColor: " + colorToHex(CYAN_BORDE) + ";");

            if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 22, 22);
            if (iconColor != null) {
                icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> iconColor));
            }
            boton.setIcon(icon);
            }
        }

    public static void estilarBotonSalir(JButton boton, String iconPath, Color hoverColor) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "borderless");
        boton.putClientProperty("JComponent.arc", 15);
        boton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        boton.setIconTextGap(15);

        boton.putClientProperty("FlatLaf.style",
                "background: #DC3545;"
                        + "foreground: #ffffff;"
                        + "hoverBackground: " + colorToHex(hoverColor) + ";"
                        + "hoverForeground: #ffffff");

        if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 22, 22);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> Color.WHITE));
            boton.setIcon(icon);
        }
    }

    public static void crearTarjetasEstadisticas(JPanel panel, String titulo, String valor, String iconPath) {
        panel.setLayout(new java.awt.BorderLayout(10, 10));
        estilarCard(panel);

        javax.swing.JLabel lblTitulo = new javax.swing.JLabel(titulo.toUpperCase());
        lblTitulo.setForeground(TEXTO_SECUNDARIO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.BOLD, 12f));

        javax.swing.JLabel lblValor = new javax.swing.JLabel(valor);
        lblValor.setForeground(TEXTO_PRIMARIO);
        lblValor.setFont(lblValor.getFont().deriveFont(java.awt.Font.BOLD, 28f));

        if (iconPath != null) {
            com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon(iconPath, 30, 30);
            icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> CYAN_BORDE));
            panel.add(new javax.swing.JLabel(icon), java.awt.BorderLayout.WEST);

        }

        javax.swing.JPanel pnlTexto = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
        pnlTexto.setOpaque(false);
        pnlTexto.add(lblTitulo);
        pnlTexto.add(lblValor);

        panel.add(pnlTexto, java.awt.BorderLayout.CENTER);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    public static void estilarCampoForm(JTextField campo, String placeholder) {
        String style = "background: " + colorToHex(FONDO_CARD) + ";"
                + "foreground: #ffffff;"
                + "placeholderForeground: " + colorToHex(TEXTO_SECUNDARIO) + ";"
                + "focusedBackground: " + colorToHex(FONDO_CLARO) + ";"
                + "borderColor: " + colorToHex(CYAN_BORDE) + ";"
                + "focusedBorderColor: " + colorToHex(CYAN_BORDE) + ";";

        campo.putClientProperty("FlatLaf.style", style);
        campo.putClientProperty("JTextField.placeholderText", placeholder);
        campo.putClientProperty("JTextField.padding", new java.awt.Insets(6, 15, 6, 15));
    }

    public static void estilarCampo(JTextField campo, String placeholder, String iconPath) {

        String style = "background: " + colorToHex(FONDO_CARD) + ";"
                + "foreground: #ffffff;"
                + "placeholderForeground: " + colorToHex(TEXTO_SECUNDARIO) + ";"
                + "focusedBackground: " + colorToHex(FONDO_CLARO) + ";"
                + "borderColor: " + colorToHex(CYAN_BORDE) + ";"
                + "focusedBorderColor: " + colorToHex(CYAN_BORDE) + ";";

        if (campo instanceof JPasswordField) {
            style += "showRevealButton: true;";
        } else {
            style += "showClearButton: true;";
        }

        campo.putClientProperty("FlatLaf.style", style);
        campo.putClientProperty("JTextField.placeholderText", placeholder);
        campo.putClientProperty("JTextField.padding", new java.awt.Insets(8, 15, 8, 15));

        if (iconPath != null) {
            try {
                FlatSVGIcon icon = new FlatSVGIcon(iconPath);
                icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> CYAN_BORDE));
                campo.putClientProperty("JTextField.leadingIcon", icon);

            } catch (Exception e) {
                System.err.println("Error cargando icono: " + iconPath);
            }
        }
    }

    public static void setEstadoError(JTextField campo, boolean tieneError) {
        if (tieneError) {
            campo.putClientProperty("JComponent.outline", "error");
        } else {
            campo.putClientProperty("JComponent.outline", null);
        }
        campo.repaint();
    }

    public static void marcarError(JTextField campo, boolean error) {
        if (error) {
            campo.putClientProperty("JComponent.outline", "error");
        } else {
            campo.putClientProperty("JComponent.outline", null);
        }
        campo.repaint();
    }

    public static void estilarBotonPrincipal(JButton boton) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("JButton.arc", 999);
        boton.setBackground(CYAN_BORDE);
        boton.setForeground(FONDO_PRINCIPAL);
        boton.setFocusable(false);
    }

    public static void estilarCard(JComponent panel) {
        panel.putClientProperty("FlatLaf.style",
                "arc: 40; background: " + colorToHex(FONDO_CARD) + ";");
        // Efecto glow: borde cyan + sombra exterior suave
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 211, 252, 60), 3),  // glow exterior
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CYAN_BORDE, 1),              // borde principal
                BorderFactory.createEmptyBorder(2, 2, 2, 2)                 // padding interno
            )
        ));
    }

    /**
     * Establece el icono principal para un JLabel usando un archivo SVG.
     * <br>
     * <b>Ejemplo de uso en "Custom Code" (Post-Creation Code) de NetBeans:</b>
     * 
     * <pre>
     * unprg.capa_presentacion.utils.UiHelper.establecerIconoPrincipal(logoInventario, "icons/Inventario.svg");
     * </pre>
     * 
     * @param label El componente JLabel al que asignar el icono.
     * @param path  Ruta del recurso dentro del classpath (ej:
     *              "icons/Inventario.svg").
     */
    public static void establecerIconoPrincipal(JLabel label, String path) {
        establecerIcono(label, path, 60, 60);
    }

    /**
     * Establece un icono SVG con dimensiones perzonalizadas.
     * <br>
     * <b>Ejemplo:</b>
     * 
     * <pre>
     * UiHelper.establecerIcono(lblPeque, "icons/user.svg", 24, 24);
     * </pre>
     * 
     * @param label  El label donde se pondrá el icono.
     * @param path   Ruta del SVG (ej: "icons/icon.svg").
     * @param width  Ancho deseado en pixeles.
     * @param height Alto deseado en pixeles.
     */
    public static void establecerIcono(JLabel label, String path, int width, int height) {
        FlatSVGIcon icono = new FlatSVGIcon(path, width, height);
        label.setIcon(icono);
    }

    public static void establecerIcono(JLabel label, String path, int width, int height, Color color) {
        FlatSVGIcon icono = new FlatSVGIcon(path, width, height);
        if (color != null) {
            icono.setColorFilter(new FlatSVGIcon.ColorFilter(c -> color));
        }
        label.setIcon(icono);
    }

    private static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Convierte un JPanel en un botón interactivo.
     * Agrega eventos de mouse para cambiar el color de fondo al pasar el cursor.
     * Al entrar, oscurece el color (resta 20 a RGB). Al salir, restaura el
     * original.
     * 
     * @param panel El JPanel al que se le agregarán los efectos.
     */
    public static void convertirABoton(JPanel panel) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            private Color colorOriginal = panel.getBackground(); // Capturamos una sola vez

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // Si el color cambió por otra razón, actualizamos, pero evitamos oscurecer
                // sobre lo oscuro
                if (!panel.getBackground().equals(darken(colorOriginal))) {
                    colorOriginal = panel.getBackground();
                }

                panel.setBackground(darken(colorOriginal));
                panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                panel.setBackground(colorOriginal);
                panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }

            // Efecto extra al hacer click (opcional, para mayor feedback)
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                panel.setBackground(darken(darken(colorOriginal))); // Aún más oscuro al clickar
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (panel.contains(e.getPoint())) {
                    panel.setBackground(darken(colorOriginal)); // Vuelve a estado "hover"
                } else {
                    panel.setBackground(colorOriginal); // Vuelve a normal
                }
            }

            private Color darken(Color c) {
                int r = Math.max(0, c.getRed() - 20);
                int g = Math.max(0, c.getGreen() - 20);
                int b = Math.max(0, c.getBlue() - 20);
                return new Color(r, g, b);
            }
        });
    }

    // ========== MÉTODOS PARA REPORTES ==========
    
    /**
     * Estiliza una tabla con el tema FlatLaf oscuro y filos cyan en las líneas
     */
    public static void estilarTabla(JTable tabla) {
        tabla.setBackground(FONDO_CARD);
        tabla.setForeground(TEXTO_PRIMARIO);
        tabla.setSelectionBackground(CYAN_BORDE);
        tabla.setSelectionForeground(FONDO_PRINCIPAL);
        tabla.setGridColor(CYAN_BORDE);
        tabla.setRowHeight(35);
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));
        tabla.setFillsViewportHeight(true);
        
        tabla.putClientProperty("JTable.showHorizontalLines", true);
        tabla.putClientProperty("JTable.showVerticalLines", true);
        tabla.putClientProperty("JTable.gridColor", CYAN_BORDE);
        
        // Estilizar header
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(FONDO_OSCURO);
        header.setForeground(CYAN_BORDE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 13f));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setReorderingAllowed(false);
        header.setBorder(BorderFactory.createLineBorder(CYAN_BORDE, 1));
        
        // Renderer personalizado con borde cyan en las filas
        DefaultTableCellRenderer filoRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    comp.setBackground(CYAN_BORDE);
                    comp.setForeground(FONDO_PRINCIPAL);
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    comp.setBackground(row % 2 == 0 ? FONDO_CARD : FONDO_CLARO);
                    comp.setForeground(TEXTO_PRIMARIO);
                }
                
                setBorder(BorderFactory.createLineBorder(CYAN_BORDE, 1));
                setHorizontalAlignment(JLabel.CENTER);
                return comp;
            }
        };
        
        // Aplicar renderer a todas las columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(filoRenderer);
        }
    }
    
    /**
     * Estiliza un JScrollPane para tablas
     */
    public static void estilarScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(CYAN_BORDE, 1));
        scrollPane.getViewport().setBackground(FONDO_CARD);
        scrollPane.putClientProperty("JScrollPane.smoothScrolling", true);
    }
    
    /**
     * Estiliza un JTabbedPane con el tema personalizado
     */
    public static void estilarTabbedPane(JTabbedPane tabbedPane) {
        tabbedPane.putClientProperty("JTabbedPane.tabType", "card");
        tabbedPane.putClientProperty("JTabbedPane.tabHeight", 40);
        tabbedPane.putClientProperty("JTabbedPane.tabInsets", new java.awt.Insets(8, 20, 8, 20));
        tabbedPane.putClientProperty("JTabbedPane.selectedBackground", BOTON_AMARILLO);
        tabbedPane.putClientProperty("JTabbedPane.selectedForeground", FONDO_PRINCIPAL);
        tabbedPane.putClientProperty("JTabbedPane.showTabSeparators", false);
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD, 12f));
    }
    
    /**
     * Estiliza un campo de búsqueda para filtros
     */
    public static void estilarCampoBusqueda(JTextField campo, String placeholder) {
        String style = "background: " + colorToHex(FONDO_CARD) + ";"
                + "foreground: #ffffff;"
                + "placeholderForeground: " + colorToHex(TEXTO_SECUNDARIO) + ";"
                + "focusedBackground: " + colorToHex(FONDO_CLARO) + ";"
                + "borderColor: " + colorToHex(CYAN_BORDE) + ";"
                + "focusedBorderColor: " + colorToHex(CYAN_BORDE) + ";"
                + "showClearButton: true;";
        
        campo.putClientProperty("FlatLaf.style", style);
        campo.putClientProperty("JTextField.placeholderText", placeholder);
        campo.putClientProperty("JTextField.padding", new java.awt.Insets(8, 12, 8, 12));
        
        try {
            FlatSVGIcon icon = new FlatSVGIcon("icons/search.svg", 16, 16);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> TEXTO_SECUNDARIO));
            campo.putClientProperty("JTextField.leadingIcon", icon);
        } catch (Exception e) {
            // Si no existe el icono, continuar sin él
        }
    }
    
    /**
     * Estiliza un JComboBox
     */
    public static void estilarComboBox(JComboBox<?> combo) {
        combo.putClientProperty("JComboBox.buttonStyle", "none");
        combo.putClientProperty("FlatLaf.style", 
            "background: " + colorToHex(FONDO_CARD) + ";" +
            "foreground: #ffffff;" +
            "borderColor: " + colorToHex(CYAN_BORDE) + ";" +
            "buttonBackground: " + colorToHex(BOTON_AMARILLO) + ";" +
            "buttonArrowColor: " + colorToHex(FONDO_PRINCIPAL) + ";");
    }
    
    /**
     * Crea un panel de estadística para el dashboard de reportes
     */
    public static JPanel crearPanelEstadistica(String titulo, String valor, Color colorAccento) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(FONDO_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CYAN_BORDE, 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.putClientProperty("JComponent.arc", 20);
        
        JLabel lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setForeground(TEXTO_SECUNDARIO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 11f));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(TEXTO_PRIMARIO);
        lblValor.setFont(lblValor.getFont().deriveFont(Font.BOLD, 24f));
        
        JPanel pnlContenido = new JPanel(new GridLayout(2, 1, 0, 5));
        pnlContenido.setOpaque(false);
        pnlContenido.add(lblTitulo);
        pnlContenido.add(lblValor);
        
        panel.add(pnlContenido, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Estiliza un botón secundario (para acciones como exportar, refrescar)
     */
    public static void estilarBotonSecundario(JButton boton) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("FlatLaf.style",
            "background: " + colorToHex(FONDO_CARD) + ";" +
            "foreground: #ffffff;" +
            "hoverBackground: " + colorToHex(FONDO_CLARO) + ";" +
            "borderColor: " + colorToHex(CYAN_BORDE) + ";");
        boton.setFocusable(false);
    }
    
    /**
     * Formatea un valor monetario a string
     */
    public static String formatearMoneda(double valor) {
        return String.format("S/ %.2f", valor);
    }

    /**
     * Redondea las esquinas de un panel existente con un radio personalizado.
     * Aplica pintura personalizada para dibujar esquinas redondeadas.
     */
    public static void redondearPanel(JPanel panel, int arcRadius) {
        final Color bgColor = panel.getBackground();
        panel.setOpaque(false);
        
        // Guardar el UI original y sobrescribir paintComponent
        panel.setUI(new javax.swing.plaf.basic.BasicPanelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), arcRadius, arcRadius));
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    /**
     * Redondea las esquinas de un panel con radio por defecto (20)
     */
    public static void redondearPanel(JPanel panel) {
        redondearPanel(panel, 20);
    }

    /**
     * Clase de panel con esquinas redondeadas para usar en nuevos componentes.
     * Uso: new UiHelper.RoundedPanel(20) en lugar de new JPanel()
     */
    public static class RoundedPanel extends JPanel {
        private int arcRadius;
        private Color backgroundColor;

        public RoundedPanel(int arcRadius) {
            this.arcRadius = arcRadius;
            setOpaque(false);
        }

        public RoundedPanel() {
            this(20);
        }

        @Override
        public void setBackground(Color bg) {
            this.backgroundColor = bg;
            super.setBackground(bg);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor != null ? backgroundColor : getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcRadius, arcRadius));
            g2.dispose();
            super.paintComponent(g);
        }

        public void setArcRadius(int arcRadius) {
            this.arcRadius = arcRadius;
            repaint();
        }

        public int getArcRadius() {
            return arcRadius;
        }
    }


    // ========== BOTONES DE OPERACIÓN (AGREGAR, MODIFICAR, LIMPIAR, SALIR) ==========

    /** Color teal para operaciones generales (Agregar, Modificar, Limpiar) */
    public static final Color TEAL_OPERACION = new Color(0, 150, 136);
    /** Color teal hover */
    private static final Color TEAL_HOVER = new Color(0, 121, 107);
    /** Color rojo para botón Salir */
    private static final Color ROJO_SALIR = new Color(211, 47, 47);
    /** Color rojo hover para botón Salir */
    private static final Color ROJO_SALIR_HOVER = new Color(183, 28, 28);

    /**
     * Estiliza un botón de operación general (AGREGAR, MODIFICAR, LIMPIAR).
     * Fondo teal, texto blanco, icono SVG blanco con bordes redondeados.
     *
     * @param boton    El JButton a estilizar.
     * @param iconPath Ruta del SVG dentro del classpath (ej: "icons/plus.svg").
     */
    public static void estilarBotonOperacion(JButton boton, String iconPath) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("JComponent.arc", 12);
        boton.setIconTextGap(8);
        boton.setFocusable(false);
        boton.setFont(boton.getFont().deriveFont(java.awt.Font.BOLD, 12f));

        boton.putClientProperty("FlatLaf.style",
                "background: " + colorToHex(TEAL_OPERACION) + ";"
                        + "foreground: #ffffff;"
                        + "hoverBackground: " + colorToHex(TEAL_HOVER) + ";"
                        + "hoverForeground: #ffffff;"
                        + "pressedBackground: " + colorToHex(TEAL_HOVER.darker()) + ";"
                        + "borderWidth: 0;"
                        + "innerFocusWidth: 0;");

        if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 18, 18);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> Color.WHITE));
            boton.setIcon(icon);
        }
    }

    /**
     * Estiliza el botón SALIR con fondo rojo, texto blanco e icono SVG blanco.
     *
     * @param boton    El JButton a estilizar.
     * @param iconPath Ruta del SVG (ej: "icons/salir.svg").
     */
    public static void estilarBotonSalirOperacion(JButton boton, String iconPath) {
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("JComponent.arc", 12);
        boton.setIconTextGap(8);
        boton.setFocusable(false);
        boton.setFont(boton.getFont().deriveFont(java.awt.Font.BOLD, 12f));

        boton.putClientProperty("FlatLaf.style",
                "background: " + colorToHex(ROJO_SALIR) + ";"
                        + "foreground: #ffffff;"
                        + "hoverBackground: " + colorToHex(ROJO_SALIR_HOVER) + ";"
                        + "hoverForeground: #ffffff;"
                        + "pressedBackground: " + colorToHex(ROJO_SALIR_HOVER.darker()) + ";"
                        + "borderWidth: 0;"
                        + "innerFocusWidth: 0;");

        if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 18, 18);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> Color.WHITE));
            boton.setIcon(icon);
        }
    }

    //grafico 
    public static void inicializarGrafico(JPanel panel) {
        // Colores del tema
        Color fondoPanel = new Color(0,185,221);      // Teal del fondo
        Color colorBarras = new Color(255, 200, 50);    // Amarillo/dorado que contrasta bien
        Color colorTexto = new Color(255, 255, 255);    // Blanco para texto
        Color colorEjes = new Color(200, 230, 240);     // Gris claro para ejes
        Font fuenteEjes = new Font("SansSerif", Font.PLAIN, 10); // Fuente pequeña

        // 1. Obtener los 5 materiales con más stock del DAO
        List<Material> materiales = materialDAO.listar();
        
        // Ordenar materiales por stock en orden descendente
        materiales.sort((m1, m2) -> Integer.compare(m2.getStock(), m1.getStock()));
        
        // Tomar solo los primeros 5 (o menos si hay menos de 5 materiales)
        int cantidad = Math.min(5, materiales.size());
        
        // 2. Crear el dataset con los datos obtenidos del DAO
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < cantidad; i++) {
            Material material = materiales.get(i);
            dataset.addValue(material.getStock(), "Stock", material.getNombProducto());
        }

        // 3. Crear el gráfico sin títulos ni leyendas
        JFreeChart chart = ChartFactory.createBarChart(
            null, null, null, dataset,
            PlotOrientation.VERTICAL, false, false, false);

        // 4. Fondo del gráfico
        chart.setBackgroundPaint(fondoPanel);

        // 5. Configurar el plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(fondoPanel);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(255, 255, 255, 50));

        // 6. Configurar eje Y (rango)
        plot.getRangeAxis().setTickLabelPaint(colorTexto);
        plot.getRangeAxis().setTickLabelFont(fuenteEjes);
        plot.getRangeAxis().setAxisLinePaint(colorEjes);
        plot.getRangeAxis().setTickMarkPaint(colorEjes);

        // 7. Configurar eje X (categorías) - fuente pequeña para que quepan los nombres
        plot.getDomainAxis().setTickLabelPaint(colorTexto);
        plot.getDomainAxis().setTickLabelFont(fuenteEjes);
        plot.getDomainAxis().setAxisLinePaint(colorEjes);
        plot.getDomainAxis().setTickMarkPaint(colorEjes);
        plot.getDomainAxis().setMaximumCategoryLabelLines(2); // Permite 2 líneas si es largo

        // 8. Personalizar las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        renderer.setShadowVisible(false);
        renderer.setMaximumBarWidth(0.12);
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());

        // 9. Vincular al JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(fondoPanel);
        chartPanel.setBorder(null);

        panel.setLayout(new BorderLayout());
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}
