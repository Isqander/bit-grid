package grid.bit.model;

import java.io.Serializable;

public class CellId  implements Serializable {
    private Long gridRow;
    private Long gridColumn;

    public CellId() {
    }

    public CellId(Long gridRow, Long gridColumn) {
        this.gridRow = gridRow;
        this.gridColumn = gridColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellId cellId = (CellId) o;

        if (!gridRow.equals(cellId.gridRow)) return false;
        return gridColumn.equals(cellId.gridColumn);
    }

    @Override
    public int hashCode() {
        int result = gridRow.hashCode();
        result = 31 * result + gridColumn.hashCode();
        return result;
    }
}
