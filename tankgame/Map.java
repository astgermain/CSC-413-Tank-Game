package tankgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JPanel {
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
 /*   
    
    private BufferedImage view;
    private int[] pixels;
        
    public Map(){
        //Create a BufferedImage that will represent our view.
		view = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);


		//Create an array for pixels
		pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
    }
    public void render(Graphics graphics)
	{
		graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}

	//Render our image to our array of pixels.
	public void renderImage(BufferedImage image, int xPosition, int yPosition, int xZoom, int yZoom)
	{
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderArray(imagePixels, image.getWidth(), image.getHeight(), xPosition, yPosition, xZoom, yZoom);
	}
        public void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPosition, int yPosition, int xZoom, int yZoom) 
	{
		for(int y = 0; y < renderHeight; y++)
			for(int x = 0; x < renderWidth; x++)
				for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++)
					for(int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++)
						setPixel(renderPixels[x + y * renderWidth], (x * xZoom) + xPosition + xZoomPosition, ((y * yZoom) + yPosition + yZoomPosition));
	}
        private void setPixel(int pixel, int x, int y) 
	{
		
	}
    private BufferedImage bg;
    private Graphics2D g2d;
    public void init(){
        try {
            bg = ImageIO.read(GameWorld.class.getResource("Resources/Background.bmp"));
        } 
        catch (Exception e) {
            System.out.println("Cannot find a resource");
        }
    }
    
    public void createMap(){
        int tileWidth = bg.getWidth(this);
        int tileHeight = bg.getHeight(this);
        int NumberX = (int) (1280 / tileWidth);
        int NumberY = (int) (960 / tileHeight);
        System.out.println(bg);
        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                int move = 0;
                
                g2d.drawImage(bg, j * tileWidth, 
                        i * tileHeight + (move % tileHeight), tileWidth, 
                        tileHeight, this);
            }
        }
    }
    
    public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Image picture = Toolkit.getDefaultToolkit().getImage("Resources/Background.bmp");
    g2.drawImage(picture, 10, 10, this);
    g2.finalize();
  }
    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }
*/
}
