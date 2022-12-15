package pgdp.pools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaskPool<T, R> {
	private HashMap<Task, Task> tasks_hm;

	protected TaskPool() {
		// TODO ?
		tasks_hm = new HashMap<>();
	}

	public Task<T, R> insert(Task<T, R> task) {
		// TODO
		if (tasks_hm.containsValue(task)) {
			return tasks_hm.get(task);
		}
		tasks_hm.put(task, task);
		return tasks_hm.get(task);
	}

	public Task<T, R> getByValue(T input, TaskFunction<T, R> function) {
		// TODO
		for (Map.Entry<Task, Task> meta : tasks_hm.entrySet()) {
			Task a_task = meta.getValue();
			if (a_task.getInput() == input && a_task.getTaskFunction() == function) {
				return a_task;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TaskFunction<Integer, Integer> f = new TaskFunction<>(FunctionLib.SQUARE);
		TaskPool<Integer, Integer> tp = new TaskPool<>();

		System.out.println(tp.getByValue(1, f)); // null

		Task<Integer, Integer> t1 = new Task<>(1, f);
		Task<Integer, Integer> t2 = new Task<>(1, f);
		System.out.println(t1 == tp.insert(t1)); // true
		System.out.println(t1 == tp.insert(t2)); // true
		System.out.println(t1 == tp.getByValue(1, f)); // true
	}
}
