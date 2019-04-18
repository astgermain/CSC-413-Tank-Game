package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 *
 * @author anthony-pc
 */
public class Tank {

    private int x, y, vx, vy, sX, sY, camX, camY, width, height, health, lives, powerup;
    private final int r = 6;
    private short angle;
    private BufferedImage img;
    public boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    TankGame game;
    Collisions c;
    public AffineTransform whereDoesBulletturn;
    public AffineTransform whereDoesBulletturn2;
    public Tank(int x, int y, int vx, int vy, short angle, TankGame game) {
        this.x = 288;
        this.y = 448;
        //Marks static position of tank at center screen
        this.sX = this.x;
        this.sY = this.y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.width = 64;
        this.height = 64;
        this.health = 160;
        this.lives = 3;
        this.img = game.getTerrainPiece(3);
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    public int getSX() {
        return this.sX;
    }

    public int getSY() {
        return this.sY;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setAngle(short angle) {
        this.angle = angle;
    }
    public short getAngle() {
        return this.angle;
    }
    public void setHealth(int newHealth) {
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }
    public void setLives(int newLives) {
        this.lives = lives;
    }
    public int getLives() {
        return this.lives;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    
    public void paint(Graphics g) {
        Graphics2D graphic2D = (Graphics2D) g;
        
            //System.out.println(getX() + " " + getY());
        if(x + game.getYOffsetp2() > 608 || y + game.getXOffsetp2() > game.HEIGHT){
            AffineTransform rotationP2side = AffineTransform.getTranslateInstance(x + game.getYOffsetp2(), y + game.getXOffsetp2());
            rotationP2side.rotate(Math.toRadians(angle), this.img.getWidth() / 2, this.img.getHeight() / 2);
            graphic2D.drawImage(this.img, rotationP2side, null);
            for(int i = 0; i < this.health/10; i++){
                g.drawImage(game.getTerrainPiece(7), x + game.getYOffsetp2() + (i*4), y + game.getXOffsetp2() - 16, game);
            }
            for(int l = 0; l < this.lives; l++){
                g.drawImage(game.getTerrainPiece(8), x + game.getYOffsetp2() + (l*24), y + game.getXOffsetp2() + 68, game);
            }
            //System.out.println("Second tank: " + (x + game.getYOffsetp2()) + ", " + (y + game.getXOffsetp2()));
            whereDoesBulletturn2 = rotationP2side;
        }

        AffineTransform rotation = AffineTransform.getTranslateInstance(sX, sY);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2, this.img.getHeight() / 2);
        graphic2D.drawImage(this.img, rotation, null);
        //For drawing healthbar
        for(int j = 0; j < this.health/10; j++){
            g.drawImage(game.getTerrainPiece(7), sX + (j*4), sY - 16, game);
        }
        //For drawing lives
        for(int k = 0; k < this.lives; k++){
            g.drawImage(game.getTerrainPiece(8), sX + (k*24), sY + 68, game);
        }
        
        whereDoesBulletturn = rotation;
        //Shows where tank is on screen
        //System.out.println(this.toString());
    }

    
    public void update(TankGame game) {
        this.game = game;
        this.c = new Collisions();
        if(!c.tankNoProjectileCollide(game)){
            this.health -= 16;
            if(this.health == 0 && this.lives > 0){
                this.lives--;
                this.health = 160;
            }
            else if(this.health == 0 && this.lives == 0){
                //EXPLODE THE TANK HERE AND CALL GAME OVER P2 WINS
                
            }
            //System.out.println("Health is: " + this.health);
        }
        if (this.UpPressed && c.wallNoTankCollide(game) && c.tankNoTankCollide(game)) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed){
            this.rotateRight();
        }
        
        
    }

    private void rotateLeft() {
        this.angle -= 3;
    }

    private void rotateRight() {
        this.angle += 3;
    }

    private void moveBackwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        camX -= vx;
        camY -= vy;
        game.setXOffsetp1(-camY);
        game.setYOffsetp1(-camX);
    }

    public void moveForwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        camX += vx;
        camY += vy;
        game.setXOffsetp1(-camY);
        game.setYOffsetp1(-camX);
    }
    public void shoot(TankGame game){
        this.game = game;
        Projectile proj, projGhost;
        proj = new Projectile(null,x,y,powerup);
        projGhost = new Projectile(null,x ,y ,powerup);
        //Bullet start for projGhost
        //System.out.println((x) + ", " + (y));
        //System.out.println((x + game.getYOffsetp2()) + ", " + (y - game.getXOffsetp2()));
        game.projListP1.add(proj);
        game.projListP1Ghost.add(projGhost);
        //System.out.println("Shot");
    }

    private void checkBorder() {
        if (x < 0) {
            x = 0;
        }
        if (x >= 740) {
            x = 740;
        }
        if (y < 0) {
            y = 0;
        }
        if (y >= 720) {
            y = 720;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    
    
}
