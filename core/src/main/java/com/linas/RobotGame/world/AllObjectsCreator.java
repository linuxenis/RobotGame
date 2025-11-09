package com.linas.RobotGame.world;

import com.linas.RobotGame.enums.*;
import com.linas.RobotGame.objects.*;
import com.linas.RobotGame.objects.Module;

public class AllObjectsCreator
{
    public static void create(GameWorld gameWorld)
    {
        //walls
        for (int i = -4; i <= 1; i++)
        {
            gameWorld.addGameObject(new Wall(i, -1, 1, 1));
        }
        for (int i = -4; i <= 5; i++)
        {
            gameWorld.addGameObject(new Wall(i, 6, 1, 1));
        }
        for (int i = -8; i <= -4; i++)
        {
            gameWorld.addGameObject(new Wall(i, 1, 1, 1));
            gameWorld.addGameObject(new Wall(i, 5, 1, 1));
        }
        for (int i = 2; i <= 6; i++)
        {
            gameWorld.addGameObject(new Wall(i, -4, 1, 1));
        }
        for (int i = 2; i <= 4; i++)
        {
            gameWorld.addGameObject(new Wall(-8, i, 1, 1));
        }
        for (int i = 0; i <= 4; i++)
        {
            gameWorld.addGameObject(new Wall(3, i, 1, 1));
        }
        for (int i = 0; i <= 5; i++)
        {
            gameWorld.addGameObject(new Wall(5, i, 1, 1));
        }
        for (int i = -3; i <= 1; i++)
        {
            gameWorld.addGameObject(new Wall(2, i, 1, 1));
        }
        for (int i = -3; i <= 0; i++)
        {
            gameWorld.addGameObject(new Wall(6, i, 1, 1));
        }
        gameWorld.addGameObject(new Wall(-4, 4, 1, 1));
        gameWorld.addGameObject(new Wall(-4, 2, 1, 1));
        gameWorld.addGameObject(new Wall(-4, 0, 1, 1));
        gameWorld.addGameObject(new Wall(-2, 0, 1, 1));
        gameWorld.addGameObject(new Wall(-2, 1, 1, 1));
        gameWorld.addGameObject(new Wall(-1, 1, 1, 1));
        gameWorld.addGameObject(new Wall(1, 1, 1, 1));

        //Doors
        gameWorld.addGameObject(new Door(0, 1, 1, 1));
        gameWorld.addGameObject(new Door(-4, 3, 1, 1));
        gameWorld.addGameObject(new Door(3, 5, 1, 1));
        gameWorld.addGameObject(new Door(4, 0, 1, 1));

        //Keys
        gameWorld.addGameObject(new Key(-1, 0, 1, 1));
        gameWorld.addGameObject(new Key(-6, 3, 1, 1));
        gameWorld.addGameObject(new Key(-3, 0, 1, 1));
        gameWorld.addGameObject(new Key(1, 5, 1, 1));

        //Modules
        gameWorld.addGameObject(new Module(1, 0, 1, 1, ModuleType.DAMAGEDHEALTH));
        gameWorld.addGameObject(new Module(2, 2, 1, 1, ModuleType.FULLHEALTH));
        gameWorld.addGameObject(new Module(0, 3, 1, 1, ModuleType.LIGHT));
        gameWorld.addGameObject(new Module(-3, 5, 1, 1, ModuleType.ENERGY));
        for (int i = -7; i <= -5; i++)
        {
            gameWorld.addGameObject(new Module(i, 4, 1, 1, ModuleType.FULLHEALTH));
        }
        for (int i = 2; i <= 3; i++)
        {
            gameWorld.addGameObject(new Module(-7, i, 1, 1, ModuleType.ENERGY));
        }
        for (int i = -6; i <= -5; i++)
        {
            gameWorld.addGameObject(new Module(i, 2, 1, 1, ModuleType.LIGHT));
        }

        //Traps
        for (int i = 1; i <= 5; i++)
        {
            gameWorld.addGameObject(new Trap(4, i, 1, 1));
        }
        for (int i = 1; i <= 2; i++)
        {
            gameWorld.addGameObject(new Trap(-3, i, 1, 1));
        }
        gameWorld.addGameObject(new Trap(-1, 0, 1, 1));
        gameWorld.addGameObject(new Trap(-2, 4, 1, 1));
        gameWorld.addGameObject(new Trap(1, 3, 1, 1));
        gameWorld.addGameObject(new Trap(0, 5, 1, 1));

        //Exits
        gameWorld.addGameObject(new Exit(4, -2, 1, 1));

        //Checkpoints
        gameWorld.addGameObject(new Checkpoint(0, 2, 1, 1));

    }
}
