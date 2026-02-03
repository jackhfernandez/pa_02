package unprg.capa_presentacion.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

/**
 *
 * @author jackh
 */
public class UiHelper {

    public static final Color MORADO_PRINCIPAL = new Color(160, 32, 240);
    public static final Color FONDO_CARD = new Color(201, 201, 245); // total inventario
    public static final Color FONDO_OSCURO = new Color(227, 235, 246); // celeste a blanco
    public static final Color FONDO_CLARO = new Color(197, 197, 237); // tabla
    public static final Color TEXTO_PRIMARIO = new Color(30, 57, 86);
    public static final Color TEXTO_SECUNDARIO = new Color(180, 180, 180);
    public static final Color VERDE_EXITO = new Color(40, 167, 69);
    public static final Color ROJO_ALERTA = new Color(220, 53, 69);
    public static final Color AMARILLO_ADVERTENCIA = new Color(255, 193, 7);
    public static final Color AZUL_INFO = new Color(23, 162, 184);

    static {
        UIManager.put("TextComponent.arc", 999);
        UIManager.put("Component.focusColor", MORADO_PRINCIPAL);
        UIManager.put("Component.innerFocusWidth", 1);
    }

    /*
     * public static void llenarTarjetaPelicula(JPanel panel, Pelicula p) {
     * panel.removeAll();
     * panel.setLayout(new BorderLayout(10, 10));
     * estilarCard(panel);
     * 
     * JLabel lblTitulo = new JLabel(p.getTitulo());
     * lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
     * lblTitulo.setForeground(Color.WHITE);
     * 
     * String info = p.getGenero() + " • " + new
     * SimpleDateFormat("yyyy").format(p.getFechaEstreno());
     * JLabel lblInfo = new JLabel(info);
     * lblInfo.setForeground(new Color(180, 180, 180));
     * 
     * JPanel pnlTexto = new JPanel(new GridLayout(2, 1));
     * pnlTexto.setOpaque(false);
     * pnlTexto.add(lblTitulo);
     * pnlTexto.add(lblInfo);
     * 
     * panel.add(pnlTexto, BorderLayout.SOUTH);
     * panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
     * 
     * panel.revalidate();
     * panel.repaint();
     * }
     */

    public static void estilarFecha(JDateChooser chooser) {
        JButton btnCalendar = chooser.getCalendarButton();
        btnCalendar.putClientProperty("JButton.buttonType", "roundRect");
        btnCalendar.setBackground(MORADO_PRINCIPAL);
        btnCalendar.setForeground(Color.WHITE);
        JTextField editor = (JTextField) chooser.getDateEditor().getUiComponent();
        editor.putClientProperty("JTextField.arc", 999);
        editor.putClientProperty("FlatLaf.style",
                "background: #2a2b2e;"
                        + "focusedBorderColor: " + colorToHex(MORADO_PRINCIPAL));
        chooser.setBorder(null);
        chooser.setOpaque(false);
    }

    public static void estilarBotonSidebar(JButton boton, String iconPath) {
        boton.putClientProperty("JButton.buttonType", "borderless");
        boton.putClientProperty("JComponent.arc", 20);
        boton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        boton.setIconTextGap(15);

        boton.putClientProperty("FlatLaf.style",
                "background: #1e1e1e;"
                        + "foreground: #bbbbbb;"
                        + "hoverForeground: #ffffff;"
                        + "hoverBackground: #2d2d30;"
                        + "hoverForeground: #ffffff");

        if (iconPath != null) {
            FlatSVGIcon icon = new FlatSVGIcon(iconPath, 22, 22);
            icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> MORADO_PRINCIPAL));
            boton.setIcon(icon);
        }
    }

    public static void estilarBotonSalir(JButton boton, String iconPath, Color hoverColor) {
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
        lblTitulo.setForeground(new Color(150, 150, 150));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.BOLD, 12f));

        javax.swing.JLabel lblValor = new javax.swing.JLabel(valor);
        lblValor.setForeground(Color.WHITE);
        lblValor.setFont(lblValor.getFont().deriveFont(java.awt.Font.BOLD, 28f));

        if (iconPath != null) {
            com.formdev.flatlaf.extras.FlatSVGIcon icon = new com.formdev.flatlaf.extras.FlatSVGIcon(iconPath, 30, 30);
            icon.setColorFilter(new com.formdev.flatlaf.extras.FlatSVGIcon.ColorFilter(color -> MORADO_PRINCIPAL));
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
        String style = "background: #2a2b2e;"
                + "foreground: #ffffff;"
                + "placeholderForeground: #8b8b8d;"
                + "focusedBackground: #34353a;"
                + "borderColor: #3d3e42;"
                + "focusedBorderColor: " + colorToHex(MORADO_PRINCIPAL) + ";";

        campo.putClientProperty("FlatLaf.style", style);
        campo.putClientProperty("JTextField.placeholderText", placeholder);
        campo.putClientProperty("JTextField.padding", new java.awt.Insets(6, 15, 6, 15));
    }

    public static void estilarCampo(JTextField campo, String placeholder, String iconPath) {

        String style = "background: #2a2b2e;"
                + "foreground: #ffffff;"
                + "placeholderForeground: #8b8b8d;"
                + "focusedBackground: #34353a;"
                + "borderColor: #3d3e42;"
                + "focusedBorderColor: " + colorToHex(MORADO_PRINCIPAL) + ";";

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
                icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> MORADO_PRINCIPAL));
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
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("JButton.arc", 999);
        boton.setBackground(MORADO_PRINCIPAL);
        boton.setForeground(Color.WHITE);
        boton.setFocusable(false);
    }

    public static void estilarCard(JComponent panel) {
        panel.putClientProperty("JComponent.arc", 40);
        panel.putClientProperty("FlatLaf.style",
                "background: #" + Integer.toHexString(FONDO_CARD.getRGB()).substring(2));
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
        // icono.setColorFilter(new FlatSVGIcon.ColorFilter(c -> MORADO_PRINCIPAL));
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
     * Estiliza una tabla con el tema FlatLaf oscuro
     */
    public static void estilarTabla(JTable tabla) {
        tabla.setBackground(FONDO_CLARO);
        tabla.setForeground(TEXTO_PRIMARIO);
        tabla.setSelectionBackground(MORADO_PRINCIPAL);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(new Color(60, 60, 65));
        tabla.setRowHeight(35);
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));
        tabla.setFillsViewportHeight(true);
        
        tabla.putClientProperty("JTable.showHorizontalLines", true);
        tabla.putClientProperty("JTable.showVerticalLines", true);
        
        // Estilizar header
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(FONDO_OSCURO);
        header.setForeground(TEXTO_PRIMARIO);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 13f));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setReorderingAllowed(false);
        
        // Centrar contenido de celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
    /**
     * Estiliza un JScrollPane para tablas
     */
    public static void estilarScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(FONDO_CLARO);
        scrollPane.putClientProperty("JScrollPane.smoothScrolling", true);
    }
    
    /**
     * Estiliza un JTabbedPane con el tema personalizado
     */
    public static void estilarTabbedPane(JTabbedPane tabbedPane) {
        tabbedPane.putClientProperty("JTabbedPane.tabType", "card");
        tabbedPane.putClientProperty("JTabbedPane.tabHeight", 40);
        tabbedPane.putClientProperty("JTabbedPane.tabInsets", new java.awt.Insets(8, 20, 8, 20));
        tabbedPane.putClientProperty("JTabbedPane.selectedBackground", MORADO_PRINCIPAL);
        tabbedPane.putClientProperty("JTabbedPane.selectedForeground", Color.WHITE);
        tabbedPane.putClientProperty("JTabbedPane.showTabSeparators", false);
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD, 12f));
    }
    
    /**
     * Estiliza un campo de búsqueda para filtros
     */
    public static void estilarCampoBusqueda(JTextField campo, String placeholder) {
        String style = "background: #2a2b2e;"
                + "foreground: #ffffff;"
                + "placeholderForeground: #8b8b8d;"
                + "focusedBackground: #34353a;"
                + "borderColor: #3d3e42;"
                + "focusedBorderColor: " + colorToHex(MORADO_PRINCIPAL) + ";"
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
            "background: #2a2b2e;" +
            "foreground: #ffffff;" +
            "buttonBackground: " + colorToHex(MORADO_PRINCIPAL) + ";" +
            "buttonArrowColor: #ffffff;");
    }
    
    /**
     * Crea un panel de estadística para el dashboard de reportes
     */
    public static JPanel crearPanelEstadistica(String titulo, String valor, Color colorAccento) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(FONDO_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorAccento, 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.putClientProperty("JComponent.arc", 20);
        
        JLabel lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setForeground(TEXTO_SECUNDARIO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 11f));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(colorAccento);
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
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.putClientProperty("FlatLaf.style",
            "background: #2a2b2e;" +
            "foreground: #ffffff;" +
            "hoverBackground: #3d3e42;" +
            "borderColor: " + colorToHex(MORADO_PRINCIPAL) + ";");
        boton.setFocusable(false);
    }
    
    /**
     * Formatea un valor monetario a string
     */
    public static String formatearMoneda(double valor) {
        return String.format("S/ %.2f", valor);
    }

}
