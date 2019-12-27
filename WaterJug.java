import java.util.*;

class State {
	int cap_jug1;
	int cap_jug2;
	int curr_jug1;
	int curr_jug2;
	int goal;
	int depth;
	int num;
	State parentPt;

	public State(int cap_jug1, int cap_jug2, int curr_jug1, int curr_jug2, int goal, int depth) {
		this.cap_jug1 = cap_jug1;
		this.cap_jug2 = cap_jug2;
		this.curr_jug1 = curr_jug1;
		this.curr_jug2 = curr_jug2;
		this.goal = goal;
		this.depth = depth;
		this.num = 0;
		this.parentPt = null;
	}

	public LinkedList<State> getSuccessors() {

		// TO DO: get all successors and return them in proper order
		LinkedList<State> successors = new LinkedList<State>();
		if (curr_jug1 < cap_jug1) {
			State next = new State(cap_jug1, cap_jug2, cap_jug1, curr_jug2, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		if (curr_jug2 < cap_jug2) {
			State next = new State(cap_jug1, cap_jug2, curr_jug1, cap_jug2, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		if (curr_jug1 <= cap_jug1 && curr_jug1 != 0) {
			State next = new State(cap_jug1, cap_jug2, 0, curr_jug2, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		if (curr_jug2 <= cap_jug2 && curr_jug2 != 0) {
			State next = new State(cap_jug1, cap_jug2, curr_jug1, 0, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		if (curr_jug1 != cap_jug1 && curr_jug2 != 0) {
			int new_jug1 = curr_jug1 + curr_jug2;
			int new_jug2 = 0;
			if (new_jug1 > cap_jug1) {
				new_jug2 = new_jug1 - cap_jug1;
				new_jug1 = cap_jug1;
			}
			State next = new State(cap_jug1, cap_jug2, new_jug1, new_jug2, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		if (curr_jug2 != cap_jug2 && curr_jug1 != 0) {
			int new_jug2 = curr_jug1 + curr_jug2;
			int new_jug1 = 0;
			if (new_jug2 > cap_jug2) {
				new_jug1 = new_jug2 - cap_jug2;
				new_jug2 = cap_jug2;
			}
			State next = new State(cap_jug1, cap_jug2, new_jug1, new_jug2, goal, depth);
			next.num = this.num + 1;
			next.parentPt = this;
			successors.add(next);
		}
		successors.sort(Comparator.comparing(State::toString));
		return successors;
	}

	public String toString() {
		return (String.valueOf(curr_jug1) + String.valueOf(curr_jug2));
	}

	public boolean isGoalState() {

		// TO DO: determine if the state is a goal node or not and return boolean
		return (curr_jug1 == goal) || (curr_jug2 == goal);
	}

	public boolean equals(State state) {
		return this.toString().equals(state.toString());
	}

	public void printState(int option, int depth) {

		// TO DO: print a State based on option (flag)

	}

	public String getOrderedPair() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.curr_jug1);
		builder.append(this.curr_jug2);
		return builder.toString().trim();
	}

	// TO DO: feel free to add/remove/modify methods/fields

}

class UninformedSearch {
	static String format(LinkedList<State> list) {
	    if (list.isEmpty())
	        return "[]";

	    StringBuilder sb = new StringBuilder();
	    sb.append('[');
	    for (int i = 0; i < list.size(); i++) {
	    	if (i == list.size() - 1) {
	    		sb.append(list.get(i));
	    		break;
	    	}
	        sb.append(list.get(i));
	        sb.append(",");
	    }
	    return sb.append(']').toString();
	}
	
	static void bfs(State curr_state) {
		// TO DO: run breadth-first search algorithm
		LinkedList<State> open = new LinkedList<>();
		LinkedList<State> close = new LinkedList<>();
		open.add(curr_state);
		System.out.println(curr_state);
		while (!open.isEmpty()) {
			curr_state = open.getFirst();
			LinkedList<State> temp = curr_state.getSuccessors();
			List<State> recorder = new ArrayList<State>();
			for (int i = 0; i < temp.size(); i++) {
				for (State state2 : open) {
					if (temp.get(i).equals(state2)) {
						recorder.add(temp.get(i));
					}

				}
				for (State state3 : close) {
					if (temp.get(i).equals(state3)) {
						recorder.add(temp.get(i));
					}
				}
			}
			open.removeFirst();
			temp.removeAll(recorder);
			open.addAll(temp);
			close.addLast(curr_state);
			if (curr_state.isGoalState()) {
				System.out.println(curr_state + " Goal");
				temp.clear();
				temp.add(curr_state);
				while (curr_state.parentPt != null) {
					curr_state = curr_state.parentPt;
					temp.addFirst(curr_state);
				}
				System.out.print("Path");
				temp.forEach(x -> System.out.print(" " + x));
				System.out.println();
				return;
			}
			System.out.println(curr_state + " " + format(open) + " " + format(close));
		}
	}

	static void dfs(State curr_state) {
		// TO DO: run depth-first search algorithm
		LinkedList<State> open = new LinkedList<>();
		LinkedList<State> close = new LinkedList<>();
		open.add(curr_state);
		System.out.println(curr_state);
		while (!open.isEmpty()) {
			curr_state = open.getLast();
			LinkedList<State> temp = curr_state.getSuccessors();
			List<State> recorder = new ArrayList<State>();
			for (int i = 0; i < temp.size(); i++) {
				for (State state2 : open) {
					if (temp.get(i).equals(state2)) {
						recorder.add(temp.get(i));
					}

				}
				for (State state3 : close) {
					if (temp.get(i).equals(state3)) {
						recorder.add(temp.get(i));
					}
				}
			}
			open.removeLast();
			temp.removeAll(recorder);
			open.addAll(temp);
			close.addLast(curr_state);
			if (curr_state.isGoalState()) {
				System.out.println(curr_state + " Goal");
				temp.clear();
				temp.add(curr_state);
				while (curr_state.parentPt != null) {
					curr_state = curr_state.parentPt;
					temp.addFirst(curr_state);
				}
				System.out.print("Path");
				temp.forEach(x -> System.out.print(" " + x));
				System.out.println();
				return;
			}
			System.out.println(curr_state + " " + format(open) + " " + format(close));
		}

	}

	static void iddfs(State curr_state, int depth) {
		// TO DO: run IDDFS search algorithm
		State saved_state = curr_state;
		for (int i = 0; i < depth + 1; i++) {
			LinkedList<State> open = new LinkedList<>();
			LinkedList<State> close = new LinkedList<>();
			open.add(curr_state);
			System.out.println(i + ":" + curr_state);
			while (!open.isEmpty()) {
				curr_state = open.getLast();
				LinkedList<State> temp = curr_state.getSuccessors();
				List<State> recorder = new ArrayList<State>();
				for (int j = 0; j < temp.size(); j++) {
					for (State state2 : open) {
						if (temp.get(j).equals(state2)) {
							recorder.add(temp.get(j));
						}
					}
					for (State state3 : close) {
						if (temp.get(j).equals(state3)) {
							recorder.add(temp.get(j));
						}
					}
				}
				open.removeLast();
				temp.removeAll(recorder);
				if (curr_state.num != i) {
					open.addAll(temp);
				}
				close.addLast(curr_state);
				if (curr_state.isGoalState()) {
					System.out.println(curr_state + " Goal");
					temp.clear();
					temp.add(curr_state);
					while (curr_state.parentPt != null) {
						curr_state = curr_state.parentPt;
						temp.addFirst(curr_state);
					}
					System.out.print("Path");
					temp.forEach(x -> System.out.print(" " + x));
					System.out.println();
					return;
				}
				System.out.println(i + ":" + curr_state + " " + format(open) + " " + format(close));
			}
			open.clear();
			close.clear();
			curr_state = saved_state;
		}
	}
}

public class WaterJug {
	public static void main(String args[]) {
		if (args.length != 6) {
			System.out.println("Invalid Number of Input Arguments");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int cap_jug1 = Integer.valueOf(args[1]);
		int cap_jug2 = Integer.valueOf(args[2]);
		int curr_jug1 = Integer.valueOf(args[3]);
		int curr_jug2 = Integer.valueOf(args[4]);
		int goal = Integer.valueOf(args[5]);

		int option = flag / 100;
		int depth = flag % 100;

		if (option < 1 || option > 5) {
			System.out.println("Invalid flag input");
			return;
		}
		if (cap_jug1 > 9 || cap_jug2 > 9 || curr_jug1 > 9 || curr_jug2 > 9) {
			System.out.println("Invalid input: 2-digit jug volumes");
			return;
		}
		if (cap_jug1 < 0 || cap_jug2 < 0 || curr_jug1 < 0 || curr_jug2 < 0) {
			System.out.println("Invalid input: negative jug volumes");
			return;
		}
		if (cap_jug1 < curr_jug1 || cap_jug2 < curr_jug2) {
			System.out.println("Invalid input: jug volume exceeds its capacity");
			return;
		}
		State init = new State(cap_jug1, cap_jug2, curr_jug1, curr_jug2, goal, depth);
		if (flag == 100) {
			init.getSuccessors().forEach(System.out::println);
		}
		if (flag == 200) {
			init.getSuccessors().forEach(s -> System.out.println(s + " " + s.isGoalState()));
		}
		if (flag == 300) {
			UninformedSearch.bfs(init);
		}
		if (flag == 400) {
			UninformedSearch.dfs(init);
		}
		if (option == 5) {
			UninformedSearch.iddfs(init, depth);
		}
	}
}
