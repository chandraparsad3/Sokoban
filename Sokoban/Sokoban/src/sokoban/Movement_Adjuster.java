/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;
import java.awt.Image;
/**
 *
 * @author ThinkPad
 */
public class Movement_Adjuster {
    private final int space=30;
    private int x;
    private int y;
    private Image image;

    public Movement_Adjuster(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean  isleftcollision(Movement_Adjuster actor)
    {
        if ((this.getX()-space==actor.getX()) && (this.getY()==actor.getY()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean  isrightcollision(Movement_Adjuster actor)
    {
        if ((this.getX()+space==actor.getX()) && (this.getY()==actor.getY()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean  istopcollision(Movement_Adjuster actor)
    {
        if ((this.getY()-space==actor.getY()) && (this.getX()==actor.getX()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean  isbuttoncollision(Movement_Adjuster actor)
    {
        if ((this.getY()+space==actor.getY()) && (this.getX()==actor.getX()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
