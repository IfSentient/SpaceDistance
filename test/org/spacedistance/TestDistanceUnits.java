package org.spacedistance;

import static org.junit.Assert.*;

import org.junit.Test;
import org.spacedistance.DistanceUnit;

public class TestDistanceUnits {

	@Test
	public void testConvertToKilometers() {
		double targetParsec = 30856780000000d; // Approximation, we'll check to the first six places in the test
		double converted = DistanceUnit.convert(1, DistanceUnit.Parsecs, DistanceUnit.Kilometers);
		int target = (int) targetParsec / 100000000;
		int result = (int) converted / 100000000;
		assertEquals(target, result);
		
		double targetLightyears = 9460731000000d; // Approximation, we'll check to the first six places
		converted = DistanceUnit.convert(1, DistanceUnit.Lightyears, DistanceUnit.Kilometers);
		target = (int) targetLightyears / 1000000;
		result = (int) converted / 1000000;
		assertEquals(target, result);
		
		double targetAu = 149598000d;
		converted = DistanceUnit.convert(1, DistanceUnit.AstronomicalUnits, DistanceUnit.Kilometers);
		target = (int) targetAu / 10000;
		result = (int) converted / 10000;
		assertEquals(target, result);
		
		double targetMiles = 1.609344;
		converted = DistanceUnit.convert(1, DistanceUnit.Miles, DistanceUnit.Kilometers);
		target = (int) targetMiles / 100000;
		result = (int) converted / 100000;
		assertEquals(target, result);
		
		double targetKilometers = 1;
		converted = DistanceUnit.convert(1, DistanceUnit.Kilometers, DistanceUnit.Kilometers);
		target = (int) targetKilometers;
		result = (int) converted;
		assertEquals(target, result);
	}
	
	@Test
	public void testConvertToMiles() {
		double targetParsec = 19173510000000d; // Approximation, we'll check to the first six places in the test
		double converted = DistanceUnit.convert(1, DistanceUnit.Parsecs, DistanceUnit.Miles);
		int target = (int) targetParsec / 10000000;
		int result = (int) converted / 10000000;
		assertEquals(target, result);
		
		double targetLightyears = 5878625540000d;
		converted = DistanceUnit.convert(1, DistanceUnit.Lightyears, DistanceUnit.Miles);
		target = (int) targetLightyears / 100000;
		result = (int) converted / 100000;
		assertEquals(target, result);
		
		double targetAu = 92955000;
		converted = DistanceUnit.convert(1, DistanceUnit.AstronomicalUnits, DistanceUnit.Miles);
		target = (int) targetAu / 10000;
		result = (int) converted / 10000;
		assertEquals(target, result);
		
		double targetMiles = 1;
		converted = DistanceUnit.convert(1, DistanceUnit.Miles, DistanceUnit.Miles);
		target = (int) targetMiles;
		result = (int) converted;
		assertEquals(target, result);
		
		double targetKilometers = 0.621371; // Approximation, we'll check to the fifth decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Kilometers, DistanceUnit.Miles);
		target = (int) (targetKilometers * 100000);
		result = (int) (converted * 100000);
		assertEquals(target, result);
	}
	
	@Test
	public void testConvertToAstronomicalUnits() {
		double targetParsec = 206264;
		double converted = DistanceUnit.convert(1, DistanceUnit.Parsecs, DistanceUnit.AstronomicalUnits);;
		int target = (int) targetParsec;
		int result = (int) converted;
		assertEquals(target, result);
		
		double targetLightyears = 63241;
		converted = DistanceUnit.convert(1, DistanceUnit.Lightyears, DistanceUnit.AstronomicalUnits);
		target = (int) targetLightyears;
		result = (int) converted;
		assertEquals(target, result);
		
		double targetAu = 1;
		converted = DistanceUnit.convert(1, DistanceUnit.AstronomicalUnits, DistanceUnit.AstronomicalUnits);
		target = (int) targetAu;
		result = (int) converted;
		assertEquals(target, result);
		
		double targetMiles = 0.0000000107578d; // Approximation, we'll check to the 12th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Miles, DistanceUnit.AstronomicalUnits);
		target = (int) (targetMiles * 1000000000000d);
		result = (int) (converted * 1000000000000d);
		
		double targetKilometers = 0.00000000668459d; // Approximation, we'll check to the 13th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Kilometers, DistanceUnit.AstronomicalUnits);
		target = (int) (targetKilometers * 10000000000000d);
		result = (int) (converted * 10000000000000d);
		assertEquals(target, result);
	}
	
	@Test
	public void testConvertToLightyears() {
		double targetParsec = 3.261564;
		double converted = DistanceUnit.convert(1, DistanceUnit.Parsecs, DistanceUnit.Lightyears);;
		int target = (int) targetParsec;
		int result = (int) converted;
		assertEquals(target, result);
		
		double targetLightyears = 1;
		converted = DistanceUnit.convert(1, DistanceUnit.Lightyears, DistanceUnit.Lightyears);
		target = (int) targetLightyears;
		result = (int) converted;
		assertEquals(target, result);
		
		double targetAu = 0.0000158125074089; 
		converted = DistanceUnit.convert(1, DistanceUnit.AstronomicalUnits, DistanceUnit.Lightyears);
		target = (int) (targetAu * 1000000000000000d);
		result = (int) (converted * 1000000000000000d);
		assertEquals(target, result);
		
		double targetMiles = 0.0000000000001701078d; // Approximation, we'll check to the 18th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Miles, DistanceUnit.Lightyears);
		target = (int) (targetMiles * 1000000000000000000d);
		result = (int) (converted * 1000000000000000000d);
		assertEquals(target, result);
		
		double targetKilometers = 0.0000000000001057001d; // Approximation, we'll check to the 18th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Kilometers, DistanceUnit.Lightyears);
		target = (int) (targetKilometers * 1000000000000000000d);
		result = (int) (converted * 1000000000000000000d);
		assertEquals(target, result);
	}
	
	@Test
	public void testConvertToParsecs() {
		double targetParsec = 1;
		double converted = DistanceUnit.convert(1, DistanceUnit.Parsecs, DistanceUnit.Parsecs);;
		int target = (int) targetParsec;
		int result = (int) converted;
		assertEquals(target, result);
		
		double targetLightyears = 0.3066014;
		converted = DistanceUnit.convert(1, DistanceUnit.Lightyears, DistanceUnit.Parsecs);
		target = (int) (targetLightyears * 1000000);
		result = (int) (converted * 1000000);
		assertEquals(target, result);
		
		double targetAu = 0.0000048481367817;
		converted = DistanceUnit.convert(1, DistanceUnit.AstronomicalUnits, DistanceUnit.Parsecs);
		target = (int) (targetAu * 1000000000000000d);
		result = (int) (converted * 1000000000000000d);
		assertEquals(target, result);
		
		double targetMiles = 0.0000000000000521553; // Approximation, we'll check to the 18th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Miles, DistanceUnit.Parsecs);
		target = (int) (targetMiles * 1000000000000000000d);
		result = (int) (converted * 1000000000000000000d);
		assertEquals(target, result);
		
		double targetKilometers = 0.00000000000003240779d; // Approximation, we'll check to the 19th decimal place
		converted = DistanceUnit.convert(1, DistanceUnit.Kilometers, DistanceUnit.Parsecs);
		target = (int) (targetKilometers * 10000000000000000000d);
		result = (int) (converted * 10000000000000000000d);
		assertEquals(target, result);
	}

}
