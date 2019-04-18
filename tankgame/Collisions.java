package tankgame;

import java.awt.List;
import java.awt.Rectangle;
import java.util.Arrays;

public class Collisions {
    TankGame game;
    int x, y, x2, y2;
    private boolean collision;
    
    public boolean wallNoTankCollide(TankGame game){
        this.game = game;
        boolean it = true;
        //int i = (int)game.whereDoesBulletturn.getTranslateX() - game.getYOffsetp1();
        //int j = (int)game.whereDoesBulletturn.getTranslateY() - game.getXOffsetp1();
        //System.out.println("x = " + (int)game.whereDoesBulletturn.getTranslateX() + " y = " + (int)game.whereDoesBulletturn.getTranslateY());
        
        //Original logic (game.whereDoesBulletturn.getTranslateX() + game.getXOffsetp1() > game.terrArr[0][0].getX() + 32 + 64 + 2
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY() + 32 + 64 + 2) 
                //|| (game.whereDoesBulletturn.getTranslateX() < game.terrArr[0][0].getX()
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY())
        
        for(int k = 0; k < game.numTerrX; k++){
            for(int l = 0; l < game.numTerrY + 20; l++){
                
                if(game.terrArr[k][l].isSolid() || game.terrArr2[k][l].isSolid()){
                    
                    
                    x = game.terrArr[k][l].getX();
                    y = game.terrArr[k][l].getY();
                    
                    int altX = x - game.getYOffsetp1();
                    int altY = y - game.getXOffsetp1();
                    
                    //Use TranslateX and Y to get exact corners
                    Rectangle p1_box = new Rectangle(game.t1.getX(), game.t1.getY(), 64, 64);
                    Rectangle terr_box = new Rectangle(altX, altY, 33, 33);
                    if(p1_box.intersects(terr_box)){
                        
                        
                      
                         it = false;
                    }
                    
                }
            }
        }
                
        
        
        
        return it;
        
        
    }
    public boolean wallNoTankCollide2(TankGame game){
        this.game = game;
        boolean it = true;
        //int i = (int)game.whereDoesBulletturn.getTranslateX() - game.getYOffsetp1();
        //int j = (int)game.whereDoesBulletturn.getTranslateY() - game.getXOffsetp1();
        //System.out.println("x = " + i + " y = " + j);
        
        //Original logic (game.whereDoesBulletturn.getTranslateX() + game.getXOffsetp1() > game.terrArr[0][0].getX() + 32 + 64 + 2
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY() + 32 + 64 + 2) 
                //|| (game.whereDoesBulletturn.getTranslateX() < game.terrArr[0][0].getX()
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY())
        
        for(int k = 0; k < game.numTerrX; k++){
            for(int l = 0; l < game.numTerrY + 20; l++){
                
                if(game.terrArr[k][l].isSolid() || game.terrArr2[k][l].isSolid()){
                    
                    
                    
                    x2 = game.terrArr2[k][l].getX();
                    y2 = game.terrArr2[k][l].getY();
                    
                    int altX2 = x2 - game.getYOffsetp2();
                    int altY2 = y2 - game.getXOffsetp2();
                    
                    Rectangle p2_box = new Rectangle(game.t2.getX(), game.t2.getY(), 64, 64);
                    Rectangle terr2_box = new Rectangle(altX2, altY2, 32, 32);
                    
                    if(p2_box.intersects(terr2_box)){
                        
                        
                        //System.out.println("blocked2");
                        it = false;
                    }
                }
            }
        }
                
        
        
        
        return it;
        
        
    }
    
    
    public boolean tankNoTankCollide(TankGame game){
        
        Rectangle p2_box = new Rectangle(game.t2.getX(), game.t2.getY(), 64, 64);
        Rectangle p1_box = new Rectangle(game.t1.getX(), game.t1.getY(), 64, 64);
        if(p1_box.intersects(p2_box)){
            return false;
        }
        
        return true;
    }
    public boolean tankBackNoTankCollide(TankGame game){
        
        Rectangle p2_box = new Rectangle(game.t2.getX(), game.t2.getY(), 64, 64);
        Rectangle p1_box = new Rectangle(game.t1.getX(), game.t1.getY(), 64, 64);
        if(p1_box.intersects(p2_box)){
            return false;
        }
        
        return true;
    }
    public boolean uWallNoProjectileCollide(TankGame game){
        //List<String> terrArr2AsList = Arrays.asList(game.terrArr[i]);
        //List<String> Arr2AsList = Arrays.asList(game.terrArr[i]);
        this.game = game;
        boolean it = true;
        //int i = (int)game.whereDoesBulletturn.getTranslateX() - game.getYOffsetp1();
        //int j = (int)game.whereDoesBulletturn.getTranslateY() - game.getXOffsetp1();
        //System.out.println("x = " + i + " y = " + j);
        
        //Original logic (game.whereDoesBulletturn.getTranslateX() + game.getXOffsetp1() > game.terrArr[0][0].getX() + 32 + 64 + 2
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY() + 32 + 64 + 2) 
                //|| (game.whereDoesBulletturn.getTranslateX() < game.terrArr[0][0].getX()
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY())
        
        for(int k = 0; k < game.numTerrX; k++){
            for(int l = 0; l < game.numTerrY + 20; l++){
                
                if(game.terrArr[k][l].isSolid() || game.terrArr2[k][l].isSolid()){
                    
                    
                    
                    x2 = game.terrArr2[k][l].getX();
                    y2 = game.terrArr2[k][l].getY();
                    
                    int altX2 = x2 - game.getYOffsetp2();
                    int altY2 = y2 - game.getXOffsetp2();
                    for(int m = 0; m < game.projListP2.size(); m++){
                        Rectangle projP2_box = new Rectangle(game.projListP2.get(m).getOX(), game.projListP2.get(m).getOY(), 24, 24);
                        Rectangle terr2_box = new Rectangle(altX2, altY2, 32, 32);
                        if(projP2_box.intersects(terr2_box)){
                                game.projListP2.remove(m);
                                game.projListP2Ghost.remove(m);
                            if(game.terrArr2[k][l].isBreakable()){
                                game.terrArr2[k][l].setVisible(false);
                                game.terrArr2[k][l].setSolid(false);
                                game.terrArr[k][l].setVisible(false);
                                game.terrArr[k][l].setSolid(false);
                                
                            }
                            
                                return false;
                            
                        
                        //System.out.println("blocked2");
                        
                    }
                    }
                   
                    
                    
                }
            }
        }
          
        return true;
    }
    
    public boolean uWallNoProjectileCollide2(TankGame game){
        //List<String> terrArr2AsList = Arrays.asList(game.terrArr[i]);
        //List<String> Arr2AsList = Arrays.asList(game.terrArr[i]);
        this.game = game;
        boolean it = true;
        
        //int i = (int)game.whereDoesBulletturn.getTranslateX() - game.getYOffsetp1();
        //int j = (int)game.whereDoesBulletturn.getTranslateY() - game.getXOffsetp1();
        //System.out.println("x = " + i + " y = " + j);
        
        //Original logic (game.whereDoesBulletturn.getTranslateX() + game.getXOffsetp1() > game.terrArr[0][0].getX() + 32 + 64 + 2
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY() + 32 + 64 + 2) 
                //|| (game.whereDoesBulletturn.getTranslateX() < game.terrArr[0][0].getX()
                //&& game.whereDoesBulletturn.getTranslateY() > game.terrArr[0][0].getY())
        
        for(int k = 0; k < game.numTerrX; k++){
            for(int l = 0; l < game.numTerrY + 20; l++){
                collision = false;
                if(game.terrArr[k][l].isSolid() || game.terrArr2[k][l].isSolid()){
                    
                    
                    
                    x2 = game.terrArr[k][l].getX();
                    y2 = game.terrArr[k][l].getY();
                    
                    int altX2 = x2 - game.getYOffsetp1();
                    int altY2 = y2 - game.getXOffsetp1();
                    for(int m = 0; m < game.projListP1.size(); m++){
                        Rectangle projP2_box = new Rectangle(game.projListP1.get(m).getOX(), game.projListP1.get(m).getOY(), 24, 24);
                        Rectangle terr2_box = new Rectangle(altX2, altY2, 32, 32);
                        if(projP2_box.intersects(terr2_box)){
                            game.projListP1.remove(m);
                            game.projListP1Ghost.remove(m);
                            collision = true;
                            if(game.terrArr[k][l].isBreakable()){
                                game.terrArr2[k][l].setVisible(false);
                                game.terrArr2[k][l].setSolid(false);
                                game.terrArr[k][l].setVisible(false);
                                game.terrArr[k][l].setSolid(false);
                                
                            }
                            
                        return collision;
                        //System.out.println("blocked2");
                        
                    }
                    }
                    
                    
                    
                }
            }
        }
          
        return collision;
    }
    public boolean bWallNoProjectileCollide(TankGame game){
        
        
        return true;
    }
    //Give bullets values for each tank, if bullet value != tank value it hits
    //Give bullets width and height
    public boolean tankNoProjectileCollide(TankGame game){
        this.game = game;
        for(int i = 0; i < game.projListP2.size(); i++){
            Rectangle projP2_box = new Rectangle(game.projListP2.get(i).getOX(), game.projListP2.get(i).getOY(), 16, 16);
            Rectangle p1_box = new Rectangle(game.t1.getX(), game.t1.getY(), 64, 64);
            //System.out.println("Projectile from p2 is here: " + game.projListP2.get(i).getX() + " , " + game.projListP2.get(i).getY());
            //System.out.println("P1 tank is here: " + game.t1.getX() + " , " + game.t1.getY());
            if(projP2_box.intersects(p1_box)){
                //System.out.println("Shot!");
                game.projListP2.remove(i);
                return false;
            }
        }
        //Optimization note** can probably just remove ghost proj when reg proj is removed
        for(int i = 0; i < game.projListP2Ghost.size(); i++){
            Rectangle projP2_box = new Rectangle(game.projListP2Ghost.get(i).getOX(), game.projListP2Ghost.get(i).getOY(), 16, 16);
            Rectangle p1_box = new Rectangle(game.t1.getX(), game.t1.getY(), 64, 64);
            //System.out.println("Projectile from p2 is here: " + game.projListP2.get(i).getX() + " , " + game.projListP2.get(i).getY());
            //System.out.println("P1 tank is here: " + game.t1.getX() + " , " + game.t1.getY());
            if(projP2_box.intersects(p1_box)){
                //System.out.println("Shot!");
                game.projListP2Ghost.remove(i);
                return false;
            }
        }
        
        return true;
    }
    
    public boolean tank2NoProjectileCollide(TankGame game){
        this.game = game;
        for(int j = 0; j < game.projListP1.size(); j++){
            Rectangle projP1_box = new Rectangle(game.projListP1.get(j).getOX(), game.projListP1.get(j).getOY(), 16, 16);
            Rectangle p2_box = new Rectangle(game.t2.getX(), game.t2.getY(), 64, 64);
            //System.out.println("Projectile from p2 is here: " + game.projListP2.get(i).getX() + " , " + game.projListP2.get(i).getY());
            //System.out.println("P1 tank is here: " + game.t1.getX() + " , " + game.t1.getY());
            if(projP1_box.intersects(p2_box)){
                //System.out.println("Shot!");
                game.projListP1.remove(j);
                return false;
            }
        }
        for(int j = 0; j < game.projListP1Ghost.size(); j++){
            Rectangle projP1_box = new Rectangle(game.projListP1Ghost.get(j).getOX(), game.projListP1Ghost.get(j).getOY(), 16, 16);
            Rectangle p2_box = new Rectangle(game.t2.getX(), game.t2.getY(), 64, 64);
            //System.out.println("Projectile from p2 is here: " + game.projListP2.get(i).getX() + " , " + game.projListP2.get(i).getY());
            //System.out.println("P1 tank is here: " + game.t1.getX() + " , " + game.t1.getY());
            if(projP1_box.intersects(p2_box)){
                //System.out.println("Shot!");
                game.projListP1Ghost.remove(j);
                return false;
            }
        }
        
        return true;
    }
    
    
}
