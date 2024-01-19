package rhythm_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class RhythmGame extends JFrame { //GUI

    private Image screenImage; //This is for double buffering.
    private Graphics screenGraphic; //This is for double buffering.
    private ImageIcon button1 = new ImageIcon(Main.class.getResource("../source/picture/button1.jpg"));
    private ImageIcon button2 = new ImageIcon(Main.class.getResource("../source/picture/button2.jpg"));
    private Image background = new ImageIcon(Main.class.getResource("../source/picture/introBackground.jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../source/picture/menuBar.jpg")));
    private JButton cloaseButton = new JButton(button1);
    private ImageIcon startButton1 = new ImageIcon(Main.class.getResource("../source/picture/startButton1.jpg"));
    private ImageIcon startButton2 = new ImageIcon(Main.class.getResource("../source/picture/startButton2.jpg"));
    private ImageIcon exitButton1 = new ImageIcon(Main.class.getResource("../source/picture/exitButton1.jpg"));
    private ImageIcon exitButton2 = new ImageIcon(Main.class.getResource("../source/picture/exitButton2.jpg"));
    private JButton startButton = new JButton(startButton1);
    private JButton exitButton = new JButton(exitButton1);

    private ImageIcon rightButtonImg = new ImageIcon(Main.class.getResource("../source/picture/rightButton.jpg"));
    private ImageIcon leftButtonImg = new ImageIcon(Main.class.getResource("../source/picture/leftButton.jpg"));
    private JButton rightButton = new JButton(rightButtonImg);
    private JButton leftButton = new JButton(leftButtonImg);

    private ImageIcon normalButtonImg = new ImageIcon(Main.class.getResource("../source/picture/normalButton.jpg"));
    private ImageIcon hardButtonImg = new ImageIcon(Main.class.getResource("../source/picture/hardButton.jpg"));
    private JButton normalButton = new JButton(normalButtonImg);
    private JButton hardButton = new JButton(hardButtonImg);

    private ImageIcon backButtonImg = new ImageIcon(Main.class.getResource("../source/picture/backButton.jpg"));
    private JButton backButton = new JButton(backButtonImg);

    ArrayList<Track> trackList = new ArrayList<Track>();

    private Image selectedImage;
    private Music selectedMusic;
    private int nowSelected = 0;

    private int mouseX, mouseY;

    private boolean isMainScreen = false;

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

        //Make image instance for intro
        Music introMusic = new Music("ncs1.mp3",true);
        introMusic.start();

        trackList.add(new Track("music1.jpg","background.jpg","music1.mp3","music1.mp3"));
        trackList.add(new Track("music2.jpg","background.jpg","music2.mp3","music2.mp3"));
        trackList.add(new Track("music3.jpg","background.jpg","music3.mp3","music3.mp3"));
        System.out.println(trackList.size());

        //Menu Bar Button
        cloaseButton.setBounds(1245,0,30,30);
        cloaseButton.setBorderPainted(false);
        cloaseButton.setContentAreaFilled(false);
        cloaseButton.setFocusPainted(false);
        cloaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cloaseButton.setIcon(button2);
                cloaseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                cloaseButton.setIcon(button1);
                cloaseButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        add(cloaseButton);

        //Start Button
        startButton.setBounds(40,200,509,144);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButton2);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                startButton.setIcon(startButton1);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                //Game event
                startButton.setVisible(false);
                exitButton.setVisible(false);
                leftButton.setVisible(true);
                rightButton.setVisible(true);
                normalButton.setVisible(true);
                hardButton.setVisible(true);
                background = new ImageIcon(Main.class.getResource("../source/picture/backGround.jpg")).getImage();
                isMainScreen = true;
                introMusic.close();
                selectTrack(0);
            }
        });
        add(startButton);

        //Exit Button
        exitButton.setBounds(40,400,509,144);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButton2);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(exitButton1);
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

        //Right Button
        rightButton.setVisible(false);
        rightButton.setBounds(1080,310,60,60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonImg);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                rightButton.setIcon(rightButtonImg);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                selectRight();
            }
        });
        add(rightButton);

        //Left Button
        leftButton.setVisible(false);
        leftButton.setBounds(140,310,60,60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonImg);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                leftButton.setIcon(leftButtonImg);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                selectLeft();
            }
        });
        add(leftButton);

        //Back Button
        backButton.setVisible(false);
        backButton.setBounds(20,50,60,60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonImg);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                backButton.setIcon(backButtonImg);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                backMain();
            }
        });
        add(backButton);

        //Menu Bar
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

        //Normal Button
        normalButton.setVisible(false);
        normalButton.setBounds(375,580,250,67);
        normalButton.setBorderPainted(false);
        normalButton.setContentAreaFilled(false);
        normalButton.setFocusPainted(false);
        normalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                normalButton.setIcon(normalButtonImg);
                normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                normalButton.setIcon(normalButtonImg);
                normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                gameStart(nowSelected, "normal");
            }
        });
        add(normalButton);

        //Hard Button
        hardButton.setVisible(false);
        hardButton.setBounds(655,580,250,67);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonImg);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e){
                hardButton.setIcon(hardButtonImg);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                Music buttonMusic = new Music("clickSound.mp3",false);
                buttonMusic.start();
                gameStart(nowSelected, "hard");
            }
        });
        add(hardButton);
    }

    @Override
    public void paint(Graphics g){ //paint() will be activate at first
        screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //Set the image size
        screenGraphic = screenImage.getGraphics(); //Get the instance of screenImage
        screenDraw(screenGraphic);
        g.drawImage(screenImage,0,0,null);
    }

    public void screenDraw(Graphics g){ //Drawing the image on 0,0 position
        g.drawImage(background,0,0,null);
        if (isMainScreen){
            g.drawImage(selectedImage,340,100,null);
        }
        paintComponents(g);
        this.repaint(); //recall the paint() Method
    }

    public void selectTrack(int nowSelected){
        if (selectedMusic != null){
            selectedMusic.close();
        }
        selectedImage = new ImageIcon(Main.class.getResource("../source/picture/" + trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music (trackList.get(nowSelected).getStartMusic(),true);
        selectedMusic.start();
    }

    public void selectLeft(){
        if (nowSelected == 0){
            nowSelected = trackList.size() - 1;
        }else {
            nowSelected--;
        }
        selectTrack(nowSelected);
    }

    public void selectRight(){
        if (nowSelected == trackList.size() - 1){
            nowSelected = 0;
        }else {
            nowSelected++;
        }
        selectTrack(nowSelected);
    }

    public void gameStart(int nowSelected, String diff){
        if (selectedMusic != null){
            selectedMusic.close();
        }
        isMainScreen = false;
        rightButton.setVisible(false);
        leftButton.setVisible(false);
        normalButton.setVisible(false);
        hardButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("../source/picture/" + trackList.get(nowSelected).getGameImage())).getImage();
        backButton.setVisible(true);
    }

    public void backMain(){
        isMainScreen = true;
        rightButton.setVisible(true);
        leftButton.setVisible(true);
        normalButton.setVisible(true);
        hardButton.setVisible(true);
        background = new ImageIcon(Main.class.getResource("../source/picture/backGround.jpg")).getImage();
        backButton.setVisible(false);
        selectTrack(nowSelected);
    }
}

