
import java.io.File;
import java.util.Scanner;

public class SetUp {


    public static void main(String[] args){

        System.out.print("Enter home team names sep by space: ");
        Scanner sc = new Scanner(System.in);
        String homeNames = sc.nextLine();
        System.out.print("Enter player numbers in same order sep by space: ");
        String homeNums = sc.nextLine();

        String[] homePlayers = homeNames.split(" ");
        String[] homeNumbers = homeNums.split(" ");
        Player[] playersHome = new Player[homePlayers.length];
        for(int i = 0;i < homePlayers.length;i++){
            playersHome[i] = new Player("H",homePlayers[i],homeNumbers[i]);
        }

        System.out.print("Enter away team names sep by space: ");
        String awayString = sc.nextLine();
        System.out.print("Enter player numbers in same order sep by space: ");
        String awayNums = sc.nextLine();

        String[] awayPlayers = awayString.split(" ");
        String[] awayNumbers = awayNums.split(" ");
        Player[] playersAway = new Player[awayPlayers.length];
        for(int i = 0;i < awayPlayers.length;i++){
            playersAway[i] = new Player("A",awayPlayers[i],awayNumbers[i]);
        }

        InputFrame obj = new InputFrame(playersHome,playersAway);
    }

    public SetUp(){
        System.out.print("Enter home team names followed by number sep by space: ");
        Scanner sc = new Scanner(System.in);
        String namesString = sc.nextLine();

        String[] playerNames = namesString.split(" ");
        Player[] playersHome = new Player[playerNames.length];
        for(int i = 0;i < playerNames.length;i+=2){
            playersHome[i] = new Player("H",playerNames[i],playerNames[i+1]);
        }

        System.out.print("Enter away team names followed by number sep by space: ");
        String awayString = sc.nextLine();

        String[] awayPlayers = awayString.split(" ");
        Player[] playersAway = new Player[awayPlayers.length];
        for(int i = 0;i < awayPlayers.length;i+=2){
            playersAway[i] = new Player("A",awayPlayers[i],awayPlayers[i+1]);
        }

        InputFrame obj = new InputFrame(playersHome,playersAway);
    }
}
