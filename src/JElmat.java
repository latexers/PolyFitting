// Referenced classes of package jamlab:
//            RowColumnIndex

public class JElmat extends Matrix {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JElmat(int numRows, int numCols) {
		super(numRows, numCols);
	}

	public JElmat(int numRows, int numCols, double value) {
		super(numRows, numCols, value);
	}

	public JElmat(double array[][]) {
		super(array);
	}

	public JElmat(double array[][], int numRows, int numCols) {
		super(array, numRows, numCols);
	}

	public JElmat(double arrayVals[], int numRows) {
		super(arrayVals, numRows);
	}

	public static double[][] convertTo2D(double x[]) {
		double holder[][] = new double[1][];
		double temp[] = (double[]) x.clone();
		holder[0] = temp;
		return holder;
	}

	public static Matrix convertToMatrix(double x[]) {
		return new JElmat(convertTo2D(x));
	}

	public double[] matrixArrayRow(int rowNumber) {
		double result[][] = getArrayCopy();
		if (rowNumber > result.length - 1)
			throw new IllegalArgumentException(
					" matrixArrowRow : Row index is out-of-bound.");
		else
			return result[rowNumber];
	}

	public Matrix matrixRow(int rowNumber) {
		return convertToMatrix(matrixArrayRow(rowNumber));
	}

	public Matrix matrixColumn(int colNumber) {
		int rows = getRowDimension();
		int cols = getColumnDimension();
		if (colNumber > cols - 1)
			throw new IllegalArgumentException(
					"matrixColumn : Column index is out-of-bound.");
		else
			return (JElmat) getMatrix(0, rows - 1, colNumber, colNumber);
	}

	public static double[] flip(double a[]) {
		int len = a.length;
		double result[] = new double[len];
		for (int i = 0; i < len; i++)
			result[i] = a[len - 1 - i];

		return result;
	}

	public void flipLR() {
		int row = getRowDimension();
		double temp[][] = getArray();
		for (int i = 0; i < row; i++) {
			double singleRow[] = temp[i];
			temp[i] = flip(singleRow);
		}

	}

	public static Matrix flipLR(Matrix mat) {
		int row = mat.getRowDimension();
		double temp[][] = mat.getArray();
		for (int i = 0; i < row; i++) {
			double singleRow[] = temp[i];
			temp[i] = flip(singleRow);
		}

		return mat;
	}

	public void flipUD() {
		int row = getRowDimension();
		double result[][] = getArray();
		double temp[][] = (double[][]) result.clone();
		for (int i = 0; i < row; i++)
			result[i] = temp[row - 1 - i];

	}

	public static Matrix flipUD(Matrix mat) {
		int row = mat.getRowDimension();
		double result[][] = mat.getArray();
		double temp[][] = (double[][]) result.clone();
		for (int i = 0; i < row; i++)
			result[i] = temp[row - 1 - i];

		return mat;
	}

	public Matrix rot90() {
		return rot90(1);
	}

	public Matrix rot90(int quadrant) {
		int K = Math.abs(quadrant);
		K %= 4;
		Matrix internal = new Matrix(getArrayCopy());
		Matrix temp = null;
		switch (K) {
		default:
			break;

		case 0: // '\0'
			temp = internal;
			break;

		case 1: // '\001'
			if (quadrant > 0)
				temp = flipLR(internal).transpose();
			else
				temp = flipUD(internal).transpose();
			break;

		case 2: // '\002'
			if (quadrant > 0) {
				temp = flipLR(internal).transpose();
				temp = flipLR(temp).transpose();
			} else {
				temp = flipUD(internal).transpose();
				temp = flipUD(temp).transpose();
			}
			break;

		case 3: // '\003'
			if (quadrant > 0)
				temp = flipUD(internal).transpose();
			else
				temp = flipLR(internal).transpose();
			break;
		}
		return temp;
	}

	public double singleIndex(int ind) {
		double temp[] = getColumnPackedCopy();
		int len = temp.length;
		if (ind >= len)
			throw new ArrayIndexOutOfBoundsException(String.valueOf(String
					.valueOf((new StringBuffer("singleIndex : Index = "))
							.append(ind).append(", should be less than ")
							.append(len).append(" ."))));
		else
			return temp[ind];
	}

	public static Matrix rot90(Matrix mat) {
		return rot90(mat, 1);
	}

	public static Matrix rot90(Matrix matrix, int quadrant) {
		int K = Math.abs(quadrant);
		K %= 4;
		Matrix temp = null;
		Matrix internal = new Matrix(matrix.getArrayCopy());
		switch (K) {
		default:
			break;

		case 0: // '\0'
			temp = internal;
			break;

		case 1: // '\001'
			if (quadrant > 0)
				temp = flipLR(internal).transpose();
			else
				temp = flipUD(internal).transpose();
			break;

		case 2: // '\002'
			if (quadrant > 0) {
				temp = flipLR(internal).transpose();
				temp = flipLR(temp).transpose();
			} else {
				temp = flipUD(internal).transpose();
				temp = flipUD(temp).transpose();
			}
			break;

		case 3: // '\003'
			if (quadrant > 0)
				temp = flipUD(internal).transpose();
			else
				temp = flipLR(internal).transpose();
			break;
		}
		return temp;
	}

	public static Matrix eye(int m, int n)
			throws ArrayIndexOutOfBoundsException {
		if (m < 1 || n < 1)
			throw new ArrayIndexOutOfBoundsException(
					"eye : Index is less than 1.");
		double temp[][] = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				if (i == j)
					temp[i][j] = 1.0D;
				else
					temp[i][j] = 0.0D;

		}

		return new Matrix(temp);
	}

	public static Matrix eye(int n) {
		return eye(n, n);
	}

	public static Matrix ones(int n) throws ArrayIndexOutOfBoundsException {
		return ones(n, n);
	}

	public static Matrix ones(int m, int n)
			throws ArrayIndexOutOfBoundsException {
		if (m < 1 || n < 1)
			throw new ArrayIndexOutOfBoundsException(
					"ones : Index is less than 1.");
		else
			return new Matrix(m, n, 1.0D);
	}

	public static Matrix zeros(int n) throws ArrayIndexOutOfBoundsException {
		return zeros(n, n);
	}

	public static Matrix zeros(int m, int n)
			throws ArrayIndexOutOfBoundsException {
		if (m < 1 || n < 1)
			throw new ArrayIndexOutOfBoundsException(
					"zeros : Index is less than 1.");
		else
			return new Matrix(m, n, 0.0D);
	}

	public static RowColumnIndex find(double A[][]) {
		return find(A, 105, 0.0D);
	}

	public static RowColumnIndex find(double A[][], int relational, double value) {
		int row = 0;
		int col = 0;
		int count = 0;
		if (A == null)
			return new RowColumnIndex();
		row = A.length;
		col = A[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				switch (relational) {
				default:
					break;

				case 100: // 'd'
					if (A[i][j] > value)
						count++;
					break;

				case 101: // 'e'
					if (A[i][j] >= value)
						count++;
					break;

				case 102: // 'f'
					if (A[i][j] < value)
						count++;
					break;

				case 103: // 'g'
					if (A[i][j] <= value)
						count++;
					break;

				case 104: // 'h'
					if (A[i][j] == value)
						count++;
					break;

				case 105: // 'i'
					if (A[i][j] != value)
						count++;
					break;
				}

		}

		if (count != 0) {
			int row_ind[] = new int[count];
			int col_ind[] = new int[count];
			double arrayValues[] = new double[count];
			count = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++)
					switch (relational) {
					default:
						break;

					case 100: // 'd'
						if (A[i][j] > value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 101: // 'e'
						if (A[i][j] >= value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 102: // 'f'
						if (A[i][j] < value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 103: // 'g'
						if (A[i][j] <= value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 104: // 'h'
						if (A[i][j] == value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 105: // 'i'
						if (A[i][j] != value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;
					}

			}

			return new RowColumnIndex(row_ind, col_ind, arrayValues);
		} else {
			return new RowColumnIndex();
		}
	}

	public static RowColumnIndex find(Matrix mat) {
		return find(mat, 105, 0.0D);
	}

	public static RowColumnIndex find(Matrix mat, int relational, double value) {
		int row = 0;
		int col = 0;
		int count = 0;
		if (mat == null)
			return new RowColumnIndex();
		row = mat.getRowDimension();
		col = mat.getColumnDimension();
		double A[][] = mat.getArray();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				switch (relational) {
				default:
					break;

				case 100: // 'd'
					if (A[i][j] > value)
						count++;
					break;

				case 101: // 'e'
					if (A[i][j] >= value)
						count++;
					break;

				case 102: // 'f'
					if (A[i][j] < value)
						count++;
					break;

				case 103: // 'g'
					if (A[i][j] <= value)
						count++;
					break;

				case 104: // 'h'
					if (A[i][j] == value)
						count++;
					break;

				case 105: // 'i'
					if (A[i][j] != value)
						count++;
					break;
				}

		}

		if (count != 0) {
			int row_ind[] = new int[count];
			int col_ind[] = new int[count];
			double arrayValues[] = new double[count];
			count = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++)
					switch (relational) {
					default:
						break;

					case 100: // 'd'
						if (A[i][j] > value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 101: // 'e'
						if (A[i][j] >= value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 102: // 'f'
						if (A[i][j] < value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 103: // 'g'
						if (A[i][j] <= value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 104: // 'h'
						if (A[i][j] == value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;

					case 105: // 'i'
						if (A[i][j] != value) {
							row_ind[count] = i;
							col_ind[count] = j;
							arrayValues[count] = A[i][j];
							count++;
						}
						break;
					}

			}

			return new RowColumnIndex(row_ind, col_ind, arrayValues);
		} else {
			return new RowColumnIndex();
		}
	}

	public static Matrix vander(double a[]) throws Exception {
		return vander(a, 0);
	}

	public static Matrix vander(double a[], int numColumns) throws Exception {
		int arrayLength = a.length;
		int col = 0;
		double temp = 0.0D;
		if (numColumns < 0)
			throw new Exception(
					"Vander :- Column dimensions is out-of-bound, index limits boundries is >= 0 ");
		if (numColumns == 0)
			col = arrayLength;
		else
			col = numColumns;
		Matrix vanderMatrix = new Matrix(arrayLength, col, 1.0D);
		for (int i = 0; i < arrayLength; i++) {
			for (int j = 0; j < col - 1; j++) {
				temp = Math.pow(a[i], col - 1 - j);
				vanderMatrix.set(i, j, temp);
			}

		}

		return vanderMatrix;
	}

	public static double[][] repmat(double a[][], int m, int n)
			throws Exception {
		int row = a.length;
		int col = a[0].length;
		int countRow = 0;
		int countColumn = 0;
		if (m < 1 || n < 1)
			throw new Exception("Repmat :- Index should be at least 1.");
		int newRowDim = row * m;
		int newColDim = col * n;
		double result[][] = new double[newRowDim][];
		double tempHolder[] = new double[newColDim];
		for (int i = 0; i < newRowDim; i++) {
			for (int j = 0; j < newColDim; j++) {
				tempHolder[j] = a[countRow][countColumn++];
				if (countColumn == col)
					countColumn = 0;
			}

			if (++countRow == row)
				countRow = 0;
			result[i] = tempHolder;
			tempHolder = new double[newColDim];
		}

		return result;
	}

	public static Matrix repmat(Matrix matrix, int m, int n) throws Exception {
		double a[][] = matrix.getArrayCopy();
		int row = a.length;
		int col = a[0].length;
		int countRow = 0;
		int countColumn = 0;
		if (m < 1 || n < 1)
			throw new Exception("Repmat :- Index should be at least 1.");
		int newRowDim = row * m;
		int newColDim = col * n;
		double result[][] = new double[newRowDim][];
		double tempHolder[] = new double[newColDim];
		for (int i = 0; i < newRowDim; i++) {
			for (int j = 0; j < newColDim; j++) {
				tempHolder[j] = a[countRow][countColumn++];
				if (countColumn == col)
					countColumn = 0;
			}

			if (++countRow == row)
				countRow = 0;
			result[i] = tempHolder;
			tempHolder = new double[newColDim];
		}

		return new Matrix(result);
	}

	public static double[][] reshape(double a[][], int newrow, int newcol)
			throws Exception {
		int row = a.length;
		int col = a[0].length;
		int count = 0;
		double columnVector[][] = toColumnVector(a);
		if (row * col != newrow * newcol || newrow < 1 || newcol < 1)
			throw new Exception("Exception : reshape - OutofBoundIndex.");
		double result[][] = new double[newrow][newcol];
		for (int i = 0; i < newrow; i++) {
			for (int j = 0; j < newcol; j++)
				result[i][j] = columnVector[count++][0];

		}

		return result;
	}

	public static Matrix reshape(Matrix matrix, int newrow, int newcol)
			throws Exception {
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		int count = 0;
		Matrix columnVector = toColumnVector(matrix);
		if (row * col != newrow * newcol || newrow < 1 || newcol < 1)
			throw new Exception("Exception : reshape - OutofBoundIndex.");
		Matrix result = new Matrix(newrow, newcol);
		for (int i = 0; i < newrow; i++) {
			for (int j = 0; j < newcol; j++)
				result.set(i, j, columnVector.get(count++, 0));

		}

		return result;
	}

	public static double[][] toColumnVector(double a[][]) {
		int row = a.length;
		int col = a[0].length;
		double result[][];
		if (col == 1) {
			result = (double[][]) a.clone();
		} else {
			result = new double[row][1];
			int count = 0;
			for (int j = 0; j < col; j++) {
				for (int i = 0; i < row; i++)
					result[count++][0] = a[i][j];

			}

		}
		return result;
	}

	public static Matrix toColumnVector(Matrix matrix) {
		double a[] = matrix.getColumnPackedCopy();
		double result[][] = new double[1][];
		result[0] = a;
		Matrix matResult = new Matrix(result);
		return matResult.transpose();
	}

	public Matrix toMatrixColumnVector() {
		double a[] = getColumnPackedCopy();
		double result[][] = new double[1][];
		result[0] = a;
		Matrix matResult = new Matrix(result);
		return matResult.transpose();
	}

	public static double[][] toRowVector(double a[][]) {
		int row = a.length;
		int col = a[0].length;
		double result[][];
		if (row == 1) {
			result = (double[][]) a.clone();
		} else {
			result = new double[1][col];
			int count = 0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++)
					result[0][count++] = a[i][j];

			}

		}
		return result;
	}

	public static Matrix toRowVector(Matrix matrix) {
		double a[] = matrix.getRowPackedCopy();
		double result[][] = new double[1][];
		result[0] = a;
		return new Matrix(result);
	}

	public Matrix toMatrixRowVector() {
		double a[] = getRowPackedCopy();
		double result[][] = new double[1][];
		result[0] = a;
		return new JElmat(result);
	}

	public Matrix tril() {
		return tril(0);
	}

	public Matrix tril(int diagonal) {
		double temp[][] = getArrayCopy();
		int rows = getRowDimension();
		int cols = getColumnDimension();
		if (-(rows - 1) <= diagonal && diagonal <= cols - 1) {
			if (diagonal == 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (j > i)
							temp[i][j] = 0.0D;

				}

			} else if (diagonal < 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (j > i + diagonal)
							temp[i][j] = 0.0D;

				}

			} else if (diagonal > 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (j > i + diagonal)
							temp[i][j] = 0.0D;

				}

			}
			return new JElmat(temp);
		}
		if (diagonal >= cols)
			return new JElmat(temp);
		if (diagonal <= -rows) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++)
					temp[i][j] = 0.0D;

			}

		}
		return new JElmat(temp);
	}

	public Matrix triu() {
		return triu(0);
	}

	public Matrix triu(int diagonal) {
		double temp[][] = getArrayCopy();
		int rows = getRowDimension();
		int cols = getColumnDimension();
		if (-(rows - 1) <= diagonal && diagonal <= cols - 1) {
			if (diagonal == 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (i > j)
							temp[i][j] = 0.0D;

				}

			} else if (diagonal < 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (i + diagonal > j)
							temp[i][j] = 0.0D;

				}

			} else if (diagonal > 0) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++)
						if (i + diagonal > j)
							temp[i][j] = 0.0D;

				}

			}
			return new Matrix(temp);
		}
		if (diagonal <= -rows)
			return new Matrix(temp);
		if (diagonal >= cols) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++)
					temp[i][j] = 0.0D;

			}

		}
		return new Matrix(temp);
	}

	public boolean isRowVector() {
		int rows = getRowDimension();
		return rows == 1;
	}

	public boolean isColumnVector() {
		int cols = getColumnDimension();
		return cols == 1;
	}

	public Matrix diag() {
		return diag(0);
	}

	public Matrix diag(int K) {
		double arrayCopy[][] = getArrayCopy();
		int rows = getRowDimension();
		int cols = getColumnDimension();
		int smallDim = rows >= cols ? cols : rows;
		JElmat temp = new JElmat(arrayCopy);
		JElmat result = null;
		if (temp.isRowVector()) {
			result = new JElmat(cols, cols);
			for (int i = 0; i < cols; i++)
				result.set(i, i, temp.get(0, i));

			return result;
		}
		if (temp.isColumnVector()) {
			result = new JElmat(rows, rows);
			for (int i = 0; i < rows; i++)
				result.set(i, i, temp.get(i, 0));

			return result;
		}
		if (-(rows - 1) <= K && K <= cols - 1) {
			if (K == 0) {
				result = new JElmat(smallDim, 1);
				for (int i = 0; i < smallDim; i++)
					result.set(i, 0, temp.get(i, i));

			} else {
				if (K > 0)
					return diag();
				if (K < 0)
					return diag();
			}
		} else {
			return null;
		}
		temp = result;
		return temp;
	}

	public boolean isEqual(JElmat jmat) {
		int thisSizeRow = getRowDimension();
		int thisSizeCol = getColumnDimension();
		int rowSizeJmat = jmat.getRowDimension();
		int colSizeJmat = jmat.getColumnDimension();
		double thisArray[][] = null;
		double jmatArray[][] = null;
		boolean result = true;
		if (thisSizeRow != rowSizeJmat || thisSizeCol != colSizeJmat)
			return false;
		thisArray = getArray();
		jmatArray = jmat.getArray();
		label0: for (int i = 0; i < thisSizeRow; i++) {
			int j = 0;
			do {
				if (j >= thisSizeCol)
					continue label0;
				if (thisArray[i][j] != jmatArray[i][j]) {
					result = false;
					break label0;
				}
				j++;
			} while (true);
		}

		return result;
	}

	public static Matrix linspace(double leftBound, double rightBound) {
		return linspace(leftBound, rightBound, 100);
	}

	public static Matrix linspace(double leftBound, double rightBound,
			int nPoints) {
		double startX = 0.0D;
		double endX = 0.0D;
		boolean flip = false;
		if (nPoints < 1)
			throw new IllegalArgumentException(
					"linspace : Number of points should be at least 1 .");
		if (nPoints == 1) {
			double linVector[] = new double[1];
			linVector[0] = rightBound;
			return new Matrix(convertTo2D(linVector));
		}
		if (leftBound == rightBound) {
			double linVector[] = new double[nPoints];
			for (int i = 0; i < nPoints; i++)
				linVector[i] = leftBound;

			return new Matrix(convertTo2D(linVector));
		}
		if (rightBound < leftBound) {
			startX = rightBound;
			endX = leftBound;
			flip = true;
		} else {
			startX = leftBound;
			endX = rightBound;
		}
		double temp[] = new double[nPoints];
		double result[] = new double[nPoints];
		for (int p = 0; p < nPoints; p++) {
			if (p == 0) {
				temp[p] = 0.0D;
				result[p] = startX;
				continue;
			}
			if (p == nPoints - 1) {
				result[p] = endX;
			} else {
				temp[p] = temp[p - 1] + 1.0D;
				result[p] = startX + (temp[p] * (endX - startX))
						/ (double) (nPoints - 1);
			}
		}

		if (flip)
			result = flip(result);
		return new Matrix(convertTo2D(result));
	}

	public static Matrix logspace(double leftBound, double rightBound) {
		return logspace(leftBound, rightBound, 50);
	}

	public static Matrix logspace(double leftBound, double rightBound,
			int nPoints) {
		double startX = 0.0D;
		double endX = 0.0D;
		boolean flip = false;
		if (nPoints < 1)
			throw new IllegalArgumentException(
					"logspace : Number of points should be at least 1 .");
		if (nPoints == 1) {
			double linVector[] = new double[1];
			linVector[0] = Math.pow(10D, rightBound);
			return new Matrix(convertTo2D(linVector));
		}
		if (leftBound == rightBound) {
			double linVector[] = new double[nPoints];
			for (int i = 0; i < nPoints; i++)
				linVector[i] = Math.pow(10D, leftBound);

			return new Matrix(convertTo2D(linVector));
		}
		if (rightBound < leftBound) {
			startX = rightBound;
			endX = leftBound;
			flip = true;
		} else {
			startX = leftBound;
			endX = rightBound;
		}
		double temp[] = new double[nPoints];
		double result[] = new double[nPoints];
		for (int p = 0; p < nPoints; p++) {
			if (p == 0) {
				temp[p] = 0.0D;
				result[p] = Math.pow(10D, startX);
				continue;
			}
			if (p == nPoints - 1) {
				if (endX == 3.1415926535897931D)
					endX = Math.log(3.1415926535897931D) / Math.log(10D);
				result[p] = Math.pow(10D, endX);
			} else {
				temp[p] = temp[p - 1] + 1.0D;
				result[p] = Math.pow(10D, startX + (temp[p] * (endX - startX))
						/ (double) (nPoints - 1));
			}
		}

		if (flip)
			result = flip(result);
		return new Matrix(convertTo2D(result));
	}

	public static final int GREATER_THAN = 100;
	public static final int GREATER_THAN_AND_EQUAL_TO = 101;
	public static final int LESS_THAN = 102;
	public static final int LESS_THAN_AND_EQUAL_TO = 103;
	public static final int EQUAL_TO = 104;
	public static final int NOT_EQUAL_TO = 105;
}