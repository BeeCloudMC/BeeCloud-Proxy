package net.fap.beecloud.math;

public class BeeCloudMath {

	public BeeCloudMath() {
	}

	public static double round(double d) {
		return round(d, 0);
	}

	public static double round(double d, int precision) {
		return (double) Math.round(d * Math.pow(10.0D, precision)) / Math.pow(10.0D, precision);
	}

}
