import javafx.geometry.HorizontalDirection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Rebound extends JFrame{

    public Rebound(Player[] homePlayers,Player[] awayPlayers){
        super("Rebound");
        setLayout(new FlowLayout());
        setSize(490,190);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(480,150);
        JTextField team = new JTextField("Team",10);
        JTextField number = new JTextField("Number",10);
        JTextField possession = new JTextField("Possession",10);
        panel.add(team);
        panel.add(number);
        panel.add(possession);
        add(panel,BorderLayout.CENTER);
        panel.setVisible(true);

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
                String teamS = team.getText();
                String numberS = number.getText();
                String possessionS = possession.getText();

                String nicePos = "D";
                if(possessionS.equalsIgnoreCase("o")) {
                  nicePos = "O";
                }

                //----------------------------------------
                JPanel temp = new JPanel(new FlowLayout());
                JTextField type = new JTextField("R",2);
                type.setEditable(false);
                JTextField num = new JTextField(numberS,2);
                num.setEditable(false);
                JTextField out = new JTextField(nicePos,2);
                out.setEditable(false);
                JTextField tem = new JTextField(teamS,2);
                JButton edit = new JButton("Edit");
                temp.add(type);
                temp.add(tem);
                temp.add(num);
                temp.add(out);
                temp.add(edit);
                InputFrame.container.add(temp);
                temp.setSize(590,10);
                temp.setVisible(true);
                temp.setOpaque(true);
                temp.setBackground(Color.BLACK);
                InputFrame.container.setSize(350,600);

                JButton done = new JButton("done");

                edit.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent f) {
                    System.out.println("Rebound Editor");

                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                x.downOffRebounds();
                              }
                              else{
                                x.downDefRebounds();
                              }
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                x.downOffRebounds();
                              }
                              else{
                                x.downDefRebounds();
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

                  }
                });

                done.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent g) {
                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                x.upOffRebounds();
                              }
                              else{
                                x.upDefRebounds();
                              }
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              if(out.getText().equalsIgnoreCase("o")){
                                x.upOffRebounds();
                              }
                              else{
                                x.upDefRebounds();
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
                  }
                });
                //---------------------------------------
                if(teamS.equalsIgnoreCase("H")){
                    for(Player x:homePlayers){
                        if(x.getNumber().equalsIgnoreCase(numberS)){
                            if(possessionS.equalsIgnoreCase("O")){
                                x.upOffRebounds();
                            }
                            else{
                                x.upDefRebounds();
                            }
                        }
                    }
                }
                else{
                    for(Player x:awayPlayers){
                        if(x.getNumber().equalsIgnoreCase(numberS)){
                            if(possessionS.equalsIgnoreCase("O")){
                                x.upOffRebounds();
                            }
                            else{
                                x.upDefRebounds();
                            }
                        }
                    }
                }
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
