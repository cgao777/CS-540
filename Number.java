import java.util.*;

public class Number{
           
    public static String getStep(int x, int y) {
        // TO DO
        // Implement the getStep method
        String result = "";
        class node{
        	int x;
        	int num;
        }
        LinkedList<node> queue = new LinkedList<node>(); 
        node first = new node();
        first.x = x;
        first.num = 0;
        queue.add(first);

        while(true) {
        	node curr = queue.pop();
    		node new_node0 = new node();
        	if (curr.x == y) {
        		result = String.valueOf(curr.num);
        		break;
        	}
    		new_node0.x = curr.x + 1;
    		new_node0.num = curr.num + 1;
    		if (new_node0.x > 0 && new_node0.x < 100) {
    			queue.addLast(new_node0);
    		}
    		node new_node1 = new node();
    		new_node1.x = curr.x - 1;
    		new_node1.num = curr.num + 1;
    		if (new_node1.x > 0 && new_node1.x < 100) {
    			queue.addLast(new_node1);
    		}
    		node new_node2 = new node();
    		new_node2.x = curr.x * 3;
    		new_node2.num = curr.num + 1;
    		if (new_node2.x > 0 && new_node2.x < 100) {
    			queue.addLast(new_node2);
    		}
    		node new_node3 = new node();
    		new_node3.x = curr.x * curr.x;
    		new_node3.num = curr.num + 1;
    		if (new_node3.x > 0 && new_node3.x < 100) {
    			queue.addLast(new_node3);
    		}
        }
        
        return result;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        
        System.out.println(getStep(Integer.parseInt(args[0]), Integer.parseInt(args[1])));

    }
}