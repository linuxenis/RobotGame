package com.linas.RobotGame.objects;

import com.badlogic.gdx.*;
import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class
Exit extends GameObject
{
    public Exit(int x, int y, float width, float height)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture("exit");
        solid = false;
    }

    public Exit(Exit other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
    }

    @Override
    public GameObject copy()
    {
        return new Exit(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        System.out.println("YOU WON!");
        Gdx.app.exit();
    }
}
