// Referenced classes of package jamlab:
//            JElmat

public class RowColumnIndex {

	public RowColumnIndex() {
	}

	public RowColumnIndex(int r_index[], int c_index[], double values[]) {
		_rowIndex = r_index;
		_colIndex = c_index;
		_values = values;
		_totalElements = r_index.length;
		_elementValuesMatrix = new Matrix(JElmat.convertTo2D(values));
	}

	public int[] getRowIndex() {
		return _rowIndex;
	}

	public int[] getColumnIndex() {
		return _colIndex;
	}

	public double[] getElementValues() {
		return _values;
	}

	public int getTotalElements() {
		return _totalElements;
	}

	public Matrix getElementValuesInMatrix() {
		return _elementValuesMatrix;
	}

	private int _rowIndex[];
	private int _colIndex[];
	private double _values[];
	private int _totalElements;
	private Matrix _elementValuesMatrix;
}