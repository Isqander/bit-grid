-- Put in here any db modifications, if needed, feel free to alter existing tables

ALTER TABLE grid_cell alter column value TYPE INT USING value::integer;

CREATE INDEX grid_column_id_value_idx ON grid_cell(grid_column_id, value)