import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SeasonTally {
    public static ArrayList<Player> players = new ArrayList<>();
    private static DecimalFormat df = new DecimalFormat(".##");


    public static void main(String[] args){
        readFiles();
        exit();
    }

    public static void readFiles(){
        File file = new File("/Users/noahprime/MyStuff/JavaPractice/ShotChart/Shots/shots.txt");

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String[] shot = sc.nextLine().split(",");
                Player player = null;
                if(players.isEmpty()){
                    player = new Player("H",shot[0],"0");
                    players.add(player);
                }
                else {
                    for (Player p : players) {
                        if (p.getName().equalsIgnoreCase(shot[0])) {
                            player = p;
                        }
                    }
                    if (player == null) {
                        player = new Player("H", shot[0], "0");
                        players.add(player);
                    }
                }



                player.upShotsTaken();

                boolean madeShot = false;
                if(shot[3].equalsIgnoreCase("1")){
                    madeShot = true;
                    player.addShot(Integer.parseInt(shot[1]),Integer.parseInt(shot[2]),1);
                }
                if(isThree(Integer.parseInt(shot[1]),Integer.parseInt(shot[2])) && madeShot){
                    player.upThreesMade();
                }
                else if(isThree(Integer.parseInt(shot[1]),Integer.parseInt(shot[2]))){
                    player.upThreesMissed();
                    player.addShot(Integer.parseInt(shot[1]),Integer.parseInt(shot[2]),0);
                }
                else if(!isThree(Integer.parseInt(shot[1]),Integer.parseInt(shot[2])) && madeShot){
                    player.upTwosMade();
                }
                else{
                    player.upTwosMissed();
                    player.addShot(Integer.parseInt(shot[1]),Integer.parseInt(shot[2]),0);
                }
            }
        } catch (FileNotFoundException fnfe){
            System.err.print(fnfe);
        }


        /*File[] files = new File("/Users/noahprime/Desktop/write-ups").listFiles();
        ArrayList<Player> players = new ArrayList<>();
        try {
            for(File x:files){
                Scanner scanner = new Scanner(x);
                scanner.nextLine();
                while (!scanner.next().equals("\n")){
                    players.add(new Player("H",scanner.next(),"14"));
                }

                scanner.nextLine();
                scanner.nextLine();

                while (!scanner.next().equals("\n")){
                    String[] shots = scanner.nextLine().split(",");
                    int t = 0;
                    if(shots[3].equalsIgnoreCase("Make")){
                        t = 1;
                    }
                    for(Player dude:players){
                        if(dude.getName().equals(shots[0])){
                            dude.addShot(Integer.parseInt(shots[1]),Integer.parseInt(shots[2]),t);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("File Not Found");
            System.exit(1);
        }*/
    }

    public static boolean isThree(int x,int y){
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

    public static void exit(){
        File newDirectory = new File("/Users/noahprime/Desktop/season");
        newDirectory.mkdir();

        for(Player x:players){
            x.dump("/Users/noahprime/Desktop/season");
        }

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
            BufferedImage court = ImageIO.read(new File("/Users/noahprime/MyStuff/JavaPractice/ShotChart/src/nbacourt.png"));
            g.drawImage(court,xform,null);
        } catch (IOException io){
            System.err.println("Can't find court image");
            System.exit(1);
        }
        for(Player p:players){
            for(int[] x:p.getShots()){
                if(x[2] == 0){
                    try {
                        BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/MyStuff/JavaPractice/ShotChart/src/x.png"));
                        g.drawImage(imageRead, (x[0]*3)-33, (x[1]*3)-26, 24, 24, null);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                }
                else if(x[2] == 1){
                    try {
                        BufferedImage imageRead = ImageIO.read(new File("/Users/noahprime/MyStuff/JavaPractice/ShotChart/src/greenO.png"));
                        g.drawImage(imageRead, (x[0]*3)-31, (x[1]*3)-24, 28, 28, null);
                    } catch (IOException fnfe) {
                        System.out.println("can't find image file");
                    }
                }
            }

            try{
                ImageIO.write(image, "png", new File("/Users/noahprime/Desktop/season" + "/team.png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        try{
            FileWriter pw = new FileWriter("/Users/noahprime/Desktop/season" + "/writeup.txt", true);
            pw.write("Season Totals:\n");
            pw.write("\n");
            for (Player x : players) {
                pw.write( x.getName() + ": " + x.getPoints() + " Points" + ": shooting " + (int)x.getShotsMade() + "/" + (int)x.getShotsTaken() + " (" + df.format(x.getShootingPercentage() * 100) + "%)" + ": 3pt " + (int)x.getThreesMade() + "/" + (int)(x.getThreesMade() + x.getThreesMissed()) + " (" + df.format(x.getThreePtPercentage() * 100) + ")\n");
                System.out.println(x.getName() + ": " + x.getPoints() + " Points" + ": shooting " + (int)x.getShotsMade() + "/" + (int)x.getShotsTaken() + " (" + df.format(x.getShootingPercentage() * 100) + "%)" + ": 3pt " + (int)x.getThreesMade() + "/" + (int)(x.getThreesMade() + x.getThreesMissed()) + " (" + df.format(x.getThreePtPercentage() * 100) + ")");
            }
            pw.close();
            System.exit(0);
        } catch (IOException fnfe) {
            System.out.println(fnfe);
            System.exit(0);
        }
    }
}
