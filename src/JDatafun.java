public final class JDatafun {

	private JDatafun() {
	}

	public static Matrix min(Matrix X, Matrix Y) {
		int x_rows = X.getRowDimension();
		int x_cols = X.getColumnDimension();
		int y_rows = Y.getRowDimension();
		double Xarray[][] = X.getArray();
		double Yarray[][] = Y.getArray();
		Matrix result = null;
		if (x_rows != y_rows || x_cols != y_rows)
			throw new IllegalArgumentException(
					"Error : Incompatible matrix dimensions.");
		result = new Matrix(x_rows, x_cols);
		for (int i = 0; i < x_rows; i++) {
			for (int j = 0; j < x_cols; j++)
				result.set(i, j, Xarray[i][j] >= Yarray[i][j] ? Yarray[i][j]
						: Xarray[i][j]);

		}

		return result;
	}

	public static Matrix sum(Matrix matrix) {
		return sum(matrix, 1);
	}

	public static Matrix sum(Matrix matrix, int Dim) {
		double internal[][] = matrix.getArrayCopy();
		double temp = 0.0D;
		Dim = Math.abs(Dim);
		Dim %= 2;
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		double summing[][];
		if (Dim == 1) {
			summing = new double[1][col];
			for (int j = 0; j < col; j++) {
				for (int i = 0; i < row; i++)
					temp += internal[i][j];

				summing[0][j] = temp;
				temp = 0.0D;
			}

		} else {
			summing = new double[row][1];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++)
					temp += internal[i][j];

				summing[i][0] = temp;
				temp = 0.0D;
			}

		}
		return new Matrix(summing);
	}
}