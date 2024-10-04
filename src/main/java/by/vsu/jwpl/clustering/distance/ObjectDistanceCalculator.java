package by.vsu.jwpl.clustering.distance;

public interface ObjectDistanceCalculator<T> {
	double calc(T obj1, T obj2);
}
