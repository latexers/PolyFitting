public final class JElfun {

	private JElfun() {
	}

	public static Matrix sign(Matrix matrix) {
		double d[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (d[i][j] > 0.0D) {
					result.set(i, j, 1.0D);
					continue;
				}
				if (d[i][j] == 0.0D) {
					result.set(i, j, 0.0D);
					continue;
				}
				if (d[i][j] < 0.0D)
					result.set(i, j, -1D);
			}

		}

		return result;
	}

	public static Matrix isInfinte(Matrix matrix) {
		double d[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col, 1.0D);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (!Double.isInfinite(d[i][j]))
					result.set(i, j, 0.0D);

		}

		return result;
	}

	public static Matrix pow(double base, Matrix index) {
		double d[][] = index.getArray();
		int row = index.getColumnDimension();
		int col = index.getRowDimension();
		double result[][] = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result[i][j] = Math.pow(base, d[i][j]);

		}

		return new Matrix(result);
	}

	public static Matrix pow(Matrix base, double index) {
		double d[][] = base.getArray();
		int row = base.getColumnDimension();
		int col = base.getRowDimension();
		double result[][] = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result[i][j] = Math.pow(d[i][j], index);

		}

		return new Matrix(result);
	}

	public static Matrix exp(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, Math.exp(internal[i][j]));

		}

		return result;
	}

	public static Matrix sin(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, Math.sin(internal[i][j]));

		}

		return result;
	}

	public static Matrix asin(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (internal[i][j] <= 1.0D || internal[i][j] >= -1D)
					result.set(i, j, Math.asin(internal[i][j]));
				else
					result.set(i, j, (0.0D / 0.0D));

		}

		return result;
	}

	public static Matrix cos(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, Math.cos(internal[i][j]));

		}

		return result;
	}

	public static Matrix acos(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (internal[i][j] <= 1.0D || internal[i][j] >= -1D)
					result.set(i, j, Math.acos(internal[i][j]));
				else
					result.set(i, j, (0.0D / 0.0D));

		}

		return result;
	}

	public static Matrix sqrt(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (internal[i][j] < 0.0D)
					result.set(i, j, (0.0D / 0.0D));
				else
					result.set(i, j, Math.sqrt(internal[i][j]));

		}

		return result;
	}

	public static Matrix tan(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, Math.tan(internal[i][j]));

		}

		return result;
	}

	public static Matrix atan(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, Math.atan(internal[i][j]));

		}

		return result;
	}

	public static Matrix sync(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				if (internal[i][j] != 0.0D)
					result.set(i, j, Math.sin(internal[i][j]) / internal[i][j]);
				else
					result.set(i, j, 1.0D);

		}

		return result;
	}

	public static Matrix sinh(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, 0.5D * (Math.exp(internal[i][j]) - Math
						.exp(-internal[i][j])));

		}

		return result;
	}

	public static Matrix cosh(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(i, j, 0.5D * (Math.exp(internal[i][j]) + Math
						.exp(-internal[i][j])));

		}

		return result;
	}

	public static Matrix tanh(Matrix matrix) {
		double internal[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		Matrix result = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				result.set(
						i,
						j,
						(Math.exp(internal[i][j]) - Math.exp(-internal[i][j]))
								/ (Math.exp(internal[i][j]) + Math
										.exp(-internal[i][j])));

		}

		return result;
	}

	public static Matrix log10(Matrix matrix) {
		return logN(10D, matrix);
	}

	public static Matrix logN(double base, Matrix matrix) {
		Matrix result = null;
		try {
			result = logN(matrix, base);
		} catch (Exception exception) {
		}
		return result;
	}

	public static Matrix logN(Matrix matrix, double base) throws Exception {
		double b = 1.0D;
		double temp[][] = matrix.getArray();
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		double result[][] = new double[row][col];
		if (base <= (double) 0)
			throw new Exception(
					"logN : Negative or zero base result in a Complex Number or negative Infinity.");
		b = Math.log(base);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (temp[i][j] <= 0.0D)
					throw new Exception(
							"logN : Negative or zero base result in a Complex Number or negative Infinity.");
				result[i][j] = Math.log(temp[i][j]) / b;
			}

		}

		return new Matrix(result);
	}
}