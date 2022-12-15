package pgdp.pools;

import java.util.Objects;
import java.util.function.Function;

public class TaskFunction<T, R> {
	private static int count;
	private final int ID;
	private final Function<T, R> function;

	public TaskFunction(Function<T, R> function) {
		// TODO wie bekommt die erste Instanz ID = 0 die zweite ID = 1 usw
		this.function = function;
		this.ID = count++;
	}

	public R apply(T input) {
		return function.apply(input);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		//Code aus w08p03 tuple.java
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		TaskFunction<?, ?> tafu = (TaskFunction<?, ?>) obj;
		return Objects.equals(ID, tafu.ID);
	}

	public static void main(String[] args) {
		TaskFunction<Integer, Integer> f1 = new TaskFunction<>(FunctionLib.SQUARE);
		TaskFunction<Integer, Integer> f2 = new TaskFunction<>(FunctionLib.SUM_OF_HALFS);
		TaskFunction<Integer, Integer> f3 = new TaskFunction<>(FunctionLib.SQUARE);
		System.out.println(f1.equals(f2)); // false
		System.out.println(f1.equals(f3)); // false
		System.out.println(f1.equals(f1)); // true
		System.out.println(f1.apply(2)); // 4
	}
}
