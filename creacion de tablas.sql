-- ELIMINAR TABLAS EN ORDEN SEGURO
DROP TABLE IF EXISTS 
    metodo_de_pago_compra,
    metodo_de_pago,
    compra,
    valoracion_de_juego,
    producto_carro,
    carro,
    lista_deseos_producto,
    lista_deseos,
    valoracion_de_producto,
    categoria_producto,
    juego_cartas,
    cartas,
    juego_de_mesa,
    tienda_producto,
    permisos,
    producto,
    tienda,
    categoria,
    usuario
CASCADE;

-- 1. USUARIO
CREATE TABLE usuario (
    id_usuario serial PRIMARY KEY,
    correo_usuario varchar(30) UNIQUE,
    clave varchar(30),
    rol varchar(10),
    ubicacion varchar(20),
    nombre_usuario char(50),
    apellido_usuario char(50),
    direccion_usuario varchar(30)
);

-- 2. CATEGORIA
CREATE TABLE categoria (
    id_categoria serial PRIMARY KEY,
    descripcion text
);

-- 3. TIENDA
CREATE TABLE tienda (
    id_tienda serial PRIMARY KEY,
    nombre_tienda char(30),
    nombre_jefe varchar(20),
    id_usuario serial,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- 4. PRODUCTO
CREATE TABLE producto (
    id_producto serial PRIMARY KEY,
    descripcion_producto varchar(100),
    tipo_producto varchar(30),
    stock integer,
    precio_unitario integer,
    url varchar(250),
    imagen varchar(250),
    direccion_comercial varchar(30) UNIQUE,
    id_categoria serial,
    id_tienda serial,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_tienda) REFERENCES tienda(id_tienda)
);

-- 5. PERMISOS
CREATE TABLE permisos (
    id_permiso serial PRIMARY KEY,
    tipo_permiso varchar(30),
    correo_usuario varchar(30),
    FOREIGN KEY (correo_usuario) REFERENCES usuario(correo_usuario)
);

-- 6. TIENDA_PRODUCTO
CREATE TABLE tienda_producto (
    id_producto serial,
    direccion_comercial varchar(30),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (direccion_comercial) REFERENCES producto(direccion_comercial)
);

-- 7. JUEGO DE MESA
CREATE TABLE juego_de_mesa (
    id_juego_de_mesa serial PRIMARY KEY,
    tipo_juego_mesa varchar(30),
    id_producto serial,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- 8. CARTAS
CREATE TABLE cartas (
    id_carta serial PRIMARY KEY,
    rareza char(20),
    estado char(20),
    año date
);

-- 9. JUEGO_CARTAS
CREATE TABLE juego_cartas (
    id_juego_cartas serial PRIMARY KEY,
    rareza char(20),
    estado char(20),
    año date,
    id_producto serial,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- 10. CATEGORIA_PRODUCTO
CREATE TABLE categoria_producto (
    id_categoria serial,
    id_producto serial,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- 11. VALORACION DE PRODUCTO
CREATE TABLE valoracion_de_producto (
    id_valoracion serial PRIMARY KEY,
    puntos_valoracion integer,
    correo_usuario varchar(30),
    id_producto serial,
    FOREIGN KEY (correo_usuario) REFERENCES usuario(correo_usuario),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- 12. LISTA DE DESEOS
CREATE TABLE lista_deseos (
    id_lista serial PRIMARY KEY,
    correo_usuario varchar(30),
    FOREIGN KEY (correo_usuario) REFERENCES usuario(correo_usuario)
);

-- 13. LISTA DESEOS PRODUCTO
CREATE TABLE lista_deseos_producto (
    id_producto serial,
    id_lista serial,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_lista) REFERENCES lista_deseos(id_lista)
);

-- 14. CARRO
CREATE TABLE carro (
    id_carro serial PRIMARY KEY,
    precio_total integer,
    estado bool,
    correo_usuario varchar(30),
    FOREIGN KEY (correo_usuario) REFERENCES usuario(correo_usuario)
);

-- 15. PRODUCTO_CARRO
CREATE TABLE producto_carro (
    id_producto serial,
    id_carro serial,
    cantidad_producto integer,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_carro) REFERENCES carro(id_carro)
);

-- 16. VALORACION DE JUEGO
CREATE TABLE valoracion_de_juego (
    id_juego_valoracion serial PRIMARY KEY,
    puntos_valoracion integer,
    id_producto serial,
    correo_usuario varchar(30),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (correo_usuario) REFERENCES usuario(correo_usuario)
);

-- 17. COMPRA
CREATE TABLE compra (
    id_compra serial PRIMARY KEY,
    detalle varchar(100),
    fecha date,
    total_compra integer,
    id_carro serial,
    FOREIGN KEY (id_carro) REFERENCES carro(id_carro)
);

-- 18. METODO DE PAGO
CREATE TABLE metodo_de_pago (
    id_pago serial PRIMARY KEY,
    tipo_metodo varchar(50)
);

-- 19. METODO DE PAGO COMPRA
CREATE TABLE metodo_de_pago_compra (
    id_pago serial,
    id_compra serial,
    FOREIGN KEY (id_pago) REFERENCES metodo_de_pago(id_pago),
    FOREIGN KEY (id_compra) REFERENCES compra(id_compra)
);

