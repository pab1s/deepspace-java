/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import deepspace.GameUniverse;
import View.DeepSpaceView;
import View.GUI.MainWindow;
import Controller.Controller;
/**
 *
 * @author pablo
 */
public class PlayWithGUI {
    public static void main(String[] args) {
        GameUniverse game = new GameUniverse();
        DeepSpaceView view = MainWindow.getInstance();
        Controller controller = Controller.getInstance();
        controller.setModelView(game, view);
        controller.start();
    }
    
}
