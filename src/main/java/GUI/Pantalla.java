/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.PopUp.AvisoPopUp;
import GUI.PopUp.ErrorPopUp;
import modelo.HttpServerApagarPC;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * @author Gc
 */
public class Pantalla extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JButton jBotonOFF;
    private javax.swing.JButton jBotonON;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private HttpServerApagarPC httpServidor = null;
    // End of variables declaration
    /**
     * Creates new form Pantalla
     */
    public Pantalla() {
        initComponents();//inicio de componenetes
        setLocationRelativeTo(null);//pantalla centrada
        setResizable(false);// no se puede agrandar la pantalla


        ImageIcon imageIcon = new ImageIcon("src/main/resources/recursoIconoPNG.png");
        setIconImage(imageIcon.getImage());

        setVisible(true);//una vez que se instancia ya se muestra
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBotonON = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBotonOFF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Servidor Local");

        jBotonON.setText("ON");
        jBotonON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonONActionPerformed(evt);
            }
        });

        jLabel2.setText("Inicio del servidor");

        jBotonOFF.setText("OFF");
        jBotonOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonOFFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(134, Short.MAX_VALUE)
                                .addComponent(jBotonON, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jBotonOFF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addGap(157, 157, 157))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBotonOFF)
                                        .addComponent(jBotonON))
                                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void jBotonONActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //si el boton se aprieta se abre el servidor http
        httpServidor = new HttpServerApagarPC();
        try
        {
            httpServidor.iniciar();
            if(httpServidor == null)
            {
                ErrorPopUp popUp = new ErrorPopUp(this,true,"El servidor no pudo abrirse");
            }
            else
            {
                AvisoPopUp popUp = new AvisoPopUp(this,true,"El servidor se encuentra encendido");
            }
        } catch (Exception e)
        {
            ErrorPopUp popUp = new ErrorPopUp(this,true,e.getMessage()+" No se pudo abrir el servidor");
        }
    }

    private void jBotonOFFActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(httpServidor!=null)
        {
            httpServidor.apagarServidor();
            AvisoPopUp popUp = new AvisoPopUp(this,true,"El servidor se ha cerrado correctamente");
            httpServidor = null;
        }
        else
        {
            ErrorPopUp popUp = new ErrorPopUp(this,true,"El servidor no se encuentra abierto");
        }
    }


}


