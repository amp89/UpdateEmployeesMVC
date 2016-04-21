package data;

import java.util.List;

public class Results {
/*
 * TODO: i think this method is complete
 */
	private List<String> rowsReturned;
	private List<String> colsReturned;
	private int rowsAffected;
	private String errorMessage;
	public Results(List<String> rowsReturned, List<String> colsReturned, int rowsAffected, String errorStatement) {
		super();
		this.rowsReturned = rowsReturned;
		this.colsReturned = colsReturned;
		this.rowsAffected = rowsAffected;
		this.errorMessage = errorStatement;
	}
	public List<String> getRowsReturned() {
		return rowsReturned;
	}
	public void setRowsReturned(List<String> rowsReturned) {
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
	public void setErrorMessage(String errorStatement) {
		this.errorMessage = errorStatement;
	}
	
	@Override
	public String toString() {
		return "Results [rowsReturned=" + rowsReturned + ", colsReturned=" + colsReturned + ", rowsAffected="
				+ rowsAffected + ", errorStatement=" + errorMessage + "]";
	}
	
	
	
}
