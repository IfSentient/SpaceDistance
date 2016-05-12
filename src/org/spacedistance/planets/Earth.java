package org.spacedistance.planets;

import java.util.Date;

import org.spacedistance.DistanceUnit;
import org.spacedistance.Planet;
import org.spacedistance.Star;
import org.spacedistance.stars.Sol;

public class Earth extends Planet {

	private static final double PERIHELION_DISTANCE = 147100000; // Kilometers
	private static final double RADIUS = 6371; // kilometers
	private static final double ECCENTRICITY = 0.0167; // Technically this varies, but over hundreds of thousands of years
	private static final Star ORBITING = new Sol();
	private static final long MS_IN_DAY = 86400000;
	private static final long MS_IN_YEAR = 31557600000l;
	
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
	
	/**
	 * Custom Earth-centric methods dealing with Date objects
	 */
	
	/**
	 * Sets the time since midnight and the time since the last perihelion based on the entered date.
	 * @param date
	 */
	public void setDate(Date date) {
		this.setTimeSinceMidnight(date.getTime() % MS_IN_DAY);
		this.setTimeSinceLastPerihelion(this.getTimeSinceLastPerihelion(date));
	}
	
	public long getTimeSinceLastPerihelion(Date date) {
		// TODO: Need a perihelion catalog of some sort
		return 0l;
	}
	
}
