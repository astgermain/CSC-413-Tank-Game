package tankgame;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 *
 * @author A
 */
public class TankControl extends Observable implements KeyListener{
    private Tank t1;
    private Tank2 t2;
    private final int up, down, right, left;
    
    TankGame game;

    public TankControl(Tank t1, int up, int down, int left, int right) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }
    public TankControl(Tank2 t2, int up, int down, int left, int right) {
        this.t2 = t2;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (keyCode == VK_UP) {
            TankGame.tank2U = true;
        }
        if (keyCode == VK_DOWN) {
            TankGame.tank2D = true;
        }
        if (keyCode == VK_LEFT) {
            TankGame.tank2L = true;
        }
        if (keyCode == VK_RIGHT) {
            TankGame.tank2R = true;
        }
        if(keyCode == ke.VK_ENTER) {
            TankGame.tank2S = true;
        }
        if(keyCode == ke.VK_A) {
            TankGame.tankL = true;
        }
        if(keyCode == ke.VK_D) {
            TankGame.tankR = true;
        }
        if(keyCode == ke.VK_S) {
            TankGame.tankD = true;
        }
        if(keyCode == ke.VK_W) {
            TankGame.tankU = true;
        }
        if(keyCode == ke.VK_SPACE) {
            TankGame.tankS = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (keyCode  == VK_UP) {
            TankGame.tank2U = false;
        }
        if (keyCode == VK_DOWN) {
            TankGame.tank2D = false;
        }
        if (keyCode  == VK_LEFT) {
            TankGame.tank2L = false;
        }
        if (keyCode  == VK_RIGHT) {
            TankGame.tank2R = false;
        }
        if(keyCode == ke.VK_ENTER) {
            TankGame.tank2S = false;
        }
        if(keyCode == ke.VK_A) {
            TankGame.tankL = false;
        }
        if(keyCode == ke.VK_D) {
            TankGame.tankR = false;
        }
        if(keyCode == ke.VK_S) {
            TankGame.tankD = false;
        }
        if(keyCode == ke.VK_W) {
            TankGame.tankU = false;
        }
        if(keyCode == ke.VK_SPACE) {
            TankGame.tankS = false;
        }
    }
}
