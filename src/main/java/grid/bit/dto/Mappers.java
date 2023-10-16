package grid.bit.dto;

import grid.bit.exceptions.RequestValidationException;
import grid.bit.model.Grid;
import grid.bit.model.GridCell;
import grid.bit.model.GridColumn;
import grid.bit.model.GridRow;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class Mappers {
    public static GridDto gridEntityToDto(Grid grid) {
        GridDto dto = new GridDto();
        BeanUtils.copyProperties(grid, dto);
        dto.setId(grid.getId());

        return dto;
    }

    public static Grid gridDtoToEntity(GridDto dto) {
        Grid grid = new Grid();
        BeanUtils.copyProperties(dto, grid);
        grid.setId(dto.getId());

        if (dto.getColumns() != null) {
            grid.setColumns(dto.getColumns()
                    .stream()
                    .map(Mappers::gridColumnDtoToEntity)
                    .peek(it -> it.setGrid(grid))
                    .collect(Collectors.toList()));
        }
        if (dto.getRows() != null) {
            grid.setRows(dto.getRows()
                    .stream()
                    .map(Mappers::gridRowDtoToEntity)
                    .peek(it -> it.setGrid(grid))
                    .collect(Collectors.toList()));
        }

        return grid;
    }

    public static GridColumnDto gridColumnEntityToDto(GridColumn gridColumn) {
        GridColumnDto dto = new GridColumnDto();
        BeanUtils.copyProperties(gridColumn, dto);
        dto.setId(gridColumn.getId());

        return dto;
    }

    public static GridColumn gridColumnDtoToEntity(GridColumnDto dto) {
        GridColumn gridColumn = new GridColumn();
        BeanUtils.copyProperties(dto, gridColumn);

        return gridColumn;
    }

    public static GridRowDto gridRowEntityToDto(GridRow gridRow) {
        GridRowDto dto = new GridRowDto();
        BeanUtils.copyProperties(gridRow, dto);

        return dto;
    }

    public static GridRow gridRowDtoToEntity(GridRowDto dto) {
        GridRow gridRow = new GridRow();
        BeanUtils.copyProperties(dto, gridRow);

        return gridRow;
    }

    public static GridCell gridCellDtoToEntity(GridCellDto dto, Long columnId, Long rowId) throws RequestValidationException {
        GridCell gridCell = new GridCell();
        BeanUtils.copyProperties(dto, gridCell);
        gridCell.setGridColumn(columnId);
        gridCell.setGridRow(rowId);
        Integer intValue = null;
        try {
            intValue = (dto.getValue() == null || dto.getValue().isEmpty()) ? null : Integer.parseInt(dto.getValue(), 2);
        } catch (NumberFormatException e) {
            log.warn("Wrong cell value format", e);
            throw new RequestValidationException("Wrong cell value format");
        }
        gridCell.setValue(intValue);

        return gridCell;
    }

    public static GridCellDto gridCellEntityToDto(GridCell gridCell) {
        GridCellDto dto = new GridCellDto();
        BeanUtils.copyProperties(gridCell, dto);
        return dto;
    }
}