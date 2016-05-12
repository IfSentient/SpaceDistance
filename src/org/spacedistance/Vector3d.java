package org.spacedistance;

public class Vector3d {
	
	private double x, y, z;
	
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getLength() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}
	
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	public double getZ() { return this.z; }
	
	public Vector3d add(Vector3d vector) {
		return add(this, vector);
	}
	
	public Vector3d subtract(Vector3d vector) {
		return subtract(this, vector);
	}
	
	public static Vector3d add(Vector3d vector1, Vector3d vector2) {
		return new Vector3d(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY(), vector1.getZ() + vector2.getZ());
	}
	
	public static Vector3d subtract(Vector3d vector1, Vector3d vector2) {
		return new Vector3d(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY(), vector1.getZ() - vector2.getZ());
	}
	
}
