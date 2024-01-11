package rhythm_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class RhythmGame extends JFrame { //GUI

    private Image screenImage; //This is for double buffering.
    private Graphics screenGraphic; //This is for double buffering.
    private ImageIcon button1 = new ImageIcon(Main.class.getResource("../source/picture/button1.jpg"));
    private ImageIcon button2 = new ImageIcon(Main.class.getResource("../source/picture/button2.jpg"));
    private Image introBackground = new ImageIcon(Main.class.getResource("../source/picture/introBackground.jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../source/picture/menuBar.jpg")));
    private JButton exitButton = new JButton(button1);

    private int mouseX, mouseY;

    public RhythmGame(){
        setUndecorated(true);
        setTitle("Rhythm Game"); //Set the tittle of program
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); // Set the size of program
        setResizable(false); //Fix the size of program
        setLocationRelativeTo(null); //Set the location of program at center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When user close the window, program will be closed.
        setVisible(true); //Set the program is visible.
        setBackground(new Color(0,0,0,0));
        setLayout(null);

        exitButton.setBounds(1245,0,30,30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);


        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(button2);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(button1);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        add(exitButton);

        menuBar.setBounds(0,0,1280,30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x-mouseX, y-mouseY);
            }
        });
        add(menuBar);

        //Make image instance for intro

        Music introMusic = new Music("ncs1.mp3",true);
        introMusic.start();
    }

    @Override
    public void paint(Graphics g){ //paint() will be activate at first
        screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //Set the image size
        screenGraphic = screenImage.getGraphics(); //Get the instance of screenImage
        screenDraw(screenGraphic);
        g.drawImage(screenImage,0,0,null);
    }

    public void screenDraw(Graphics g){ //Drawing the image on 0,0 position
        g.drawImage(introBackground,0,0,null);
        paintComponents(g);
        this.repaint(); //recall the paint() Method
    }
}
