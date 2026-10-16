USE techshop;

CREATE TABLE IF NOT EXISTS producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    detalle TEXT,
    precio DECIMAL(12,2) NOT NULL,
    existencias INT NOT NULL,
    ruta_imagen VARCHAR(1024),
    activo TINYINT(1) DEFAULT 1,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

INSERT INTO producto (id_categoria, descripcion, detalle, precio, existencias, activo) VALUES
(1, 'Laptop Pro 15', 'Laptop 15 pulgadas, 16GB RAM, 512GB SSD', 899.99, 10, 1),
(2, 'Smartphone X', 'Celular 128GB, pantalla OLED', 599.00, 25, 1),
(3, 'Tablet 10', 'Tablet 10 pulgadas, 64GB', 299.50, 15, 1),
(4, 'Audifonos BT', 'Audífonos inalámbricos con cancelación de ruido', 89.90, 40, 1),
(5, 'Mouse Gamer', 'Mouse óptico 16000 DPI', 29.99, 60, 1);