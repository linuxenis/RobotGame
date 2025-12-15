package com.linas.RobotGame.objects;

import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Door extends GameObject
{
    private boolean closed = true;

    public Door(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("doorClosed");
        solid = true;
    }

    public Door(Door other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
        this.closed = other.closed;
    }

    @Override
    public GameObject copy()
    {
        return new Door(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        if (!closed) return;
        closed = false;
        solid = false;
        texture = AssetManager.getTexture("doorOpen");
        AssetManager.getSound("unlockSound").play();

        playerState.setKey(false);
    }

    public boolean isPassable(PlayerState playerState)
    {
        if(closed)
        {
            return playerState.hasKey();
        }
        else
        {
            return !solid;
        }
    }
}
