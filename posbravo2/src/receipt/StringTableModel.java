package receipt;
import javax.swing.table.AbstractTableModel;

/**
 * Class extends the AbstractTableModel class to create a read-only tabled model of String data
 * types with adjustable row and column length. The class includes methods to search, sort and filter
 * these values. 
 * @author Stephen Collins
 *
 */
public class StringTableModel extends AbstractTableModel {
	public static final int INCLUDE = 1; //These constants determine the behavior of the search, sort and filter methods
	public static final int EXCLUDE = 2; 
	public static final int ASCENDING = 3; 
	public static final int DESCENDING = 4;
	public static final int MATCHES = 5;
	public static final int CONTAINS = 6;
	public static final int STARTS_WITH = 7;
	public static final int ENDS_WITH = 8;
	
	private static final long serialVersionUID = 1L; //Default value
	private static final int NOT_FOUND = -1; //Returned by search methods when the search key is not found
	private String[] columnNames;
	private String[] columnNamesCopy; //Used to expand the columnNames array
	private String[][] data;
	private String[][] dataCopy; //Used to expand the the data array
	private int rowIndexMap[];  //Use is explained at the getRowIndexMap() method
	
	/**
	 * Instantiates the StringTableModel class with a String array of column names 
	 * and a 2D String array of data.
	 * @param columnNames
	 * @param data
	 */
	StringTableModel(String[] columnNames, String[][] data){
		this.columnNames = columnNames;
		this.data = data;
	}
	/**
	 * Instantiates the StringTableModel class with a String array of column names
	 * and an empty 2D String array of data.
	 * @param columnNames
	 */
	StringTableModel(String[] columnNames) {
		this.columnNames = columnNames;
		data = new String[0][0];
	}
	/**
	 * Instantiates a StringTableModel class with an empty String array for column names
	 * and an empty 2D String array of data.
	 */
	StringTableModel() {
		columnNames = new String[0];
		data = new String[0][0];
	}
	/**
	 * Returns the number of columns in the table model
	 */
	public int getColumnCount() { return columnNames.length; }
	/**
	 * Returns the number of rows in the table model
	 */
	public int getRowCount() { return data.length; }
	/**
	 * Returns the name of the column for the argument index
	 */
	public String getColumnName(int col) { return columnNames[col]; }
	/**
	 * Returns the String value at the argument index in the 2D data array
	 */
	public String getValueAt(int row, int col) { return data[row][col]; }
	/**
	 * Sets the String value at the argument index in the 2D data array
	 * to a new String value.
	 * @param newValue
	 * @param row
	 * @param col
	 */
	public void setValueAt(String newValue, int row, int col) { data[row][col] = newValue; }
	/**
	 * Sets the table to be read-only
	 */
	public boolean isCellEditable(int row, int col) { return false; }
	/**
	 * Expands the table model's data array to include a new row. The length of the argument
	 * array should be equal to the number of columns in the table model. Adding a new row
	 * to the table model invalidates the index map generated sorting the table model.
	 * @param newRow
	 */
	public void addRow(String[] newRow) {
		dataCopy = new String[data.length+1][columnNames.length];
		
		for(int row=0; row < data.length; row++) {
			for(int col=0; col < columnNames.length; col++) {
				dataCopy[row][col] = data[row][col];
			}
		}
		for(int col=0; col < columnNames.length; col++)
			dataCopy[data.length][col] = newRow[col];
		
		data = dataCopy;
		dataCopy = null;
		rowIndexMap = null;
	}
	/**
	 * Expands the table model's data array to include a new row. If the length of the 
	 * argument array is less than the number of columns in the table model, the remaining
	 * columns will be filled with empty Strings. Adding a new row to the table model 
	 * invalidates the index map generated sorting the table model.
	 * @param newRow
	 */
	public void addBufferedRow(String[] newRow) {
		dataCopy = new String[data.length+1][columnNames.length];
		
		for(int row=0; row < data.length; row++) {
			for(int col=0; col < columnNames.length; col++) {
				dataCopy[row][col] = data[row][col];
			}
		}
		for(int col=0; col < newRow.length && col < columnNames.length; col++)
			dataCopy[data.length][col] = newRow[col];
		for(int col=newRow.length; col < columnNames.length; col++)
			dataCopy[data.length][col]= "";
		
		data = dataCopy;
		dataCopy = null;
		rowIndexMap = null;
	}
	/**
	 * Returns all values within a table model row in the form of a String array
	 * @param row
	 * @return
	 */
	public String[] getRowData(int row) {
		try {
			columnNamesCopy = new String[columnNames.length];
			for(int col=0; col < columnNames.length; col++)
				columnNamesCopy[col] = data[row][col];
		
			return columnNamesCopy;
		}
		finally {
			columnNamesCopy = null;
		}
	}
	/**
	 * Removes the row at the argument index from this table model. Deleting a row
	 * invalidates the index map generated by sorting the table model.
	 * @param oldRow
	 */
	public void deleteRow(int deleteIndex) {
		dataCopy = new String[data.length-1][columnNames.length];
		
		for(int row=0; row < deleteIndex; row++) {
			for(int col=0; col < columnNames.length; col++) {
				dataCopy[row][col] = data[row][col];
			}
		}
		for(int row= deleteIndex+1; row < data.length; row++) {
			for(int col=0; col < columnNames.length; col++) {
				dataCopy[row-1][col] = data[row][col];
			}
		}
		
		data = dataCopy;
		dataCopy = null;
		rowIndexMap = null;
	}
	/**
	 * Removes all rows from this table model. Deleting all rows invalidates
	 * the index map generated by sorting the table model.
	 */
	public void deleteAllRows() {
		data = new String[0][0];
		rowIndexMap = null;
	}
	/**
	 * Sets all of the values for the row at the argument index to the values within the argument
	 * String array. The length of the argument String array should be equal to the number of columns
	 * in the table model.
	 * @param rowData
	 * @param row
	 */
	public void setRow(String[] rowData, int row) {
		for(int col=0; col < columnNames.length; col++)
			data[row][col] = rowData[col];
	}
	/**
	 * Sets all of the values for the row at the argument index to the values within the argument
	 * String array. If the length of the argument array is less than the number of columns in the
	 * table model, the remaining columns will be overwritten by empty Strings.
	 * @param rowData
	 * @param row
	 */
	public void setBufferedRow(String[] rowData, int row) {
		for(int col=0; col < rowData.length && col < columnNames.length; col++)
			data[row][col] = rowData[col];
		for(int col=rowData.length; col < columnNames.length; col++)
			data[row][col] = "";
	}
	/**
	 * Adds a new column to the table model and fills it with empty Strings. 
	 * @param newColumn
	 */
	public void addColumn(String newColumn) {
		columnNamesCopy = new String[columnNames.length+1];
		
		for(int col=0; col < columnNames.length; col++)
			columnNamesCopy[col] = columnNames[col];
		columnNamesCopy[columnNames.length] = newColumn;
		
		dataCopy = new String[data.length][columnNames.length+1];
		
		for(int row=0; row < data.length; row++)
			for(int col=0; col < columnNames.length; col++)
				dataCopy[row][col] = data[row][col];
		for(int row=0; row < data.length; row++)
			dataCopy[row][columnNames.length]= "";
		
		columnNames = columnNamesCopy;
		columnNamesCopy = null;
		data = dataCopy;
		dataCopy = null;
	}
	/**
	 * Deletes a column from the table model.
	 * @param col
	 */
	public void deleteColumn(int col) {
		columnNamesCopy = new String[columnNames.length-1];
		
		for(int count=0; count < col; count++)
			columnNamesCopy[count] = columnNames[count];
		for(int count=col+1; count < columnNames.length; count++)
			columnNamesCopy[count-1] = columnNames[count];
		
		dataCopy = new String[data.length][columnNames.length-1];
		
		for(int row=0; row < data.length; row++)
			for(int count=0; count < col; count++)
					dataCopy[row][count] = data[row][count];
		for(int row=0; row < data.length; row++)
			for(int count=col+1; count < columnNames.length; count++)
				dataCopy[row][count-1] = data[row][count];
		
		columnNames = columnNamesCopy;
		columnNamesCopy = null;
		data = dataCopy;
		dataCopy = null;
	}
	/**
	 * Sets the name of the column at the argument index to a new String value
	 * @param newColumn
	 * @param col
	 */
	public void setColumnName(String newColumn, int col) {
		columnNames[col] = newColumn;
	}
	/**
	 * Searches every String value in the table model for a key value and returns
	 * the row index of the first exact match or -1 if a match is not found.
	 * @param key
	 * @return
	 */
	public int searchAll(String key) {
		return searchAll(0,0,key,MATCHES);
	}
	/**
	 * Searches every String value in the table model for a key value using one of
	 * the following StringTableModel constants:
	 * 
	 * MATCHES - Searches for an exact match between the String value and key value.
	 * CONTAINS - Tests whether the String value contains the key value.
	 * STARTS_WITH - Tests whether the String value starts with the key value.
	 * ENDS_WITH - Tests whether the String value ends with the key value. 
	 * 
	 * Returns the row index of the first match or -1 if a match is not found.
	 * @param key
	 * @param searchParameter
	 * @return
	 */
	public int searchAll(String key, int searchParameter) {
		return searchAll(0,0,key,searchParameter);
	}
	/**
	 * Searches every String value in the table model beginning at the argument
	 * row and column indices for a key value and returns the row index of the first 
	 * exact match or -1 if a match is not found.
	 * @param startRow
	 * @param startCol
	 * @param key
	 * @return
	 */
	public int searchAll(int startRow, int startCol, String key) {
		return searchAll(startRow,startCol,key,MATCHES);
	}
	/**
	 * Searches every String value in the table model beginning at the argument row
	 * and column indices for a key value using one of the following StringTableModel 
	 * constants:
	 * 
	 * MATCHES - Searches for an exact match between the String value and key value.
	 * CONTAINS - Tests whether the String value contains the key value.
	 * STARTS_WITH - Tests whether the String value starts with the key value.
	 * ENDS_WITH - Tests whether the String value ends with the key value. 
	 * 
	 * Returns the row index of the first match or -1 if a match is not found.
	 * @param startRow
	 * @param startCol
	 * @param key
	 * @param searchParameter
	 * @return
	 */
	public int searchAll(int startRow,int startCol, String key, int searchParameter) {
		if(searchParameter == MATCHES) {
			for(int col=0; col < columnNames.length; col++)
				for(int row=0; row < data.length; row++) 
					if(data[row][col].equals(key))
						return row;
		}
		else if(searchParameter == CONTAINS) {
			for(int col=0; col < columnNames.length; col++)
				for(int row=0; row < data.length; row++) 
					if(data[row][col].indexOf(key) > -1)
						return row;
		}
		else if(searchParameter == STARTS_WITH) {
			for(int col=0; col < columnNames.length; col++)
				for(int row=0; row < data.length; row++) 
					if(data[row][col].startsWith(key))
						return row;
		}
		else if(searchParameter == ENDS_WITH) {
			for(int col=0; col < columnNames.length; col++)
				for(int row=0; row < data.length; row++) 
					if(data[row][col].endsWith(key))
						return row;
		}
		return NOT_FOUND;
	}
	/**
	 * Searches the column at the argument column index for a String key value and returns
	 * the row index of the first exact match or -1 if a match is not found.
	 * @param col
	 * @param key
	 * @return
	 */
	public int searchColumn(int col, String key) {
		return searchColumn(0,col,key,MATCHES);
	}
	/**
	 * Searches the column at the argument column index for a String key value using one
	 * of the following StringTableModel constants:
	 * 
	 * MATCHES - Searches for an exact match between the String value and key value.
	 * CONTAINS - Tests whether the String value contains the key value.
	 * STARTS_WITH - Tests whether the String value starts with the key value.
	 * ENDS_WITH - Tests whether the String value ends with the key value. 
	 * 
	 * Returns the row index of the first match or -1 if a match is not found.
	 * @param col
	 * @param key
	 * @param searchParameter
	 * @return
	 */
	public int searchColumn(int col, String key, int searchParameter) {
		return searchColumn(0,col,key,searchParameter);
	}
	/**
	 * Searches the column at the argument column index beginning at the argument
	 * row index for a String key value and returns the row index of the first
	 * exact match or -1 if a match if not found.
	 * @param startRow
	 * @param col
	 * @param key
	 * @return
	 */
	public int searchColumn(int startRow, int col, String key) {
		return searchColumn(startRow,col,key,MATCHES);
	}
	/**
	 * Searches the column at the argument column index beginning at the argument
	 * row index for a String key value using one of the following 
	 * StringTableModel constants:
	 * 
	 * MATCHES - Searches for an exact match between the String value and key value.
	 * CONTAINS - Tests whether the String value contains the key value.
	 * STARTS_WITH - Tests whether the String value starts with the key value.
	 * ENDS_WITH - Tests whether the String value ends with the key value. 
	 * 
	 * Returns the row index of the first match or -1 if a match is not found.
	 */
	public int searchColumn(int startRow, int col, String key, int searchParameter) {
		if (searchParameter == MATCHES) {
			for(int row=startRow; row < data.length; row++) 
				if(data[row][col].equals(key))
					return row;
		}
		else if (searchParameter == CONTAINS) {
			for(int row=startRow; row < data.length; row++) 
				if(data[row][col].indexOf(key) > -1)
					return row;
		}
		else if (searchParameter == STARTS_WITH) {
			for(int row=startRow; row < data.length; row++) 
				if(data[row][col].startsWith(key))
					return row;
		}
		else if (searchParameter == ENDS_WITH) {
			for(int row=startRow; row < data.length; row++) 
				if(data[row][col].endsWith(key))
					return row;
		}
		
		return NOT_FOUND;
	}
	/**
	 * Searches the column at the argument column index for a String key value
	 * and returns the row index of the first exact match or -1 if a match is not found.
	 * 
	 * Binary search is a quick search method which assumes the column contains a sorted
	 * collection of unique String values. Exhaustive searching of non-unique columns should
	 * be performed by the default search methods. This method assumes the column is sorted 
	 * in ascending order.
	 * @param col
	 * @param key
	 * @return
	 */
	public int binarySearch(int col, String key) {
		return binarySearch(0, col, key, ASCENDING);
	}
	/**
	 * Searches the column at the argument column index for a String key value
	 * and returns the row index of the first exact match or -1 if a match is not found.
	 * 
	 * Binary search is a quick search method which assumes the column contains a sorted
	 * collection of unique String values, sorted according to the following StringTableModel
	 * constants:
	 * 
	 * ASCENDING - String values are sorted in lexographical order from top to bottom
	 * DESCENDING - String values are sorted in lexographical order from bottom to top.
	 * 
	 * Exhaustive searching of non-unique columns should be performed by the default search methods.
	 * @param col
	 * @param key
	 * @param sortOrder
	 * @return
	 */
	public int binarySearch(int col, String key, int sortOrder) {
		return binarySearch(0, col, key, sortOrder);
	}
	/**
	 * Searches the column at the argument column index beginning at the argument row index
	 * for a String key value and returns the row index of the first exact match or -1 if
	 * a match is not found.
	 * 
	 * Binary search is a quick search method which assumes the column contains a sorted
	 * collection of unique String values. Exhaustive searching of non-unique columns should
	 * be performed by the default search methods. This method assumes the column is sorted 
	 * in ascending order.
	 * @param startRow
	 * @param col
	 * @param key
	 * @return
	 */
	public int binarySearch(int startRow, int col, String key) {
		return binarySearch(startRow, col, key, ASCENDING);
	}
	/**
	 * Searches the column at the argument column index beginning at the argument row
	 * index for a String key value and returns the row index of the first exact match 
	 * or -1 if a match is not found.
	 * 
	 * Binary search is a quick search method which assumes the column contains a sorted
	 * collection of unique String values, sorted according to the follwoing StringTableModel
	 * constants:
	 * 
	 * ASCENDING - String values are sorted in lexographical order from top to bottom
	 * DESCENDING - String values are sorted in lexographical order from bottom to top.
	 * 
	 * Exhaustive searching of non-unique columns should be performed by the default search methods.
	 * @param startRow
	 * @param col
	 * @param key
	 * @param sortOrder
	 * @return
	 */
	public int binarySearch(int startRow, int col, String key, int sortOrder) {
		if(sortOrder == ASCENDING) {
			int minIndex = startRow;
			int maxIndex = data.length-1;
			int midIndex;
			
			while(maxIndex >= minIndex) {
				midIndex = (maxIndex + minIndex) / 2;
				
				if(data[midIndex][col].compareToIgnoreCase(key) < 0) {
					minIndex = midIndex+1;
				}
				else if(data[midIndex][col].compareToIgnoreCase(key) > 0) {
					maxIndex = midIndex-1;
				}
				else
					return midIndex;
			}
		}
		else if(sortOrder == DESCENDING) {
			int minIndex = startRow;
			int maxIndex = data.length-1;
			int midIndex;
			
			while(maxIndex >= minIndex) {
				midIndex = (maxIndex + minIndex) / 2;
				
				if(data[midIndex][col].compareToIgnoreCase(key) > 0) {
					minIndex = midIndex+1;
				}
				else if(data[midIndex][col].compareToIgnoreCase(key) < 0) {
					maxIndex = midIndex-1;
				}
				else
					return midIndex;
			}
		}
		return NOT_FOUND;
	}
	/**
	 * Sorts the table model according the the values within the column at the 
	 * argument column index using the following StringTableModel constants:
	 * 
	 * ASCENDING - String values are sorted in lexographical order from top to bottom
	 * DESCENDING - String values are sorted in lexographical order from bottom to top.
	 * 
	 * @param sortConstant
	 * @param col
	 */
	public void sort(int col, int sortOrder) {
		dataCopy = new String[data.length][columnNames.length];
		rowIndexMap = new int[data.length];
		for(int count=0; count < rowIndexMap.length; count++)
			rowIndexMap[count] = count;
		
		if(sortOrder == ASCENDING) {
			int storedIndex;
			
			for(int count = rowIndexMap.length-1; count > 0; count--)
				for(int index=0; index < count; index++)
					if(data[rowIndexMap[index]][col].compareToIgnoreCase(data[rowIndexMap[index+1]][col]) > 0) {
						storedIndex = rowIndexMap[index];
						rowIndexMap[index] = rowIndexMap[index+1];
						rowIndexMap[index+1] = storedIndex;
					}
		}
		else if(sortOrder == DESCENDING){
			int storedIndex;
			
			for(int count = rowIndexMap.length-1; count > 0; count--)
				for(int index=0; index < count; index++)
					if(data[rowIndexMap[index]][col].compareToIgnoreCase(data[rowIndexMap[index+1]][col]) < 0) {
						storedIndex = rowIndexMap[index];
						rowIndexMap[index] = rowIndexMap[index+1];
						rowIndexMap[index+1] = storedIndex;
					}
		}
				
		for(int row=0; row < data.length; row++)
			for(int column=0; column < columnNames.length; column++)
				dataCopy[row][column] = data[rowIndexMap[row]][column];
		
		data = dataCopy;
		dataCopy = null;
	}
	/**
	 * Whenever the table model is sorted, an integer array is generated which contains the pre-sorted indices of
	 * the table models rows arranged in their post-sort order.
	 * 
	 * If a table containing the following data:
	 * Index	Data
	 * 0		E
	 * 1		B
	 * 2		F
	 * 3		C
	 * 4		D
	 * 5		A
	 * 
	 * Was sorted in ascending order with the original index numbers retained:
	 * Index	Data
	 * 5		A
	 * 1		B
	 * 3		C
	 * 4		D
	 * 0		E
	 * 2		F
	 * 
	 * The row index map stores these original index numbers. It can be used to synchronize an object array with the
	 * table model. If the table model is sorted, the row index map can be retrieved and used to quickly sort the
	 * object array so that the index numbers of the array and table model are synchronized.
	 * 
	 * Example code:
	 * 
	 * int[] rowIndexMap = stringTableModel.getRowIndexMap();
	 * 
	 * for(int index=0; index < objectArray.length; index++)
	 * 		sortedObjectArray[index] = objectArray[rowIndexMap[index]];
	 * 
	 * @return
	 */
	public int[] getRowIndexMap() { 
		return rowIndexMap; 
	}
	/**
	 * Filters the table model data by checking a String filter against the String values of
	 * the column at the argument column index. Uses the following StringTableModel constants
	 * to determine filter behavior:
	 * 
	 * INCLUDE - Any value which matches the filter is included in the table model
	 * EXCLUDE - Any value which matches the filter is excluded from the table model
	 * 
	 * Matches against the filter are determined using the following StringTableModel constants:
	 * 
	 * MATCHES - Searches for an exact match between the String value and filter.
	 * CONTAINS - Tests whether the String value contains the filter.
	 * STARTS_WITH - Tests whether the String value starts with the filter.
	 * ENDS_WITH - Tests whether the String value ends with the filter. 
	 * 
	 * Any values filtered from the table model are removed from the table model's data array
	 * and must be re-entered by the calling class if they wish to be viewed again.
	 * 
	 * @param col
	 * @param parameter
	 * @param filterConstant
	 * @param searchConstant
	 */
	public void filterData(int col, String filter, int filterMethod, int filterParameter) {
		if(filterMethod == INCLUDE) {
			if(filterParameter == MATCHES) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(data[row][col].equals(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == CONTAINS) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(data[row][col].indexOf(filter) > -1) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == STARTS_WITH) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(data[row][col].startsWith(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == ENDS_WITH) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(data[row][col].endsWith(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
		}
		else if(filterMethod == EXCLUDE) {
			if(filterParameter == MATCHES) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(!data[row][col].equals(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == CONTAINS) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(data[row][col].indexOf(filter) == -1) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == STARTS_WITH) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(!data[row][col].startsWith(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}
			else if(filterParameter == ENDS_WITH) {
				dataCopy = new String[data.length][columnNames.length];
				int filteredRows = 0;
				
				for(int row=0; row < data.length; row++) 
					if(!data[row][col].endsWith(filter)) {
						for(int count=0; count < columnNames.length; count++)
							dataCopy[filteredRows][count] = data[row][count];
						filteredRows++;
					}
				
				data = new String[filteredRows][columnNames.length];
				for(int row=0; row < filteredRows; row++)
					for(int count=0; count < columnNames.length; count++)
						data[row][count] =  dataCopy[row][count];
				
				dataCopy = null;
				rowIndexMap = null;
			}			
		}
	}
}
