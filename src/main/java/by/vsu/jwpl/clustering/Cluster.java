package by.vsu.jwpl.clustering;

import java.util.Objects;
import java.util.Set;

public class Cluster<T> {
	private final T object;

	private final Set<Cluster<T>> subClusters;
	private final double distance;

	public Cluster(T object) {
		this(object, null, 0.0);
	}

	public Cluster(Set<Cluster<T>> subClusters, double distance) {
		this(null, subClusters, distance);
	}

	private Cluster(T object, Set<Cluster<T>> subClusters, double distance) {
		this.object = object;
		this.subClusters = subClusters;
		this.distance = distance;
	}

	public T getObject() {
		return object;
	}

	public Set<Cluster<T>> getSubClusters() {
		return subClusters;
	}

	public double getDistance() {
		return distance;
	}

	public boolean isSimple() {
		return object != null;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Cluster<?> cluster = (Cluster<?>) obj;
		boolean isSimpleThis = isSimple();
		boolean isSimpleOther = cluster.isSimple();
		if(isSimpleThis && isSimpleOther) {
			return Objects.equals(object, cluster.object);
		} else if(!isSimpleThis && !isSimpleOther) {
			return Math.abs(distance - cluster.distance) < 0.00001 && Objects.equals(subClusters, cluster.subClusters);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		if(isSimple()) {
			return Objects.hash(object);
		} else {
			return Objects.hash(subClusters, distance);
		}
	}
}
