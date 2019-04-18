package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Terrain2 {
    private int x, y, oX, oY;
    private BufferedImage image;
    private boolean solid, breakable, visible;
    TankGame game;
    Terrain2(int x, int y, TankGame game, boolean isVisible, int terrIndex){
        this.oX = x;
        this.oY = y;
        this.game = game;
        solid = false;
        breakable = false;
        this.visible = isVisible;
        this.image = game.getTerrainPiece(terrIndex);
    }

    public void update(TankGame game){
        this.game = game;   
        //Deals with placement of terrain
        x = oX + game.getXOffsetp2();
        y = oY + game.getYOffsetp2();
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public boolean isSolid(){
        return this.solid;
    }
    public boolean isBreakable(){
        return this.breakable;
    }
    public int getX(){
        return this.y;
    }
    public int getY(){
        return this.x;
    }
    public void setSolid(boolean solid){
        this.solid = solid;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void setBreakable(boolean breakable){
        this.breakable = breakable;
    }
    public void paint(Graphics g){
        int xMaxRange = (game.WIDTH / 2);
        //if(x > -32 && ((x < TankGame.WIDTH - game.getXOffsetp2() + 32)||(x < TankGame.WIDTH + 32)) 
        //&& y > -32 && ((y < TankGame.HEIGHT - game.getYOffsetp2() + 32)||(y < TankGame.WIDTH + 32))){
        //}
       
        
        if(y > 624 && visible){
            g.drawImage(this.image, y, x, null); 
            solid = true;
        }  
            
        
    }
}
