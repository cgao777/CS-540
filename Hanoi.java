import java.util.*;

public class Hanoi {
    
    public static List<String> getSuccessor(String[] hanoi) {
        // TO DO
        // Implement the getSuccessor method
        List<String> result = new ArrayList<>();
        for (int i = 0; i < hanoi.length ; i ++) {
        	if (i + 1 != hanoi.length) {
        		//if pivot <-- right(0)
        		int pivot = Integer.valueOf(hanoi[i].substring(0, 1));
        		int right = Integer.valueOf(hanoi[i + 1].substring(0, 1));
        		
        		if (right != 0 && ((pivot > right && right != 0) || (hanoi[i].equals("0")) && right != 0)) {
        			 String str = new String();
        			 for (int j = 0; j < hanoi.length; j++) {
        				 if (j == i) {
        					 if (hanoi[i].equals("0")) {
        						 str += hanoi[i + 1].substring(0, 1) + " ";
        					 }else if(Integer.valueOf(hanoi[i].charAt(0)) > Integer.valueOf(hanoi[i + 1].charAt(0)) && hanoi[i + 1] != "0") {
        						 str += hanoi[i + 1].substring(0, 1) + hanoi[i] + " ";
        					 }else {
        						 str += hanoi[i] + " ";
        					 }
        				 }else if (j == i + 1) {
        					 if (hanoi[i].equals("0") || Integer.valueOf(hanoi[i].charAt(0)) > Integer.valueOf(hanoi[j].charAt(0))) {
        						 if (hanoi[j].length() == 1) {
        							 str += "0 ";
        						 }else {
        							 str += hanoi[j].substring(1) + " ";
        						 }
        					 }
        				 }else {
        					 str += hanoi[j] + " ";
        				 }
        			 }
        			 str = str.trim();
        			 result.add(str);
        		}
        	}
        	
        	if ( i != 0) {
        		//if left --> pivot
        		int pivot = Integer.valueOf(hanoi[i].substring(0, 1));
        		int left = Integer.valueOf(hanoi[i - 1].substring(0, 1));
        		if (left != 0 && ((pivot > left && left != 0)|| (pivot == 0 && left != 0))) {
        			 String str = new String();
        			 for (int j = 0; j < hanoi.length; j++) {
        				 if (j == i - 1) {
        					 if (hanoi[i].equals("0") || Integer.valueOf(hanoi[i].charAt(0)) > Integer.valueOf(hanoi[j].charAt(0))) {
        						 if (hanoi[j].length() == 1) {
        							 str += "0 ";
        						 }else {
        							 str += hanoi[j].substring(1) + " ";
        						 }
        					 }else {
        						 str += hanoi[j] + " ";
        					 }
        				 }else if (j == i) {
        					 if (hanoi[i].equals("0")) {
        						 str += hanoi[i - 1].substring(0, 1) + " ";
        					 }else if(Integer.valueOf(hanoi[i].charAt(0)) > Integer.valueOf(hanoi[i - 1].charAt(0)) && hanoi[i - 1] != "0") {
        						 str += hanoi[i - 1].substring(0, 1) + hanoi[i] + " ";
        					 }else {
        						 str += hanoi[i] + " ";
        					 }
        				 }else {
        					 str += hanoi[j] + " ";
        				 }
        			 }
        			 str = str.trim();
        			 result.add(str);
        		}
        	}
        }
        return result;    
    }

    public static void main(String[] args) {
        if (args.length < 3) {
	        return;
	    }
        
        List<String> sucessors = getSuccessor(args);
        for (int i = 0; i < sucessors.size(); i++) {
            System.out.println(sucessors.get(i));
        }    
    }
 
}