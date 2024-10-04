package by.vsu.jwpl.clustering;

import by.vsu.jwpl.clustering.distance.nearest.ClusterNearestDistanceCalculator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClustererTest {
	@Test
	void performClustering() {
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
		Cluster<Point> actual = Clusterer.performClustering(points, new ClusterNearestDistanceCalculator<>(new PointDistanceCalculator()));
		Cluster<Point> a = new Cluster<>(points.get(0));
		Cluster<Point> b = new Cluster<>(points.get(1));
		Cluster<Point> c = new Cluster<>(points.get(2));
		Cluster<Point> d = new Cluster<>(points.get(3));
		Cluster<Point> e = new Cluster<>(points.get(4));
		Cluster<Point> f = new Cluster<>(points.get(5));
		Cluster<Point> g = new Cluster<>(points.get(6));
		Cluster<Point> h = new Cluster<>(points.get(7));
		Cluster<Point> i = new Cluster<>(points.get(8));
		Cluster<Point> j = new Cluster<>(points.get(9));
		Cluster<Point> k = new Cluster<>(points.get(10));
		Cluster<Point> l = new Cluster<>(Set.of(g, h, i), 1.0);
		Cluster<Point> m = new Cluster<>(Set.of(a, c), 1.4142135623731);
		Cluster<Point> n = new Cluster<>(Set.of(b, d), 1.4142135623731);
		Cluster<Point> o = new Cluster<>(Set.of(m ,n), 2.0);
		Cluster<Point> p = new Cluster<>(Set.of(e, f), 2.23606797749979);
		Cluster<Point> q = new Cluster<>(Set.of(j, k), 2.23606797749979);
		Cluster<Point> r = new Cluster<>(Set.of(l, o), 3.16227766016838);
		Cluster<Point> s = new Cluster<>(Set.of(p, r), 3.60555127546399);
		Cluster<Point> expected = new Cluster<>(Set.of(q, s), 4.47213595499958);
		assertEquals(expected, actual);
	}
}
