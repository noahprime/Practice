
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Player {

    private JPanel panel;
    private String name;
    private String team;
    private String number;
    public ArrayList<int[]> shots = new ArrayList<>();
    private double points;
    private double shotsMade;
    private double shotsTaken;
    private double threesMade;
    private double threesMissed;
    private double twosMade;
    private double twosMissed;
    private double drawnFouls;
    private double shootingPercentage;
    private double freeThrowPercentage;
    private double threePtPercentage;
    private double twoPtPercentage;
    private double shotsBlocked;
    private double shotsGoaltended;
    private int offRebounds;
    private int defRebounds;
    private int fouls;
    private int steals;
    private int blocks;
    private int assists;
    private int freeThrowsMade;
    private int freeThrowsTaken;

    public Player(String team,String name,String number){
        this.team = team;
        this.number = number;
        this.name = name;
        this.points = 0;
        this.drawnFouls = 0;
        this.shootingPercentage = 0;
        this.shotsBlocked = 0;
        this.threesMade = 0;
        this.threesMissed = 0;
        this.twosMade = 0;
        this.twosMissed = 0;
        this.shotsGoaltended = 0;
        this.offRebounds = 0;
        this.defRebounds = 0;
        this.fouls = 0;
        this.steals = 0;
        this.blocks = 0;
        this.freeThrowsMade = 0;
        this.freeThrowsTaken = 0;
        this.assists = 0;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public int getFreeThrowsTaken() {
        return freeThrowsTaken;
    }

    public void upFreeThrowsMade() {
        freeThrowsMade++;
        setPoints();
    }

    public void downFreeThrowsMade() {
        freeThrowsMade--;
        setPoints();
    }

    public void upFreeThrowsTaken() {
        freeThrowsTaken++;
    }


    public void downFreeThrowsTaken() {
        freeThrowsTaken--;
    }

    public void setFreeThrowPercentage(){
      if(freeThrowsTaken == 0) {
        this.freeThrowPercentage = 0;
      }
      else{
        this.freeThrowPercentage = ((double)freeThrowsMade/freeThrowsTaken);
      }
    }

    public double getFreeThrowPercentage() {
      return freeThrowPercentage;
    }

    public int getBlocks() {
        return blocks;
    }

    public void upBlocks(){
        blocks++;
    }

    public void downBlocks(){
        blocks--;
    }

    public int getAssists() {
        return assists;
    }

    public void upAssists(){
        assists++;
    }

    public void downAssists(){
        assists--;
    }

    public int getSteals(){
        return steals;
    }

    public void upSteals(){
        steals++;
    }

    public void downSteals(){
        steals--;
    }

    public int getDefRebounds() {
        return defRebounds;
    }

    public int getOffRebounds() {
        return offRebounds;
    }

    public int getFouls() {
        return fouls;
    }

    public void upFouls() {
        fouls++;
    }

    public void downFouls() {
        fouls--;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getNumber() {
        return number;
    }

    public double getPoints(){
        return points;
    }

    public void setPoints(){
        this.points = 3*getThreesMade() + 2*getTwosMade() + getFreeThrowsMade();
    }

    public void upShotsMade(){
        this.shotsMade++;
        setShootingPercentage();
    }

    public void downShotsMade(){
        this.shotsMade--;
        setShootingPercentage();
    }

    public double getShotsMade(){
        return this.shotsMade;
    }

    public void upShotsTaken(){
        this.shotsTaken++;
        setShootingPercentage();
    }

    public void downShotsTaken(){
        this.shotsTaken--;
        setShootingPercentage();
    }

    public double getShotsTaken(){
        return this.shotsTaken;
    }

    public double getThreesMade() {
        return threesMade;
    }

    public void upThreesMade(){
        this.threesMade++;
        setThreePtPercentage();
        setShootingPercentage();
        setPoints();
    }

    public void downThreesMade(){
        this.threesMade--;
        setThreePtPercentage();
        setShootingPercentage();
        setPoints();
    }

    public double getThreesMissed(){
        return threesMissed;
    }

    public void upThreesMissed(){
        this.threesMissed++;
        setShootingPercentage();
        setThreePtPercentage();
        setPoints();
    }

    public void downThreesMissed(){
        this.threesMissed--;
        setShootingPercentage();
        setThreePtPercentage();
        setPoints();
    }

    public double getTwosMade() {
        return twosMade;
    }

    public void upTwosMade(){
        this.twosMade++;
        setTwoPtPercentage();
        setShootingPercentage();
        setPoints();
    }

    public void downTwosMade(){
        this.twosMade--;
        setTwoPtPercentage();
        setShootingPercentage();
        setPoints();
    }

    public double getTwosMissed(){
        return twosMissed;
    }

    public void upTwosMissed(){
        this.twosMissed++;
        setShootingPercentage();
        setTwoPtPercentage();
    }

    public void downTwosMissed(){
        this.twosMissed--;
        setShootingPercentage();
        setTwoPtPercentage();
        setPoints();
    }

    public double getShootingPercentage() {
        return shootingPercentage;
    }

    public void setShootingPercentage() {
      if((getThreesMissed() + getTwosMissed() + getTwosMade() + getThreesMade()) == 0) {
        this.shootingPercentage = 0;
      }
      else{
        this.shootingPercentage = (getThreesMade() + getTwosMade())/(getThreesMissed() + getTwosMissed() + getTwosMade() + getThreesMade());
      }
    }

    public double getTwoPtPercentage() {
        return twoPtPercentage;
    }

    public void setTwoPtPercentage(){
      if((getTwosMissed() + getTwosMade()) == 0){
        this.twoPtPercentage = 0;
      }
      else{
        this.twoPtPercentage = getTwosMade()/(getTwosMissed() + getTwosMade());
      }
    }

    public double getThreePtPercentage() {
        return threePtPercentage;
    }

    public void setThreePtPercentage(){
      if((getThreesMissed() + getThreesMade()) == 0) {
        this.threePtPercentage = 0;
      }
      else{
        this.threePtPercentage = getThreesMade()/(getThreesMissed() + getThreesMade());
      }
    }

    public double getDrawnFouls() {
        return drawnFouls;
    }

    public void upDrawnFouls(){
        this.drawnFouls++;
    }

    public void downDrawnFouls(){
        this.drawnFouls--;
    }

    public double getShotsBlocked() {
        return shotsBlocked;
    }

    public void upBlockedShots(){
        this.shotsBlocked++;
    }

    public void downBlockedShots(){
        this.shotsBlocked--;
    }

    public double getShotsGoaltended() {
        return shotsGoaltended;
    }

    public void upShotsGoaltended(){
        shotsGoaltended++;
    }

    public ArrayList<int[]> getShots() {
        return shots;
    }

    public void addShot(int x, int y, int t){
        int[] shot = {x,y,t};
        shots.add(shot);
    }

    public void removeShot(int x, int y){
      for(int[] i:shots){
        if(i[0] == x && i[1] == y){
          shots.remove(i);
          break;
        }
      }
    }

    public void upOffRebounds(){
        offRebounds++;
    }

    public void downOffRebounds(){
        offRebounds--;
    }

    public void upDefRebounds(){
        defRebounds++;
    }

    public void downDefRebounds(){
        defRebounds--;
    }

    public void dump(String file){

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

        for(int[] x:shots){
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
            ImageIO.write(image, "png", new File(file + "/" + getName() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
