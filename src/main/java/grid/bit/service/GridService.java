package grid.bit.service;

import grid.bit.dto.*;
import grid.bit.exceptions.RequestValidationException;
import grid.bit.model.Grid;
import grid.bit.repository.GridRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static grid.bit.dto.Mappers.gridDtoToEntity;
import static grid.bit.dto.Mappers.gridEntityToDto;

@Service
@Slf4j
public class GridService {
    private final GridRepository gridRepository;

    public GridService(GridRepository gridRepository) {
        this.gridRepository = gridRepository;
    }

    public GridDto create(GridDto gridDto) throws RequestValidationException {
        if (gridDto.getName() == null || gridDto.getName().isBlank()) {
            log.warn("Grid name in create request is blank");
            throw new RequestValidationException("Grid name in request is empty");
        }
        if (gridDto.getName().length() > 200) {
            log.warn("Grid name in request is {}, that is more than 200 symbols", gridDto.getName().length());
            throw new RequestValidationException("Grid name in request is more than 200 symbols");
        }
        gridDto.setRows(List.of(new GridRowDto()));
        gridDto.setColumns(List.of(new GridColumnDto()));
        Grid grid = gridDtoToEntity(gridDto);
        Grid grind = gridRepository.saveAndFlush(grid);
        return gridEntityToDto(grind);
    }

    public List<GridDto> getAll() {
        return gridRepository.findAll().stream().map(Mappers::gridEntityToDto).collect(Collectors.toList());
    }

    public GridDto update(GridDto grid, Long id) {
        grid.setId(id);
        Grid savedGrid = gridRepository.saveAndFlush(gridDtoToEntity(grid));
        return gridEntityToDto(savedGrid);
    }

    @Transactional
    public void delete(Long id) {
        gridRepository.deleteById(id);
    }
}