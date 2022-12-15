package pgdp.pools;

import java.util.Objects;

public class Task<T, R> {
	private final T input;
	private R result;
	private final TaskFunction<T, R> taskFunction;

	protected Task(T input, TaskFunction<T, R> taskFunction) {
		// TODO
		this.input = input;
		this.taskFunction = taskFunction;
	}

	public R getResult() {
		// TODO
		result = this.taskFunction.apply(this.input);
		return result;
	}

	@Override
	public int hashCode() {
		// TODO
		return Objects.hash(input, taskFunction);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO
		//Code aus w08p03 tuple.java
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Task<?, ?> ta = (Task<?, ?>) obj;
		return Objects.equals(input, ta.getInput()) && Objects.equals(taskFunction, ta.getTaskFunction());
	}

	//Getter
	public T getInput() {
		return input;
	}

	public TaskFunction<T, R> getTaskFunction() {
		return taskFunction;
	}

	public static void main(String[] args) {
		TaskFunction<Integer, Integer> f1 = new TaskFunction<>(FunctionLib.INC);
		TaskFunction<Integer, Integer> f2 = new TaskFunction<>(FunctionLib.INC);
		Task<Integer, Integer> t1 = new Task<>(1, f1);
		Task<Integer, Integer> t2 = new Task<>(1, f1);
		Task<Integer, Integer> t3 = new Task<>(1, f2);

		System.out.println(t1.equals(t2)); // true
		System.out.println(t1.equals(t3)); // false

		System.out.println(t1.getResult()); // 2
	}
}
