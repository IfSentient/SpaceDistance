package org.spacedistance;

public enum DistanceUnit {
	Parsecs(0.000000000000032407793), Lightyears(0.0000000000001057), AstronomicalUnits(0.00000000668459), Miles(0.621371), Kilometers(1);
	
	private double conversionFactor;
	private DistanceUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	protected double getConversionFactor() {
		return this.conversionFactor;
	}
	
	public static double convert(double amount, DistanceUnit unitFrom, DistanceUnit unitTo) {
		if(unitFrom == unitTo) return amount;
		if(unitFrom == Kilometers) {
			return amount * unitTo.getConversionFactor();
		} else if(unitTo == Kilometers) {
			return amount * (1.0 / unitFrom.getConversionFactor());
		} else {
			return convert(convert(amount, unitFrom, Kilometers), Kilometers, unitTo);
		}
	}
}
