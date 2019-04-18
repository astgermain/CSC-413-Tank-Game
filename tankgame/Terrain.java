package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Terrain {
    private int x, y, oX, oY;
    private boolean solid, breakable, visible;
    private BufferedImage image;
    TankGame game;
    Terrain(int x, int y, TankGame game, boolean isVisible, int terrIndex){
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
        this.x = oX + game.getXOffsetp1();
        this.y = oY + game.getYOffsetp1();
        
        
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public int getX(){
        return this.y;
    }
    public int getY(){
        return this.x;
    }
    public boolean isSolid(){
        return this.solid;
    }
    public boolean isBreakable(){
        return this.breakable;
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
        
        //First part makes sure x renders a bit before
        if(this.x >= -32 && this.y < xMaxRange - 16 && visible){
            g.drawImage(this.image, this.y, this.x, null);
            solid = true;
        }    
        
    
          
            
            
        
        //System.out.println("This is where Terrain is: " + x + ", " + y);
        
        
        
        /*
        //To load only part of screen
        if(x > -32 && ((x < TankGame.WIDTH - game.xOffset + 32)||(x < TankGame.WIDTH + 32)) && y > -32 && ((y < TankGame.HEIGHT - game.yOffset + 32)||(y < TankGame.WIDTH + 32))){
            //g.setColor(Color.BLACK);
            //g.fillRect(x,y,32,32);
            //g.drawImage(game.uWall, y, x, null);
        
            //g.setColor(Color.WHITE);
            //g.fillRect(x,y,31,31);
        }  
        */
    }
}
