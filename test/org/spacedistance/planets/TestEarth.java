package org.spacedistance.planets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.spacedistance.DistanceUnit;
import org.spacedistance.Vector3d;
import org.spacedistance.planets.Earth;

public class TestEarth {

	Earth earth;
	
	@Before
	public void setUpTest() {
		earth = new Earth();
	}
	
	@Test
	public void testEarthDistanceToCenter() {
		double distance = earth.getDistanceFromCenter(0, 0, 0, DistanceUnit.Kilometers);
		assertEquals((int)Math.floor(earth.getRadius(DistanceUnit.Kilometers)), (int)Math.floor(distance));
	}
	
	@Test
	public void testEarthCenterDistanceToSunCenter() {
		Vector3d vectorDistance = earth.getVectorDistanceFromStarCenterToCenter(earth.getOrbitingStar(), DistanceUnit.Kilometers);
		long distance = (long) vectorDistance.getLength();
		assertEquals((long) earth.getPerihelionDistance(DistanceUnit.Kilometers), distance);
	}
	
	@Test
	public void testEarthDistanceToSunCenter() {
		double distance = earth.getDistanceFromStarCenter(earth.getOrbitingStar(), DistanceUnit.Kilometers);
		double checkDistance = earth.getPerihelionDistance(DistanceUnit.Kilometers) + earth.getRadius(DistanceUnit.Kilometers);
		assertEquals((long) checkDistance, (long) distance);
		
		earth.setTimeSinceMidnight(earth.getLengthOfDay() / 2);
		distance = earth.getDistanceFromStarCenter(earth.getOrbitingStar(), DistanceUnit.Kilometers);
		checkDistance = earth.getPerihelionDistance(DistanceUnit.Kilometers) - earth.getRadius(DistanceUnit.Kilometers);
		earth.setTimeSinceMidnight(0);
		assertEquals((long) checkDistance, (long) distance);
	}
	
	@Test
	public void testEarthDistanceToSunSurface() {
		double distance = earth.getDistanceFromStarSurface(earth.getOrbitingStar(), DistanceUnit.Kilometers);
		double checkDistance = earth.getPerihelionDistance(DistanceUnit.Kilometers) + earth.getRadius(DistanceUnit.Kilometers) - earth.getOrbitingStar().getRadius(DistanceUnit.Kilometers);
		assertEquals((long) checkDistance, (long) distance);

		earth.setTimeSinceMidnight(earth.getLengthOfDay() / 2);
		distance = earth.getDistanceFromStarSurface(earth.getOrbitingStar(), DistanceUnit.Kilometers);
		checkDistance = earth.getPerihelionDistance(DistanceUnit.Kilometers) - earth.getRadius(DistanceUnit.Kilometers) - earth.getOrbitingStar().getRadius(DistanceUnit.Kilometers);
		earth.setTimeSinceMidnight(0);
		assertEquals((long) checkDistance, (long) distance);
	}
	
	@Test
	public void testEarthDistanceToMars() {
		Mars mars = new Mars();
		double distance = earth.getDistanceFromPlanet(mars, DistanceUnit.Kilometers);
		double checkDistance = mars.getPerihelionDistance(DistanceUnit.Kilometers) - earth.getPerihelionDistance(DistanceUnit.Kilometers) - earth.getRadius(DistanceUnit.Kilometers) - mars.getRadius(DistanceUnit.Kilometers);
		assertEquals((long) checkDistance, (long) distance);
	}

}
