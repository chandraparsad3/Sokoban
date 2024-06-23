/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ThinkPad
 */
public class diamond extends Movement_Adjuster{
    public diamond(int x,int y)
    {
        super(x,y);
        ImageIcon ico=new ImageIcon("Sokoban/Diamond.png");
        Image img=ico.getImage();
        this.setImage(img);
    }
}
