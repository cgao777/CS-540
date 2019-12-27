import java.util.Scanner;
import java.io.*;

public class NeuralNet {
	public static double activation(double[] x, double[] w) {
		double temp = 0;
		for (int i = 0; i < w.length; i++) {
			temp += x[i] * w[i];
		}
		return 1 / (1 + Math.exp(-temp));
	}

	public static void main(String[] args) {
		int flag = Integer.valueOf(args[0]);
		double[] w1 = new double[5];
		double[] w2 = new double[5];
		double[] w3 = new double[3];
		double[] a1 = new double[5];
		double a2[] = new double[3];
		double a3;

		w1[0] = Double.valueOf(args[1]);
		w1[1] = Double.valueOf(args[2]);
		w1[2] = Double.valueOf(args[3]);
		w1[3] = Double.valueOf(args[4]);
		w1[4] = Double.valueOf(args[5]);
		w2[0] = Double.valueOf(args[6]);
		w2[1] = Double.valueOf(args[7]);
		w2[2] = Double.valueOf(args[8]);
		w2[3] = Double.valueOf(args[9]);
		w2[4] = Double.valueOf(args[10]);
		w3[0] = Double.valueOf(args[11]);
		w3[1] = Double.valueOf(args[12]);
		w3[2] = Double.valueOf(args[13]);

		if (flag == 100) {
			a1[0] = 1;
			a1[1] = Double.valueOf(args[14]);
			a1[2] = Double.valueOf(args[15]);
			a1[3] = Double.valueOf(args[16]);
			a1[4] = Double.valueOf(args[17]);
			a2[0] = 1;
			a2[1] = activation(a1, w1);
			a2[2] = activation(a1, w2);
			a3 = activation(a2, w3);
			System.out.printf("%.5f %.5f\n%.5f\n", a2[1], a2[2], a3);

		} else if (flag == 200) {
			a1[0] = 1;
			a1[1] = Double.valueOf(args[14]);
			a1[2] = Double.valueOf(args[15]);
			a1[3] = Double.valueOf(args[16]);
			a1[4] = Double.valueOf(args[17]);
			a2[0] = 1;
			a2[1] = activation(a1, w1);
			a2[2] = activation(a1, w2);
			a3 = activation(a2, w3);
			double y = Double.valueOf(args[18]);
			double h1_3 = (a3 - y) * a3 * (1 - a3);
			System.out.printf("%.5f\n", h1_3);

		} else if (flag == 300) {
			a1[0] = 1;
			a1[1] = Double.valueOf(args[14]);
			a1[2] = Double.valueOf(args[15]);
			a1[3] = Double.valueOf(args[16]);
			a1[4] = Double.valueOf(args[17]);
			a2[0] = 1;
			a2[1] = activation(a1, w1);
			a2[2] = activation(a1, w2);
			a3 = activation(a2, w3);
			double y = Double.valueOf(args[18]);
			double h1_3 = (a3 - y) * a3 * (1 - a3);
			double h1_2 = h1_3 * w3[1] * a2[1] * (1 - a2[1]);
			double h2_2 = h1_3 * w3[2] * a2[2] * (1 - a2[2]);
			System.out.printf("%.5f %.5f\n", h1_2, h2_2);

		} else if (flag == 400) {
			a1[0] = 1;
			a1[1] = Double.valueOf(args[14]);
			a1[2] = Double.valueOf(args[15]);
			a1[3] = Double.valueOf(args[16]);
			a1[4] = Double.valueOf(args[17]);
			a2[0] = 1;
			a2[1] = activation(a1, w1);
			a2[2] = activation(a1, w2);
			a3 = activation(a2, w3);
			double y = Double.valueOf(args[18]);
			double h1_3 = (a3 - y) * a3 * (1 - a3);
			double h1_2 = h1_3 * w3[1] * a2[1] * (1 - a2[1]);
			double h2_2 = h1_3 * w3[2] * a2[2] * (1 - a2[2]);
			double[] d1_2 = new double[5];
			double[] d2_2 = new double[5];
			double[] d1_3 = new double[3];
			for (int i = 0; i < 5; i++) {
				d1_2[i] = h1_2 * a1[i];
				d2_2[i] = h2_2 * a1[i];
			}
			for (int i = 0; i < 3; i++) {
				d1_3[i] = h1_3 * a2[i];
			}
			System.out.printf("%.5f %.5f %.5f\n", d1_3[0], d1_3[1], d1_3[2]);
			System.out.printf("%.5f %.5f %.5f %.5f %.5f\n", d1_2[0], d1_2[1], d1_2[2], d1_2[3], d1_2[4]);
			System.out.printf("%.5f %.5f %.5f %.5f %.5f\n", d2_2[0], d2_2[1], d2_2[2], d2_2[3], d2_2[4]);

		} else if (flag == 500) {
			double n = Double.valueOf(args[14]);
			try {
				File f = new File("train.csv");
				Scanner sc = new Scanner(f);
				while (sc.hasNext()) {
					String[] s = sc.nextLine().split(",");
					a1[0] = 1;
					a1[1] = Double.parseDouble(s[0]);
					a1[2] = Double.parseDouble(s[1]);
					a1[3] = Double.parseDouble(s[2]);
					a1[4] = Double.parseDouble(s[3]);
					a2[0] = 1;
					a2[1] = activation(a1, w1);
					a2[2] = activation(a1, w2);
					a3 = activation(a2, w3);
					double y = Double.valueOf(s[4]);
					double h1_3 = (a3 - y) * a3 * (1 - a3);
					double h1_2 = h1_3 * w3[1] * a2[1] * (1 - a2[1]);
					double h2_2 = h1_3 * w3[2] * a2[2] * (1 - a2[2]);
					double[] d1_2 = new double[5];
					double[] d2_2 = new double[5];
					double[] d1_3 = new double[3];
					for (int i = 0; i < 5; i++) {
						d1_2[i] = h1_2 * a1[i];
						d2_2[i] = h2_2 * a1[i];
						w1[i] -= n * d1_2[i];
						w2[i] -= n * d2_2[i];
					}
					for (int i = 0; i < 3; i++) {
						d1_3[i] = h1_3 * a2[i];
						w3[i] -= n * d1_3[i];
					}
					System.out.printf("%.5f %.5f %.5f %.5f %.5f ", w1[0], w1[1], w1[2], w1[3], w1[4]);
					System.out.printf("%.5f %.5f %.5f %.5f %.5f ", w2[0], w2[1], w2[2], w2[3], w2[4]);
					System.out.printf("%.5f %.5f %.5f\n", w3[0], w3[1], w3[2]);
					File f2 = new File("eval.csv");
					Scanner sc2 = new Scanner(f2);
					double E = 0;
					while (sc2.hasNext()) {
						s = sc2.nextLine().split(",");
						a1[0] = 1;
						a1[1] = Double.parseDouble(s[0]);
						a1[2] = Double.parseDouble(s[1]);
						a1[3] = Double.parseDouble(s[2]);
						a1[4] = Double.parseDouble(s[3]);
						a2[0] = 1;
						a2[1] = activation(a1, w1);
						a2[2] = activation(a1, w2);
						a3 = activation(a2, w3);
						y = Double.valueOf(s[4]);
						E += Math.pow(a3 - y, 2) / 2;
					}
					System.out.printf("%.5f\n", E);
					sc2.close();
				}
				sc.close();
			} catch (FileNotFoundException ex) {
				System.out.println("File Not Found.");
			}
		}else if(flag == 600) {
			double n = Double.valueOf(args[14]);
			try {
				File f = new File("train.csv");
				Scanner sc = new Scanner(f);
				while (sc.hasNext()) {
					String[] s = sc.nextLine().split(",");
					a1[0] = 1;
					a1[1] = Double.parseDouble(s[0]);
					a1[2] = Double.parseDouble(s[1]);
					a1[3] = Double.parseDouble(s[2]);
					a1[4] = Double.parseDouble(s[3]);
					a2[0] = 1;
					a2[1] = activation(a1, w1);
					a2[2] = activation(a1, w2);
					a3 = activation(a2, w3);
					double y = Double.valueOf(s[4]);
					double h1_3 = (a3 - y) * a3 * (1 - a3);
					double h1_2 = h1_3 * w3[1] * a2[1] * (1 - a2[1]);
					double h2_2 = h1_3 * w3[2] * a2[2] * (1 - a2[2]);
					double[] d1_2 = new double[5];
					double[] d2_2 = new double[5];
					double[] d1_3 = new double[3];
					for (int i = 0; i < 5; i++) {
						d1_2[i] = h1_2 * a1[i];
						d2_2[i] = h2_2 * a1[i];
						w1[i] -= n * d1_2[i];
						w2[i] -= n * d2_2[i];
					}
					for (int i = 0; i < 3; i++) {
						d1_3[i] = h1_3 * a2[i];
						w3[i] -= n * d1_3[i];
					}
				}
				sc.close();
				File f2 = new File("test.csv");
				Scanner sc2 = new Scanner(f2);
				double count = 0;
				double correct = 0;
				while(sc2.hasNext()) {
					String[] s = sc2.nextLine().split(",");
					a1[0] = 1;
					a1[1] = Double.parseDouble(s[0]);
					a1[2] = Double.parseDouble(s[1]);
					a1[3] = Double.parseDouble(s[2]);
					a1[4] = Double.parseDouble(s[3]);
					a2[0] = 1;
					a2[1] = activation(a1, w1);
					a2[2] = activation(a1, w2);
					a3 = activation(a2, w3);
					count++;
					int result;
					if(a3 <= 0.5) {
						result = 0;
					}else {
						result = 1;
					}
					if(result == Integer.parseInt(s[4]))
						correct++;
				System.out.printf("%d %d %.5f\n", Integer.parseInt(s[4]), result, a3);
				}
				System.out.printf("%.2f\n", correct / count);
			}catch (FileNotFoundException ex) {
					System.out.println("File Not Found.");
			}
		}
	}
}
