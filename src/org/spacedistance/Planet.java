package org.spacedistance;

public abstract class Planet {

	public static final double GRAVITATIONAL_CONSTANT = 0;
	
	private double apogee = 0.0;
	private long msSinceLastPerihelion = 0;
	private long msSinceMidnight = 0;
	private double latitude = 0.0;
	private double longitude = 0.0;
	private double altitude = 0.0;
	
	public abstract double getEccentricity();
	public abstract double getPerihelionDistance(DistanceUnit unit);
	public abstract double getRadius(DistanceUnit unit);
	public abstract long getLengthOfDay();
	public abstract long getLengthOfYear();
	public abstract Star getOrbitingStar();
	
	public Planet() {
		// Calculate the apogee, or semi-major axis, from the perihelion distance (distance of the planet's closest approach to its local star)
		this.apogee = this.getPerihelionDistance(DistanceUnit.Kilometers) * (1 + this.getEccentricity()) / (1 - Math.pow(this.getEccentricity(), 2));
	}
	
	public void setTimeSinceLastPerihelion(long ms) {
		this.msSinceLastPerihelion = ms;
	}
	
	public void setTimeSinceMidnight(long ms) {
		this.msSinceMidnight = ms;
	}
	
	public void setLatitude(double degreesLatitude) {
		this.latitude = degreesLatitude;
	}
	
	public void setLongitude(double degreesLongitude) {
		this.longitude = degreesLongitude;
	}
	
	public void setAltitude(double km) {
		this.altitude = km;
	}
	
	public long getTimeSinceLastPerihelion() { 
		return this.msSinceLastPerihelion;
	}
	
	public long getTimeSinceMidnight() {
		return this.msSinceMidnight;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getAltitude(DistanceUnit unit) {
		return DistanceUnit.convert(this.altitude, DistanceUnit.Kilometers, unit);
	}
	
	public double getDistanceFromStarSurface(Star star, DistanceUnit unit) {
		return this.getVectorDistanceFromStarCenter(star, unit).getLength() - star.getRadius(unit);
	}
	
	public double getDistanceFromStarCenter(Star star, DistanceUnit unit) {
		return this.getVectorDistanceFromStarCenter(star, unit).getLength();
	}
	
	public double getDistanceFromCenter(double latitude, double longitude, double altitude, DistanceUnit unit) {
		return this.getVectorDistanceFromCenter(unit).getLength();
	}
	
	public double getDistanceFromPlanet(Planet planet, DistanceUnit unit) {
		return this.getVectorDistanceFromPlanetCenter(planet, unit).getLength() - planet.getRadius(unit);
	}
	
	public Vector3d getVectorDistanceFromStarCenter(Star star, DistanceUnit unit) {
		if(this.getOrbitingStar().equals(star)) {
			double theta = this.getTrueAnomaly();
			/*
			 * r = a(1 - e^2) / (1 + cos(theta))
			 * where
			 * e is the eccentricity
			 * theta is the true anomaly
			 */
			double radius = this.apogee * (1 - Math.pow(this.getEccentricity(), 2)) / (1 + (this.getEccentricity() * Math.cos(theta))); // Kilometers
			radius = DistanceUnit.convert(radius, DistanceUnit.Kilometers, unit);
			double x = radius * Math.cos(theta);
			double y = radius * Math.sin(theta);
			Vector3d distanceFromLocalStarToPlanet = new Vector3d(x, y, 0);
			return distanceFromLocalStarToPlanet.add(this.getVectorDistanceFromCenter(unit));
		}
		Vector3d distanceFromLocalStarToPlanet = this.getVectorDistanceFromStarCenter(this.getOrbitingStar(), unit);
		// TODO: distance to non-local stars
		return null;
	}
	
	public Vector3d getVectorDistanceFromPlanetCenter(Planet planet, DistanceUnit unit) {
		if(this.getOrbitingStar().equals(planet.getOrbitingStar())) {
			Vector3d surfaceToStarCenter = this.getVectorDistanceFromStarCenter(this.getOrbitingStar(), unit);
			Vector3d otherPlanetCenterToStarCenter = planet.getVectorDistanceFromStarCenterToCenter(this.getOrbitingStar(), unit);
			return otherPlanetCenterToStarCenter.subtract(surfaceToStarCenter);
		} else {
			// TODO
			return null;
		}
	}
	
	/**
	 * 
	 * @param date the date at which you are determining this vector, as the vector will be relative to time and space (the planet rotates and orbits a star)
	 * @param latitude degrees latitude. Negative degrees are degrees south, positive are degrees north.
	 * @param longitude degrees longitude. Negative degrees are degrees west, positive are degrees east.
	 * @param altitude
	 * @param unit
	 * @return Vector distance from center wrt to the orbiting star's position
	 */
	public Vector3d getVectorDistanceFromCenter(DistanceUnit unit) {
		// TODO: rotational bulge? Would depend on a lot of factors. Maybe another abstract method to fetch it.
		
		// Approximation for now
		double offsetAngleFrom0 = (this.getLongitude() < 0 ? this.getLongitude() + 360 : this.getLongitude()) * (Math.PI / 180);
		double theta = offsetAngleFrom0 + (this.getTimeSinceMidnight() * ((2 * Math.PI) / this.getLengthOfDay())) + this.getTrueAnomaly(); // At midnight the sun is directly opposite 0 degrees longitude
		double x = (this.getRadius(unit) + this.getAltitude(unit)) * Math.cos(theta);
		double y = (this.getRadius(unit) + this.getAltitude(unit)) * Math.sin(theta);
		double z = (this.getRadius(unit) + this.getAltitude(unit)) * Math.sin(this.getLatitude() * (180 / Math.PI));
		return new Vector3d(x, y, z);
	}
	
	public Vector3d getVectorDistanceFromStarCenterToCenter(Star star, DistanceUnit unit) {
		if(star.equals(getOrbitingStar())) {
			double theta = this.getTrueAnomaly();
			double radius = this.apogee * (1 - Math.pow(this.getEccentricity(), 2)) / (1 + (this.getEccentricity() * Math.cos(theta))); // Kilometers
			radius = DistanceUnit.convert(radius, DistanceUnit.Kilometers, unit);
			double x = radius * Math.cos(theta);
			double y = radius * Math.sin(theta);
			return new Vector3d(x, y, 0);
		}
		// TODO: distance to non-local stars
		return null;
	}
	
	/**
	 * Get the true anomaly of the orbit
	 * @param msSincePerihelion
	 * @return true anomaly, in radians
	 */
	public double getTrueAnomaly() {
		// TODO: This is the true anomaly of a circular orbit. Needs to be improved to be for an ellipse
		double theta = (this.getTimeSinceLastPerihelion() * 2 * Math.PI) / this.getLengthOfYear(); // 2PI to put into radians
		if(theta < 0) theta += (2 * Math.PI);
		return theta;
	}
	
	public boolean equals(Planet planet) {
		return (planet.getClass().isAssignableFrom(this.getClass()) && planet.getTimeSinceLastPerihelion() == this.getTimeSinceLastPerihelion());
	}
	
}
