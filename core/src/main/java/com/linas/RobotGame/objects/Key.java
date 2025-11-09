package com.linas.RobotGame.objects;

import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Key extends GameObject
{
    public Key(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("key");
        solid = false;
    }

    public Key(Key other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
    }

    @Override
    public GameObject copy()
    {
        return new Key(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        if (!playerState.hasKey())
        {
            AssetManager.getSound("pickupSound").play();
            playerState.setKey(true);
            gameWorld.removeGameObject(this);
        }
    }
}
