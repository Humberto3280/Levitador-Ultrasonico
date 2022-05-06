package views;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author Edwin Andrés Samboní Ortiz
 */
public class GUI_Conexion extends javax.swing.JFrame {

    //ATRIBUTOS
    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private String estado;
    //CONSTRUCTOR
    public GUI_Conexion() {
       
        //Inicializo atributos
        estado="Desconectado";
        
        //Inicializo los componentes del JFrameForm
        initComponents();
        
        //Modifico el cierre de ventana
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Obtengo los puertos disponibles
        getPuertos();
    }
    
    //OBTENER PUERTOS
    private void getPuertos(){
        //Elimino los items por defecto del combo box
        jCBPuerto.removeAllItems();
        //Valido si hay puertos disponibles
        if(ino.getPortsAvailable()>0){
            //Recorro con un foreach y añado los puertos como nuevos items
            ino.getSerialPorts().forEach(i->jCBPuerto.addItem(i));
            //Activo el botón de conectar
            jBConectar.setEnabled(true);
        }else{
            //Si no hay puertos disponibles, el botón de conectar debe estar 
            //desactivado.
            jBConectar.setEnabled(false);
        }
    }
    
    private void conexion(){
        //Valido si se puede conectar.
        if(jBConectar.getText().equals("Conectar")){
            try {
                //Realizo la conexión con arduino.
                ino.arduinoTX(jCBPuerto.getSelectedItem().toString(), 9600);
                estado="Conectado";
                
                //Cambio las caracteristicas del botón conectar
                jBConectar.setText("Desconectar");
                jBConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnDesconectar.png")));

                //Desactivo las opciones de refrescar y de selección del puerto.
                jBRefrescar.setEnabled(false);
                jCBPuerto.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(GUI_Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                //Genero un retardo de 1 segundo.
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI_Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Envio el dato de encendido del led
            led(1);
            //Cambio las caracteristicas del botón conectar
            jBConectar.setText("Desconectar");
            jBConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnDesconectar.png")));
        }else{
            try {
                //Si entro aqui, significa que el botón tiene como texto "Desconectar"
                //por lo tanto se procede a indicarlo fisicamente y mediante código.
                led(0);
                ino.killArduinoConnection();
                estado="Desconectado";

                //Cambio las caracteristicas del botón conectar.
                jBConectar.setText("Conectar");
                jBConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnConectar.png")));

                //Habilito las opciones de refrescar y de selección del puerto.
                jBRefrescar.setEnabled(true);
                jCBPuerto.setEnabled(true);
            } catch (Exception ex) {
                Logger.getLogger(GUI_Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void led(int estado){
        try {
            ino.sendData(String.valueOf(estado));
        } catch (Exception ex) {
            Logger.getLogger(GUI_Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLTitulo = new javax.swing.JLabel();
        jPPuerto = new javax.swing.JPanel();
        jLPuerto = new javax.swing.JLabel();
        jCBPuerto = new javax.swing.JComboBox<>();
        jBRefrescar = new javax.swing.JButton();
        jPComectar = new javax.swing.JPanel();
        jBConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Titulo.png"))); // NOI18N
        jLTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 70));

        jPPuerto.setBackground(new java.awt.Color(13, 13, 55));
        jPPuerto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLPuerto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLPuerto.setForeground(new java.awt.Color(255, 255, 255));
        jLPuerto.setText("Puerto: ");
        jPPuerto.add(jLPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jCBPuerto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCBPuerto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCBPuerto.setMaximumSize(new java.awt.Dimension(70, 40));
        jCBPuerto.setMinimumSize(new java.awt.Dimension(70, 40));
        jCBPuerto.setPreferredSize(new java.awt.Dimension(70, 40));
        jPPuerto.add(jCBPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 70, 40));

        jBRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refreshicon.png"))); // NOI18N
        jBRefrescar.setBorderPainted(false);
        jBRefrescar.setContentAreaFilled(false);
        jBRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBRefrescar.setFocusPainted(false);
        jBRefrescar.setMaximumSize(new java.awt.Dimension(30, 22));
        jBRefrescar.setMinimumSize(new java.awt.Dimension(30, 22));
        jBRefrescar.setPreferredSize(new java.awt.Dimension(30, 22));
        jBRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRefrescarActionPerformed(evt);
            }
        });
        jPPuerto.add(jBRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 20, 20));

        jPanel1.add(jPPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 100));

        jPComectar.setBackground(new java.awt.Color(13, 13, 55));
        jPComectar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnConectar.png"))); // NOI18N
        jBConectar.setText("Conectar");
        jBConectar.setBorder(null);
        jBConectar.setBorderPainted(false);
        jBConectar.setContentAreaFilled(false);
        jBConectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBConectar.setMaximumSize(new java.awt.Dimension(121, 67));
        jBConectar.setMinimumSize(new java.awt.Dimension(121, 67));
        jBConectar.setPreferredSize(new java.awt.Dimension(121, 67));
        jBConectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBConectarMouseClicked(evt);
            }
        });
        jPComectar.add(jBConectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 100, 40));

        jPanel1.add(jPComectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 250));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRefrescarActionPerformed
        getPuertos();
    }//GEN-LAST:event_jBRefrescarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Nombre de los botones
        String btn[]={"Si", "No"};
        //Cuadro de dialogo que permite elegir entre dos opciones.
        int eleccion = JOptionPane.showOptionDialog(this, "¿Está seguro de cerrar la aplicación", "Levitador por Ultrasonido",
                0, 0, null, btn, this);
        
        //Valido que opción se escogio
        if(eleccion==JOptionPane.YES_OPTION){
            //Compruebo si se ha realizado una conexión con arduino.
            if(estado.equals("Conectado")){
                conexion();
            }
            
            //Ciero el aplicativo
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jBConectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBConectarMouseClicked
        if(evt.getClickCount()<2){
            conexion();
        }
    }//GEN-LAST:event_jBConectarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBConectar;
    private javax.swing.JButton jBRefrescar;
    private javax.swing.JComboBox<String> jCBPuerto;
    private javax.swing.JLabel jLPuerto;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JPanel jPComectar;
    private javax.swing.JPanel jPPuerto;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
