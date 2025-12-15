package com.linas.RobotGame.objects;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.linas.RobotGame.world.*;

public abstract class GameObject implements Copyable<GameObject>
{
    protected GridPoint2 coordinates;
    protected float width, height;
    protected Texture texture;
    protected boolean solid;

    public GameObject(int x, int y, float width, float height)
    {
        this.coordinates = new GridPoint2(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public abstract GameObject copy();

    public abstract void interact(GameWorld gameWorld, PlayerState playerState);

    public GridPoint2 getCoordinates()
    {
        return coordinates;
    }

    public float getWidth()
    {
        return width;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public boolean isSolid()
    {
        return solid;
    }

    public boolean isPassable(PlayerState playerState)
    {
        return !solid;
    }
}
