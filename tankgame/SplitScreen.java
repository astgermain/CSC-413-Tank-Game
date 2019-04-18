package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class SplitScreen {
    TankGame game;
    private BufferedImage image;
    private int x, y;
    SplitScreen(int x, int y, TankGame game){
        this.x = x;
        this.y = y;
        this.game = game;
    }
    public void update(TankGame game) {
        this.game = game;
    }
    
    public void paint(Graphics g, int terrIndex){
        
        g.drawImage(game.getTerrainPiece(terrIndex), y - 16, x, null);                    
        
    }
}
