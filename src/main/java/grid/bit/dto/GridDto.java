package grid.bit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GridDto {
    private Long id;
    private String name;
    private int cellSize;
    private List<GridColumnDto> columns;
    private List<GridRowDto> rows;
}
