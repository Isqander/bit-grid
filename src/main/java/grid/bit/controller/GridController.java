package grid.bit.controller;

import grid.bit.dto.GridDto;
import grid.bit.exceptions.RequestValidationException;
import grid.bit.service.GridService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("grid")
@RestController
public class GridController {
    private final GridService gridService;

    public GridController(GridService gridService) {
        this.gridService = gridService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GridDto create(@RequestBody GridDto grid) {
        return gridService.create(grid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GridDto> getAll() {
        return gridService.getAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public GridDto update(
            @RequestBody GridDto grid,
            @PathVariable Long id
    ) {
        return gridService.update(grid, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gridService.delete(id);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<String> handleException(RequestValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}