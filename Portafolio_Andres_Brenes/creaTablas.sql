USE techshop;

DELETE FROM categoria;
ALTER TABLE categoria AUTO_INCREMENT = 1;

INSERT INTO categoria (descripcion, ruta_imagen, activo) VALUES
  ('Computadoras', NULL, TRUE),
  ('Celulares',    NULL, TRUE),
  ('Tablets',      NULL, TRUE),
  ('Audífonos',    NULL, TRUE),
  ('Accesorios',   NULL, TRUE),
  ('Consolas',     NULL, FALSE);

SELECT * FROM categoria;