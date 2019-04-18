package tankgame;

import java.util.*;

public class GameEvent extends Observable{
    
    public GameEvent() {
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
        
}

