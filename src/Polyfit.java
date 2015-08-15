import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * Title: Java Matrix Laboratory
 * <p>
 * Description:
 * <p>
 * Copyright: Copyright (c) 2000
 * <p>
 * 
 * @author Sione .K. Palu
 * @version 1.0
 */
public class Polyfit {
	private Matrix R;
	private double[] polynomialCoefficients;
	private int degreeOfFreedom;
	private double norm;
	private double yIntercept;
	private Matrix polyCoeffMatrix;
	int order;

	public Polyfit(double[] _x, double[] _y, int order) throws Exception {
		this.order = order;
		int xLength = _x.length, yLength = _y.length;
		Matrix y2D = new Matrix(JElmat.convertTo2D(_y));
		Matrix yTranspose = y2D.transpose();

		if (xLength != yLength) {
			throw new Exception(
					" Polyfit :- The lengths of the 2-input array parameters must be equal.");
		}
		if (xLength < 2) {
			throw new Exception(
					" Polyfit :- There must be at least 2 data points for polynomial fitting.");
		}
		if (order < 0) {
			throw new Exception(
					" Polyfit :- The polynomial fitting order cannot be less than zero.");
		}
		if (order >= xLength) {
			throw new Exception(" Polyfit :- The polynomial order = " + order
					+ " , must be less than the number of data points = "
					+ xLength);
		}
		Matrix tempMatrix = null;
		try {
			Matrix vander = JElmat.vander(_x, order + 1);
			tempMatrix = vander;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		QRDecomposition qr = new QRDecomposition(tempMatrix); Matrix Q = qr.getQ(); R = qr.getR();
		Matrix result = R.inverse().times(Q.transpose().times(yTranspose));
		double[][] arrayResult = result.transpose().getArray();
		polynomialCoefficients = arrayResult[0];
		degreeOfFreedom = yLength - (order + 1);
		Matrix r = yTranspose.minus(tempMatrix.times(result));
		norm = r.norm2();
		polyCoeffMatrix = new Matrix(JElmat.convertTo2D(polynomialCoefficients));
		yIntercept = polynomialCoefficients[polynomialCoefficients.length - 1];

	}// end constructor

	public double[] getPolynomialCoefficients() {
		return polynomialCoefficients;
	}

	public Matrix getR() {
		return R;
	}

	public int getDegreeOfFreedom() {
		return degreeOfFreedom;
	}

	public double getNorm() {
		return norm;
	}

	public double getYIntercept() {
		return yIntercept;
	}

	public Matrix getPolyCoeffMatrix() {
		return polyCoeffMatrix;
	}

	public int getOrder() {
		return order;
	}

	public double calValue(double x) {
		double value = 0;
		for (int i = 0; i < polynomialCoefficients.length; i++) {
			value += polynomialCoefficients[i]
					* Math.pow(x, polynomialCoefficients.length - 1 - i);
		}
		return value;
	}

	public double[] calValues(double[] x) {
		double[] y = new double[x.length];
		for(int i = 0; i < x.length; i++) {
			y[i] = calValue(x[i]);
		}

		return y;
	}
	public double[] interpolation(double maxX, double minX,double step) {
		LinkedList<Double> xList = new LinkedList<>();
		for (double x = minX; x < maxX; x = x + step) {
			xList.add(x);
		}

		double[] reslut = new double[xList.size()];
		for(int i = 0; i < reslut.length; i++) {
			reslut[i] = xList.get(i);
		}
		return  reslut;
	}

}// ---------------------------- End Class Definition
// ------------------------------