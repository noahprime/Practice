import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeScreen extends JFrame implements ActionListener{
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton openButton;
    private JButton saveButton;
    private JScrollPane scroller;
    private JTextArea text;

    // Constructor
    public HomeScreen() {
        super("Shot Chart Creator");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initialize();
        setSize(700,700);
        // Follow instructions for Stage1
        // Follow instructions for Stage2 to call initialize()
    }

    // Initialize the GUI
    // Follow instructions for Stage 2
    private void initialize() {
        // Add a panel for the toolbar
        JPanel buttons = new JPanel();
        add(buttons,BorderLayout.NORTH);

        // Add buttons to the toolbar
        // Example: addButton(toolbar, "Cut");
        // Add button for "Cut"
        // Add button for "Copy"

        addButton(buttons,"New Game");
        addButton(buttons,"Compile");
        addButton(buttons,"Choose Roster");
        addButton(buttons,"Past Games");
        addButton(buttons,"Resume");

        //Add background image
        JPanel bkg = new JPanel();
        bkg.setSize(600,600);
        bkg.add(new DrawPanel(),BorderLayout.CENTER);
        add(bkg,BorderLayout.CENTER);


        // Add a text area within a scrolling pane by following instructions for Stage 4
        //text = new JTextArea();
        //scroller = new JScrollPane(text);
        //add (scroller,BorderLayout.CENTER);
        // Follow instructions for Stage 6
        // Add button for "Open"

        // Follow instructions for Stage 7
        // Add button for "Save"


    }

    // Add a button to a panel
    // Follow instructions for Stage 3
    private void addButton(JPanel panel, String label) {
        // Follow instructions for Stage 3
        JButton button = new JButton(label);
        panel.add(button);
        button.addActionListener(this);
    }

    // Event-handler for button-press
    // //Follow instructions for Stage 3
    public void actionPerformed(ActionEvent e) {
        // Follow instructions for Stage 3
        System.out.println(e.getActionCommand() + " pressed");
        // Follow instructions for Stage 5
        if (e.getActionCommand() == "New Game") {
            SetUp newGame = new SetUp();
        }
        else if (e.getActionCommand() == "Compile") { //--> Follow instructions for Stage
            text.copy();
        }
        else if (e.getActionCommand() == "Choose Roster") { //--> Follow instructions for Stage 5
            text.paste();
        }
        else if (e.getActionCommand() == "Past Games") { // ---> Follow instructions for Stage 6
            readFile();
        }
        else if (e.getActionCommand() == "Resume") { // ---> Follow instructions for Stage 7
            writeFile();
        }

    }

    // Read a file in Stage 6
    private void readFile() {
        // Follow instructions for Stage 6
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION){
            try{
                String filenname = chooser.getSelectedFile().getAbsolutePath();
                text.setText(new String(Files.readAllBytes(Paths.get(filenname))));
            }
            catch (IOException e){
                System.out.println("Cannot read the file " + e);
            }
        }
    }

    // Write to a file in Stage 7
    private void writeFile() {
        // Follow instructions for Stage 7
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION){
            try{
                String filename = chooser.getSelectedFile().getAbsolutePath();
                Files.write(Paths.get(filename),text.getText().getBytes());
            }
            catch (IOException e){
                System.out.println("Cannot write to file " + e);
            }
        }
    }

    public static void main(String[] args) {
        HomeScreen frame = new HomeScreen();
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            try {
                BufferedImage spurs = ImageIO.read(new File("/Users/noahprime/MyStuff/JavaPractice/ShotChart/src/court.png"));
                g.drawImage(spurs,0,0,600/2,1358/2,null);
            } catch (IOException io){
                System.err.println("Can't find image");
                System.exit(1);
            }
        }
    }

}
