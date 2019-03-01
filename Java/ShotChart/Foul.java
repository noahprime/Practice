import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Foul extends JFrame {

    public Foul(Player[] homePlayers,Player[] awayPlayers){
        super("Foul");
        setLayout(new FlowLayout());
        setSize(490,190);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(480,150);
        JTextField teamFouling = new JTextField("Team Fouling",10);
        JTextField numberFouling = new JTextField("Number Fouling",10);
        JTextField numberFouled = new JTextField("Number Fouled",10);
        panel.add(teamFouling);
        panel.add(numberFouling);
        panel.add(numberFouled);
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
                String teamS = teamFouling.getText();
                String numberFoulingS = numberFouling.getText();
                String numberFouledS = numberFouled.getText();
                //----------------------------------------
                JPanel temp = new JPanel(new FlowLayout());
                JTextField type = new JTextField("F",2);
                type.setEditable(false);
                JTextField num = new JTextField(numberFoulingS,2);
                num.setEditable(false);
                JTextField numF = new JTextField(numberFouledS,2);
                numF.setEditable(false);
                JTextField tem = new JTextField(teamS,2);
                JButton edit = new JButton("Edit");
                temp.add(type);
                temp.add(tem);
                temp.add(num);
                temp.add(numF);
                temp.add(edit);
                InputFrame.container.add(temp);
                temp.setSize(590,10);
                temp.setVisible(true);
                temp.setOpaque(true);
                temp.setBackground(Color.ORANGE);
                InputFrame.container.setSize(350,600);

                JButton done = new JButton("done");

                edit.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent f) {
                    System.out.println("Assist Editor");

                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              x.downFouls();
                                for(Player y:awayPlayers){
                                    if(y.getNumber().equalsIgnoreCase(numF.getText())){
                                        y.downDrawnFouls();
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              x.downFouls();
                                for(Player y:homePlayers){
                                    if(y.getNumber().equalsIgnoreCase(numF.getText())){
                                        y.downDrawnFouls();
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
                    numF.setEditable(true);

                  }
                });

                done.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent g) {
                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              x.upFouls();
                                for(Player y:awayPlayers){
                                    if(y.getNumber().equalsIgnoreCase(numF.getText())){
                                        y.upDrawnFouls();
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                              x.upFouls();
                                for(Player y:homePlayers){
                                    if(y.getNumber().equalsIgnoreCase(numF.getText())){
                                        y.upDrawnFouls();
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
                    numF.setEditable(false);
                  }
                });
                //---------------------------------------
                if(teamS.equalsIgnoreCase("H")){
                    for(Player x:homePlayers){
                        if(x.getNumber().equalsIgnoreCase(numberFoulingS)){
                          x.upFouls();
                            for(Player y:awayPlayers){
                                if(y.getNumber().equalsIgnoreCase(numberFouledS)){
                                    y.upDrawnFouls();
                                }
                            }
                        }
                    }
                }
                else{
                    for(Player x:awayPlayers){
                        if(x.getNumber().equalsIgnoreCase(numberFoulingS)){
                          x.upFouls();
                            for(Player y:homePlayers){
                                if(y.getNumber().equalsIgnoreCase(numberFouledS)){
                                    y.upDrawnFouls();
                                }
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
