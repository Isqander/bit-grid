package grid.bit.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grid_row")
public class GridRow extends AbstractEntity<Long> {
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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gridRow")
    public List<GridCell> getCells() {
        return cells;
    }

    public void setCells(List<GridCell> cells) {
        this.cells = cells;
    }
}