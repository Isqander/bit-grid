package grid.bit.controller;

import grid.bit.dto.CommonPrefixResponse;
import grid.bit.dto.GridColumnDto;
import grid.bit.service.GridColumnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("grid/column")
@RestController
public class GridColumnController {
    private final GridColumnService gridColumnService;

    public GridColumnController(GridColumnService gridColumnService) {
        this.gridColumnService = gridColumnService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GridColumnDto insertColumn(@RequestParam Long afterColumnId) {
        return gridColumnService.insertColumn(afterColumnId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gridColumnService.delete(id);
    }

    @GetMapping(value = "/{id}/common-prefix", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonPrefixResponse getCommonPrefix(@PathVariable Long id) {
        return gridColumnService.getCommonPrefix(id);
    }
}