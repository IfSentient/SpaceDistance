package org.spacedistance;

public abstract class Star {

	public static final double GRAVITATIONAL_CONSTANT = Planet.GRAVITATIONAL_CONSTANT;
	
	public abstract double getRadius(DistanceUnit unit);
	
	public boolean equals(Star star) {
		return (star.getClass().isAssignableFrom(this.getClass()));
	}
	
}
