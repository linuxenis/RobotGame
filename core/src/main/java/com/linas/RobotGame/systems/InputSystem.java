package com.linas.RobotGame.systems;

import com.badlogic.gdx.*;
import com.linas.RobotGame.enums.*;

public class InputSystem
{
    public static Direction moveDirection()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            return Direction.RIGHT;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            return Direction.LEFT;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            return Direction.UP;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            return Direction.DOWN;
        }
        return null;
    }
}
