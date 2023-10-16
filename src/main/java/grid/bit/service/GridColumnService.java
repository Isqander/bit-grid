package grid.bit.service;

import grid.bit.dto.CommonPrefixResponse;
import grid.bit.dto.GridColumnDto;
import grid.bit.model.GridColumn;
import grid.bit.repository.GridCellRepository;
import grid.bit.repository.GridColumnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static grid.bit.dto.Mappers.gridColumnEntityToDto;

@Service
public class GridColumnService {
    private final GridColumnRepository gridColumnRepository;
    private final GridCellRepository gridCellRepository;

    public GridColumnService(GridColumnRepository gridColumnRepository, GridCellRepository gridCellRepository) {
        this.gridColumnRepository = gridColumnRepository;
        this.gridCellRepository = gridCellRepository;
    }

    @Transactional
    public GridColumnDto insertColumn(Long afterColumnId) {
        GridColumn columnBefore = gridColumnRepository.getById(afterColumnId);
        gridColumnRepository.shiftBeforeInsert(columnBefore.getGrid().getId(), columnBefore.getNumber());
        GridColumn gridColumn = gridColumnRepository
                .saveAndFlush(new GridColumn(columnBefore.getNumber() + 1, columnBefore.getGrid()));
        return gridColumnEntityToDto(gridColumn);
    }

    public void delete(Long id) {
        gridColumnRepository.deleteById(id);
    }

    @Transactional
    public CommonPrefixResponse getCommonPrefix(Long columnId) {
        int maxValue = gridCellRepository.getMaximumValue(columnId);
        int minValue = gridCellRepository.getMinimumValue(columnId);
        int xorDiff = maxValue^minValue;
        String diffBinary = Integer.toBinaryString(xorDiff);
        String maxBinary = Integer.toBinaryString(maxValue);
        int cellSize = gridColumnRepository.getById(columnId).getGrid().getCellSize();
        String leadingZeroes = "0".repeat(cellSize-maxBinary.length());
        return new CommonPrefixResponse(leadingZeroes + maxBinary.substring(0, maxBinary.length()-diffBinary.length()));
    }
}