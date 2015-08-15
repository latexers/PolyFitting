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

public class Polyval {

	private double[] Yout;
	private Matrix youtMatrix;
	private double[] ErrorBounds;
	private Matrix ErrorBoundsMatrix;

	public Polyval(double[] _x, Polyfit polyfit) {
		double[] polyCoeff = polyfit.getPolynomialCoefficients();
		int coeffLen = polyCoeff.length;
		int degree = polyfit.getDegreeOfFreedom();
		double normr = polyfit.getNorm();
		Matrix R = polyfit.getR();
		Matrix _xMatrix = new Matrix(JElmat.convertTo2D(_x));

		youtMatrix = JElmat.zeros(1, _xMatrix.getColumnDimension());
		Matrix pC;
		for (int i = 0; i < coeffLen; i++) {
			pC = new Matrix(1, _xMatrix.getColumnDimension(), polyCoeff[i]);
			youtMatrix.arrayTimesEquals(_xMatrix).plusEquals(pC);
		}
		int order = coeffLen;
		Matrix V = null;
		Matrix errorDelta = null;
		try {
			V = JElmat.vander(_x, order);
		} catch (Exception ex) {
		}
		Matrix Rinv = R.inverse();
		Matrix E = V.times(Rinv);
		Matrix matOnes = null;
		Matrix e = null;

		if (order == 0) {
			matOnes = JElmat.ones(E.getRowDimension(), E.getColumnDimension());
			e = JElfun.sqrt(matOnes.plus(E.arrayTimes(E)));
		} else {
			Matrix SumT = JDatafun.sum(E.arrayTimes(E).transpose()).transpose();
			int rowDim = SumT.getRowDimension();
			int colDim = SumT.getColumnDimension();
			matOnes = JElmat.ones(rowDim, colDim);
			e = JElfun.sqrt(matOnes.plus(SumT));
		}

		if (degree == 1) {
			double[][] inf = new double[1][1];
			inf[0][0] = Double.POSITIVE_INFINITY;
			try {
				errorDelta = new Matrix(JElmat.repmat(inf, e.getRowDimension(),
						e.getColumnDimension()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			errorDelta = e.times(normr / Math.sqrt(degree));
		}

		double[][] tempDelta = { { 0. }, { 0. } }; // initialize

		try {
			tempDelta = JElmat.reshape(errorDelta, 1, _x.length).getArrayCopy();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ErrorBounds = tempDelta[0];
		ErrorBoundsMatrix = new Matrix(JElmat.convertTo2D(ErrorBounds));
		double[][] yM = youtMatrix.getArrayCopy();
		Yout = yM[0];
	}// ----End constructor----

	public Matrix getYoutMatrix() {
		return youtMatrix;
	}

	public double[] getYout() {
		return Yout;
	}

	public double[] getErrorBounds() {
		return ErrorBounds;
	}

	public Matrix getErrorBoundsMatrix() {
		return ErrorBoundsMatrix;
	}

}// ------------------------ End Class Definition -------------------------