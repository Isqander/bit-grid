package grid.bit.controller;

import grid.bit.AbstractIntegrationTest;
import grid.bit.model.GridCell;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql("GridCellControllerTest.sql")
public class GridCellControllerTest extends AbstractIntegrationTest {
    private static final String BASE_URL = "/grid/cell";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void set_changesValueInCell() throws Exception {
        mockMvc.perform(post(BASE_URL + "/{columnId}/{rowId}", 55500055530L, 55500055540L).contentType(APPLICATION_JSON)
                        .content("{\"value\":\"100110111011\"}"))
                .andExpect(status().isNoContent());
        flush();

        List<GridCell> gridCell = findAll(GridCell.class);
        assertThat(gridCell).isNotNull().hasSize(1);
        assertThat(gridCell.get(0).getGridColumn()).isEqualTo(55500055530L);
        assertThat(gridCell.get(0).getGridRow()).isEqualTo(55500055540L);
        assertThat(gridCell.get(0).getValue()).isEqualTo(Integer.parseInt("100110111011", 2));
    }
}