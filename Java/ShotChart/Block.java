import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Block extends JFrame {

    public Block(Player[] homePlayers,Player[] awayPlayers){
        super("Block");
        setLayout(new FlowLayout());
        setSize(490,190);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(480,150);
        JTextField team = new JTextField("Team",10);
        JTextField numberBlocking = new JTextField("Number Blocking",10);
        panel.add(team);
        panel.add(numberBlocking);
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
                String numberS = numberBlocking.getText();
                //----------------------------------------
                JPanel temp = new JPanel(new FlowLayout());
                JTextField type = new JTextField("B",2);
                type.setEditable(false);
                JTextField num = new JTextField(numberS,2);
                num.setEditable(false);
                JTextField tem = new JTextField(teamS,2);
                JButton edit = new JButton("Edit");
                temp.add(type);
                temp.add(tem);
                temp.add(num);
                temp.add(edit);
                InputFrame.container.add(temp);
                temp.setSize(590,10);
                temp.setVisible(true);
                temp.setOpaque(true);
                temp.setBackground(Color.BLUE);
                InputFrame.container.setSize(350,600);

                JButton done = new JButton("done");

                edit.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent f) {
                    System.out.println("Block Editor");

                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                                x.downBlocks();
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                                x.downBlocks();
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

                  }
                });

                done.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent g) {
                    if(tem.getText().equalsIgnoreCase("H")){
                        for(Player x:homePlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                                x.upBlocks();
                            }
                        }
                    }
                    else{
                        for(Player x:awayPlayers){
                            if(x.getNumber().equalsIgnoreCase(num.getText())){
                                x.upBlocks();
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
                  }
                });
                //---------------------------------------
                if(teamS.equalsIgnoreCase("H")){
                    for(Player x:homePlayers){
                        if(x.getNumber().equalsIgnoreCase(numberS)){
                            x.upBlocks();
                        }
                    }
                }
                else{
                    for(Player x:awayPlayers){
                        if(x.getNumber().equalsIgnoreCase(numberS)){
                            x.upBlocks();
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
