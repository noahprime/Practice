
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.imageio.*;

public class InputFrame extends JFrame implements KeyListener{

    String FILENAME;
    Player[] playersHome;
    Player[] playersAway;
    JPanel panel;
    public static JPanel container = new JPanel();

    private static DecimalFormat df = new DecimalFormat(".##");



    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        String key = e.getKeyText(e.getKeyCode());
        Scanner sc = new Scanner(System.in);
        switch (key){
            case "R":
                Rebound r = new Rebound(playersHome,playersAway);
                break;

            case "F":
                Foul f = new Foul(playersHome,playersAway);
                break;

            case "B":
                Block b = new Block(playersHome,playersAway);
                break;

            case "S":
                Steal s = new Steal(playersHome,playersAway);
                break;

            case "T":
                FreeThrow free = new FreeThrow(playersHome,playersAway);
                break;

            case "A":
                Assist a = new Assist(playersHome,playersAway);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public InputFrame(){

    }

    public String calculateScore(Player[] arr){
      int score = 0;
      for(Player p:arr){
        score += p.getPoints();
      }
      return Integer.toString(score);
    }


    public InputFrame(Player[] playersHome,Player[] playersAway) {
        super("Chart Creator");
        //------------------------------------------------
        JFrame feed = new JFrame("Live Feed");
        JPanel boxScore = new JPanel();
        boxScore.setLayout(new BoxLayout(boxScore, BoxLayout.Y_AXIS));
        boxScore.setSize(400,300);
        boxScore.setVisible(true);
        JScrollPane scrolls = new JScrollPane(boxScore);
        feed.add(scrolls);
        feed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        feed.setSize(400,300);
        feed.setVisible(true);
        JPanel score = new JPanel(new FlowLayout());
        JTextField home = new JTextField(calculateScore(playersHome),4);
        JTextField away = new JTextField(calculateScore(playersAway),4);
        JButton up = new JButton("update");
        score.add(home);
        score.add(away);
        score.add(up);

        up.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent f) {
            home.setText(calculateScore(playersHome));
            away.setText(calculateScore(playersAway));
          }
        });

        home.setEditable(false);
        away.setEditable(false);
        score.setSize(400,10);
        score.setVisible(true);
        boxScore.add(score);

        JPanel labels = new JPanel(new FlowLayout());
        JTextField nameLab = new JTextField("Name",8);
        JTextField ptsLab = new JTextField("Points",3);
        JTextField assLab = new JTextField("Assists",4);
        JTextField rebLab = new JTextField("Rebounds",4);
        JTextField steLab = new JTextField("Steals",4);
        labels.add(nameLab);
        labels.add(ptsLab);
        labels.add(assLab);
        labels.add(rebLab);
        labels.add(steLab);
        nameLab.setEditable(false);
        ptsLab.setEditable(false);
        assLab.setEditable(false);
        rebLab.setEditable(false);
        steLab.setEditable(false);
        labels.setSize(400,10);
        boxScore.add(labels);
        labels.setVisible(true);

        for(Player guy:playersHome) {
          JPanel personalPanel = new JPanel(new FlowLayout());
          JTextField name = new JTextField(guy.getName(),10);
          JTextField pts = new JTextField(Integer.toString((int)guy.getPoints()),3);
          JTextField ass = new JTextField(Integer.toString(guy.getAssists()),3);
          JTextField reb = new JTextField(Integer.toString(guy.getDefRebounds()+guy.getOffRebounds()),3);
          JTextField ste = new JTextField(Integer.toString(guy.getSteals()),3);
          personalPanel.add(name);
          personalPanel.add(pts);
          personalPanel.add(ass);
          personalPanel.add(reb);
          personalPanel.add(ste);
          name.setEditable(false);
          pts.setEditable(false);
          ass.setEditable(false);
          reb.setEditable(false);
          ste.setEditable(false);
          JButton update = new JButton("update");
          personalPanel.add(update);

          update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
              pts.setText(Integer.toString((int)guy.getPoints()));
              ass.setText(Integer.toString(guy.getAssists()));
              reb.setText(Integer.toString(guy.getDefRebounds()+guy.getOffRebounds()));
              ste.setText(Integer.toString(guy.getSteals()));
            }
          });

          personalPanel.setSize(400,10);
          boxScore.add(personalPanel);
          personalPanel.setVisible(true);
        }
        boxScore.setSize(400,300);
        //-------------------------------------------------
        JFrame list = new JFrame("Plays");
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setSize(350,600);
        container.setVisible(true);
        JScrollPane scroller = new JScrollPane(container);
        list.add(scroller);
        list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list.setSize(350,600);
        list.setVisible(true);
        //-------------------------------------------------
        panel = new JPanel(new BorderLayout());
        panel.add(new DrawPanel(), BorderLayout.CENTER);


        JButton exit = new JButton("Exit");
        this.add(exit, BorderLayout.SOUTH);

        setVisible(true);
        this.add(panel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,515);
        this.setResizable(false);
        this.setVisible(true);



        JFileChooser chooser = new JFileChooser();
        chooser.setVisible(true);
        int option = chooser.showSaveDialog(this);
        String filename = null;
        if (option == JFileChooser.APPROVE_OPTION){
            filename = chooser.getSelectedFile().getAbsolutePath();
        }
        FILENAME = filename;


        File newDirectory = new File(FILENAME);
        newDirectory.mkdir();

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*try{
                  FileWriter stats = new FileWriter(FILENAME + "/gamelog.txt", true);
                  stats.write(box.getText());
                  stats.close();
                } catch(IOException asdf) {
                  System.out.println("IOException Xyx Line 129");
                }*/

                for(Player x:playersHome){
                    x.dump(FILENAME);
                }

                ///////////////////////////////////////////////////////////
                BufferedImage image = new BufferedImage(1455,1365, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                AffineTransform xform = AffineTransform.getScaleInstance(1, 1);
                g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                try {
                    BufferedImage court = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/nbacourt.png"));
                    g.drawImage(court,xform,null);
                } catch (IOException io){
                    System.err.println("Can't find court image");
                    System.exit(1);
                }
                int homeScore = 0;
                int homeDefRebounds = 0;
                int homeOffRebounds = 0;
                int homeSteals = 0;
                int homeBlocks = 0;
                int homeFouls = 0;
                double homeThreesMade = 0;
                double homeThreesTaken = 0;
                double homeShotsMade = 0;
                double homeShotsTaken = 0;
                int homeAssists = 0;
                for(Player p:playersHome){
                    homeScore = homeScore + (int)p.getPoints();
                    homeDefRebounds = homeDefRebounds + p.getDefRebounds();
                    homeOffRebounds = homeOffRebounds + p.getOffRebounds();
                    homeSteals = homeSteals + p.getSteals();
                    homeBlocks = homeBlocks + p.getBlocks();
                    homeFouls = homeFouls + p.getFouls();
                    homeThreesMade = homeThreesMade + p.getThreesMade();
                    homeThreesTaken = homeThreesTaken + p.getThreesMissed() + p.getThreesMade();
                    homeShotsMade = homeShotsMade + p.getThreesMade() + p.getTwosMade();
                    homeShotsTaken = homeShotsTaken + p.getThreesMade() + p.getTwosMade() + p.getThreesMissed() + p.getTwosMissed();
                    homeAssists += p.getAssists();

                    for(int[] x:p.getShots()){
                        if(x[2] == 0){
                            try {
                                BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/x.png"));
                                g.drawImage(imageRead, (x[0]*3)-33, (x[1]*3)-26, 24, 24, null);
                            } catch (IOException fnfe) {
                                System.out.println("can't find image file");
                            }
                        }
                        else if(x[2] == 1){
                            try {
                                BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/greenO.png"));
                                g.drawImage(imageRead, (x[0]*3)-31, (x[1]*3)-24, 28, 28, null);
                            } catch (IOException fnfe) {
                                System.out.println("can't find image file");
                            }
                        }
                    }

                    try{
                        ImageIO.write(image, "png", new File(FILENAME + "/Home Team.png"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                //////////////////////////////////////////////////////////
                BufferedImage image2 = new BufferedImage(1455,1365, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image2.createGraphics();
                AffineTransform xform2 = AffineTransform.getScaleInstance(1, 1);
                g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                try {
                    BufferedImage court = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/nbacourt.png"));
                    g2.drawImage(court,xform2,null);
                } catch (IOException io){
                    System.err.println("Can't find court image");
                    System.exit(1);
                }

                int awayScore = 0;
                int awayDefRebounds = 0;
                int awayOffRebounds = 0;
                int awaySteals = 0;
                int awayBlocks = 0;
                int awayFouls = 0;
                double awayThreesMade = 0;
                double awayThreesTaken = 0;
                double awayShotsMade = 0;
                double awayShotsTaken = 0;
                int awayAssists = 0;
                for(Player p:playersAway){
                    awayScore = awayScore + (int)p.getPoints();
                    awayDefRebounds = awayDefRebounds + p.getDefRebounds();
                    awayOffRebounds = awayOffRebounds + p.getOffRebounds();
                    awaySteals = awaySteals + p.getSteals();
                    awayBlocks = awayBlocks + p.getBlocks();
                    awayFouls = awayFouls + p.getFouls();
                    awayThreesMade = awayThreesMade + p.getThreesMade();
                    awayThreesTaken = awayThreesTaken + p.getThreesMissed() + p.getThreesMade();
                    awayShotsMade = awayShotsMade + p.getThreesMade() + p.getTwosMade();
                    awayShotsTaken = awayShotsTaken + p.getThreesMade() + p.getTwosMade() + p.getThreesMissed() + p.getTwosMissed();
                    awayAssists += p.getAssists();

                    for(int[] x:p.getShots()){
                        if(x[2] == 0){
                            try {
                                BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/x.png"));
                                g2.drawImage(imageRead, (x[0]*3)-33, (x[1]*3)-26, 24, 24, null);
                            } catch (IOException fnfe) {
                                System.out.println("can't find image file");
                            }
                        }
                        else if(x[2] == 1){
                            try {
                                BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/greenO.png"));
                                g2.drawImage(imageRead, (x[0]*3)-31, (x[1]*3)-24, 28, 28, null);
                            } catch (IOException fnfe) {
                                System.out.println("can't find image file");
                            }
                        }
                    }

                    try{
                        ImageIO.write(image2, "png", new File(FILENAME + "/Away Team.png"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                //////////////////////////////////////////////////////////

                try{
                    FileWriter pw = new FileWriter(FILENAME + "/writeup.txt", true);
                    FileWriter pw2 = new FileWriter("/Users/noahprime/Practice/Java/ShotChart/Shots/shots.txt", true);
                    pw.write("Score: " + homeScore + "-" + awayScore + "(Home-Away)" + "\n");
                    pw.write("\n");
                    pw.write("Home Totals: Points: " + homeScore
                    + ", Shooting: " + (int)homeShotsMade + "/" + (int)homeShotsTaken
                    + " (" + df.format((homeShotsMade/homeShotsTaken)*100) + "), Three's: "
                    + (int)homeThreesMade + "/" + (int)homeThreesTaken
                    + " (" + df.format((homeThreesMade/homeThreesTaken)*100)
                    + "), Assists: " + homeAssists + ", Defensive Rebounds: " + homeDefRebounds
                    + ", Offensive Rebounds: " + homeOffRebounds + ", Fouls: "
                    + homeFouls + ", Steals: " + homeSteals + ", Blocks: "
                    + homeBlocks + "\n");
                    pw.write("\n");
                    pw.write("Away Totals: Points: " + awayScore
                    + ", Shooting: " + (int)awayShotsMade + "/" + (int)awayShotsTaken
                    + " (" + df.format((awayShotsMade/awayShotsTaken)*100) + "), Three's: "
                    + (int)awayThreesMade + "/" + (int)awayThreesTaken
                    + " (" + df.format((awayThreesMade/awayThreesTaken)*100)
                    + "), Assists: " + awayAssists + ", Defensive Rebounds: " + awayDefRebounds
                    + ", Offensive Rebounds: " + awayOffRebounds + ", Fouls: "
                    + awayFouls + ", Steals: " + awaySteals + ", Blocks: "
                    + awayBlocks + "\n");
                    pw.write("\n");
                    pw.write("Individuals: " + "\n");
                    pw.write("Home Team:\n");
                    System.out.println("Home Team: ");
                    for (Player x : playersHome) {
                        for(int[] s:x.getShots()){
                            pw2.write(x.getName()+","+s[0]+","+s[1]+","+s[2]+"\n");
                        }
                        pw.write( x.getName() + ": " + x.getPoints()
                        + " Points" + ": shooting " + (int)(x.getThreesMade() + x.getTwosMade())
                        + "/" + (int)(x.getThreesMade() + x.getTwosMade() + x.getThreesMissed() + x.getTwosMissed())
                        + " (" + df.format(x.getShootingPercentage() * 100)
                        + "%)" + ": 3pt " + (int)x.getThreesMade() + "/"
                        + (int)(x.getThreesMade() + x.getThreesMissed())
                        + " (" + df.format(x.getThreePtPercentage() * 100)
                        + "%): Free-Throws " + (int)x.getFreeThrowsMade() + "/"
                        + (int)x.getFreeThrowsTaken() + " ("
                        + df.format(x.getFreeThrowPercentage()*100)
                        + "%):  Assists " + x.getAssists() + " Rebounds " + (x.getOffRebounds()+x.getDefRebounds())
                        + ": Steals "+ x.getSteals()
                        + ": Blocks "+ x.getBlocks() +"\n");
                        //System.out.println(x.getName() + ": " + x.getPoints() + " Points" + ": shooting " + (int)x.getShotsMade() + "/" + (int)x.getShotsTaken() + " (" + df.format(x.getShootingPercentage() * 100) + "%)" + ": 3pt " + (int)x.getThreesMade() + "/" + (int)(x.getThreesMade() + x.getThreesMissed()) + " (" + df.format(x.getThreePtPercentage() * 100) + "%): Rebounds " + (x.getOffRebounds()+x.getDefRebounds())+": Steals "+ x.getSteals() + ": Blocks "+ x.getBlocks());
                    }
                    pw.write("\n");
                    pw.write("\n");
                    pw.write("Away Team:\n");
                    System.out.println("Away Team: ");
                    for (Player x : playersAway) {
                        pw.write(x.getName() + ": " + x.getPoints()
                        + " Points" + ": shooting " + (int)(x.getThreesMade()
                        + x.getTwosMade()) + "/" + (int)(x.getThreesMade()
                        + x.getTwosMade() + x.getThreesMissed()
                        + x.getTwosMissed())
                        + " (" + df.format(x.getShootingPercentage() * 100)
                        + "%)" + ": 3pt " + (int)x.getThreesMade() + "/"
                        + (int)(x.getThreesMade() + x.getThreesMissed()) + " ("
                        + df.format(x.getThreePtPercentage() * 100)
                        + "%): Rebounds " + (x.getOffRebounds()+x.getDefRebounds())
                        +": Steals "+ x.getSteals() + ": Blocks "+ x.getBlocks()
                        +"\n");
                        //System.out.println(x.getName() + ": " + x.getPoints() + " Points" + ": shooting " + (int)x.getShotsMade() + "/" + (int)x.getShotsTaken() + " (" + df.format(x.getShootingPercentage() * 100) + "%)" + ": 3pt " + (int)x.getThreesMade() + "/" + (int)(x.getThreesMade() + x.getThreesMissed()) + " (" + df.format(x.getThreePtPercentage() * 100) + "%): Rebounds " + (x.getOffRebounds()+x.getDefRebounds())+": Steals "+ x.getSteals() + ": Blocks "+ x.getBlocks() );
                    }
                    pw.close();
                    pw2.close();
                    dispose();
                    System.exit(0);
                } catch (IOException fnfe) {
                    System.out.println(fnfe);
                    dispose();
                    System.exit(0);
                }
            }
        });

        this.playersHome = playersHome;
        this.playersAway = playersAway;


        panel.setFocusable(true);
        panel.addKeyListener(this);
        panel.setFocusTraversalKeysEnabled(false);

        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                ShotEvent pop = new ShotEvent(x, y, FILENAME, playersHome,playersAway,panel);
                System.out.println(x + ", " + y);
            }
        });
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            try {
                BufferedImage court = ImageIO.read(new File("/Users/noahprime/Practice/Java/ShotChart/src/nbacourt.png"));
                g.drawImage(court,7,5,485,455,null);
            } catch (IOException io){
                System.err.println("Can't find court image");
                System.exit(1);
            }
        }
    }
}
