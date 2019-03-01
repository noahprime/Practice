import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

public class ShotEvent extends JFrame {

    JButton ok;
    JButton cancel;
    public boolean madeShot;
    public boolean cancelBool = false;

    public ShotEvent(int x,int y,String outputFileName,Player[] playersHome,Player[] playersAway ,JPanel panel){

        super("Shot");
        setLayout(new FlowLayout());
        setSize(490,190);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel shot = new JPanel(new FlowLayout());
        shot.setSize(480,150);
        JTextField team = new JTextField("Team",8);
        JTextField number = new JTextField("Number",8);
        JTextField outcome = new JTextField("Outcome",8);
        JTextField info = new JTextField("Info",8);
        shot.add(team);
        shot.add(number);
        shot.add(outcome);
        shot.add(info);
        add(shot,BorderLayout.CENTER);
        shot.setVisible(true);

        JPanel toolbar = new JPanel();
        toolbar.setSize(480,10);
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        toolbar.add(ok);
        toolbar.add(cancel);
        add(toolbar,BorderLayout.SOUTH);
        toolbar.setVisible(true);

        setSize(500,200);

        getRootPane().setDefaultButton(ok);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color green = new Color(4,150,19);

                madeShot = false;
                String teamS = team.getText();
                String numberS = number.getText();
                String outcomeS = outcome.getText();
                String infoS = info.getText();
                String ptsS = "2";
                if(isThree(x,y)) {
                  ptsS = "3";
                }

                String niceOutcome = "X";
                if(outcomeS.equalsIgnoreCase("o")) {
                  niceOutcome = "O";
                }
                if(infoS.equalsIgnoreCase("B")){
                    Block b = new Block(playersHome,playersAway);
                }
                else if(infoS.equalsIgnoreCase("F")){
                    Foul f = new Foul(playersHome,playersAway);
                }
                else if(infoS.equalsIgnoreCase("A")){
                    Assist a = new Assist(playersHome,playersAway);
                }
                else{
                  infoS = "";
                }

                //----------------------------------------
                JPanel temp = new JPanel(new FlowLayout());
                JTextField type = new JTextField("S",2);
                type.setEditable(false);
                JTextField num = new JTextField(numberS,2);
                num.setEditable(false);
                JTextField tem = new JTextField(teamS,2);
                tem.setEditable(false);
                JTextField out = new JTextField(niceOutcome,2);
                out.setEditable(false);
                JTextField pts = new JTextField(ptsS,2);
                pts.setEditable(false);
                JTextField inf = new JTextField(infoS,2);
                inf.setEditable(false);
                JButton edit = new JButton("Edit");
                temp.add(type);
                temp.add(tem);
                temp.add(num);
                temp.add(out);
                temp.add(pts);
                temp.add(inf);
                temp.add(edit);
                InputFrame.container.add(temp);
                temp.setSize(590,10);
                temp.setVisible(true);
                temp.setOpaque(true);
                temp.setBackground(Color.RED);
                InputFrame.container.setSize(350,600);

                JButton done = new JButton("done");

                edit.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent f) {
                    System.out.println("Shot Editor");

                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player p:playersHome){
                            if(p.getNumber().equalsIgnoreCase(num.getText())){
                                p.removeShot(x,y);
                                if(out.getText().equalsIgnoreCase("o")){
                                  if(pts.getText().equalsIgnoreCase("3")){
                                    p.downThreesMade();
                                  }
                                  else{
                                    p.downTwosMade();
                                  }
                                }
                                else{
                                  if(pts.getText().equalsIgnoreCase("3")){
                                    p.downThreesMissed();
                                  }
                                  else{
                                    p.downTwosMissed();
                                  }
                                }
                                break;
                            }
                        }
                    }
                    else{
                        for(Player p:playersAway){
                            if(p.getNumber().equalsIgnoreCase(num.getText())){
                              p.removeShot(x,y);
                              if(out.getText().equalsIgnoreCase("o")){
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.downThreesMade();
                                }
                                else{
                                  p.downTwosMade();
                                }
                              }
                              else{
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.downThreesMissed();
                                }
                                else{
                                  p.downTwosMissed();
                                }
                              }
                            }
                        }
                    }

                    temp.remove(edit);
                    temp.add(done);
                    temp.setSize(590,10);
                    InputFrame.container.setSize(350,600);
                    type.setEditable(true);
                    tem.setEditable(true);
                    num.setEditable(true);
                    out.setEditable(true);
                    pts.setEditable(true);
                    inf.setEditable(true);

                  }
                });

                done.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent g) {
                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player p:playersHome){
                            if(p.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                p.addShot(x,y,1);
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.upThreesMade();
                                }
                                else{
                                  p.upTwosMade();
                                }
                              }
                              else{
                                p.addShot(x,y,0);
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.upThreesMissed();
                                }
                                else{
                                  p.upTwosMissed();
                                }
                              }
                            }
                        }
                    }
                    else{
                        for(Player p:playersAway){
                            if(p.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                p.addShot(x,y,1);
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.upThreesMade();
                                }
                                else{
                                  p.upTwosMade();
                                }
                              }
                              else{
                                p.addShot(x,y,0);
                                if(pts.getText().equalsIgnoreCase("3")){
                                  p.upThreesMissed();
                                }
                                else{
                                  p.upTwosMissed();
                                }
                              }
                            }
                        }
                    }

                    temp.remove(done);
                    temp.add(edit);
                    temp.setSize(590,10);
                    InputFrame.container.setSize(350,600);
                    type.setEditable(false);
                    tem.setEditable(false);
                    num.setEditable(false);
                    out.setEditable(false);
                    pts.setEditable(false);
                    inf.setEditable(false);
                  }
                });
                //---------------------------------------

                Player player = null;
                if(teamS.equalsIgnoreCase("H")) {
                    for (Player y : playersHome) {
                        if (numberS.equals(y.getNumber())) {
                            player = y;
                        }
                    }
                }
                if(teamS.equalsIgnoreCase("A")) {
                    for (Player y : playersAway) {
                        if (numberS.equals(y.getNumber())) {
                            player = y;
                        }
                    }
                }
                if(outcomeS.equalsIgnoreCase("O")){
                    madeShot = true;
                    player.addShot(x,y,1);
                }
                else{
                  player.addShot(x,y,0);
                }
                if(isThree(x,y) && madeShot){
                    player.upThreesMade();
                    try {
                        BufferedImage image = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/greenO.png"));
                        Graphics g = panel.getGraphics();
                        g.drawImage(image, x-4, y-4, 8, 8, null);
                        repaint();
                        panel.setVisible(true);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                    repaint();
                    panel.setVisible(true);
                }
                else if(isThree(x,y)){
                    player.upThreesMissed();
                    try {
                        BufferedImage image = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/x.png"));
                        Graphics g = panel.getGraphics();
                        g.drawImage(image, x-4, y-4, 8, 8, null);
                        repaint();
                        panel.setVisible(true);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                }
                else if(!isThree(x,y) && madeShot){
                    player.upTwosMade();
                    try {
                        BufferedImage image = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/greenO.png"));
                        Graphics g = panel.getGraphics();
                        g.drawImage(image, x-4, y-4, 8, 8, null);
                        repaint();
                        panel.setVisible(true);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                    repaint();
                    panel.setVisible(true);
                }
                else{
                    player.upTwosMissed();
                    try {
                        BufferedImage image = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/x.png"));
                        Graphics g = panel.getGraphics();
                        g.drawImage(image, x-4, y-4, 8, 8, null);
                        repaint();
                        panel.setVisible(true);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                }
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelBool = true;
                dispose();
            }
        });

    }

    public boolean isThree(int x,int y){
        if(y>145){
            if((x-249)*(x-249) + (y-57)*(y-57) > 230*230){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(x<39 || x>459){
                return true;
            }
        }
        return false;
    }

    public boolean isCancelBool() {
        return cancelBool;
    }

    public boolean getMadeShot(){
        return this.madeShot;
    }
}
