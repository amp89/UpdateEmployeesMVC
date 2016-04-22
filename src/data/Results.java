package data;

import java.util.List;

public class Results {
/*
 * TODO: i think this method is complete
 */
	private List<List<String>> rowsReturned;
	private List<String> colsReturned;
	private int rowsAffected;
	private String errorMessage = "";
	
	
	
	public Results(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public Results(int rowsAffected) {
		super();
		this.rowsAffected = rowsAffected;
	}

	public Results(List<List<String>> rowsReturned, List<String> colsReturned) {
		super();
		this.rowsReturned = rowsReturned;
		this.colsReturned = colsReturned;
	}

	public Results(List<List<String>> rowsReturned, List<String> colsReturned, int rowsAffected, String errorMessage) {
		super();
		this.rowsReturned = rowsReturned;
		this.colsReturned = colsReturned;
		this.rowsAffected = rowsAffected;
		this.errorMessage = errorMessage;
	}

	public List<List<String>> getRowsReturned() {
		return rowsReturned;
	}

	public void setRowsReturned(List<List<String>> rowsReturned) {
		this.rowsReturned = rowsReturned;
	}

	public List<String> getColsReturned() {
		return colsReturned;
	}

	public void setColsReturned(List<String> colsReturned) {
		this.colsReturned = colsReturned;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}

	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "Results [rowsReturned=" + rowsReturned + ", colsReturned=" + colsReturned + ", rowsAffected="
				+ rowsAffected + ", errorMessage=" + errorMessage + "]";
	}
	
	
	
	
}
