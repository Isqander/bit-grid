package grid.bit.service;

import grid.bit.dto.GridCellDto;
import grid.bit.exceptions.RequestValidationException;
import grid.bit.model.GridCell;
import grid.bit.repository.GridCellRepository;
import grid.bit.repository.GridColumnRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static grid.bit.dto.Mappers.*;

@Service
@Slf4j
public class GridCellService {
    private final GridCellRepository gridCellRepository;
    private final GridColumnRepository gridColumnRepository;

    public GridCellService(GridCellRepository gridCellRepository, GridColumnRepository gridColumnRepository) {
        this.gridCellRepository = gridCellRepository;
        this.gridColumnRepository = gridColumnRepository;
    }

    public GridCellDto setCell(GridCellDto cell, Long columnId, Long rowId) {
        if(cell.getValue().length() != gridColumnRepository.getById(columnId).getGrid().getCellSize()){
            log.warn("Wrong cell value length: {}", cell.getValue());
            throw new RequestValidationException("Wrong cell value length");
        }
        GridCell createdGridCell = gridCellRepository.saveAndFlush(gridCellDtoToEntity(cell, columnId, rowId));
        return gridCellEntityToDto(createdGridCell);
    }
}