package tankgame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

//I REALIZED HOW MUCH EASIER THIS COULD BE WITHOUT CANVAS
//*****************Some Notes About The Game***************
// 1) Tank collision with other tanks and walls doesn't work 100% if you back into things
//
// 2) I ran out of time so I panicked and chose not to implement a file reader to build the map
//    Even though it probably would have taken less time
//
// 3) I panicked and made public variable where I shouldn't have because I felt like writing
//    getters and setters would take to long for some reason.
//
// 4) Another thing I wanted to fix was in the Collisions file, I used a 3 nested for loop
//    to go through all the terrain objects and check them with the projectiles for intersection
//    I later realized I was looping through objects that weren't even relevant and wanted
//    to put all my created objects  in an arraylist and check it so it ran faster
//
// 5) My background doesn't move with the tank because when I set it too, I get image
//    tearing where there was no image before and couldn't find a fix for it besides
//    rendering the image on a larger scale which I felt could be inefficient
//    As well, the bg draws everytime I paint which I feel could have been made to paint
//    just once as the background.
//
// 6) There are random comments everywhere sorry
//
// 7) Couldn't get random black and green stuff out of pictures so the collision 
//    sizes are set to include it.
// 8) I included just a few breakable walls
// 9) Did not get to powerups and minimap, power ups would have been easy just
//    create a terrain piece, change a tank value and maybe an image, and set a timer
//    then make it disappear
//***MOST IMPORTANT****
//*******************
//*******************
// 10) I only used code from TankRotationExample as you said we oould use it as a 
//     starting point, and I used Airstrike for reference if I ran into any big walls
// 11) Some classes are not implemented but I'm keeping them just in case
public class TankGame extends Canvas implements Runnable {

    private BufferedImage bg, bWall, uWall, sWall, tank, tank2, tankLive, tank2Live, proj, hb, expl = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    BufferedImage[] bimg = new BufferedImage[11];

    JFrame frame;
    private int xOffset1, yOffset1, xOffset2, yOffset2;
    public ArrayList<Projectile> projListP1, projListP2, projListP1Ghost, projListP2Ghost;
    public static boolean running = false;
    private int speed = 6;
    public static final String TITLE = "Tank Game";
    Tank t1;
    Tank2 t2;
    SplitScreen ss;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;
    int xMaxRange = (WIDTH / 2);
    public static final Dimension gameDim = new Dimension(WIDTH, HEIGHT);
    private int mapSize = 2;
    private int mapSizeX = mapSize * WIDTH;
    private int mapSizeY = mapSize * HEIGHT;
    private int terrainSize = 32;
    public int numTerrX = (mapSize * WIDTH) / terrainSize;
    public int numTerrY = (mapSize * HEIGHT) / terrainSize;
    public int bgX = ((WIDTH) / 320) + 1;
    public int bgY = ((HEIGHT) / 240) + 1;
    int xOffsetMax = (mapSize * WIDTH);
    int yOffsetMax = (mapSize * HEIGHT);
    Terrain terrArr[][] = new Terrain[numTerrX][numTerrY + 20];
    Terrain2 terrArr2[][] = new Terrain2[numTerrX][numTerrY + 20];
    private ArrayList<Terrain> terrList;
    private ArrayList<Terrain2> terr2List;
    SplitScreen ssArr[][] = new SplitScreen[numTerrX + 1][numTerrY + 1];
    public static boolean canShoot, canShoot2, isAlive = true, isAlive2 = true, left, right, down, up, tankL, tankR, tankD, tankU, tankP, tankS, tank2L, tank2R, tank2D, tank2U, tank2P, tank2S;
    MapControl MC = new MapControl();
    TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    Collisions c = new Collisions();
    Thread t;
    public AffineTransform whereDoesBulletturn;
    public AffineTransform whereDoesBulletturn2;

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (t == me) {
            update();
            paint();
            try {
                t.sleep(1000 / 144);
            } catch (InterruptedException e) {
                break;
            }

        }

    }

    public synchronized void start() {
        running = true;
        t = new Thread(this);
        t.start();

    }

    public synchronized void stop() {
        running = false;
        System.exit(0);
    }

    public TankGame() {

        try {
            bg = ImageIO.read(new File("src/tankgame/Resources/Background.bmp"));
            tank = ImageIO.read(new File("src/tankgame/Resources/Tank1.gif"));
            tank2 = ImageIO.read(new File("src/tankgame/Resources/Tank2.gif"));
            bWall = ImageIO.read(new File("src/tankgame/Resources/Wall1.gif"));
            uWall = ImageIO.read(new File("src/tankgame/Resources/Wall2.gif"));
            sWall = ImageIO.read(new File("src/tankgame/Resources/Wall3.gif"));
            proj = ImageIO.read(new File("src/tankgame/Resources/Shell.gif"));
            hb = ImageIO.read(new File("src/tankgame/Resources/healthbar.gif"));
            tankLive = ImageIO.read(new File("src/tankgame/Resources/Tank1Live.gif"));
            tank2Live = ImageIO.read(new File("src/tankgame/Resources/Tank2Live.gif"));
            expl = ImageIO.read(new File("src/tankgame/Resources/Explosion_large.gif"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        bimg[0] = bg;
        bimg[1] = bWall;
        bimg[2] = uWall;
        bimg[3] = tank;
        bimg[4] = tank2;
        bimg[5] = sWall;
        bimg[6] = proj;
        bimg[7] = hb;
        bimg[8] = tankLive;
        bimg[9] = tank2Live;
        bimg[10] = expl;

        setMinimumSize(gameDim);
        setMaximumSize(gameDim);
        setPreferredSize(gameDim);
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.addKeyListener(MC);
        this.addKeyListener(tc1);
        this.addKeyListener(tc2);
        init();

        requestFocus();
    }

    public void init() {

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                canShoot = true;
                canShoot2 = true;

            }
        }, 200, 200);
        //CHANGE TO ARRAYLIST
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 20; j++) {
                terrArr[i][j] = new Terrain(i * 32, j * 32, this, true, 2);
            }
        }
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 20; j++) {
                terrArr2[i][j] = new Terrain2(i * 32, j * 32, this, true, 2);
            }
        }
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 1; j++) {
                ssArr[i][j] = new SplitScreen(i * 32, j * 32, this);
            }
        }
        this.terrList = new ArrayList<Terrain>();
        this.terr2List = new ArrayList<Terrain2>();
        this.projListP1 = new ArrayList<Projectile>();
        this.projListP2 = new ArrayList<Projectile>();
        this.projListP1Ghost = new ArrayList<Projectile>();
        this.projListP2Ghost = new ArrayList<Projectile>();
        //Adjust spawn values of tank and stuff here********HERE*********
        t1 = new Tank(0, 0, 0, 0, (short) 0, this);
        t2 = new Tank2(0, 0, 0, 0, (short) 0, this);

    }

    public void update() {
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 20; j++) {
                terrArr[i][j].update(this);
            }
        }
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 20; j++) {

                terrArr2[i][j].update(this);
            }
        }
        for (int i = 0; i < numTerrX; i++) {
            for (int j = 0; j < numTerrY + 1; j++) {
                ssArr[i][j].update(this);
            }
        }
        
        terrArr[10][10].update(this);
        terrArr[11][10].update(this);
        terrArr[12][10].update(this);
       
        terrArr[10][11].update(this);
        terrArr[10][12].update(this);
        terrArr[10][13].update(this);
        terrArr[10][14].update(this);
       
        terrArr[10][10].setBreakable(true);
        terrArr[11][10].setBreakable(true);
        terrArr[12][10].setBreakable(true);
       
        terrArr[10][11].setBreakable(true);
        terrArr[10][12].setBreakable(true);
        terrArr[10][13].setBreakable(true);
        terrArr[10][14].setBreakable(true);
       
        terrArr[10][10].setImage(bimg[1]);
        terrArr[11][10].setImage(bimg[1]);
        terrArr[12][10].setImage(bimg[1]);
       
        terrArr[10][11].setImage(bimg[1]);
        terrArr[10][12].setImage(bimg[1]);
        terrArr[10][13].setImage(bimg[1]);
        terrArr[10][14].setImage(bimg[1]);
        
        
        terrArr2[10][10].update(this);
        terrArr2[11][10].update(this);
        terrArr2[12][10].update(this);
        
        terrArr2[10][11].update(this);
        terrArr2[10][12].update(this);
        terrArr2[10][13].update(this);
        terrArr2[10][14].update(this);
    
        terrArr2[10][10].setBreakable(true);
        terrArr2[11][10].setBreakable(true);
        terrArr2[12][10].setBreakable(true);
     
        terrArr2[10][11].setBreakable(true);
        terrArr2[10][12].setBreakable(true);
        terrArr2[10][13].setBreakable(true);
        terrArr2[10][14].setBreakable(true);
        
        terrArr2[10][10].setImage(bimg[1]);
        terrArr2[11][10].setImage(bimg[1]);
        terrArr2[12][10].setImage(bimg[1]);
        
        terrArr2[10][11].setImage(bimg[1]);
        terrArr2[10][12].setImage(bimg[1]);
        terrArr2[10][13].setImage(bimg[1]);
        terrArr2[10][14].setImage(bimg[1]);
        
        
        
        
        t1.update(this);
        t2.update(this);
        updateProjectilesP1(t1);
        updateProjectilesP1Ghost(t1);
        updateProjectilesP2(t2);
        updateProjectilesP2Ghost(t2);
        tank1Control();
        tank2Control();
        whereDoesBulletturn = t1.whereDoesBulletturn;
        whereDoesBulletturn2 = t1.whereDoesBulletturn2;
    }

    private void tank1Control() {
        if (isAlive) {
            if (tankU) {
                t1.toggleUpPressed();
            } else if (!(tankU)) {
                t1.unToggleUpPressed();
            } else {
                t1.unToggleUpPressed();

            }
            if (tankD) {
                t1.toggleDownPressed();
            } else if (!(tankD)) {
                t1.unToggleDownPressed();
            } else {
                t1.unToggleDownPressed();
            }
            if (tankL) {
                t1.toggleLeftPressed();
            } else if (!(tankL)) {
                t1.unToggleLeftPressed();
            } else {
                t1.unToggleLeftPressed();
            }
            if (tankR) {
                t1.toggleRightPressed();
            } else if (!(tankR)) {
                t1.unToggleRightPressed();
            } else {
                t1.unToggleRightPressed();
            }
            if (tankS && canShoot) {
                t1.shoot(this);
                canShoot = false;
            }
        }

    }

    private void tank2Control() {
        if (isAlive2) {
            if (tank2U) {
                t2.toggleUpPressed();
            } else if (!(tank2U)) {
                t2.unToggleUpPressed();
            }
            if (tank2D) {
                t2.toggleDownPressed();
            } else if (!(tank2D)) {
                t2.unToggleDownPressed();
            }
            if (tank2L) {
                t2.toggleLeftPressed();
            } else if (!(tank2L)) {
                t2.unToggleLeftPressed();
            }
            if (tank2R) {
                t2.toggleRightPressed();
            } else if (!(tank2R)) {
                t2.unToggleRightPressed();
            }
            if (tank2S && canShoot2) {
                t2.shoot(this);
                canShoot2 = false;

            }
            /*if((tank2P)){
            proj.toggleFire();
        }
        else if(!(tank2P)){
            proj.toggleFire();
        }
             */
        }
    }

    public void paint() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // Draws the background ***NOT IDEAL DRAWS REPETITVELY***

        for (int x = 0; x < bgX; x++) {
            for (int y = 0; y < bgY; y++) {
                //ADDING OFFSET MAKES BG MOVE BUT WITH MASSIVE TEARING

                g.drawImage(bg, x * bg.getWidth(), y * bg.getHeight(), bg.getWidth(), bg.getHeight(), null);

            }
        }

        //Draws the outer boundaries for Player 1 and Player 2
        drawBorderP1(g);
        drawBorderP2(g);
        drawMap(g);
        //

        //Draws both tanks
        t1.paint(g);
        t2.paint(g);
        drawProjectilesP1(g);
        drawProjectilesP1Ghost(g);
        drawProjectilesP2(g);
        drawProjectilesP2Ghost(g);
        splitScreen(g, 5);
        checkGameOver();
        g.dispose();
        bs.show();

        //System.out.println(this.toString());
    }

    public void drawBorderP1(Graphics g) {
        for (int i = 0; i < numTerrX; i++) {
            terrList.add(terrArr[i][0]);
            terrArr[i][0].paint(g);
        }
        for (int j = 0; j < numTerrY + 20; j++) {
            terrList.add(terrArr[0][j]);
            terrArr[0][j].paint(g);
        }
        for (int k = 0; k < numTerrX; k++) {
            terrList.add(terrArr[k][numTerrY + 19]);
            terrArr[k][numTerrY + 19].paint(g);
        }
        for (int l = 0; l < numTerrY + 20; l++) {
            terrList.add(terrArr[numTerrX - 1][l]);
            terrArr[numTerrX - 1][l].paint(g);
        }

    }

    public void drawBorderP2(Graphics g) {
        for (int i = 0; i < numTerrX; i++) {
            terr2List.add(terrArr2[i][0]);
            terrArr2[i][0].paint(g);
        }
        for (int j = 0; j < numTerrY + 20; j++) {
            terr2List.add(terrArr2[0][j]);
            terrArr2[0][j].paint(g);
        }
        for (int k = 0; k < numTerrX; k++) {
            terr2List.add(terrArr2[k][numTerrY + 19]);
            terrArr2[k][numTerrY + 19].paint(g);
        }
        for (int l = 0; l < numTerrY + 20; l++) {
            terr2List.add(terrArr2[numTerrX - 1][l]);
            terrArr2[numTerrX - 1][l].paint(g);
        }

    }
    public void drawMap(Graphics g){
        
        //I realize this would be easier and better with a text file but I
        //Simply ran out of time
        terrList.add(terrArr[10][10]);
        terrArr[10][10].paint(g);
        terrList.add(terrArr[11][10]);
        terrArr[11][10].paint(g);
        terrList.add(terrArr[12][10]);
        terrArr[12][10].paint(g);
       
        terrList.add(terrArr[10][11]);
        terrArr[10][11].paint(g);
        terrList.add(terrArr[10][12]);
        terrArr[10][12].paint(g);
        terrList.add(terrArr[10][13]);
        terrArr[10][13].paint(g);
        terrList.add(terrArr[10][14]);
        terrArr[10][14].paint(g);
        terrList.add(terrArr[14][11]);
     
        
        terr2List.add(terrArr2[10][10]);
        terrArr2[10][10].paint(g);
        terr2List.add(terrArr2[11][10]);
        terrArr2[11][10].paint(g);
        terr2List.add(terrArr2[12][10]);
        terrArr2[12][10].paint(g);
     
        terr2List.add(terrArr2[10][11]);
        terrArr2[10][11].paint(g);
        terr2List.add(terrArr2[10][12]);
        terrArr2[10][12].paint(g);
        terr2List.add(terrArr2[10][13]);
        terrArr2[10][13].paint(g);
        terr2List.add(terrArr2[10][14]);
        terrArr2[10][14].paint(g);
        terr2List.add(terrArr2[14][11]);
 
    }

    public void splitScreen(Graphics g, int terrIndex) {
        for (int m = 0; m < numTerrY; m++) {
            ssArr[m][(656 / 32)].paint(g, terrIndex);
        }
    }

    public void updateProjectilesP1Ghost(Tank tank) {

        for (int i = 0; i < projListP1Ghost.size(); i++) {

            if (projListP1Ghost.get(i).getX() > mapSizeX || projListP1Ghost.get(i).getY() > mapSizeY
                    || projListP1Ghost.get(i).getX() < -mapSizeX || projListP1Ghost.get(i).getY() < -mapSizeY) {

            } else if ((projListP1Ghost.get(i)).isVisible() || i == 0) {

                //System.out.println(projListP1.get(i).getOX() + getYOffsetp2() + ", " + (projListP1.get(i).getOY() + getXOffsetp2()));
                projListP1Ghost.get(i).update(0, 0, tank.getAngle(), getXOffsetp2(), getYOffsetp2());
                //projListP1.get(i).setVisible(true);
                projListP1Ghost.get(i).setProj(bimg[6]);
            } else {
            }
        }

    }

    public void updateProjectilesP2Ghost(Tank2 tank2) {

        for (int i = 0; i < projListP2Ghost.size(); i++) {

            if (projListP2Ghost.get(i).getX() > mapSizeX || projListP2Ghost.get(i).getY() > mapSizeY
                    || projListP2Ghost.get(i).getX() < -mapSizeX || projListP2Ghost.get(i).getY() < -mapSizeY) {

                projListP2Ghost.remove(i);
            } else if ((projListP2Ghost.get(i)).isVisible() || i == 0) {

                //System.out.println(projListP2Ghost.get(i).getOX() + getYOffsetp2() + ", " + (projListP2Ghost.get(i).getOY() + getXOffsetp2()));
                projListP2Ghost.get(i).update(0, 0, tank2.getAngle(), getXOffsetp1(), getYOffsetp1());
                //projListP1.get(i).setVisible(true);
                projListP2Ghost.get(i).setProj(bimg[6]);
            } else {
                projListP2Ghost.remove(i);
            }
        }

    }

    public void updateProjectilesP1(Tank tank) {

        for (int i = 0; i < projListP1.size(); i++) {
            if (c.uWallNoProjectileCollide2(this)) {
            } else if (projListP1.get(i).getX() > mapSizeX || projListP1.get(i).getY() > mapSizeY
                    || projListP1.get(i).getX() < -mapSizeX || projListP1.get(i).getY() < -mapSizeY) {
            } else if ((projListP1.get(i)).isVisible()) {
                //System.out.println(tank.getSX() + ", " + tank.getSY());
                projListP1.get(i).update(tank.getSX(), tank.getSY(), tank.getAngle(), xOffset1, yOffset1);
                projListP1.get(i).setProj(bimg[6]);
            } else {
            }
        }
    }

    public void updateProjectilesP2(Tank2 tank2) {

        for (int i = 0; i < projListP2.size(); i++) {
            if (!(c.uWallNoProjectileCollide(this))) {

            } //System.out.println(projListP2.get(i).getX());
            else if (projListP2.get(i).getX() > mapSizeX || projListP2.get(i).getY() > mapSizeY
                    || projListP2.get(i).getX() < -mapSizeX || projListP2.get(i).getY() < -mapSizeY) {
                projListP2.remove(i);
            } else if ((projListP2.get(i)).isVisible()) {
                //System.out.println(tank2.getSX() + ", " + tank2.getSY());
                projListP2.get(i).update(tank2.getSX(), tank2.getSY(), tank2.getAngle(), xOffset2, yOffset2);
                projListP2.get(i).setProj(bimg[6]);
            } else {
            }
        }

    }

    public void drawProjectilesP1Ghost(Graphics g) {
        for (int i = 0; i < projListP1Ghost.size(); i++) {
            if (projListP1Ghost.get(i).getX() > xMaxRange - 16 && projListP1Ghost.get(i).getY() > 0
                    && projListP1Ghost.get(i).getX() < WIDTH + 16 && projListP1Ghost.get(i).getY() > -16) {

                if ((projListP1Ghost.get(i)).isVisible()) {

                    projListP1Ghost.get(i).paint(g, this);
                } else {
                    projListP1Ghost.remove(i);
                }
            }
        }
    }

    public void drawProjectilesP2Ghost(Graphics g) {

        for (int i = 0; i < projListP2Ghost.size(); i++) {

            if (projListP2Ghost.get(i).getX() < xMaxRange - 16 && projListP2Ghost.get(i).getY() < 976
                    && projListP2Ghost.get(i).getX() > -16 && projListP2Ghost.get(i).getY() > -16) {
                if ((projListP2Ghost.get(i)).isVisible()) {

                    projListP2Ghost.get(i).paint(g, this);
                } else {
                    projListP2Ghost.remove(i);
                }
            }
        }

    }

    public void drawProjectilesP1(Graphics g) {

        for (int i = 0; i < projListP1.size(); i++) {

            if (projListP1.get(i).getX() < xMaxRange - 16 && projListP1.get(i).getY() < 976
                    && projListP1.get(i).getX() > -16 && projListP1.get(i).getY() > -16) {
                if ((projListP1.get(i)).isVisible()) {
                    projListP1.get(i).paint(g, this);
                } else {
                    projListP1.remove(i);
                }
            }
        }
    }

    public void drawProjectilesP2(Graphics g) {

        for (int i = 0; i < projListP2.size(); i++) {

            if (projListP2.get(i).getX() > xMaxRange - 16 && projListP2.get(i).getY() < 976
                    && projListP2.get(i).getX() < WIDTH + 16 && projListP2.get(i).getY() > -16) {
                if ((projListP2.get(i)).isVisible()) {
                    projListP2.get(i).paint(g, this);
                } else {
                    projListP2.remove(i);
                }
            }
        }
    }

    public void drawExplosion() {

    }

    public void checkGameOver() {
        if (t1.getHealth() == 0 && t1.getLives() == 0) {
            t1.setImg(bimg[10]);
            
        }
        if (t2.getHealth() == 0 && t2.getLives() == 0) {
            t2.setImg(bimg[10]);
            
        }
    }

    @Override
    public String toString() {
        return "x1=" + xOffset1 + ", y1=" + yOffset1 + " " + "x2=" + xOffset2 + ", y2=" + yOffset2;
    }

    public int getXOffsetp1() {
        return xOffset1;
    }

    public int getYOffsetp1() {
        return yOffset1;
    }

    public void setXOffsetp1(int xoSet) {
        this.xOffset1 = xoSet;
    }

    public void setYOffsetp1(int yoSet) {
        this.yOffset1 = yoSet;
    }

    public int getXOffsetp2() {
        return xOffset2;
    }

    public int getYOffsetp2() {
        return yOffset2;
    }

    public Terrain getTerrList(int index) {
        return this.terrList.get(index);
    }

    public Terrain2 getTerr2List(int index) {
        return this.terr2List.get(index);
    }

    public int terrListSize() {
        return terrList.size();
    }

    public int terr2ListSize() {
        return terr2List.size();
    }

    public void setXOffsetp2(int xoSet) {
        this.xOffset2 = xoSet;
    }

    public void setYOffsetp2(int yoSet) {
        this.yOffset2 = yoSet;
    }

    public void addTerrList(Terrain terr) {
        terrList.add(terr);
    }

    public void addTerr2List(Terrain2 terr) {
        terr2List.add(terr);
    }

    public BufferedImage getTerrainPiece(int index) {
        return bimg[index];
    }

}
