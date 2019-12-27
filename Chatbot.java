import java.util.*;
import java.io.*;

public class Chatbot {
	private static String filename = "./corpus.txt";

	private static ArrayList<Integer> readCorpus() {
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int i = sc.nextInt();
					corpus.add(i);
				} else {
					sc.next();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return corpus;
	}

	static public void main(String[] args) {
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if (flag == 100) {
			int w = Integer.valueOf(args[1]);
			int count = 0;
			// TODO count occurence of w
			for (int i = 0; i < corpus.size(); i++) {
				if (corpus.get(i) == w)
					count++;
			}
			System.out.println(count);
			double p = ((double) (count + 1) / (228548 + 4700));
			System.out.printf("%.7f\n", p);
		} else if (flag == 200) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			// TODO generate
			ArrayList<Double> temp = new ArrayList<Double>();
			temp.add(0d);
			for (int i = 0; i < 4700; i++) {
				int count = 0;
				for (int j = 0; j < corpus.size(); j++) {
					if (corpus.get(j) == i)
						count++;
				}
				temp.add(((double) (count + 1) / (228548 + 4700)) + temp.get(i));
			}
			double r = (double) n1 / n2;
			for (int i = 1; i < temp.size(); i++) {
				if (r <= temp.get(i)) {
					System.out.println(i - 1);
					System.out.printf("%.7f\n", temp.get(i - 1));
					System.out.printf("%.7f\n", temp.get(i));
					break;
				}
			}
		} else if (flag == 300) {
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			int count1 = 0;
			int count2 = 0;
			// TODO
			for (int i = 0; i < corpus.size() - 1; i++) {
				if (corpus.get(i) == h) {
					count2++;
					if (corpus.get(i + 1) == w) {
						count1++;
					}
				}
			}
			System.out.println(count1);
			System.out.println(count2);
			double p = ((double) (count1 + 1) / (count2 + 4700));
			System.out.printf("%.7f\n", p);
		} else if (flag == 400) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			// TODO
			ArrayList<Double> temp = new ArrayList<Double>();
			temp.add(0d);
			for (int i = 0; i < 4700; i++) {
				int count1 = 0;
				int count2 = 0;
				for (int j = 0; j < corpus.size() - 1; j++) {
					if (corpus.get(j) == h) {
						count2++;
						if (corpus.get(j + 1) == i) {
							count1++;
						}
					}
				}
				temp.add(((double) (count1 + 1) / (count2 + 4700)) + temp.get(i));
			}
			double r = (double) n1 / n2;
			for (int i = 1; i < temp.size(); i++) {
				if (r <= temp.get(i)) {
					System.out.println(i - 1);
					System.out.printf("%.7f\n", temp.get(i - 1));
					System.out.printf("%.7f\n", temp.get(i));
					break;
				}
			}
		} else if (flag == 500) {
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);
			// TODO
			int count1 = 0;
			int count2 = 0;
			for (int j = 0; j < corpus.size() - 2; j++) {
				if (corpus.get(j) == h1 && corpus.get(j + 1) == h2) {
					count2++;
					if (corpus.get(j + 2) == w) {
						count1++;
					}
				}
			}
			System.out.println(count1);
			System.out.println(count2);
			double p = ((double) (count1 + 1) / (count2 + 4700));
			System.out.printf("%.7f\n", p);

		} else if (flag == 600) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);
			// TODO
			ArrayList<Double> temp = new ArrayList<Double>();
			temp.add(0d);
			for (int i = 0; i < 4700; i++) {
				int count1 = 0;
				int count2 = 0;
				for (int j = 0; j < corpus.size() - 2; j++) {
					if (corpus.get(j) == h1 && corpus.get(j + 1) == h2) {
						count2++;
						if (corpus.get(j + 2) == i) {
							count1++;
						}
					}
				}
				temp.add(((double) (count1 + 1) / (count2 + 4700)) + temp.get(i));
			}
			double r = (double) n1 / n2;
			for (int i = 1; i < temp.size(); i++) {
				if (r <= temp.get(i)) {
					System.out.println(i - 1);
					System.out.printf("%.7f\n", temp.get(i - 1));
					System.out.printf("%.7f\n", temp.get(i));
					break;
				}
			}
		} else if (flag == 700) {
			int seed = Integer.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);
			int h1 = 0, h2 = 0;

			Random rng = new Random();
			if (seed != -1)
				rng.setSeed(seed);

			if (t == 0) {
				// TODO Generate first word using r
				double r = rng.nextDouble();
				ArrayList<Double> temp = new ArrayList<Double>();
				temp.add(0d);
				for (int i = 0; i < 4700; i++) {
					int count = 0;
					for (int j = 0; j < corpus.size(); j++) {
						if (corpus.get(j) == i)
							count++;
					}
					temp.add(((double) (count + 1) / (228548 + 4700)) + temp.get(i));
				}
				for (int i = 1; i < temp.size(); i++) {
					if (r <= temp.get(i)) {
						h1 = i - 1;
						break;
					}
				}
				System.out.println(h1);
				if (h1 == 9 || h1 == 10 || h1 == 12) {
					return;
				}

				// TODO Generate second word using r
				r = rng.nextDouble();
				temp.clear();
				temp.add(0d);
				for (int i = 0; i < 4700; i++) {
					int count1 = 0;
					int count2 = 0;
					for (int j = 0; j < corpus.size() - 1; j++) {
						if (corpus.get(j) == h1) {
							count2++;
							if (corpus.get(j + 1) == i) {
								count1++;
							}
						}
					}
					temp.add(((double) (count1 + 1) / (count2 + 4700)) + temp.get(i));
				}
				for (int i = 1; i < temp.size(); i++) {
					if (r <= temp.get(i)) {
						h2 = i - 1;
						break;
					}
				}
				System.out.println(h2);
			} else if (t == 1) {
				h1 = Integer.valueOf(args[3]);
				// TODO Generate second word using r
				double r = rng.nextDouble();
				ArrayList<Double> temp = new ArrayList<Double>();
				temp.add(0d);
				for (int i = 0; i < 4700; i++) {
					int count1 = 0;
					int count2 = 0;
					for (int j = 0; j < corpus.size() - 1; j++) {
						if (corpus.get(j) == h1) {
							count2++;
							if (corpus.get(j + 1) == i) {
								count1++;
							}
						}
					}
					temp.add(((double) (count1 + 1) / (count2 + 4700)) + temp.get(i));
				}
				for (int i = 1; i < temp.size(); i++) {
					if (r <= temp.get(i)) {
						h2 = i - 1;
						break;
					}
				}
				System.out.println(h2);
			} else if (t == 2) {
				h1 = Integer.valueOf(args[3]);
				h2 = Integer.valueOf(args[4]);
			}

			while (h2 != 9 && h2 != 10 && h2 != 12) {
				double r = rng.nextDouble();
				int w = 0;
				// TODO Generate new word using h1,h2
				int count1 = 0;
				int count2 = 0;

				ArrayList<Double> temp = new ArrayList<Double>();
				temp.add(0d);
				for (int i = 0; i < 4700; i++) {
					count1 = 0;
					count2 = 0;
					for (int j = 0; j < corpus.size() - 2; j++) {
						if (corpus.get(j) == h1 && corpus.get(j + 1) == h2) {
							count2++;
							if (corpus.get(j + 2) == i) {
								count1++;
							}
						}
					}
					temp.add(((double) (count1 + 1) / (count2 + 4700)) + temp.get(i));
				}
				for (int i = 1; i < temp.size(); i++) {
					if (r <= temp.get(i)) {
						w = i - 1;
						break;
					}
				}
				System.out.println(w);
				h1 = h2;
				h2 = w;
			}
		}

		return;
	}
}
