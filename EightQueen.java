/*import java.util.*;
import java.lang.Math;

class State {
    char[] board;

    public State(char[] arr) {
        this.board = Arrays.copyOf(arr, arr.length);
    }

    public void printState(int option, int iteration, int seed) {

    	
        // TO DO: print output based on option (flag)
    	if(option == 1 && iteration == 0) {
    		System.out.println(heuristic(this));
    	}
    	if(option == 2 && iteration == 0) {
    		List<State> arr = new ArrayList<State>();
    		arr = successors(this);
    		arr.forEach(System.out :: println);
    		if (!arr.isEmpty())
    			System.out.println(heuristic(arr.get(0)));
    	}
    	if(option == 3) {
    		HC(this, iteration, seed);
    	}
    	if(option == 4) {
    		HCFC(this, iteration);
    	}
    	if(option == 5) {
    		SA(this, iteration, seed);
    	}

    }
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < 8; i++) {
    	    sb.append(board[i]);
    	}
    	return sb.toString();
    }

    public int findOccurrence(int[] str) {
    	int count = 0;
    	for(int i = 0; i < str.length; i++) {
    		if(str[0] == str[i]) {
    			count++;
    		}
    	}
    	return count;
    }
    // TO DO: feel free to add/remove/modify methods/fields
    public int heuristic(State st) {
    	int n = 0;
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < 8; i++) {
    	    sb.append(board[i]);
    	}
    	String b = st.toString();
    	for (int i = 0; i < 7; i ++) {
    		int[] temp = new int[8 - i];
    		for (int j = 0; j < temp.length; j++) {
    			temp[j] = Integer.valueOf(b.charAt(j)) - 48;
    			}
    		int count = findOccurrence(temp);
    		int[] temp2 = temp.clone();
    		n += count - 1;
    		for (int j = 0; j < temp.length; j++) {
    			temp[j] = Integer.valueOf(temp[j]) - j;
//    			System.out.println(String.valueOf(Integer.valueOf(temp.charAt(j) - j)));
    		}
    		count = findOccurrence(temp);
//    		System.out.println(temp2);
    		n += count - 1;
    		for (int j = 0; j < temp2.length; j++) {
    			temp2[j] = temp2.length - j - Integer.valueOf(temp2[j]);
//    			System.out.println(String.valueOf(Integer.valueOf(temp.charAt(j) - j)));
    		}
    		count = findOccurrence(temp2);
    		n += count - 1;
    		b = b.substring(1);
    	}
    	return n;
    }
    public List<State> successors(State st) {
    	List<State> state_array = new ArrayList<State>();
    	int current = heuristic(this);
    	if (current == 0) {
    		return state_array;
    	}
    	int kept = current;
    	for(int i = 0; i < 8; i ++) {
    		for(int j = 0; j < 8; j++) {
    			char[] temp = st.board.clone();
    			temp[i] = (char)(j + '0');
    			State state = new State(temp);
    			if(heuristic(state) < kept) {
    				kept = heuristic(state);
    			}
    		}
    	}
    	for(int i = 0; i < 8; i ++) {
    		for(int j = 0; j < 8; j++) {
    			char[] temp = st.board.clone();
    			temp[i] = (char)(j + '0');
    			State state = new State(temp);
    			if(heuristic(state) == kept) {
    				boolean bool = true;
    				if(st.toString().equals(state.toString()))
    					bool = false;
    				if (bool)
    					state_array.add(state);
    			}
    		}
    	}
    	return state_array;
    }
    public void HC(State st, int iteration, int seed) {
    	Random rng = new Random();
    	if (seed != -1)
    		rng.setSeed(seed);
    	State move = this;
    	for(int i = 0; i < iteration + 1; i++) {
    		System.out.println(i + ":" + move + " " + heuristic(move));
    		if (heuristic(move) == 0) {
    			System.out.println("Solved");
    			return;
    		}
    		int r = rng.nextInt(successors(move).size());
    		move = successors(move).get(r);
    	}
    }
    public State first_choice(State st) {
    	int current = heuristic(st);
    	for(int i = 0; i < 8; i ++) {
    		for(int j = 0; j < 8; j++) {
    			char[] temp = st.board.clone();
    			temp[i] = (char)(j + '0');
    			State state = new State(temp);
    			if(heuristic(state) < current) {
    				return state;
    			}
    		}
    	}
    	return null;
    }
    public void HCFC(State st, int iteration) {
    	State move = st;
    	for(int i = 0; i < iteration + 1; i++) {
    		System.out.println(i + ":" + move + " " + heuristic(move));
    		if (heuristic(move) == 0) {
    			System.out.println("Solved");
    			return;
    		}
    		move = first_choice(move);
    		if (move == null) {
    			System.out.println("Local optimum");
    			return;
    		}
    	}
    }
    public void SA(State st, int iteration, int seed) {
    	State move = st;
    	Random rng = new Random();
    	if (seed != -1) 
    		rng.setSeed(seed);
    	double rate = 0.95;
    	double temperature = 100;
    	for(int i = 0; i < iteration + 1; i++) {
    		System.out.println(i + ":" + move + " " + heuristic(move));
    		int index = rng.nextInt(7);
    		int value = rng.nextInt(7);
    		double prob = rng.nextDouble();
    		char[] temp = st.board.clone();
    		temp[index] = (char)(value + '0');
    		move = new State(temp);
    		if (heuristic(st) == 0) {
    			System.out.println("Solved");
    			return;
    		}
    		if (heuristic(move) >= heuristic(st)) {
    			if (prob >= Math.exp(-Math.abs(heuristic(st) - heuristic(move))/temperature)) {
    				move = st;
    			}else {
    				st = move;
    			}
    		}else {
    			st = move;
    		}
    		temperature *= rate;
    	}
    }
}




public class EightQueen {
    public static void main(String args[]) {
        if (args.length != 2 && args.length != 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }

        int flag = Integer.valueOf(args[0]);
        int option = flag / 100;
        int iteration = flag % 100;
        char[] board = new char[8];
        int seed = -1;
        int board_index = -1;

        if (args.length == 2 && (option == 1 || option == 2 || option == 4)) {
            board_index = 1;
        } else if (args.length == 3 && (option == 3 || option == 5)) {
            seed = Integer.valueOf(args[1]);
            board_index = 2;
        } else {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }

        if (board_index == -1) return;
        for (int i = 0; i < 8; i++) {
            board[i] = args[board_index].charAt(i);
            int pos = board[i] - '0';
            if (pos < 0 || pos > 7) {
                System.out.println("Invalid input: queen position(s)");
                return;
            }
        }

        State init = new State(board);
        init.printState(option, iteration, seed);
    }
}
*/
