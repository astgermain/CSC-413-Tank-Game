package tankgame;


import java.awt.image.BufferedImage;

public interface GameObject{
  
    public int getX();
    public int getY();
    public int getHspeed();
    public int getVspeed();
    public int getSpeed();
    public int getWidth();
    public int getHeight();
    public short getAngle();
    public BufferedImage getImage();
    public boolean isVisible();
    public boolean isSolid();
    public void setX(int x);
    public void setY(int y);
    public void setHspeed(int hspeed);
    public void setVspeed(int vspeed);
    public void setSpeed(int speed);
    public void setAngle(short angle);
    public void setAngleLeft(short angle);
    public void setAngleRight(short angle);
    public void setWidth(int width);
    public void setHeight(int height);
    public void setImage(BufferedImage image);
    
       
    
    
    
}
