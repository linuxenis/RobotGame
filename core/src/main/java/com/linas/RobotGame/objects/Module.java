package com.linas.RobotGame.objects;

import com.linas.RobotGame.enums.*;
import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class Module extends GameObject
{
    public static final int ENERGY_INCREMENT = 25;
    private ModuleType moduleType;

    public Module(int x, int y, float width, float height, ModuleType moduleType)
    {
        super(x, y, width, height);
        texture = AssetManager.getTexture(moduleType.name().toLowerCase() + "Module");
        solid = false;
        this.moduleType = moduleType;
    }

    public Module(Module other)
    {
        super(other.coordinates.x, other.coordinates.y, other.width, other.height);
        texture = other.texture;
        solid = other.solid;
        this.moduleType = other.moduleType;
    }

    @Override
    public GameObject copy()
    {
        return new Module(this);
    }

    public void interact(GameWorld gameWorld, PlayerState playerState)
    {
        if (playerState.getModulesCount() < PlayerState.MAX_MODULES)
        {
            AssetManager.getSound("pickupSound").play();
            playerState.addModule(moduleType);

            if (moduleType == ModuleType.LIGHT)
            {
                playerState.increaseVisibilityLevel();
            }
            else if (moduleType == ModuleType.ENERGY)
            {
                playerState.increaseMaxEnergy();
            }
            gameWorld.removeGameObject(this);
        }
    }
}
