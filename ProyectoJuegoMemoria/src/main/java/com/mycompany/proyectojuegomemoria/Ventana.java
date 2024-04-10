/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectojuegomemoria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.List;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import javax.swing.JLabel;
/**
 *
 * @author Erick
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
    }
    String nombrePlayer;
    //Variable que contiene la ruta del archivo que contiene los rankings
    String archivoRanking ;
    //Variables Para crear un nunmero random 
    Random rand = new Random();
    ImageIcon ComparadorImagen;
    int clickedButton1HashCode;
    int clickedButton2HashCode;
    boolean modoOscuroActivado=false;
    List<JButton> BotonesColor = new ArrayList<>();
    ImageIcon icono = new ImageIcon("src/icons/InicioJuego.png");
    ImageIcon Sol = new ImageIcon("src/icons/Sol.png");
    ImageIcon Luna = new ImageIcon("src/icons/Luna.png");
    
    
    String[] iconos = new String[] { 
        "bmo",
        "finn",
        "flame-princess",
        "futurama-bender",
        "grinch",
        "gunter",
        "homer-simpson",
        "iron-man",
        "jake",
        "lampara-de-aladino",
        "mascara-anonima",
        "momia",
        "scream",
        "son-goku",
        "super-mario"
    };

    
    public class Sonido {
    private Clip clip;

    public Sonido(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
    
     Sonido botonsound = new Sonido("src\\main\\java\\music\\botonsound.wav");
     Sonido click = new Sonido("src\\main\\java\\music\\click.wav");
     Sonido finish = new Sonido("src\\main\\java\\music\\finish.wav");
     Sonido musicaPartida = new Sonido("src\\main\\java\\music\\gameMusic.wav");
     Sonido pareja = new Sonido("src\\main\\java\\music\\parejaSound.wav");
     Sonido victoria = new Sonido("src\\main\\java\\music\\victoria.wav");
     Sonido inicio = new Sonido("src\\main\\java\\music\\startscreen.wav");
     Sonido luz = new Sonido("src\\main\\java\\music\\luz.wav");
      
public class RankingManager {

    public static void actualizarRanking(String rankingFileName, String nombreJugador, int puntaje) {
        // Cargar el ranking actual
        List<String> ranking = cargarRanking(rankingFileName);
        // Agregar la nueva entrada al ranking
        ranking.add(nombreJugador + ", " + puntaje);
        // Ordenar el ranking por puntaje en orden descendente
        ranking.sort((a, b) -> {
            int puntajeA = Integer.parseInt(a.split(", ")[1]);
            int puntajeB = Integer.parseInt(b.split(", ")[1]);
           return Integer.compare(puntajeA, puntajeB);
        });

        // Limitar el ranking a las 10 mejores entradas
        if (ranking.size() > 10) {
            ranking = ranking.subList(0, 10);
        }

        // Guardar el ranking actualizado
        guardarRanking(rankingFileName, ranking);
    }

    public static List<String> cargarRanking(String rankingFileName) {
        List<String> ranking = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(rankingFileName))) {
            while (scanner.hasNextLine()) {
                ranking.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // Manejar errores de lectura del archivo
            e.printStackTrace();
        }
        return ranking;
    }

    public static void guardarRanking(String rankingFileName, List<String> ranking) {
        try (PrintWriter writer = new PrintWriter(rankingFileName)) {
            for (String entrada : ranking) {
                writer.println(entrada);
            }
        } catch (FileNotFoundException e) {
            // Manejar errores de escritura en el archivo
            e.printStackTrace();
        }
    }
}

    
    //Variable controladora global
    int turnos,encontradas,insignia;
    String comparador,botonTipo;
    JButton controlador;
    //declarar los modelos para los combobox
    DefaultComboBoxModel ModeloParejas = new DefaultComboBoxModel();
    DefaultComboBoxModel ModeloTipos = new DefaultComboBoxModel();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        BtnCambio = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtContador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTurnos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelBtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Parejas = new javax.swing.JComboBox<>();
        Tipos = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaRanking = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        nombreJugador = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BtnCambioUsuario = new javax.swing.JButton();
        BtnModoOscuro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel4.setText("Tipo de parejas");

        BtnCambio.setText("Aplicar Cambios");
        BtnCambio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BtnCambioMouseMoved(evt);
            }
        });
        BtnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCambioActionPerformed(evt);
            }
        });

        jLabel5.setText("Parejas encontradas");

        txtContador.setEditable(false);
        txtContador.setBackground(new java.awt.Color(255, 255, 255));
        txtContador.setText("0/10");

        jLabel1.setText("Numero de intentos:");

        txtTurnos.setEditable(false);
        txtTurnos.setBackground(new java.awt.Color(255, 255, 255));
        txtTurnos.setText("0");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Juego de memoria");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        PanelBtn.setBackground(new java.awt.Color(204, 255, 255));
        PanelBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.black, java.awt.Color.darkGray));
        PanelBtn.setMaximumSize(new java.awt.Dimension(500, 600));
        PanelBtn.setMinimumSize(new java.awt.Dimension(500, 600));
        PanelBtn.setRequestFocusEnabled(false);

        javax.swing.GroupLayout PanelBtnLayout = new javax.swing.GroupLayout(PanelBtn);
        PanelBtn.setLayout(PanelBtnLayout);
        PanelBtnLayout.setHorizontalGroup(
            PanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelBtnLayout.setVerticalGroup(
            PanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setText("Numero de parejas");

        Parejas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Parejas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                son(evt);
            }
        });
        Parejas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sonido(evt);
            }
        });

        Tipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Tipos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TiposMouseMoved(evt);
            }
        });

        areaRanking.setColumns(20);
        areaRanking.setRows(5);
        jScrollPane2.setViewportView(areaRanking);

        jLabel6.setText("Jugador:");

        jLabel7.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel7.setText("TOP 10");

        BtnCambioUsuario.setText("Cambiar usuario");
        BtnCambioUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnCambioUsuarioMouseEntered(evt);
            }
        });
        BtnCambioUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCambioUsuarioActionPerformed(evt);
            }
        });

        BtnModoOscuro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnModoOscuroMouseEntered(evt);
            }
        });
        BtnModoOscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModoOscuroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCambioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BtnCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(Tipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(Parejas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BtnModoOscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContador, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(231, 231, 231))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Parejas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(BtnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCambioUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnModoOscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 501, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(nombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)
                        .addComponent(txtContador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCambioActionPerformed
    click.reproducir();   
    while (nombrePlayer == null || nombrePlayer.trim().isEmpty()) {
    nombrePlayer = JOptionPane.showInputDialog("Ingrese su nickname:");

    if (nombrePlayer == null) {
        // El usuario hizo clic en Cancelar, por lo que no ingresó un nombre.
        JOptionPane.showMessageDialog(null, "Se ha cancelado la operación.");
       return;
    } else if(nombrePlayer.trim().isEmpty())
                {
                JOptionPane.showMessageDialog(null, "No se ingresó un nombre válido.");
                }
}
        inicio.detener();
        musicaPartida.reproducir();
        PanelBtn.setLayout(new GridLayout(6, 5));
        if(modoOscuroActivado){
            PanelBtn.setBackground(Color.BLUE);
        }else{
            PanelBtn.setBackground(Color.CYAN);
        }
        nombreJugador.setText(nombrePlayer);
        PanelBtn.removeAll();
        insignia=0;
        botonTipo=Tipos.getSelectedItem().toString();
        int ControladorParejas=Integer.parseInt(Parejas.getSelectedItem().toString());
        ArrayList<Integer> numeros = new ArrayList<>();

        archivoRanking = "src/main/java/com/mycompany/proyectojuegomemoria/Rankings/"+ControladorParejas+botonTipo+".txt";
        
        ActualizarRecord();       

        encontradas=0;
        turnos=0;
        //al aceptar el boton inicializo las variables turnos y encontradas
        txtContador.setText(encontradas+"/"+ControladorParejas);
        txtTurnos.setText(turnos+"");
       
        
        if(botonTipo == "Letras" || botonTipo == "Numeros") {
            // Actualizo el texfield de turnos y encontradas
            for (int i = 1; i <= ControladorParejas * 2; i++) {
                int numeroAleatorio;

                // Si ya tenemos dos instancias de este número, elige otro
                do {
                    numeroAleatorio = rand.nextInt(ControladorParejas) + 1;
                } while (Collections.frequency(numeros, numeroAleatorio) >= 2);

                numeros.add(numeroAleatorio);
                Botones(Integer.toString(numeroAleatorio),ControladorParejas);
            }
        } else {
            ArrayList<String> iconosAleatorios = new ArrayList<>(Arrays.asList(iconos));

            Collections.shuffle(iconosAleatorios);
            
            if (iconosAleatorios.size() > ControladorParejas) {
                iconosAleatorios.subList(ControladorParejas, iconosAleatorios.size()).clear();
            }
            
            ArrayList<String> iconosDuplicadosAleatorios = new ArrayList<>();
            for (String icono : iconosAleatorios) {
                iconosDuplicadosAleatorios.add(icono);
                iconosDuplicadosAleatorios.add(icono);
            }

            Collections.shuffle(iconosDuplicadosAleatorios); 

            for (int i = 1; i <= ControladorParejas * 2 ; i++) {
                String icono = iconosDuplicadosAleatorios.get(i - 1);

                Botones(icono, ControladorParejas);
            }
        }
        
        PanelBtn.updateUI();//Al terminar todo se hace un refresh a la pantalla
    }//GEN-LAST:event_BtnCambioActionPerformed
    private void ActualizarRecord(){
        try {
            File archivo = new File(archivoRanking);
            Scanner scanner = new Scanner(archivo);
            // Creamos una variable para acumular los números leídos
            StringBuilder numerosLeidos = new StringBuilder();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                numerosLeidos.append(linea).append("\n"); // Agregar número y salto de línea
            }
            areaRanking.setText(numerosLeidos.toString());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        BtnModoOscuro.setOpaque(false);
        BtnModoOscuro.setContentAreaFilled(false);
        BtnModoOscuro.setBorderPainted(false);
        Luna.setImage(Luna.getImage().getScaledInstance(119, 119, Image.SCALE_DEFAULT));
        BtnModoOscuro.setIcon(Luna);
        PanelBtn.setMinimumSize(new Dimension(500, 500)); 
        PanelBtn.setMaximumSize(new Dimension(500, 500)); 
        inicio.reproducir();

        //Hacemos dos modelos de combobox y lo agregamos a su respectivo combo box
        ModeloParejas.addElement("10");
        ModeloParejas.addElement("11");
        ModeloParejas.addElement("12");
        ModeloParejas.addElement("13");
        ModeloParejas.addElement("14");
        ModeloParejas.addElement("15");
        Parejas.setModel(ModeloParejas);
        Parejas.setSelectedItem("10");

        ModeloTipos.addElement("Letras");
        ModeloTipos.addElement("Numeros");
        ModeloTipos.addElement("Imagenes");
        Tipos.setModel(ModeloTipos);
        Tipos.setSelectedItem("Numeros");
        //Anteriormente agregue los modelos del combobox a su respectivo combo box
        ImagenMenu();
    }//GEN-LAST:event_formWindowOpened

    private void sonido(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sonido

       botonsound.reproducir();
        // TODO add your handling code here:
    }//GEN-LAST:event_sonido

    private void son(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_son
       
// TODO add your handling code here:
    }//GEN-LAST:event_son

    private void TiposMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TiposMouseMoved
 botonsound.reproducir();
        // TODO add your handling code here:
    }//GEN-LAST:event_TiposMouseMoved

    private void BtnCambioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCambioMouseMoved
 botonsound.reproducir();
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCambioMouseMoved

    private void BtnCambioUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCambioUsuarioActionPerformed
click.reproducir();
        do {
    nombrePlayer = JOptionPane.showInputDialog("Ingrese su nickname:");

        if (nombrePlayer == null) {
            // El usuario hizo clic en Cancelar, por lo que no ingresó un nombre.
            JOptionPane.showMessageDialog(null, "Se ha cancelado la operación.");
           return;
        } else if(nombrePlayer.trim().isEmpty())
                    {
                    JOptionPane.showMessageDialog(null, "No se ingresó un nombre válido.");
                    }
        }while (nombrePlayer == null || nombrePlayer.trim().isEmpty());
        nombreJugador.setText(nombrePlayer);

    }//GEN-LAST:event_BtnCambioUsuarioActionPerformed

    private void BtnModoOscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModoOscuroActionPerformed
click.reproducir();
        modoOscuroActivado=!modoOscuroActivado;
        int numeroDeComponentes = PanelBtn.getComponents().length;
        if(modoOscuroActivado){
            getContentPane().setBackground(Color.BLACK);
            BtnModoOscuro.setBackground(Color.gray);areaRanking.setBackground(Color.gray);Parejas.setBackground(Color.gray);Tipos.setBackground(Color.gray);
             
            BtnModoOscuro.setForeground(Color.WHITE);areaRanking.setForeground(Color.white);Parejas.setForeground(Color.white);Tipos.setForeground(Color.white);
            jLabel1.setForeground(Color.WHITE);jLabel2.setForeground(Color.WHITE);jLabel3.setForeground(Color.WHITE);jLabel4.setForeground(Color.WHITE);
            jLabel5.setForeground(Color.WHITE);jLabel6.setForeground(Color.WHITE);jLabel7.setForeground(Color.WHITE);
            Sol.setImage(Sol.getImage().getScaledInstance(119, 119, Image.SCALE_DEFAULT));
            BtnModoOscuro.setIcon(Sol);
            if(numeroDeComponentes==1){
                PanelBtn.setBackground(Color.gray);
            }else{
                PanelBtn.setBackground(Color.BLUE);
                for (JButton boton : BotonesColor) {
                     ColorBoton(boton);
                }
            }
        }else{
            getContentPane().setBackground(null);           
            BtnModoOscuro.setBackground(null);areaRanking.setBackground(null);Parejas.setBackground(null);Tipos.setBackground(null);
            
            BtnModoOscuro.setForeground(null);areaRanking.setForeground(null);Parejas.setForeground(null);Tipos.setForeground(null);
            jLabel1.setForeground(null);jLabel2.setForeground(null);jLabel3.setForeground(null);jLabel4.setForeground(null);
            jLabel5.setForeground(null);jLabel6.setForeground(null);jLabel7.setForeground(null);
            Luna.setImage(Luna.getImage().getScaledInstance(119, 119, Image.SCALE_DEFAULT));
            BtnModoOscuro.setIcon(Luna);
            if(numeroDeComponentes==1){
                PanelBtn.setBackground(Color.BLACK);
            }else{
                PanelBtn.setBackground(Color.CYAN);
                for (JButton boton : BotonesColor) {
                     ColorBoton(boton);
                }
            }
        }

    }//GEN-LAST:event_BtnModoOscuroActionPerformed

    private void BtnModoOscuroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnModoOscuroMouseEntered
botonsound.reproducir();
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnModoOscuroMouseEntered

    private void BtnCambioUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCambioUsuarioMouseEntered
botonsound.reproducir();
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCambioUsuarioMouseEntered
    private void ColorBoton(JButton x){
        if(modoOscuroActivado){
            if(x.getText()=="↻"){
                x.setBackground(Color.BLACK);
                x.setForeground(Color.WHITE);
            }
            else{
                if(x.isEnabled()){
                    x.setBackground(Color.WHITE);
                    x.setForeground(Color.BLACK);
                }
                else{
                    x.setBackground(Color.red);
                    x.setForeground(Color.gray);
                }
            }
        }else{
            if(x.getText()=="↻"){
                x.setBackground(Color.WHITE);
                x.setForeground(Color.BLACK);
            }else{
                if(x.isEnabled()){
                    x.setBackground(Color.BLACK);
                    x.setForeground(Color.WHITE);
                }else{
                    x.setBackground(Color.red);
                    x.setForeground(Color.gray);
                }
                
            }
            
        }
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
    public void ImagenMenu(){
        PanelBtn.removeAll();
        PanelBtn.setLayout(new FlowLayout());
        icono.setImage(icono.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel imagenLabel = new JLabel(icono);
        imagenLabel.setPreferredSize(new Dimension(500, 500)); // Establece el tamaño deseado
        PanelBtn.add(imagenLabel);
        
        if(modoOscuroActivado){
            PanelBtn.setBackground(Color.gray);
        }else{
            PanelBtn.setBackground(Color.BLACK);
        }
        
    }
    public void Botones(String random, int parejas){
        Font font = new Font("Dialog", Font.PLAIN, 30);
        JButton btn= new JButton("↻");
        btn.setMinimumSize(new Dimension(90, 90));
        btn.setMaximumSize(new Dimension(90, 90));
        btn.setFont(font);
        ColorBoton(btn);
        BotonesColor.add(btn);
        
        
        
        btn.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Numeros".equals(botonTipo)){
                    btn.setText(random+"");
                }
                if("Letras".equals(botonTipo)){
                    char si= (char)(Integer.parseInt(random)+64);
                    btn.setText(si+"");
                }
                if("Imagenes".equals(botonTipo)){
                    ImageIcon icon = new ImageIcon("src/icons/" + random + ".png");
                    
                    btn.setIcon(icon);
                    btn.setText(""); 
                }
                insignia++;
                
                ColorBoton(btn);
                if(insignia==1){
                    insignia++;
                    if("Numeros".equals(botonTipo)||"Letras".equals(botonTipo)){
                        comparador=btn.getText(); 
                    }
                    if("Imagenes".equals(botonTipo)){
                        ComparadorImagen = (ImageIcon) btn.getIcon();
                        
                        
                    }                   
                    controlador = btn;
                    clickedButton1HashCode = (int) e.getSource().hashCode();
                }
                if(insignia==2){  
                    clickedButton2HashCode = (int) e.getSource().hashCode();
                        
                    if(clickedButton1HashCode == clickedButton2HashCode) {
                        insignia = 1;
                        return;
                    }
                    insignia=0;
                    turnos++;
                    txtTurnos.setText(turnos+"");
                                   
                    if("Numeros".equals(botonTipo)||"Letras".equals(botonTipo)){
                        
                        
                        //Validacion si el contenido de los botones es igual
                        if(btn.getText().equals(comparador)){
                            encontradas++;  //Suma las parejas encontradas
                            pareja.reproducir();
                            JOptionPane.showMessageDialog(null, "Pareja encontrada"); //Le muestra al usuario que encontro una pareja
                            txtContador.setText(encontradas+"/"+parejas); //Se actualiza el contador de parejas encontradas
                            //Validacion para seguir Jugando o saber si la partida termino
                            btn.setEnabled(false);
                            controlador.setEnabled(false);
                            //Se desactivan ambos botones
                            btn.setBackground(Color.red);
                            controlador.setBackground(Color.red);
                          if(encontradas == parejas-1){
                              finish.reproducir();
                          }

                            if(encontradas==parejas){
                                musicaPartida.detener();
                                victoria.reproducir();
                                JOptionPane.showMessageDialog(null, "Juego terminado en "+turnos+" intentos");
                               RankingManager r = new RankingManager();
                               ImagenMenu();
                               r.actualizarRanking(archivoRanking, nombrePlayer, turnos);   
                               ActualizarRecord();
                            }
                        }
                        //Lo que se debe hacer si los botones no son iguales
                        else{
                            JOptionPane.showMessageDialog(null, "Sigue intentando");
                            //Si los botones no son iguales Se regresan  como estaban antes de pulsarlos y le da la opcion al usuario de pulsarlos otra vez
                            btn.setText("↻");
                            controlador.setText("↻");
                            btn.setEnabled(true);
                            controlador.setEnabled(true);
                            ColorBoton(btn);
                            ColorBoton(controlador);
                            
                        }
                    }
                    
                  
                  
                    
                    if("Imagenes".equals(botonTipo)){
                        JButton clickedButton = (JButton) e.getSource();
                        
                        ImageIcon icon1 = ComparadorImagen;                        
                        ImageIcon icon2 = (ImageIcon) clickedButton.getIcon();

                        Image image1 = icon1.getImage();
                        Image image2 = icon2.getImage();
                        
                        if(image1.equals(image2)){
                            pareja.reproducir();
                            encontradas++;
                            JOptionPane.showMessageDialog(null, "Pareja encontrada");
                            //Hacemos que los botones no se puedan precionar 
                            controlador.setEnabled(false);
                            btn.setEnabled(false);
                            //Hacemos los botones rojos para que resalten de los precionados 
                            //que son negros y de los que no estan precionados que son blancos
                            btn.setBackground(Color.red);
                            controlador.setBackground(Color.red);
                            //Ya que el setEneable(false) hace gris el icono llamamos al mismo icono 
                            
                            ImageIcon icon = new ImageIcon("src/icons/" + random + ".png");
                            //El icono lo aplicamos en ambos botones
                            btn.setIcon(icon);
                            controlador.setIcon(icon);
                            //Le devolvemos el color a ambos iconos para que no se vean grises
                             btn.setDisabledIcon(icon);
                             controlador.setDisabledIcon(icon);
                            txtContador.setText(encontradas+"/"+parejas);

                             if(encontradas == parejas-1){
                              finish.reproducir();
                          }
                            
                            if(encontradas==parejas){
                               musicaPartida.detener();
                               victoria.reproducir();
                               JOptionPane.showMessageDialog(null, "Juego terminado en "+turnos+" intentos");
                               ImagenMenu();
                               RankingManager r = new RankingManager();
                               r.actualizarRanking(archivoRanking, nombrePlayer, turnos);   
                               ActualizarRecord();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Sigue intentando");
                            btn.setText("↻");
                            controlador.setText("↻");
                            ColorBoton(btn);
                            ColorBoton(controlador);
                            clickedButton.setIcon(new ImageIcon(""));
                            controlador.setIcon(new ImageIcon(""));

                        }
                        
                    }
                }
                PanelBtn.updateUI();
            }
        });
        PanelBtn.add(btn);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCambio;
    private javax.swing.JButton BtnCambioUsuario;
    private javax.swing.JButton BtnModoOscuro;
    private javax.swing.JPanel PanelBtn;
    private javax.swing.JComboBox<String> Parejas;
    private javax.swing.JComboBox<String> Tipos;
    private javax.swing.JTextArea areaRanking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombreJugador;
    private javax.swing.JTextField txtContador;
    private javax.swing.JTextField txtTurnos;
    // End of variables declaration//GEN-END:variables
}
