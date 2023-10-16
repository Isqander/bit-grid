package grid.bit.controller;

import grid.bit.dto.GridCellDto;
import grid.bit.exceptions.RequestValidationException;
import grid.bit.service.GridCellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("grid/cell")
@RestController
public class GridCellController {
    private final GridCellService gridCellService;

    public GridCellController(GridCellService gridCellService) {
        this.gridCellService = gridCellService;
    }

    @PostMapping("/{columnId}/{rowId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public GridCellDto setCellValue(
            @RequestBody GridCellDto cell,
            @PathVariable Long columnId,
            @PathVariable Long rowId
    ) {
        return gridCellService.setCell(cell, columnId, rowId);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<String> handleException(RequestValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}