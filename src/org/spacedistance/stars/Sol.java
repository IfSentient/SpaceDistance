package org.spacedistance.stars;

import org.spacedistance.DistanceUnit;
import org.spacedistance.Star;

public class Sol extends Star {

	private static final double RADIUS = 696300; // Kilometers
	
	@Override
	public double getRadius(DistanceUnit unit) {
		return DistanceUnit.convert(RADIUS, DistanceUnit.Kilometers, unit);
	}

}
