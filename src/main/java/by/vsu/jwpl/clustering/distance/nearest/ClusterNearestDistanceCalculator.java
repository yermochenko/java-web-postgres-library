package by.vsu.jwpl.clustering.distance.nearest;

import by.vsu.jwpl.clustering.Cluster;
import by.vsu.jwpl.clustering.distance.ClusterDistanceCalculator;
import by.vsu.jwpl.clustering.distance.ObjectDistanceCalculator;

import java.util.Set;

public class ClusterNearestDistanceCalculator<T> extends ClusterDistanceCalculator<T> {
	public ClusterNearestDistanceCalculator(ObjectDistanceCalculator<T> distanceCalculator) {
		super(distanceCalculator);
	}

	@Override
	public double calc(Cluster<T> cluster1, Cluster<T> cluster2) {
		if(cluster1.isSimple() && cluster2.isSimple()) {
			// A
			return calc(cluster1.getObject(), cluster2.getObject());
		} else if(cluster1.isSimple()) {
			// B
			return calc(cluster1, cluster2.getSubClusters());
		} else if(cluster2.isSimple()) {
			// C
			return calc(cluster2, cluster1.getSubClusters());
		} else {
			// D
			return calc(cluster1.getSubClusters(), cluster2.getSubClusters());
		}
	}

	private double calc(Cluster<T> cluster, Set<Cluster<T>> clusters) {
		return clusters.stream().map(element -> calc(cluster, element)).min(Double::compare).orElseThrow();
	}

	private double calc(Set<Cluster<T>> clusters1, Set<Cluster<T>> clusters2) {
		return clusters1.stream().map(element -> calc(element, clusters2)).min(Double::compare).orElseThrow();
	}
}
