package by.vsu.jwpl.clustering;

class Point {
	private final double x;
	private final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Point{" +
				"x=" + getX() +
				", y=" + getY() +
				'}';
	}
}
