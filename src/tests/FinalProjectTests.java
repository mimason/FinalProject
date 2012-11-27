package tests;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;
import main.Launcher;
import main.Projectile;
import main.Target;
import main.World;

import org.junit.BeforeClass;
import org.junit.Test;


public class FinalProjectTests {
	private static World world;
	@BeforeClass
	public static void setUp() {
		world = new World();
		
	}
	@Test
	public void testCreatingProjectiles() {
		Launcher launcher = world.getLauncher();
		launcher.setAngle(90);
		launcher.setPower(10);
		launcher.launch();
		Assert.assertEquals(1,world.getProjectiles().size());
		Assert.assertEquals(10,world.getProjectiles().get(0).getyVel());
		Assert.assertEquals(0,world.getProjectiles().get(0).getxVel());
		launcher.setAngle(0);
		launcher.launch();
		Assert.assertEquals(2,world.getProjectiles().size());
		Assert.assertEquals(0,world.getProjectiles().get(1).getyVel());
		Assert.assertEquals(10,world.getProjectiles().get(1).getxVel());
		
	}
	@Test
	public void testCollisions() {
		//in the middle
		world.getProjectiles().clear();
		
		world.makeTarget(10,10);
		world.getProjectiles().add(new Projectile(15,5,0,0));
		world.checkCollisions();
		Assert.assertEquals(1,world.getTargets().size());
		Assert.assertTrue(world.getTargets().get(0).isHIt());
		
		world.makeTarget(30, 10);
		world.getProjectiles().add(new Projectile(30,10,0,0));
		world.checkCollisions();
		Assert.assertEquals(2,world.getTargets().size());
		Assert.assertTrue(world.getTargets().get(1).isHIt());
		
		world.getProjectiles().add(new Projectile(-30,10,0,0));
		world.checkCollisions();
		Assert.assertEquals(2,world.getProjectiles().size());
		
	}
	@Test
	public void testProjectileMovement() {
		world.getProjectiles().clear();
		world.getProjectiles().add(new Projectile(100,100,10,10));
		world.moveProjectiles(1);
		Assert.assertEquals(world.getProjectiles().get(0).getxVel(),10);
		Assert.assertEquals(world.getProjectiles().get(0).getxPos(),110);
		Assert.assertEquals(world.getProjectiles().get(0).getyVel(),90.2);
		Assert.assertEquals(world.getProjectiles().get(0).getyPos(),110);		
	}
	@Test
	public void testGenerateTargets(){
		ArrayList<Target> targets = world.getTargets();
		targets.clear();
		world.generateTargets(200);
		Assert.assertEquals(200, targets.size());
		Collections.sort(targets);
		//Target lastTarget = targets.get(0);
		for(int i = 1; i < targets.size();i++){
			if((targets.get(i-1).getLocation()+targets.get(i-1).getSize()) > targets.get(i).getLocation()){
				Assert.fail("Overlap!!! no good");
			}
		}
	}
	@Test
	public void testSpeedCalc(){
		world.getProjectiles().clear();
		world.getProjectiles().add(new Projectile(50,50,3,4));
		Assert.assertEquals(5,world.getProjectiles().get(0).calcSpeed());
		
		world.getProjectiles().clear();
		world.getProjectiles().add(new Projectile(50,50,12,5));
		Assert.assertEquals(13,world.getProjectiles().get(1).calcSpeed());
		
	}

}
