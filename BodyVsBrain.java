import java.io.*;
import java.util.*;

public class BodyVsBrain {
	private static String filename = "./data.csv";

	private static List<double[]> readData() {
		List<double[]> data = new ArrayList<double[]>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			sc.nextLine();
			while (sc.hasNext()) {
				String[] s = sc.nextLine().split(",");
				double[] i = new double[2];
				i[0] = Double.parseDouble(s[0]);
				i[1] = Double.parseDouble(s[1]);
				data.add(i);
				}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return data;
	}
	private static double MSE(double b0, double b1, List<double[]> data) {
		double MSE = 0;
		for (int i = 0; i < data.size(); i++) {
			MSE += Math.pow((b0 + b1 * data.get(i)[0] - data.get(i)[1]), 2);
		}
		MSE /= (double)data.size();
		return MSE;
	}
	private static double db0_dMSE(double b0, double b1, List<double[]> data) {
		double db0_dMSE = 0;
		for (int i = 0; i < data.size(); i++) {
			db0_dMSE += b0 + b1 * data.get(i)[0] - data.get(i)[1];
		}
		db0_dMSE = 2 * db0_dMSE / (double)data.size();
		return db0_dMSE;
	}
	private static double db1_dMSE(double b0, double b1, List<double[]> data) {
		double db1_dMSE = 0;
		for (int i = 0; i < data.size(); i++) {
			db1_dMSE += (b0 + b1 * data.get(i)[0] - data.get(i)[1]) * data.get(i)[0];
		}
		db1_dMSE = 2 * db1_dMSE / (double)data.size();
		return db1_dMSE;
	}
	public static void main(String[] args) {
		List<double[]> data = readData();
		int flag = Integer.valueOf(args[0]);
		
		if(flag == 100) {
			System.out.println(data.size());
			double mean_weight = 0;
			double mean_brain = 0;
			double std_weight = 0;
			double std_brain = 0;
			
			for(int i = 0; i < data.size(); i++) {
				mean_weight += data.get(i)[0];
				mean_brain += data.get(i)[1];
			}
			mean_weight /= (double)data.size();
			mean_brain /= (double)data.size();
			for (int i =0; i < data.size(); i++) {
				std_weight += Math.pow((data.get(i)[0] - mean_weight), 2);
				std_brain += Math.pow((data.get(i)[1] - mean_brain), 2);
			}
			std_weight = Math.sqrt(std_weight / (double)(data.size() - 1));
			std_brain = Math.sqrt(std_brain / (double)(data.size() - 1));
			
			System.out.printf("%.4f ", mean_weight);
			System.out.printf("%.4f\n", std_weight);
			System.out.printf("%.4f ", mean_brain);
			System.out.printf("%.4f\n", std_brain);
			
		}else if(flag == 200) {
			double b0 = Double.valueOf(args[1]);
			double b1 = Double.valueOf(args[2]);
			System.out.printf("%.4f\n", MSE(b0, b1, data));
		}else if(flag == 300) {
			double b0 = Double.valueOf(args[1]);
			double b1 = Double.valueOf(args[2]);
			System.out.printf("%.4f\n", db0_dMSE(b0, b1, data));
			System.out.printf("%.4f\n", db1_dMSE(b0, b1, data));
		}else if(flag == 400) {
			double n = Double.valueOf(args[1]);
			double T = Double.valueOf(args[2]);
			double B0 = 0;
			double B1 = 0;
			
			for (int t = 1; t <= T; t++) {
				double temp_B0 = 0;
				temp_B0 = B0 - n * db0_dMSE(B0, B1, data);
				B1 = B1 - n * db1_dMSE(B0, B1, data);
				B0 = temp_B0;
				System.out.print(t + " ");
				System.out.printf("%.4f ", B0);
				System.out.printf("%.4f ", B1);
				System.out.printf("%.4f\n", MSE(B0, B1, data));
			}
			
		}else if(flag == 500) {
			double mean_weight = 0;
			double mean_brain = 0;
			double B0 = 0;
			double B1 = 0;
			double temp1 = 0;
			double temp2 = 0;
			
			for(int i = 0; i < data.size(); i++) {
				mean_weight += data.get(i)[0];
				mean_brain += data.get(i)[1];
			}
			mean_weight /= (double)data.size();
			mean_brain /= (double)data.size();
			for (int i =0; i < data.size(); i++) {
				temp1 += (data.get(i)[0] - mean_weight) * (data.get(i)[1] - mean_brain);
				temp2 += Math.pow((data.get(i)[0] - mean_weight), 2);
			}
			B1 = temp1 / temp2;
			B0 = mean_brain - B1 * mean_weight;
			System.out.printf("%.4f ", B0);
			System.out.printf("%.4f ", B1);
			System.out.printf("%.4f\n", MSE(B0, B1, data));
		}else if(flag == 600) {
			double mean_weight = 0;
			double mean_brain = 0;
			double B0 = 0;
			double B1 = 0;
			double temp1 = 0;
			double temp2 = 0;
			
			for(int i = 0; i < data.size(); i++) {
				mean_weight += data.get(i)[0];
				mean_brain += data.get(i)[1];
			}
			mean_weight /= (double)data.size();
			mean_brain /= (double)data.size();
			for (int i =0; i < data.size(); i++) {
				temp1 += (data.get(i)[0] - mean_weight) * (data.get(i)[1] - mean_brain);
				temp2 += Math.pow((data.get(i)[0] - mean_weight), 2);
			}
			B1 = temp1 / temp2;
			B0 = mean_brain - B1 * mean_weight;
			System.out.printf("%.4f\n", B0 + B1 * Double.valueOf(args[1]));
			
		}else if(flag == 700) {
			double n = Double.valueOf(args[1]);
			double T = Double.valueOf(args[2]);
			List<double[]> x = new ArrayList<double[]>();
			double mean_weight = 0;
			double std_weight = 0;
			double B0 = 0;
			double B1 = 0;
			
			for(int i = 0; i < data.size(); i++) {
				mean_weight += data.get(i)[0];
			}
			mean_weight /= (double)data.size();
			for (int i =0; i < data.size(); i++) {
				std_weight += Math.pow((data.get(i)[0] - mean_weight), 2);
			}
			std_weight = Math.sqrt(std_weight / (double)(data.size() - 1));
			
			for (int i = 0; i < data.size(); i++) {
				double[] temp = new double[2];
				temp[0] = (data.get(i)[0] - mean_weight) / std_weight;
				temp[1] = data.get(i)[1];
				x.add(temp);
			}

			for (int t = 1; t <= T; t++) {
				double temp_B0 = 0;
				temp_B0 = B0 - n * db0_dMSE(B0, B1, x);
				B1 = B1 - n * db1_dMSE(B0, B1, x);
				B0 = temp_B0;
				System.out.print(t + " ");
				System.out.printf("%.4f ", B0);
				System.out.printf("%.4f ", B1);
				System.out.printf("%.4f\n", MSE(B0, B1, x));
			}
		}else if (flag == 800) {
			Random r = new Random();
			int item = r.nextInt(data.size());
			double n = Double.valueOf(args[1]);
			double T = Double.valueOf(args[2]);
			List<double[]> x = new ArrayList<double[]>();
			double mean_weight = 0;
			double std_weight = 0;
			double B0 = 0;
			double B1 = 0;
			
			for(int i = 0; i < data.size(); i++) {
				mean_weight += data.get(i)[0];
			}
			mean_weight /= (double)data.size();
			for (int i =0; i < data.size(); i++) {
				std_weight += Math.pow((data.get(i)[0] - mean_weight), 2);
			}
			std_weight = Math.sqrt(std_weight / (double)(data.size() - 1));
			
			for (int i = 0; i < data.size(); i++) {
				double[] temp = new double[2];
				temp[0] = (data.get(i)[0] - mean_weight) / std_weight;
				temp[1] = data.get(i)[1];
				x.add(temp);
			}

			for (int t = 1; t <= T; t++) {
				double temp_B0 = 0;
				item = r.nextInt(data.size());
				temp_B0 = B0 - n * 2 * (B0 + B1 * x.get(item)[0] - x.get(item)[1]);
				B1 = B0 - n * 2 * (B0 + B1 * x.get(item)[0] - x.get(item)[1]) * x.get(item)[0];
				B0 = temp_B0;
				System.out.print(t + " ");
				System.out.printf("%.4f ", B0);
				System.out.printf("%.4f ", B1);
				System.out.printf("%.4f\n", MSE(B0, B1, x));
			}
		}
		return;
	}
}
