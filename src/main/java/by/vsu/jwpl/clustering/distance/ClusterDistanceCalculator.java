package by.vsu.jwpl.clustering.distance;

import by.vsu.jwpl.clustering.Cluster;

abstract public class ClusterDistanceCalculator<T> {
	private final ObjectDistanceCalculator<T> distanceCalculator;

	public ClusterDistanceCalculator(ObjectDistanceCalculator<T> distanceCalculator) {
		this.distanceCalculator = distanceCalculator;
	}

	protected final double calc(T obj1, T obj2) {
		return distanceCalculator.calc(obj1, obj2);
	}

	public abstract double calc(Cluster<T> cluster1, Cluster<T> cluster2);
}
