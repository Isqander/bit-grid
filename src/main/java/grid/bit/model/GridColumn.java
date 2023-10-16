package grid.bit.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grid_column")
public class GridColumn extends AbstractEntity<Long> {

    public GridColumn() {
    }

    public GridColumn(int number, Grid grid) {
        this.number = number;
        this.grid = grid;
    }

    private int number;
    private Grid grid;
    private List<GridCell> cells;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "grid_id")
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gridColumn")
    public List<GridCell> getCells() {
        return cells;
    }

    public void setCells(List<GridCell> cells) {
        this.cells = cells;
    }
}