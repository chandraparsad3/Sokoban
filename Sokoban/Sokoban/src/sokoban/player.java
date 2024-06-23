/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.awt.Image;
import javax.swing.ImageIcon;

public class player  extends Movement_Adjuster{
    public player(int x,int y)
    {
        super(x,y);
        ImageIcon ico=new ImageIcon("Sokoban/actor.jpg");
        Image img=ico.getImage();
        this.setImage(img);
    }
    public void move(int x,int y)
    {
        int nx=this.getX() +x;
        int ny=this.getY() +y;
        this.setX(nx);
        this.setY(ny);
    }
}
