package grid.bit.dto;

import lombok.Data;
import java.util.List;

@Data
public class GridColumnDto {
    public GridColumnDto() {
        number = 1;
    }

    private Long id;
    private int number;
    private GridDto grid;
    private List<GridCellDto> cells;
}