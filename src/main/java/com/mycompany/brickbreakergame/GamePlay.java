/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreakergame;

/**
 *
 * @author PRIYA
 */
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  class GamePlay extends JPanel implements KeyListener,ActionListener {
    
private boolean play=true;
    private int score=0;
    private int totalbricks=21;
    private Timer timer;
    private int delay=0; //millisecond
    private int barX=310; //playerX is barX
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    
    private MapGenerator map;
    
    public GamePlay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g){
        
        g.setColor(Color.YELLOW); //color of frame
        g.fillRect(1,1,692,592); //filling color of frame
        
        map.draw((Graphics2D) g);
        
        //g.setColor(Color.yellow); //color of border
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        
        g.setColor(Color.BLACK);  //color of the font come when we exit or lose the game
        g.setFont(new Font("MV Boli" ,Font.BOLD,25));
        g.drawString("Score: " + score ,520,30); //position of the score to display
        
        g.setColor( Color.blue); //setting the color of the bar 
        g.fillRect(barX,550,100,12);
        
        g.setColor(Color.RED);  //color of ball
        g.fillOval(ballposX,ballposY,20,20);
        if(ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli",Font.BOLD,30));
            g.drawString("Game Over!,Score:" + score ,190,300);
            
            g.setFont(new Font("MV Boli",Font.BOLD,20));
            g.drawString("Press Enter To Restart",230,350);
        }
        if(totalbricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(new Color(0XFF6464));
            g.setFont(new Font("MV Boli",Font.BOLD,30));
            g.drawString("YOU WON,SCORE:" + score,190,300);
            
            g.setFont(new Font("MV Boli",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,250);
        }
        g.dispose();
    }
    //@Override
    public void actionPerformed(ActionEvent arg0){
        timer.start();
        if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(barX,550,100,80))){
                ballYdir=-ballYdir;
            }
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.bricksWidth+80;
                        int brickY=i*map.bricksHeight+50;
                        int bricksWidth=map.bricksWidth;
                        int bricksHeight=map.bricksHeight;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,bricksWidth,bricksHeight);
                        Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect=rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBricksValue(0,i,j);
                            totalbricks--;
                            score+=5;
                            
                            if(ballposX + 19 <= brickRect.x || ballposX+1 >= brickRect.x + brickRect.width){
                                ballXdir=-ballXdir;
                            }else{
                                ballYdir=-ballYdir;
                            }
                        }
                    }
                }
            }
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){
                ballXdir=-ballXdir;
            }
            if(ballposY<0){
                ballYdir=-ballYdir;
            }
            if(ballposX>670){
                ballXdir=-ballXdir;
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(barX>=600){
                barX=600;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(barX<10){
               barX=10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ballposX=120;
                ballposY=350;
                ballXdir=-1;
                ballYdir=-2;
                score=0;
                //barX=310;
                totalbricks=21;
                map=new MapGenerator(3,7);
                repaint();
            }
        }
    }
    
    public void moveRight()
    {
        play=true;
        barX+=50;
    }
    public void moveLeft()
    {
        play=true;
        barX-=50;
    }

   // @Override
   // public void keyTyped(KeyEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    //@Override
    //p//ublic void actionPerformed(ActionEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
}


//gameplay
