package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CityGUI extends JFrame {  
    public static final int SIDE = 20;
    public final int SIZE;
    private JButton ticTacButton;
    private JPanel controlPanel;
    private PhotoCity photo;
    private City theCity;
    private Fachada fachada;

    // Menú
    private JMenuItem newItem, openItem, saveItem, importItem, exportItem, exitItem;

    public CityGUI() {
        theCity = new City();
        SIZE = theCity.getSize();
        fachada = new Fachada();  // Instanciar Fachada
        prepareElements();
        prepareElementsMenu();  // Llamar al método para crear el menú
        prepareActions();
        
        // Establecer el comportamiento de cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
    }

    private void prepareElements() {
        setTitle("Schelling City");
        photo = new PhotoCity(this);
        ticTacButton = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(ticTacButton, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE + 15, SIDE * SIZE + 72)); 
        setResizable(false);
        photo.repaint();
    }

    private void prepareElementsMenu() {
        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú de "Archivo"
        JMenu fileMenu = new JMenu("Archivo");

        // Crear las opciones del menú
        newItem = new JMenuItem("Nuevo");
        openItem = new JMenuItem("Abrir");
        saveItem = new JMenuItem("Guardar como");
        importItem = new JMenuItem("Importar");
        exportItem = new JMenuItem("Exportar como");
        exitItem = new JMenuItem("Salir");

        // Agregar las opciones al menú
        fileMenu.add(newItem);
        fileMenu.addSeparator();  // Separador
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(importItem);
        fileMenu.add(exportItem);
        fileMenu.addSeparator();  // Separador
        fileMenu.add(exitItem);

        // Agregar el menú a la barra de menú
        menuBar.add(fileMenu);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);
    }

    private void prepareActions() {
        ticTacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticTacButtonAction();
            }
        });

        // Agregar oyentes para las opciones del menú
        newItem.addActionListener(e -> optionNew());
        openItem.addActionListener(e -> optionOpen());
        saveItem.addActionListener(e -> optionSave());
        importItem.addActionListener(e -> optionImport());
        exportItem.addActionListener(e -> optionExport());
        exitItem.addActionListener(e -> optionExit());
    }

    private void ticTacButtonAction() {
        theCity.ticTac();
        photo.repaint();
    }

    // Métodos del controlador para las acciones del menú

    public void optionNew() {
        System.out.println("Nuevo: Reseteando la ciudad.");
        theCity = new City();  // Resetear la ciudad
        photo.repaint();
    }

    public void optionOpen() {
        System.out.println("Abrir archivo...");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fachada.open(file);
            } catch (CityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void optionSave() {
        System.out.println("Guardar archivo...");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fachada.save(file);
            } catch (CityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void optionImport() {
        System.out.println("Importar archivo...");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fachada.importFile(file);
            } catch (CityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void optionExport() {
        System.out.println("Exportar archivo...");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fachada.export(file);
            } catch (CityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void optionExit() {
        System.exit(0);  // Cerrar la aplicación
    }

    public City getTheCity() {
        return theCity;
    }

    public static void main(String[] args) {
        CityGUI cg = new CityGUI();
        cg.setVisible(true);
    }  
}

// Clase interna para la representación gráfica de la ciudad
class PhotoCity extends JPanel {
    private CityGUI gui;

    public PhotoCity(CityGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE + 10, gui.SIDE * gui.SIZE + 10));
    }

    public void paintComponent(Graphics g) {
        City theCity = gui.getTheCity();
        super.paintComponent(g);

        // Dibujar la cuadrícula de la ciudad
        for (int c = 0; c <= theCity.getSize(); c++) {
            g.drawLine(c * gui.SIDE, 0, c * gui.SIDE, theCity.getSize() * gui.SIDE);
        }
        for (int f = 0; f <= theCity.getSize(); f++) {
            g.drawLine(0, f * gui.SIDE, theCity.getSize() * gui.SIDE, f * gui.SIDE);
        }

        // Dibujar los elementos de la ciudad
        for (int f = 0; f < theCity.getSize(); f++) {
            for (int c = 0; c < theCity.getSize(); c++) {
                if (theCity.getItem(f, c) != null) {
                    g.setColor(theCity.getItem(f, c).getColor());
                    if (theCity.getItem(f, c).shape() == Item.SQUARE) {
                        if (theCity.getItem(f, c).isActive()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        } else {
                            g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        if (theCity.getItem(f, c).isActive()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        } else {
                            g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                    if (theCity.getItem(f, c).isAgent()) {
                        g.setColor(Color.red);
                        if (((Agent) theCity.getItem(f, c)).isHappy()) {
                            g.drawString("u", gui.SIDE * c + 6, gui.SIDE * f + 15);
                        } else if (((Agent) theCity.getItem(f, c)).isIndifferent()) {
                            g.drawString("_", gui.SIDE * c + 7, gui.SIDE * f + 10);
                        } else if (((Agent) theCity.getItem(f, c)).isDissatisfied()) {
                            g.drawString("~", gui.SIDE * c + 6, gui.SIDE * f + 17);
                        }
                    }
                }
            }
        }
    }
}
