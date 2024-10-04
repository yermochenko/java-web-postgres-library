package by.vsu.jwpl.clustering;

import by.vsu.jwpl.clustering.distance.ObjectDistanceCalculator;

class PointDistanceCalculator implements ObjectDistanceCalculator<Point> {
	@Override
	public double calc(Point p1, Point p2) {
		return Math.hypot(p1.getX() - p2.getX(), p1.getY() - p2.getY());
	}
}
