package com.linas.RobotGame.objects;

import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Wall extends GameObject
{
    public Wall(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("wall");
        solid = true;
    }

    public Wall(Wall other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
    }

    @Override
    public GameObject copy()
    {
        return new Wall(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {

    }
}
