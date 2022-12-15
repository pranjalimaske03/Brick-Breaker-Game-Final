/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreakergame;

/**
 *
 * @author PRIYA
 */
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class BrickBreakerGame {
    public static void main(String[] args){
        JFrame obj = new JFrame();
        GamePlay gameplay=new GamePlay(); 
        obj.setBounds(10,10,1000,800);
        obj.setTitle("BrickBreaker");  
        obj.setLocationRelativeTo(null);   
        obj.setResizable(false);  
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay); 
        
    }
}
