package tankgame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.lang.*;
import java.util.logging.*;
import javax.imageio.ImageIO;



//I ENDED UP NOT USING THIS
/// PLEASE IGNORE









public class GameWorld extends JApplet implements Runnable{
    private Thread thread;
    private final GameEvent geobv;
    private Graphics2D g2d;
    private String room_caption;
    final int gameScreenWidth = 1280;
    final int gameScreenHeight = 960;
    private int score, lives, mouse_x, mouse_y, room_width, room_height;
    /* Maybe not private */
    int speed = 1, move = 0;
    private BufferedImage bg, title, lagreEx, smallEx, p1Tank, p2Tank, ubWall, bWall, img;
    private Image bullet, rocket, bouncing, p1Shield, p2Shield, powerup;
    private static Canvas canvas;
    
    public GameWorld(){
       
        this.geobv = new GameEvent();
    
    }
    
  
    
    public void init() {
       
        this.setFocusable(true);
        setBackground(Color.white);
        this.setVisible(true);
		
	this.add(new Map());
        //setTitle("Tank Rotation");
        
        
        try {
            BufferedImage i = ImageIO.read(new File("src/tankgame/Resources/Tank1.gif"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        

        //this.geobv.addObserver(t1);
        
        this.setSize(gameScreenWidth, gameScreenHeight);
                
        //this.setResizable(false);
        //setLocationRelativeTo(null); 
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    
    public static void main(String[] args){
        Thread x;
        GameWorld inst = new GameWorld();
        inst.init();

        try {
            while (true) {
                inst.geobv.setChanged();
                inst.geobv.notifyObservers();
                Thread.sleep(1000/144);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFrame f = new JFrame("Tank Game");
        f.addWindowListener(new WindowAdapter() {
        });
        f.getContentPane().add("Center", inst);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        inst.start();
    }
   
    public void start() {
        System.out.println();
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }
    @Override
    public void run() {
    	
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();  
          try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }

    
}
