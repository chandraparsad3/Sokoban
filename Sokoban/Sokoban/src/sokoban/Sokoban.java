/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author ThinkPad
 */
public class Sokoban extends JFrame{
    JLabel lblUserName, lblTotalMoveCount, lblthislevel, lbltotalmove;
    JLabel lblActorimage, lblBoximage, lblDiamondimage, lblWallimage;
    JLabel lblOrder1, lblOrder2, lblOrder3;
    JLabel lblUP_Arrow, lblLEFT_Arrow, lblRIGHT_Arrow, lblDOWN_Arrow;
    JLabel lblGameSheetimage;
    
    JPanel pnlGameSheet;
    wall objWall;
    private player objPlayer;
    box objBox;
    diamond objDiamond;
    private int actor_movecount = 0;
    private final int OffSet = 180;
    private final int Space = 30;
    private final int Collision_Left = 1;
    private final int Collision_Right = 2;
    private final int Collision_Top = 3;
    private final int Collision_Bottom = 4;
    private ArrayList ArrayLWalls = new ArrayList();
    private ArrayList ArrayLBoxs = new ArrayList();
    private ArrayList ArrayLDiamonds = new ArrayList();
    private int Width = 0;
    private int Height = 0;
    private boolean CompleteStatus = false;
    String LevelComplete = "";
    GameLeveller m = new GameLeveller();
    
    public Sokoban(){
        this.setTitle("Sokoban Game");
        this.setSize(600, 500);
        this.setLayout(null);
        lblUserName = new JLabel("Chandraparsad");
        lblUserName.setBounds(20, 20, 100, 30);
        this.add(lblUserName);
        lblTotalMoveCount = new JLabel();
        lblthislevel = new JLabel();
        
        lbltotalmove = new JLabel("Total Moves");
        lbltotalmove.setBounds(15, 40, 100, 30);
        this.add(lbltotalmove);

        lblTotalMoveCount.setBounds(20, 60, 100, 30);
        this.add(lblTotalMoveCount);

        lblthislevel.setBounds(20, 100, 100, 30);
        this.add(lblthislevel);

        pnlGameSheet = new JPanel();
        pnlGameSheet.setBounds(130, 20, 350, 430);
        this.add(pnlGameSheet);

        lblGameSheetimage = new JLabel();
        lblGameSheetimage.setBounds(10, 10, 100, 430);
        lblOrder2 = new JLabel("Restart Level");
        lblOrder2.setBounds(10, 280, 100, 30);
        this.add(lblOrder2);

        lblOrder3 = new JLabel("     Press R ");
        lblOrder3.setBounds(10, 300, 100, 30);
        this.add(lblOrder3);
        
        box b = new box(30,30);
        lblBoximage = new JLabel(new ImageIcon(b.getImage()));
        lblBoximage.setSize(30,30);

        wall w = new wall(30,30);
        lblWallimage = new JLabel(new ImageIcon(w.getImage()));
        lblWallimage.setSize(30,30);

        diamond a = new diamond(30,30);
        lblDiamondimage = new JLabel(new ImageIcon(a.getImage()));
        lblDiamondimage.setSize(30, 30);
        
        objPlayer = new player(30, 30);
        lblActorimage = new JLabel(new ImageIcon(objPlayer.getImage()));
        
        LevelComplete = m.level_1;
        lblthislevel.setText("Level 1");
        startGameBoard(LevelComplete);
        

        pnlGameSheet.add(lblGameSheetimage);

        pnlGameSheet.addKeyListener(new MovementKeyAdapter());
        pnlGameSheet.setFocusable(true);

        this.setVisible(true);
    }
     public final void startGameBoard(String level)
    {

        int x = OffSet;
        int y = 130;

        for (int i = 0; i < level.length(); i++)
        {
            char item = level.charAt(i);

            if (item == '\n')
            {
                y += Space;

                if (this.Width < x)
                {
                    this.Width = x;
                }

                x = OffSet;
            }
            else if (item == '#')
            {
                objWall = new wall(x, y);

                ArrayLWalls.add(objWall);
                x += Space;
            }
            else if (item == '$')
            {
                objBox = new box(x, y);
                ArrayLBoxs.add(objBox);
                x += Space;
            }
            else if (item == '.')
            {
                objDiamond = new diamond(x, y);
                ArrayLDiamonds.add(objDiamond);
                x += Space;
            }
            else if (item == '@')
            {
                objPlayer = new player(x, y);
                x += Space;
            }
            else if (item == ' ')
            {
                x += Space;
            }
            Height = y;
        }
    }
     class MovementKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (CompleteStatus) {
                return;
            }

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                if (checkWallCollision(objPlayer,
                        Collision_Left)) {
                    JOptionPane.showMessageDialog(null, "Left Wall");
                    return;
                }

                if (checkBagCollision(Collision_Left)) {
                    JOptionPane.showMessageDialog(null, "Left Wall");
                    return;
                }
                actor_movecount += 1;
                objPlayer.move(-Space, 0);

            } else if (key == KeyEvent.VK_RIGHT) {

                if (checkWallCollision(objPlayer,
                        Collision_Right)) {
                    JOptionPane.showMessageDialog(null, "Right Wall");
                    return;
                }

                if (checkBagCollision(Collision_Right)) {
                    JOptionPane.showMessageDialog(null, "Right Wall");
                    return;
                }
                actor_movecount += 1;
                objPlayer.move(Space, 0);

            } else if (key == KeyEvent.VK_UP) {

                if (checkWallCollision(objPlayer,
                        Collision_Top)) {
                    JOptionPane.showMessageDialog(null, "Top Wall");
                    return;
                }

                if (checkBagCollision(Collision_Top)) {
                    JOptionPane.showMessageDialog(null, "Top Wall");
                    return;
                }
                actor_movecount += 1;
                objPlayer.move(0, -Space);

            } else if (key == KeyEvent.VK_DOWN) {

                if (checkWallCollision(objPlayer,
                        Collision_Bottom)) {
                    JOptionPane.showMessageDialog(null, "Bottom Wall");
                    return;
                }

                if (checkBagCollision(Collision_Bottom)) {
                    JOptionPane.showMessageDialog(null, "Bottom Wall");
                    return;
                }
                actor_movecount += 1;
                objPlayer.move(0, Space);

            } else if (key == KeyEvent.VK_R) {
                nextLevel();
            }
            lblTotalMoveCount.setText(String.valueOf(actor_movecount));
            repaint();
        }
    }
      private boolean checkWallCollision(Movement_Adjuster actor, int type) {

        if (type == Collision_Left) {

            for (int i = 0; i < ArrayLWalls.size(); i++) {
                wall wall = (wall) ArrayLWalls.get(i);
                if (actor.isleftcollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == Collision_Right) {

            for (int i = 0; i < ArrayLWalls.size(); i++) {
                wall wall = (wall) ArrayLWalls.get(i);
                if (actor.isrightcollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == Collision_Top) {

            for(int i = 0; i < ArrayLWalls.size(); i++) {
                wall wall = (wall) ArrayLWalls.get(i);
                if (actor.istopcollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == Collision_Bottom) {

            for(int i = 0; i < ArrayLWalls.size(); i++) {
                wall wall = (wall) ArrayLWalls.get(i);
                if (actor.isbuttoncollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
      private boolean checkBagCollision(int collision_type) {

        if (collision_type == Collision_Left) {

            for (int i = 0; i < ArrayLBoxs.size(); i++) {

                box bag = (box) ArrayLBoxs.get(i);
                if (objPlayer.isleftcollision(bag)) {

                    for (int j = 0; j < ArrayLBoxs.size(); j++) {
                        box item = (box) ArrayLBoxs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isleftcollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                Collision_Left)) {
                            return true;
                        }
                    }
                    bag.move(-Space, 0);
                    isLevelComplete();
                }
            }
            return false;

        } else if (collision_type == Collision_Right) {

            for (int i = 0; i < ArrayLBoxs.size(); i++) {

                box bag = (box) ArrayLBoxs.get(i);
                if (objPlayer.isrightcollision(bag)) {
                    for (int j = 0; j < ArrayLBoxs.size(); j++) {

                        box item = (box) ArrayLBoxs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isrightcollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                Collision_Right)) {
                            return true;
                        }
                    }
                    bag.move(Space, 0);
                    isLevelComplete();
                }
            }
            return false;

        } else if (collision_type == Collision_Top) {

            for (int i = 0; i < ArrayLBoxs.size(); i++) {

                box bag = (box) ArrayLBoxs.get(i);
                if (objPlayer.istopcollision(bag)) {
                    for (int j = 0; j < ArrayLBoxs.size(); j++) {

                        box item = (box) ArrayLBoxs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.istopcollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                Collision_Top)) {
                            return true;
                        }
                    }
                    bag.move(0, -Space);
                    isLevelComplete();
                }
            }

            return false;

        } else if (collision_type == Collision_Bottom) {

            for (int i = 0; i < ArrayLBoxs.size(); i++) {

                box bag = (box) ArrayLBoxs.get(i);
                if (objPlayer.isbuttoncollision(bag)) {
                    for (int j = 0; j < ArrayLBoxs.size(); j++) {

                        box item = (box) ArrayLBoxs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isbuttoncollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                Collision_Bottom)) {
                            return true;
                        }
                    }
                    bag.move(0, Space);
                    isLevelComplete();
                }
            }
        }

        return false;
    }
    public void nextLevel() {
        ArrayLDiamonds.clear();
        ArrayLBoxs.clear();
        ArrayLWalls.clear();
        startGameBoard(LevelComplete);
        actor_movecount = 0;
        CompleteStatus = false;
    }

    public void isLevelComplete() {
        int num = ArrayLBoxs.size();
        int compl = 0;

        for (int i = 0; i < num; i++) {
            box bag = (box) ArrayLBoxs.get(i);
            for (int j = 0; j < num; j++) {
                diamond area = (diamond) ArrayLDiamonds.get(j);
                if (bag.getX() == area.getX() && bag.getY() == area.getY()) {
                    compl += 1;
                }
            }
        }

        if (compl == num) {
            CompleteStatus = true;
            JOptionPane.showMessageDialog(null, "Level Complete");
            if (LevelComplete.equals(m.level_1)) {
                LevelComplete = m.level_2;
                lblthislevel.setText("Level 2");
            } else if (LevelComplete.equals(m.level_2)) {
                LevelComplete = m.level_3;
                lblthislevel.setText("Level 3");
            } else if (LevelComplete.equals(m.level_3)) {
                LevelComplete = m.level_4;
                lblthislevel.setText("Level 4");
            } else if (LevelComplete.equals(m.level_4)) {
                LevelComplete = m.level_5;
                lblthislevel.setText("Level 5");
            }
            nextLevel(); // Call nextLevel() after updating LevelComplete
            repaint();
        }
    }



    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        //lblGameBoardimage.paint(g);
        buildLevel_Map(g2d);
    }
    public void buildLevel_Map(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0, 0, lblGameSheetimage.getWidth(), lblGameSheetimage.getHeight());
        ArrayList alLevelMap = new ArrayList();
        alLevelMap.addAll(ArrayLWalls);
        alLevelMap.addAll(ArrayLDiamonds);
        alLevelMap.addAll(ArrayLBoxs);
        alLevelMap.add(objPlayer);

        for (int i = 0; i < alLevelMap.size(); i++)
        {

            Movement_Adjuster item = (Movement_Adjuster) alLevelMap.get(i);

            if ((item instanceof player) || (item instanceof box))
            {
                g2d.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, lblGameSheetimage);

            }
            else
            {
                g2d.drawImage(item.getImage(), item.getX(), item.getY(), lblGameSheetimage);
            }

            if (CompleteStatus) {
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawString("Completed", 25, 20);
                if (LevelComplete.equals("level1"))
                {
                LevelComplete = "level2";
                startGameBoard(LevelComplete);
                }
                else if (LevelComplete.equals("level2"))
                {
                LevelComplete = "level3";
                startGameBoard(LevelComplete);
                }
                else if (LevelComplete.equals("level3"))
                {
                LevelComplete = "level4";
                startGameBoard(LevelComplete);
                }
                else
                {
                LevelComplete = "level5";
                startGameBoard(LevelComplete);
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Sokoban objsk=new Sokoban();
    }
    
}
