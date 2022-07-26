/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import View.DeepSpaceView;
import Controller.Controller;
import deepspace.GameState;

/**
 *
 * @author pablo
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class MainWindow extends JFrame implements DeepSpaceView {

    private static MainWindow instance = null;
    private String appName;
    private SpaceStationView stationView;
    private EnemyStarShipView enemyView;

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private MainWindow() {
        initComponents();
        appName = "DeepSpace Beta";
        setTitle(appName);

        stationView = new SpaceStationView();
        enemyView = new EnemyStarShipView();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });
    }

    public String getAppName() {
        return appName;
    }

    @Override
    public ArrayList<String> readNamePlayers() {
        NamesCapture namesCapt = new NamesCapture(this);
        return namesCapt.getNames();
    }

    @Override
    public void updateView() {

        stationView.setSpaceStation(Controller.getInstance().getUIversion().getCurrentStation());
        stationPanel.add(stationView);
        enemyView.setEnemy(Controller.getInstance().getUIversion().getCurrentEnemy());
        enemyPanel.add(enemyView);

        GameState gameState = Controller.getInstance().getState();
        if (gameState == GameState.INIT) {
            //enemyPanel.setVisible(false);

            combatButton.setEnabled(true);
            nextTurnButton.setEnabled(false);
        }
        if (gameState == GameState.BEFORECOMBAT) {
            //enemyPanel.setVisible(false);

            combatButton.setEnabled(true);
            nextTurnButton.setEnabled(false);
        }
        if (gameState == GameState.AFTERCOMBAT) {
            //enemyPanel.setVisible(true);

            combatButton.setEnabled(false);
            nextTurnButton.setEnabled(true);
        }
        repaint();
    }

    @Override
    public void showView() {
        this.setVisible(true);
    }

    @Override
    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    @Override
    public void nextTurnNotAllowedMessage() {
        JOptionPane.showMessageDialog(this, "No puedes avanzar de turno, \nno has cumplido tu castigo.", getAppName(), JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void lostCombatMessage() {
        JOptionPane.showMessageDialog(this, "Has PERDIDO el combate. \nCumple tu castigo.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void escapeMessage() {
        JOptionPane.showMessageDialog(this, "Has logrado ESCAPAR. \nEres un gallina espacial.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void wonCombatMessage() {
        JOptionPane.showMessageDialog(this, "Has GANADO el combate. \nDisfruta de tu botín.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void wonGameMessage() {
        JOptionPane.showMessageDialog(this, "ENHORABUENA!!. \nHas ganado el juego!!.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void conversionMessage() {
        if (Controller.getInstance().getUIversion().getCurrentEnemy().getLoot().isGetEfficient()) {
            JOptionPane.showMessageDialog(this, "Has GANADO el combate. \nAdemás te has CONVERTIDO en una estación EFICIENTE. \nDisfruta de tu botín.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Has GANADO el combate. \nAdemás te has CONVERTIDO en una CIUDAD ESPACIAL. \nDisfruta de tu botín.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void noCombatMessage() {
        JOptionPane.showMessageDialog(this, "No puedes combatir en este momento.", getAppName(), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combatButton = new javax.swing.JButton();
        nextTurnButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        stationPanel = new javax.swing.JPanel();
        enemyPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        combatButton.setFont(new java.awt.Font("Fira Sans", 1, 24)); // NOI18N
        combatButton.setText("¡COMBATIR!");
        combatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combatButtonActionPerformed(evt);
            }
        });

        nextTurnButton.setFont(new java.awt.Font("Fira Sans", 1, 18)); // NOI18N
        nextTurnButton.setText("SIGUIENTE TURNO");
        nextTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTurnButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Fira Sans", 1, 14)); // NOI18N
        exitButton.setText("SALIR");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        stationPanel.setPreferredSize(new java.awt.Dimension(750, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(stationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enemyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nextTurnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nextTurnButton, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combatButtonActionPerformed
        Controller.getInstance().combat();
        updateView();
        revalidate();
    }//GEN-LAST:event_combatButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        Controller.getInstance().finish(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void nextTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTurnButtonActionPerformed
        Controller.getInstance().nextTurn();
        updateView();
        revalidate();
    }//GEN-LAST:event_nextTurnButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton combatButton;
    private javax.swing.JPanel enemyPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton nextTurnButton;
    private javax.swing.JPanel stationPanel;
    // End of variables declaration//GEN-END:variables
}
