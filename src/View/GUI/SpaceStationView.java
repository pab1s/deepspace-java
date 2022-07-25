/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import Controller.Controller;
import deepspace.GameState;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class SpaceStationView extends javax.swing.JPanel {

    DamageView damageView;
    HangarView hangarView;

    public SpaceStationView() {
        initComponents();

        repaint();
        revalidate();
    }

    void setSpaceStation(SpaceStationToUI station) {
        stationName.setText(station.getName());
        stationName.setBackground(Color.gray);
        stationName.setOpaque(true);

        stationAmmo.setText(Float.toString(station.getAmmoPower()));
        stationShield.setText(Float.toString(station.getShieldPower()));
        stationFuel.setText(Float.toString(station.getFuelUnits()));
        stationMedals.setText(Integer.toString(station.getnMedals()));

        ArrayList<WeaponToUI> weapons = station.getWeapons();
        ArrayList<ShieldToUI> shieldBoosters = station.getShieldBoosters();

        mountedWeaponsPanel.removeAll();
        mountedShieldsPanel.removeAll();
        hangarPanel.removeAll();
        pendingDamagePanel.removeAll();

        WeaponView weaponView;
        for (WeaponToUI w : weapons) {
            weaponView = new WeaponView();
            weaponView.setWeapon(w);
            mountedWeaponsPanel.add(weaponView);
        }

        ShieldView shieldView;
        for (ShieldToUI s : shieldBoosters) {
            shieldView = new ShieldView();
            shieldView.setShield(s);
            mountedShieldsPanel.add(shieldView);
        }

        damageView = new DamageView();
        damageView.setDamage(station.getPendingDamage());
        pendingDamagePanel.add(damageView);

        hangarView = new HangarView();
        hangarView.setHangar(station.getHangar());
        hangarPanel.add(hangarView);

        enabledButtons(station);

        repaint();
        revalidate();
    }

    void enabledButtons(SpaceStationToUI station) {
        boolean hangarAvaiable = station.getHangar() != null;
        boolean shieldsInHangar = false;
        boolean weaponsInHangar = false;
        boolean elementsInHangar = false;
        if (hangarAvaiable) {
            weaponsInHangar = (station.getHangar().getWeapons().size() != 0);
            shieldsInHangar = (station.getHangar().getShieldBoosters().size() != 0);

            elementsInHangar = weaponsInHangar || shieldsInHangar;
        }
        boolean weaponsMounted = station.getWeapons().size() != 0;
        boolean shieldsMounted = station.getShieldBoosters().size() != 0;

        GameState gameState = Controller.getInstance().getState();
        boolean init = gameState == GameState.INIT;
        boolean aftercombat = gameState == GameState.AFTERCOMBAT;

        discardHangarButton.setEnabled(hangarAvaiable && (init || aftercombat));
        equipButton.setEnabled(hangarAvaiable && elementsInHangar && (init || aftercombat));
        discardButton.setEnabled((elementsInHangar || weaponsMounted || shieldsMounted) && (init || aftercombat));

    }

    ArrayList<Integer> getSelectedWeaponsMounted() {
        ArrayList<Integer> selectedWeaponsMounted = new ArrayList<>();

        int i = 0;
        for (Component c : mountedWeaponsPanel.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                selectedWeaponsMounted.add(i);
            }
            i++;
        }

        return selectedWeaponsMounted;
    }

    ArrayList<Integer> getSelectedShieldsMounted() {
        ArrayList<Integer> selectedShieldsMounted = new ArrayList<>();

        int i = 0;
        for (Component c : mountedShieldsPanel.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                selectedShieldsMounted.add(i);
            }
            i++;
        }

        return selectedShieldsMounted;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stationName = new javax.swing.JLabel();
        titleStationAmmo = new javax.swing.JLabel();
        titleStationShield = new javax.swing.JLabel();
        titleStationFuel = new javax.swing.JLabel();
        titleStationMedals = new javax.swing.JLabel();
        stationFuel = new javax.swing.JLabel();
        stationShield = new javax.swing.JLabel();
        stationMedals = new javax.swing.JLabel();
        stationAmmo = new javax.swing.JLabel();
        pendingDamagePanel = new javax.swing.JPanel();
        mountedWeaponsPanel = new javax.swing.JPanel();
        mountedShieldsPanel = new javax.swing.JPanel();
        equipButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        discardHangarButton = new javax.swing.JButton();
        hangarPanel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(740, 700));

        stationName.setFont(new java.awt.Font("Fira Sans", 1, 24)); // NOI18N
        stationName.setText("SpaceStation");

        titleStationAmmo.setText("Potencia de Ataque:");

        titleStationShield.setText("Potencia de Defensa:");

        titleStationFuel.setText("Nivel de Combustible:");

        titleStationMedals.setText("Medallas:");

        stationFuel.setText("fuel");

        stationShield.setText("shield");

        stationMedals.setText("medals");

        stationAmmo.setText("ammo");

        mountedWeaponsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Potenciadores de Fuego"));
        mountedWeaponsPanel.setPreferredSize(new java.awt.Dimension(691, 150));

        mountedShieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Potenciadores de Defensa"));
        mountedShieldsPanel.setPreferredSize(new java.awt.Dimension(691, 150));

        equipButton.setText("Equipar");
        equipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipButtonActionPerformed(evt);
            }
        });

        discardButton.setText("Descartar");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        discardHangarButton.setText("Descartar Hangar Completo");
        discardHangarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardHangarButtonActionPerformed(evt);
            }
        });

        hangarPanel.setPreferredSize(new java.awt.Dimension(691, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stationName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleStationFuel)
                            .addComponent(titleStationMedals))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stationMedals)
                            .addComponent(stationFuel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleStationShield)
                            .addComponent(titleStationAmmo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stationAmmo)
                            .addComponent(stationShield))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pendingDamagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(equipButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discardButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discardHangarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hangarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(mountedWeaponsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mountedShieldsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(stationName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleStationAmmo)
                            .addComponent(stationAmmo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleStationShield)
                            .addComponent(stationShield))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleStationFuel)
                            .addComponent(stationFuel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleStationMedals)
                            .addComponent(stationMedals))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(pendingDamagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mountedWeaponsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mountedShieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hangarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discardHangarButton)
                    .addComponent(discardButton)
                    .addComponent(equipButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void equipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipButtonActionPerformed
        ArrayList<Integer> weaponsSelected = new ArrayList<>();
        ArrayList<Integer> shieldsSelected = new ArrayList<>();

        hangarView.getSelectedInHangar(weaponsSelected, shieldsSelected);
        Controller.getInstance().mount(weaponsSelected, shieldsSelected);

        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_equipButtonActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        ArrayList<Integer> weaponsMountedSelected = getSelectedWeaponsMounted();
        ArrayList<Integer> shieldsMountedSelected = getSelectedShieldsMounted();

        ArrayList<Integer> weaponsSelectedInHangar = new ArrayList<>();
        ArrayList<Integer> shieldsSelectedInHangar = new ArrayList<>();

        Controller.getInstance().discard(Controller.WEAPON, weaponsMountedSelected, shieldsMountedSelected);
        Controller.getInstance().discard(Controller.SHIELD, weaponsMountedSelected, shieldsMountedSelected);

        if (Controller.getInstance().getUIversion().getCurrentStation().getHangar() != null) {
            hangarView.getSelectedInHangar(weaponsSelectedInHangar, shieldsSelectedInHangar);
            Controller.getInstance().discard(Controller.HANGAR, weaponsSelectedInHangar, shieldsSelectedInHangar);
        }

        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardButtonActionPerformed

    private void discardHangarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardHangarButtonActionPerformed
        JOptionPane.showMessageDialog(this, "¡Hangar Completo Descartado!", MainWindow.getInstance().getAppName(), JOptionPane.INFORMATION_MESSAGE);
        Controller.getInstance().discardHangar();

        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardHangarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton discardButton;
    private javax.swing.JButton discardHangarButton;
    private javax.swing.JButton equipButton;
    private javax.swing.JPanel hangarPanel;
    private javax.swing.JPanel mountedShieldsPanel;
    private javax.swing.JPanel mountedWeaponsPanel;
    private javax.swing.JPanel pendingDamagePanel;
    private javax.swing.JLabel stationAmmo;
    private javax.swing.JLabel stationFuel;
    private javax.swing.JLabel stationMedals;
    private javax.swing.JLabel stationName;
    private javax.swing.JLabel stationShield;
    private javax.swing.JLabel titleStationAmmo;
    private javax.swing.JLabel titleStationFuel;
    private javax.swing.JLabel titleStationMedals;
    private javax.swing.JLabel titleStationShield;
    // End of variables declaration//GEN-END:variables
}