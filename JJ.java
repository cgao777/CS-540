
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.text.NumberFormat;

public class JJ{
    private static String filename = "./data.csv";
    private static ArrayList<ArrayList<Double>> readData() throws IOException{
        ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> body = new ArrayList<String>();
        ArrayList<Double> a1 = new ArrayList<Double>();
        ArrayList<Double> a2 = new ArrayList<Double>();
        
        ArrayList<String> brain = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null){
                list.add(line);
            }
            bf.close();
            fr.close();
            for(int i = 0; i < list.size(); i++) {
            	String[] weight = list.get(i).split(",");
                body.add(weight[0]);
                brain.add(weight[1]);
            }
            body.remove(0);
            brain.remove(0);
            for(int j = 0; j< body.size(); j++) {
            	a1.add(Double.parseDouble(body.get(j)));
            	a2.add(Double.parseDouble(brain.get(j)));
            }
            data.add(a1);
            data.add(a2);

        }catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }catch(IOException ex) {
        	System.out.println("Index out of bound");
        }
        
        return data;
    }
    static public void main(String[] args) throws IOException{
        ArrayList<ArrayList<Double>> list = readData();
        ArrayList<Double> body = new ArrayList<Double>();
        ArrayList<Double> brain = new ArrayList<Double>();
        java.text.Format df = new java.text.DecimalFormat("#0.0000");
        int flag = Integer.valueOf(args[0]);
        for (int i = 0; i < list.size(); i++) { 
            for (int j = 0; j < list.get(i).size(); j++) { 
            	if(i == 0) {
            		body.add(list.get(i).get(j));
            	}
            	if(i == 1) {
            		brain.add(list.get(i).get(j));
            	}
            } 
            
        } 
    

       if(flag == 100){
    	   int a = body.size();
    	   double bodys = 0;
    	   double brains = 0;
           double b = 0;
           double c = 0;
    	   System.out.println(a);
    	   for (int i = 0; i < body.size(); i++) {
    		   bodys += body.get(i);
    		   brains += brain.get(i);
    	   }
    	   System.out.println(df.format(bodys/((double)a) ) + " " + df.format(brains/((double)a)));
    	   
    	   for (int j = 0; j < body.size(); j++) {
    		   b += Math.pow((body.get(j) - (bodys/((double)a))), 2);
    		   c += Math.pow((brain.get(j) - (brains/((double)a))), 2);
    		   
    	   }
    	   double d = (double)a - 1;
    	   System.out.println(df.format(Math.sqrt(b/d)) + " " + df.format(Math.sqrt(c/d)));

        }
        else if(flag == 200){
            double b0 = Double.valueOf(args[1]);
            double b1 = Double.valueOf(args[2]);
            int n = body.size();
            double a = (double)n;
            double sum = 0;
            for(int i = 0; i < body.size(); i++) {
            	sum += Math.pow((b0 + (b1*body.get(i)) - brain.get(i)), 2);
            }
            System.out.println(df.format(sum/a)); 
           

        }
       
        else if(flag == 300){
        	double b0 = Double.valueOf(args[1]);
            double b1 = Double.valueOf(args[2]);
            int n = body.size();
            double a = (double)n;
            double sum1 = 0;
            double sum2 = 0;
            for(int i = 0; i < body.size(); i++) {
            	sum1 += b0 + (b1 * body.get(i)) - brain.get(i);
            	sum2 += (b0 + b1 * body.get(i) - brain.get(i)) * body.get(i);
            }
            System.out.println(df.format(sum1*2/a));
            System.out.println(df.format(sum2*2/a));
            

        }
       
        else if(flag == 400){
        	double arg1 = Double.valueOf(args[1]);
            double arg2 = Double.valueOf(args[2]);
            int n = body.size();
            double a = (double)n;
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;
            ArrayList<Double> b0 = new ArrayList<Double>();
            ArrayList<Double> b1 = new ArrayList<Double>();
            ArrayList<Double> mse = new ArrayList<Double>();
            b0.add((double) 0);
            b1.add((double) 0);
            mse.add((double) 0);
            for(int i = 1; i < arg2 + 1; i++) {
            	sum1 = 0;
            	sum2 = 0;
            	sum3 = 0;
            	for(int j = 0; j < body.size(); j++) {
                	sum1 += b0.get(i-1) + (b1.get(i-1) * body.get(j)) - brain.get(j);
                	sum2 += (b0.get(i-1) + b1.get(i-1) * body.get(j) - brain.get(j)) * body.get(j);
                }
            	b0.add(b0.get(i-1) - ((arg1 * sum1 * 2) /a));
            	b1.add(b1.get(i-1) - ((arg1 * sum2* 2) /a));
            	for(int s = 0; s < body.size(); s++) {
            		sum3 += Math.pow((b0.get(i) + b1.get(i) * body.get(s) - brain.get(s)) ,2);
            	}
            	mse.add(sum3/a);
            }
            for (int k = 1; k < arg2 + 1; k++) {
            	System.out.println(k + " "+ df.format(b0.get(k)) + " " + df.format(b1.get(k)) + " " + df.format(mse.get(k)));
            }

        }
       
        else if(flag == 500){
            
            

        }
        
        else if(flag == 600){
        	double arg1 = Double.valueOf(args[1]);
            
            
        }
       
        else if(flag == 700){
        	double arg1 = Double.valueOf(args[1]);
            double arg2 = Double.valueOf(args[2]);
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;
            ArrayList<Double> b0 = new ArrayList<Double>();
            ArrayList<Double> b1 = new ArrayList<Double>();
            ArrayList<Double> mse = new ArrayList<Double>();
            ArrayList<Double> newbody = new ArrayList<Double>();
            double bodys = 0;
            int n = body.size();
            double a = (double)n;
            double stdx = 0;
            double varbodys = 0;
        	for (int t = 0; t < body.size(); t++) {
     		   bodys += body.get(t);  
        	}
        	varbodys = bodys/a;
        	for(int w = 0; w < body.size(); w++) {
        		stdx += Math.sqrt(Math.pow((body.get(w) - varbodys),2)/(a-1));
        	}
        	for(int p = 0; p < body.size(); p++) {
        		newbody.add((body.get(p) - varbodys)/stdx);
        	}
            b0.add((double) 0);
            b1.add((double) 0);
            mse.add((double) 0);
            for(int i = 1; i < arg2 + 1; i++) {
            	sum1 = 0;
            	sum2 = 0;
            	sum3 = 0;
            	for(int j = 0; j < newbody.size(); j++) {
                	sum1 += b0.get(i-1) + (b1.get(i-1) * newbody.get(j)) - brain.get(j);
                	sum2 += (b0.get(i-1) + b1.get(i-1) * newbody.get(j) - brain.get(j)) * newbody.get(j);
                }
            	b0.add(b0.get(i-1) - ((arg1 * sum1 * 2) /a));
            	b1.add(b1.get(i-1) - ((arg1 * sum2* 2) /a));
            	for(int s = 0; s < newbody.size(); s++) {
            		sum3 += Math.pow((b0.get(i) + b1.get(i) * newbody.get(s) - brain.get(s)) ,2);
            	}
            	mse.add(sum3/a);
            }
            for (int k = 1; k < arg2 + 1; k++) {
            	System.out.println(k + " "+ df.format(b0.get(k)) + " " + df.format(b1.get(k)) + " " + df.format(mse.get(k)));
            }
        	
        	
        }else if(flag == 800) {
        	
        	
        }
        return;
       
        
    }
    
}