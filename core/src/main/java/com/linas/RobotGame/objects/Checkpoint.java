package com.linas.RobotGame.objects;

import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Checkpoint extends GameObject
{
    public Checkpoint(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("checkpoint");
        solid = false;
    }

    public Checkpoint(Checkpoint other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
    }

    @Override
    public GameObject copy()
    {
        return new Checkpoint(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        AssetManager.getSound("pickupSound").play();
        playerState.setEnergy(playerState.getMaxEnergy());
        gameWorld.removeGameObject(this);
        gameWorld.saveGame();
    }


}
