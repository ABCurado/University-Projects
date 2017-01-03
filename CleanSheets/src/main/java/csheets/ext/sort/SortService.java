package csheets.ext.sort;

import csheets.core.Cell;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Simple Cell Sort service.
 * 
 * @author Renato Machado
 */
public class SortService {
    
    /**
     * Sorts a range of cells.
     * 
     * @param cells Cells to be sorted.
     * @param column Column to be sorted.
     * @param order Order of the sort.
     * @return Sorted cells.
     */
    public Cell[][] sortRangeOfCells(Cell[][] cells, int column, boolean order)
    {
        // Get the relative columns of the cells.
        // For example, the first column of the cells can be B2, with a column of 1.
        // However, we need to normalize that value to 0, since our cells array
        // starts it's indexing at 0.
        final int relativeColumn = column - cells[0][0].getAddress().getColumn();
        
        Comparator<Cell[]> comparator = (final Cell[] entry1, final Cell[] entry2) -> {
            final Cell cell1 = entry1[relativeColumn];
            final Cell cell2 = entry2[relativeColumn];
            
            return cell1.getValue().compareTo(cell2.getValue());
        };
        
        if (order) {
            Arrays.sort(cells, comparator);
        } else {
            Arrays.sort(cells, Collections.reverseOrder(comparator));
        }
        
        return cells;
    }
    
}
