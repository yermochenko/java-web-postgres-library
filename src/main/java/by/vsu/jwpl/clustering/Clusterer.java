package by.vsu.jwpl.clustering;

import by.vsu.jwpl.clustering.distance.ClusterDistanceCalculator;

import java.util.*;
import java.util.stream.Collectors;

public class Clusterer {
	public static <T> Cluster<T> performClustering(List<T> objects, ClusterDistanceCalculator<T> distanceCalculator) {
		List<Cluster<T>> clusters = objects.stream().map(Cluster::new).collect(Collectors.toCollection(ArrayList::new));
		while(clusters.size() > 1) {
			double minDistance = Double.MAX_VALUE;
			List<IndexPair> pairs = new ArrayList<>();
			for(int i = 0; i < clusters.size(); i++) {
				for(int j = i + 1; j < clusters.size(); j++) {
					double distance = distanceCalculator.calc(clusters.get(i), clusters.get(j));
					if(distance < minDistance) {
						minDistance = distance;
						pairs.clear();
						pairs.add(new IndexPair(i, j));
					} else if(distance == minDistance) {
						pairs.add(new IndexPair(i, j));
					}
				}
			}

			List<Set<Integer>> indexes = new ArrayList<>();
			while(!pairs.isEmpty()) {
				IndexPair pair = pairs.remove(0);
				boolean present = false;
				for(Set<Integer> set : indexes) {
					if(set.contains(pair.i) || set.contains(pair.j)) {
						set.add(pair.i);
						set.add(pair.j);
						present = true;
					}
				}
				if(!present) {
					Set<Integer> newSet = new HashSet<>();
					newSet.add(pair.i);
					newSet.add(pair.j);
					indexes.add(newSet);
				}
			}

			List<Cluster<T>> newClusters = new ArrayList<>(indexes.size());
			List<Integer> removedIndexes = new ArrayList<>();
			for(Set<Integer> set : indexes) {
				Set<Cluster<T>> subclusters = new HashSet<>();
				for(int i : set) {
					subclusters.add(clusters.get(i));
					removedIndexes.add(i);
				}
				newClusters.add(new Cluster<>(subclusters, minDistance));
			}
			removedIndexes.sort(Collections.reverseOrder(Integer::compareTo));
			for(int i : removedIndexes) {
				clusters.remove(i);
			}
			clusters.addAll(newClusters);

		}
		return clusters.get(0);
	}

	private static class IndexPair {
		private final int i;
		private final int j;

		public IndexPair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
