package grid.bit.repository;

import grid.bit.model.GridColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GridColumnRepository extends JpaRepository<GridColumn, Long> {
    @Modifying
    @Query(value = "UPDATE grid_column SET number = number + 1 WHERE grid_id = ?1 AND number > ?2", nativeQuery = true)
    void shiftBeforeInsert(Long gridId, Integer numberBefore);

}