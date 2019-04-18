package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tank2 {
    private int x, y, vx, vy, sX, sY, camX, camY, width, height, health, lives, powerup = 0;
    private final int r = 6;
    private short angle;
    private BufferedImage img;
    public boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean hasPowerUp;
    
    TankGame game;
    Collisions c;
    public Tank2(int x, int y, int vx, int vy, short angle, TankGame game) {
        this.x = 928;
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
        this.img = game.getTerrainPiece(4);
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

    public void setImg(BufferedImage img) {
        this.img = img;
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
        if(x + game.getYOffsetp1() < 608 || y + game.getXOffsetp1() > game.HEIGHT){
            AffineTransform rotation = AffineTransform.getTranslateInstance(x + game.getYOffsetp1(), y + game.getXOffsetp1());
            rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2, this.img.getHeight() / 2);
            graphic2D.drawImage(this.img, rotation, null);
            for(int i = 0; i < this.health/10; i++){
                g.drawImage(game.getTerrainPiece(7), x + game.getYOffsetp1() + (i*4), y + game.getXOffsetp1() - 16, game);
            }
            for(int l = 0; l < this.lives; l++){
            g.drawImage(game.getTerrainPiece(9), x + game.getYOffsetp1() + (l*24), y + game.getXOffsetp1() + 68, game);
        }
        }
        
        AffineTransform rotationP1side = AffineTransform.getTranslateInstance(sX, sY);
        rotationP1side.rotate(Math.toRadians(angle), this.img.getWidth() / 2, this.img.getHeight() / 2);
        graphic2D.drawImage(this.img, rotationP1side, null);
        //For drawing healthbar
        for(int j = 0; j < this.health/10; j++){
            g.drawImage(game.getTerrainPiece(7), sX + (j*4), sY - 16, game);
        }
        //For drawing lives
        for(int k = 0; k < this.lives; k++){
            g.drawImage(game.getTerrainPiece(9), sX + (k*24), sY + 68, game);
        }
        //Shows where tank is on screen
        //System.out.println(this.toString());
    }

    
    public void update(TankGame game) {
        this.game = game;
        this.c = new Collisions();
        if(!c.tank2NoProjectileCollide(game)){
            this.health -= 16;
            //Wrote and here instead of &&
            if(this.health == 0 && this.lives > 0){
                this.lives--;
                this.health = 160;
            }
            else if(this.health == 0 && this.lives == 0){
                //EXPLODE THE TANK HERE AND CALL GAME OVER P1 WINS
            }
            //System.out.println("Health is: " + this.health);
        }
            
        
        if (this.UpPressed && c.wallNoTankCollide2(game) && c.tankNoTankCollide2(game)) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed && c.wallNoTankCollide2(game)) {
            this.rotateLeft();
        }
        if (this.RightPressed && c.wallNoTankCollide2(game)) {
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
        game.setXOffsetp2(-camY);
        game.setYOffsetp2(-camX);
    }

    public void moveForwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        camX += vx;
        camY += vy;
        game.setXOffsetp2(-camY);
        game.setYOffsetp2(-camX);
    }
    
    public void shoot(TankGame game){
        Projectile proj, projGhost;
        proj = new Projectile(null,x,y,powerup);
        projGhost = new Projectile(null,x,y ,powerup);
        game.projListP2.add(proj);
        game.projListP2Ghost.add(projGhost);
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
        return "x=" + camX + ", y=" + camY + ", angle=" + angle;
    }
}
