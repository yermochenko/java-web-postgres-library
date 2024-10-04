package by.vsu.jwpl.clustering;

import by.vsu.jwpl.clustering.distance.nearest.ClusterNearestDistanceCalculator;

import java.util.List;

public class Test {
	static <T> void output(Cluster<T> cluster, int offset) {
		if(cluster.isSimple()) {
			System.out.println("   ".repeat(offset) + cluster.getObject());
		} else {
			System.out.println("   ".repeat(offset) + cluster.getDistance());
			for(Cluster<T> subcluster : cluster.getSubClusters()) {
				output(subcluster, offset + 1);
			}
		}
	}

	public static void main(String[] args) {
		List<Point> points = List.of(
			new Point(0, -2),
			new Point(0, 2),
			new Point(1, -1),
			new Point(1, 1),
			new Point(2, 6),
			new Point(3, 8),
			new Point(4, 2),
			new Point(4, 3),
			new Point(5, 3),
			new Point(6, -2),
			new Point(8, -1)
		);
		Cluster<Point> cluster = Clusterer.performClustering(points, new ClusterNearestDistanceCalculator<>(new PointDistanceCalculator()));
		output(cluster, 0);
	}
}
