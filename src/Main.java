public class Main {
	public static void main(String[] args) {
		// X轴
		double[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		// Y轴
		 double[] y = { 2.3, 2.3, 2.4, 2.8, 2.9, 2.6, 2.9, 3.2, 3.9, 4.0, 4.3, 4.2, 4.2, 4.0, 3.8, 4.0, 3.5, 3.3, 3.2, 2.8 };
		Polyfit polyfit1;
		
		try {
			polyfit1 = new Polyfit(x, y, 3);
			System.out.print(polyfit1.calValue(2.5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
