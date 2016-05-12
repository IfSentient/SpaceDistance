package org.spacedistance.planets;

import org.spacedistance.DistanceUnit;
import org.spacedistance.Planet;
import org.spacedistance.Star;
import org.spacedistance.stars.Sol;

public class Mars extends Planet {

	private static final double PERIHELION_DISTANCE = 206620000; // Kilometers
	private static final double RADIUS = 3389.5; // kilometers
	private static final double ECCENTRICITY = 0.0934; // Technically this varies, but over hundreds of thousands of years
	private static final Star ORBITING = new Sol();
	private static final long MS_IN_DAY = 88775244;
	private static final long MS_IN_YEAR = 59355072000l;
	
	@Override
	public double getEccentricity() {
		return ECCENTRICITY;
	}
	
	@Override
	public double getPerihelionDistance(DistanceUnit unit) {
		return DistanceUnit.convert(PERIHELION_DISTANCE, DistanceUnit.Kilometers, unit);
	}
	
	@Override
	public double getRadius(DistanceUnit unit) {
		return DistanceUnit.convert(RADIUS, DistanceUnit.Kilometers, unit);
	}
	
	@Override
	public long getLengthOfDay() {
		return MS_IN_DAY;
	}
	
	@Override
	public long getLengthOfYear() {
		return MS_IN_YEAR;
	}
	
	@Override
	public Star getOrbitingStar() {
		return ORBITING;
	}

}
