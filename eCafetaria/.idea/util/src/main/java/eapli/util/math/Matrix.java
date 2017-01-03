/**
 *
 */
package eapli.util.math;

/**
 * An immutable matrix.
 *
 * @author Paulo Gandra Sousa
 *
 */
public class Matrix {

	public Matrix(double[][] elems) {
		rows = elems.length + 1;
		cols = elems[0].length + 1;
		data = new double[rows][cols];
		copyData(elems, data);
	}

	public Matrix(Matrix other) {
		rows = other.data.length + 1;
		cols = other.data[0].length + 1;
		data = new double[rows][cols];
		copyData(other.data, data);
	}

	// indexes are 1-based
	public double elementAt(int i, int j) {
		return getAt(i - 1, j - 1);
	}

	public int size() {
		return rows * cols;
	}

	/* creates a square identity matrix */
	public static Matrix identity(int n) {
		final Matrix id = zero(n, n);
		for (int i = 0; i < n; i++) {
			// for (int j = 0; j < n; j++)
			// id.putAt(i, j, 0.0);
			id.putAt(i, i, 1.0);
		}
		return id;
	}

	/* creates a square zero matrix */
	public static Matrix zero(int n) {
		return zero(n, n);
	}

	public Vector row(int index) {
		if (index < 0 || index >= rows) {
			throw new IllegalArgumentException("index");
		}
		final Vector vec = new Vector(cols, Vector.VectorType.Row);
		for (int i = 0; i < cols; i++) {
			vec.putAt(i, getAt(index, i));
		}
		return vec;
	}

	public Vector column(int index) {
		if (index < 0 || index >= cols) {
			throw new IllegalArgumentException("index");
		}
		final Vector vec = new Vector(rows, Vector.VectorType.Column);
		for (int i = 0; i < rows; i++) {
			vec.putAt(i, getAt(i, index));
		}
		return vec;
	}

	/* creates a zero matrix */
	public static Matrix zero(int r, int c) {
		final Matrix z = new Matrix(r, c);
		return z;
	}

	/* creates a "zero" matrix with the same given value */
	public static Matrix zero(int r, int c, double zero) {
		final Matrix z = new Matrix(r, c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				z.putAt(i, j, zero);
			}
		}
		return z;
	}

	// return a new matrix obtained by multiplying a matrix by a scalar
	public Matrix scale(double k) {
		final Matrix c = new Matrix(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				c.putAt(i, j, k * getAt(i, j));
			}
		}
		return c;
	}

	/* add two matrices giving a third matrix */
	public Matrix add(Matrix other) {
		if (rows != other.rows || cols != other.cols) {
			throw new IllegalStateException("matrices must be same dimension");
		}

		final Matrix dest = new Matrix(rows, cols);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dest.putAt(i, j, getAt(i, j) + other.getAt(i, j));
			}
		}
		return dest;
	}

	/* subtract two matrices giving a third matrix */
	public Matrix subtract(Matrix other) {
		if (rows != other.rows || cols != other.cols) {
			throw new IllegalStateException("matrices must be same dimension");
		}

		final Matrix dest = new Matrix(rows, cols);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dest.putAt(i, j, getAt(i, j) - other.getAt(i, j));
			}
		}
		return dest;
	}

	/* multiply two matrices giving a third one */
	public Matrix multiply(Matrix other) {
		if (cols != other.rows) {
			throw new IllegalStateException("matrix 1 cols must = matrix 2 rows");
		}

		final Matrix dest = new Matrix(rows, other.cols);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				double cellval = 0.0;
				for (int k = 0; k < cols; k++) {
					cellval += getAt(i, k) * other.getAt(k, j);
				}
				dest.putAt(i, j, cellval);
			}
		}
		return dest;
	}

	/* The trace of a square matrix is the sum of its diagonal elements */
	public double trace() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix should be square");
		}

		double trace = 0;
		for (int i = 0; i < rows; i++) {
			trace += getAt(i, i);
		}
		return trace;
	}

	// indexes are 1-based
	public double cofactor(int i, int j) {
		// (-1)^(i+j)*Mij
		return java.lang.Math.pow(-1, i + j) * minor(i, j);
	}

	// indexes are 1-based
	public double minor(int i, int j) {
		return this.submatrix(i, j).determinant1();
	}

	public Matrix transpose() {
		final Matrix dest = new Matrix(cols, rows);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dest.putAt(j, i, getAt(i, j));
			}
		}
		return dest;
	}

	// returns a diagonal matrix with the same elements in this matrix's
	// diagonal
	public Matrix Diagonal() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix should be square");
		}

		final Matrix diag = zero(rows, cols);
		for (int i = 0; i < rows; i++) {
			diag.putAt(i, i, getAt(i, i));
		}
		return diag;
	}

	// returns a row vector (a matrix with just one row) with the elements in
	// this matrix's diagonal
	public Matrix DiagonalElements() {
		// if (!IsSquare)
		// throw new InvalidOperationException("matrix should be square");
		final int k = java.lang.Math.min(rows, cols);

		final Matrix diag = Matrix.zero(1, k + 1);
		for (int i = 0; i <= k; i++) {
			diag.putAt(0, i, getAt(i, i));
		}
		return diag;
	}

	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix submatrix(int index, Vector.VectorType typ) {
		switch (typ) {
		case Row:
			return withoutRow(index);

		case Column:
			return withoutColumn(index);

		}
		throw new IllegalStateException("should not happen - unknown VectorType");
	}

	// indexes are 1-based
	public Matrix submatrix(int rowToRemove, int colToRemove) {
		if (rowToRemove <= 0 || rowToRemove > rows) {
			throw new IllegalArgumentException("rowToRemove");
		}
		if (colToRemove <= 0 || colToRemove > cols) {
			throw new IllegalArgumentException("colToRemove");
		}
		if (rows - 1 <= 0 || cols - 1 <= 0) {
			throw new IllegalStateException("cannot remove only row/column");
		}

		final Matrix sub = new Matrix(rows - 1, cols - 1);
		int desti = 0;
		for (int i = 0; i < rows; i++) {
			if (i != rowToRemove - 1) {
				int destj = 0;
				for (int j = 0; j < cols; j++) {
					if (j != colToRemove - 1) {
						sub.putAt(desti, destj, getAt(i, j));
						destj++;
					}
				}
				desti++;
			}
		}
		return sub;
	}

	// indexes are 1-based
	public Matrix submatrix(int startRow, int endRow, int startCol, int endCol) {
		if (startRow <= 0 || startRow > rows) {
			throw new IllegalArgumentException("startRow");
		}
		if (endRow <= startRow || endRow > rows) {
			throw new IllegalArgumentException("endRow");
		}
		if (startCol <= 0 || startCol > cols) {
			throw new IllegalArgumentException("startCol");
		}
		if (endCol <= startCol || endCol > cols) {
			throw new IllegalArgumentException("endCol");
		}

		final Matrix sub = new Matrix(endRow - startRow, endCol - startCol);
		for (int i = startRow - 1; i < endRow; i++) {
			for (int j = startCol - 1; j < endCol; j++) {
				sub.putAt(i - startRow + 1, j - startCol + 1, getAt(i, j));
			}
		}
		return sub;
	}

	/* The vector formed by concatenating all the columns of the matrix */
	public Matrix vectorized() {
		final Matrix vec = new Matrix(rows * cols, 1);
		int k = 0;
		for (int j = 0; j < cols; j++) {
			for (int i = 0; i < rows; i++) {
				vec.putAt(k, 0, getAt(i, j));
				k++;
			}
		}
		return vec;
	}

	public boolean isUpperDiagonal() {
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < i; j++) {
				if (getAt(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isLowerDiagonal() {
		for (int i = 0; i < rows - 1; i++) {
			for (int j = i + 1; j < cols; j++) {
				if (getAt(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isInversible() {
		// X*X' = X'*X = I

		if (!isSquare()) {
			return false;
		}

		final Matrix inv = inverse1();
		final Matrix t1 = multiply(inv);
		return t1.equals(inv.multiply(this)) && t1.isIdentity();
	}

	public Matrix inverse2() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		// Formula used to Calculate Inverse:
		// inv(A) = 1/det(A) * adj(A)
		final double det = determinant2();
		if (det == 0) {
			throw new IllegalStateException("Determinant Equals 0, Not Invertible.");
		}

		return adjoint().scale(1 / det);
	}

	public Matrix adjoint() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		final Matrix adj = new Matrix(rows, cols);

		int ii, jj, ia, ja;
		double det;
		final int tms = rows;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ia = ja = 0;

				final Matrix ap = new Matrix(tms - 1, tms - 1);

				for (ii = 0; ii < tms; ii++) {
					for (jj = 0; jj < tms; jj++) {

						if ((ii != i) && (jj != j)) {
							ap.putAt(ia, ja, getAt(ii, jj));
							ja++;
						}

					}
					if ((ii != i) && (jj != j)) {
						ia++;
					}
					ja = 0;
				}

				det = ap.determinant2();
				adj.putAt(i, j, java.lang.Math.pow(-1, i + j) * det);
			}
		}

		return adj.transpose();
	}

	public Matrix upperTriangle() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		double f1 = 0;
		double temp = 0;
		final int tms = rows; // get This Matrix Size
		int v = 1;

		iDF = 1;

		final Matrix m = new Matrix(this);

		for (int col = 0; col < tms - 1; col++) {
			for (int row = col + 1; row < tms; row++) {
				v = 1;

				/* outahere: */ while (m.getAt(col, col) == 0) // check if 0 in
				                                               // diagonal
				{
					// if so switch until not
					if (col + v >= tms) // check if switched all rows
					{
						iDF = 0;
						// break outahere;
						continue;
					} else {
						for (int c = 0; c < tms; c++) {
							temp = m.getAt(col, c);
							m.putAt(col, c, m.getAt(col + v, c)); // switch rows
							m.putAt(col + v, c, temp);
						}
						v++; // count row switchs
						iDF = iDF * -1; // each switch changes determinant
						                // factor
					}
				}

				if (m.getAt(col, col) != 0) {
					try {
						f1 = (-1) * m.getAt(row, col) / m.getAt(col, col);
						for (int i = col; i < tms; i++) {
							m.putAt(row, i, f1 * m.getAt(col, i) + m.getAt(row, i));
						}
					} catch (final Exception e) {
						// System.out.println("Still Here!!!");
					}
				}
			}
		}

		return m;
	}

	public double determinant2() {
		final Matrix triang = upperTriangle();

		double det = 1;
		for (int i = 0; i < rows; i++) {
			det *= triang.getAt(i, i);
		} // multiply down diagonal

		det *= iDF; // adjust w/ determinant factor

		return det;
	}

	private int iDF = 0; // determinant factor

	public int columnCount() {
		return cols;
	}

	public int rowCount() {
		return rows;
	}

	public boolean isSquare() {
		return rows == cols;
	}

	public boolean isSingular() {
		return determinant1() == 0.0;
	}

	public boolean isDiagonal() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) != 0.0 && i != j) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isDiagonallyDominant() {
		if (!isSquare()) {
			throw new IllegalStateException();
		}

		boolean flagIneq = false;
		for (int i = 0; i < rows; i++) {
			double accum = 0.0;
			for (int j = 0; j < cols; j++) {
				if (i != j) {
					accum += java.lang.Math.abs(getAt(i, j));
				}
			}
			if (java.lang.Math.abs(data[i][i]) < accum) {
				return false;
			}
			if (java.lang.Math.abs(data[i][i]) > accum) {
				flagIneq = true;
			}
		}
		return flagIneq;
	}

	public boolean isSimetric() {
		// return this.Equals(this.Transpose());

		if (!isSquare()) {
			throw new IllegalStateException("must be square");
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) != getAt(j, i)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isSkewSimetric() {
		// return this.Equals(this.Transpose().Scale(-1));

		if (!isSquare()) {
			throw new IllegalStateException("must be square");
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) + getAt(j, i) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Matrix.class) {
			return false;
		}

		final Matrix other = (Matrix) obj;
		if (rows != other.rows || cols != other.cols) {
			return false;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) != other.getAt(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean isIdentity() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) != 0.0 && i != j) {
					return false;
				}
				if (i == j && getAt(i, j) != 1.0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isZero() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getAt(i, j) != 0.0) {
					return false;
				}
			}
		}
		return true;
	}

	public double determinant1() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		double d = 0;
		for (int i = 0; i < cols; i++) {
			double p1 = 1.0;
			double p2 = 1.0;
			double p3 = getAt(i, 0);
			int k = i;
			for (int j = 1; j < cols; j++) {
				k = (k + 1) % cols;
				p1 *= getAt(j, k);
				p2 *= getAt(cols - j, k);
			}
			p3 *= (p1 - p2);
			d += p3;
		}
		return d;
	}

	/* ??? */
	public Matrix inverse1() {
		if (isSingular()) {
			throw new IllegalStateException("matrix is singular");
		}

		final Matrix dest = new Matrix(rows, cols);
		final double[][] d = new double[rows][cols + 1];

		for (int i = 0; i < rows; i++) {
			int nrow = i - 1;
			int ncol = i - 1;
			for (int j = 0; j < rows; j++) {
				nrow = (nrow + 1) % rows;
				if (j == 0) {
					d[j * (cols + 1)][cols] = 1;
				} else {
					d[j * (cols + 1)][cols] = 0;
				}
				for (int k = 0; k < cols; k++) {
					ncol = (ncol + 1) % cols;
					d[j * (cols + 1)][k] = data[nrow * cols][ncol];
				}
			}
			NSolve(rows, d);
			nrow = i - 1;
			for (int j = 0; j < rows; j++) {
				nrow = (nrow + 1) % rows;
				dest.putAt(nrow * cols, i, d[j * (cols + 1)][cols]);
			}
		}
		return dest;
	}

	private Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		data = new double[rows][cols];
	}

	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix withoutRow(int index) {
		if (index <= 0 || index > rows) {
			throw new IllegalArgumentException("index");
		}
		if (rows - 1 <= 0) {
			throw new IllegalStateException("cannot remove single row");
		}

		final Matrix sub = new Matrix(rows - 1, cols);
		int desti = 0;
		for (int i = 0; i < rows; i++) {
			if (i != index - 1) {
				for (int j = 0; j < cols; j++) {
					sub.putAt(desti, j, getAt(i, j));
				}
				desti++;
			}
		}
		return sub;
	}

	// removes a column from the matrix
	// indexes are 1-based
	public Matrix withoutColumn(int index) {
		if (index <= 0 || index > cols) {
			throw new IllegalArgumentException("index");
		}
		if (cols - 1 <= 0) {
			throw new IllegalStateException("cannot remove only column");
		}
		final Matrix sub = new Matrix(rows, cols - 1);
		for (int i = 0; i < rows; i++) {
			int destj = 0;
			for (int j = 0; j < cols; j++) {
				if (j != index - 1) {
					sub.putAt(i, destj, getAt(i, j));
					destj++;
				}
			}
		}
		return sub;
	}

	/* ??? */
	private void NSolve(int rows, double[][] data) {
		int i, j;

		final int cols = rows + 1;
		for (i = 0; i < rows; i++) {
			for (j = i; j < rows && data[j][j] == 0.0; j++) {
				;
			}
			if (data[j][j] == 0.0) {
				throw new IllegalStateException("singular matrix");
			}
			if (j != i) {
				for (int k = 0; k < cols; k++) {
					final double dtemp = data[i][k];
					data[i][k] = data[j][k];
					data[j][k] = dtemp;
				}
			}
			for (j = cols - 1; j >= 0; j--) {
				data[i][j] /= data[i][i];
			}
			for (j = i + 1; j < rows; j++) {
				for (int k = cols - 1; k >= i; k--) {
					data[j][k] -= data[j][i] * data[i][k];
				}
			}
		}
		for (i = rows - 2; i >= 0; i--) {
			for (j = cols - 2; j > i; j--) {
				data[i][cols - 1] -= data[i][j] * data[j][cols - 1];
				data[i][j] = 0;
			}
		}
	}

	// indexes are 0-based
	private void putAt(int i, int j, double v) {
		data[i][j] = v;
	}

	// indexes are 0-based
	private double getAt(int i, int j) {
		return data[i][j];
	}

	private void copyData(double[][] source, double[][] dest) {
		for (int i = 0; i <= data.length; i++) {
			for (int j = 0; j <= data[0].length; j++) {
				dest[i][j] = source[i][j];
			}
		}
	}

	private final int		 rows;
	private final int		 cols;
	private final double[][] data;

}
