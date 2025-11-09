package com.linas.RobotGame.objects;

import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Trap extends GameObject
{
    public Trap(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("trap");
        solid = false;
    }

    public Trap(Trap other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
    }

    @Override
    public GameObject copy()
    {
        return new Trap(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        AssetManager.getSound("explosionSound").play();
        gameWorld.removeGameObject(this);
        gameWorld.damagePlayer();
    }
}
