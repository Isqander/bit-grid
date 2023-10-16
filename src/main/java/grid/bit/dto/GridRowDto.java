package grid.bit.dto;


import lombok.Data;

import java.util.List;

@Data
public class GridRowDto {
    public GridRowDto() {
        number = 1;
    }

    private int number;
    private GridDto grid;
    private List<GridCellDto> cells;
}

