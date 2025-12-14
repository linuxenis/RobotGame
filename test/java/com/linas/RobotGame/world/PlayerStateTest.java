package com.linas.RobotGame.world;

import com.badlogic.gdx.math.GridPoint2;
import com.linas.RobotGame.enums.Direction;
import com.linas.RobotGame.enums.ModuleType;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerStateTest
{

    @Test
    public void test_addCoordinates()
    {
        PlayerState player = new PlayerState(0, 0);

        player.addCoordinates(new GridPoint2(1, -1));

        assertEquals(1, player.getCoordinates().x);
        assertEquals(-1, player.getCoordinates().y);
    }

    @Test
    public void test_spendMovementEnergy()
    {
        PlayerState player = new PlayerState();
        int initialEnergy = player.getEnergy();

        player.spendMovementEnergy();

        assertEquals("Spending movement should reduce energy by 1",
            initialEnergy - 1, player.getEnergy());
    }

    @Test
    public void test_addModule()
    {
        PlayerState player = new PlayerState();

        player.addModule(ModuleType.LIGHT);

        assertEquals("Module count should increment", 1, player.getModulesCount());
        assertEquals("Module list should contain LIGHT", ModuleType.LIGHT, player.getModules().get(0));
    }

    @Test
    public void test_TakeDamage_FullHealthModule_ConvertsToDamaged()
    {
        PlayerState player = new PlayerState();
        player.addModule(ModuleType.FULLHEALTH);

        player.takeDamage();

        assertEquals("Count should not decrease for FULLHEALTH damage", 1, player.getModulesCount());
        assertEquals("Module should convert to DAMAGEDHEALTH",
            ModuleType.DAMAGEDHEALTH, player.getModules().get(0));
    }

    @Test
    public void test_TakeDamage_EnergyModule_ReducesMaxEnergy()
    {
        PlayerState player = new PlayerState();

        int initialMax = player.getMaxEnergy();
        player.setEnergy(initialMax);

        player.addModule(ModuleType.ENERGY);

        player.takeDamage();

        assertEquals("Module should be removed", 0, player.getModulesCount());
        assertEquals("Max Energy should decrease by 25", initialMax - 25, player.getMaxEnergy());
        assertEquals("Current Energy should be capped to new Max", initialMax - 25, player.getEnergy());
    }

    @Test
    public void test_TakeDamage_LightModule_ReducesVisibility()
    {
        PlayerState player = new PlayerState();
        int initialVis = player.getVisibilityLevel();
        player.addModule(ModuleType.LIGHT);

        player.takeDamage();

        assertEquals("Module should be removed", 0, player.getModulesCount());
        assertEquals("Visibility should decrease", initialVis - 1, player.getVisibilityLevel());
    }

    @Test
    public void test_TakeDamage_DamagedHealth_JustRemoves()
    {
        PlayerState player = new PlayerState();

        player.addModule(ModuleType.DAMAGEDHEALTH);

        player.takeDamage();

        assertEquals("Module should be removed", 0, player.getModulesCount());
    }
}
