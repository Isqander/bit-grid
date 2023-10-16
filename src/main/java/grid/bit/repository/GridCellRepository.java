package grid.bit.repository;

import grid.bit.model.GridCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GridCellRepository extends JpaRepository<GridCell, Long> {

    @Query(value = "SELECT value from grid_cell where grid_column_id = ?1 AND value IS NOT NULL ORDER BY value LIMIT 1", nativeQuery = true)
    Integer getMinimumValue(Long columnId);

    @Query(value = "SELECT value from grid_cell where grid_column_id = ?1 AND value IS NOT NULL ORDER BY value DESC LIMIT 1", nativeQuery = true)
    Integer getMaximumValue(Long columnId);
}