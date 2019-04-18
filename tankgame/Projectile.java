package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Projectile {
    //Checks if bullet is in play
    boolean visible, gotAngle, gotStart;
    int pXSpeed, pYSpeed, damage, projType, x, y, oX, oY, pAngle, r;
    BufferedImage image;
    TankGame game;
    Collisions c;
    
    public Projectile(BufferedImage image, int x, int y, int projType){
        this.visible = true;
        this.x = x;
        this.y = y;
        this.oX = x;
        this.oY = y;
        this.r = 18;
        this.image = image;
        this.pXSpeed = 1;
        this.pYSpeed = 1;
        this.gotAngle = false;
        this.gotStart = false;
        switch (projType) {
            case 0:
                this.damage = 0;
                break;
            case 1:
                this.damage = 0;
                break;
            default:
                this.damage = 1;
                break;
        }
    }
    public int getX(){
        return this.x;
    }
    public int getOX(){
        return this.oX;
    }
    public int getOY(){
        return this.oY;
    }
    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setProj(BufferedImage img){
        this.image = img;
    }
    public BufferedImage getProj(){
        return this.image;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getDamage(){
        return this.damage;
    }
    public boolean isVisible(){
        return this.visible;
    }
    public void setVisible(boolean bl){
        this.visible = bl;
    }
    public void paint(Graphics g, TankGame game){
        int xMaxRange = (game.WIDTH / 2);
        
        //First if makes sure bullet from p1 side goes to p2 side or over extend the length of y
        
        //System.out.println("Bullet is here: " + this.x + ", " + this.y);    
        Graphics2D graphic2D = (Graphics2D) g;
        if(isVisible()){
            AffineTransform rotation = AffineTransform.getTranslateInstance(x + 19, y + 19);
            rotation.rotate(Math.toRadians(pAngle), 0, 0);
            //graphic2D.drawImage(image, game.whereDoesBulletturn, null);
            //game.whereDoesBulletturn.getTranslateX() SOMETHIKNG WITH THIS
            //System.out.println(pAngle);
            graphic2D.drawImage(image, rotation, null);
            //System.out.println("Bullet is here: " + this.x + ", " + this.y);
        }
    }
    public void update(int x, int y, short angle, int offsetx, int offsety){
        this.game = game;
       
        if(!(gotAngle)){
            this.pAngle = angle;
            pXSpeed = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            pYSpeed = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            gotAngle = true;
        }
        
        this.oX += pXSpeed;
        this.oY += pYSpeed;
         //this.x = this.x + 1;
        
            this.x = oX + offsety;
            this.y = oY + offsetx;
        
       
        //OPTIMIZATION DELETE BULLET OBJECT AFTER TRAVELING SO FAR BECAUSE IT IS STILL UPDATING
        //System.out.println("Bullet is here: " + this.x + ", " + this.y);
        
    }
    public void update(short angle, int offsetx, int offsety){
        this.game = game;
       
        if(!(gotAngle)){
            this.pAngle = angle;
            pXSpeed = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            pYSpeed = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            gotAngle = true;
        }
        
        this.oX += pXSpeed;
        this.oY += pYSpeed;
         //this.x = this.x + 1;
        
            this.oX = oX + offsety;
            this.oY = oY + offsetx;
        
       
        //OPTIMIZATION DELETE BULLET OBJECT AFTER TRAVELING SO FAR BECAUSE IT IS STILL UPDATING
        //System.out.println("Bullet is here: " + this.x + ", " + this.y);
        
    }
}
