package grid.bit.model;

import javax.persistence.*;

@Entity
@Table(name = "grid_cell")
@IdClass(CellId.class)
public class GridCell {
    private Integer value;
    @Id
    @Column(name = "grid_row_id")
    private Long gridRow;
    @Id
    @Column(name = "grid_column_id")
    private Long gridColumn;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getGridRow() {
        return gridRow;
    }

    public void setGridRow(Long gridRowId) {
        this.gridRow = gridRowId;
    }

    public Long getGridColumn() {
        return gridColumn;
    }

    public void setGridColumn(Long gridColumnId) {
        this.gridColumn = gridColumnId;
    }
}