insert into usuario (correo_usuario, clave, rol, ubicacion, nombre_usuario, apellido_usuario, direccion_usuario)
values ('ana.gonzalez@gmail.com', 'clave123', 'cliente', 'Santiago', 'Ana', 'Gonzalez', 'Calle Falsa 123'),
('bruno.perez@gmail.com', 'segura456', 'cliente', 'Valparaíso', 'Bruno', 'Perez', 'Av. Alemania 456'),
('carla.vidal@gmail.com', 'carla789', 'vendedor', 'Concepción', 'Carla', 'Vidal', 'Pasaje Los Álamos 78'),
('daniel.rojas@gmail.com', 'dani321', 'cliente', 'Temuco', 'Daniel', 'Rojas', 'Villa El Bosque 12'),
('elena.martinez@gmail.com', 'elena999', 'admin', 'Santiago', 'Elena', 'Martinez', 'Calle Mirador 56'),
('felipe.torres@gmail.com', 'torres123', 'vendedor', 'Iquique', 'Felipe', 'Torres', 'Calle Norte 34'),
('gabriela.soto@gmail.com', 'gabriela1', 'cliente', 'Antofagasta', 'Gabriela', 'Soto', 'Condominio Costa Azul 9'),
('hector.mena@gmail.com', 'mena456', 'vendedor', 'La Serena', 'Hector', 'Mena', 'Calle del Sol 20'),
('ines.fernandez@gmail.com', 'inespass', 'cliente', 'Puerto Montt', 'Ines', 'Fernandez', 'Ruta 5 Sur KM 1020'),
('jorge.morales@gmail.com', 'jorgepass', 'admin', 'Rancagua', 'Jorge', 'Morales', 'Camino El Álamo 45');
/*serial no debo ingresarlo a mano*/

insert into categoria (descripcion)
values ('Rol'),
('Guerra'),
('Cooperativo'),
('Competitivo'),
('Familiar'),
('Estrategia');

insert into tienda (nombre_tienda, nombre_jefe, id_usuario)
values ('Todo Juegos', 'Carla', 3),
('Play Center', 'Felipe', 6),
('Mega Games', 'Hector', 8);

insert into producto
(nombre_producto, tipo_producto, stock, precio_unitario, url, imagen, direccion_comercial, id_tienda)
values
('Catan', 'Juego de mesa', 10, 29990, 'https://ejemplo.com/catan', 'catan.jpg', 'CAT001', 1),
('Risk', 'Juego de mesa', 15, 24990, 'https://ejemplo.com/risk', 'risk.jpg', 'RISK001', 1),
('7 Wonders', 'Juego de mesa', 12, 26990, 'https://ejemplo.com/7wonders', '7wonders.jpg', '7W001', 2),
('Jenga', 'Juego de mesa', 25, 14990, 'https://ejemplo.com/jenga', 'jenga.jpg', 'JEN001', 3),
('Zombicide', 'Juego de mesa', 8, 39990, 'https://ejemplo.com/zombicide', 'zombicide.jpg', 'ZOM001', 1),
('Carcassonne', 'Juego de mesa', 14, 25990, 'https://ejemplo.com/carcassonne', 'carcassonne.jpg', 'CARC001', 2),
('Gloomhaven', 'Juego de mesa', 5, 99990, 'https://ejemplo.com/gloomhaven', 'gloomhaven.jpg', 'GLOOM001', 1),
('Charizard Base Set', 'Carta coleccionable', 3, 150000, 'https://ejemplo.com/charizard', 'charizard.jpg', 'POK001', 1),
('Black Lotus Alpha', 'Carta coleccionable', 1, 300000, 'https://ejemplo.com/blacklotus', 'blacklotus.jpg', 'MTG001', 2),
('Blue-Eyes White Dragon', 'Carta coleccionable', 2, 80000, 'https://ejemplo.com/blueeyes', 'blueeyes.jpg', 'YGO001', 3);

insert into tienda_producto (id_producto, direccion_comercial)
values
(1, 'CAT001'),
(2, 'RISK001'),
(3, '7W001'),
(4, 'JEN001'),
(5, 'ZOM001'),
(6, 'CARC001'),
(7, 'GLOOM001'),
(8, 'POK001'),
(9, 'MTG001'),
(10, 'YGO001');

insert into juego_mesa (descripcion, id_producto)
values
('Estrategia de comercio y construcción', 1),
('Conquista global con ejércitos', 2),
('Construcción de civilizaciones con cartas', 3),
('Habilidad física con torre de bloques', 4),
('Supervivencia cooperativa contra zombis', 5),
('Colocación estratégica de losetas medievales', 6),
('Aventura épica con campaña persistente', 7);

insert into carta_coleccionable (rareza, estado, año, id_producto)
values
('Holo Rara', 'Usado', '1999-01-01', 8),
('Mítica', 'Nuevo', '1993-01-01', 9),
('Ultra Rara', 'Nuevo', '2002-01-01', 10);

insert into valoracion_de_producto (puntos_valoracion, correo_usuario, id_producto)
values
(5, 'ana.gonzalez@gmail.com', 1),
(4, 'bruno.perez@gmail.com', 2),
(5, 'daniel.rojas@gmail.com', 3),
(3, 'gabriela.soto@gmail.com', 4),
(5, 'ines.fernandez@gmail.com', 5);

insert into lista_deseos (correo_usuario)
values
('ana.gonzalez@gmail.com'),
('bruno.perez@gmail.com'),
('daniel.rojas@gmail.com');

insert into lista_deseos_producto (id_producto, id_lista)
values
(8, 1),
(9, 2),
(10, 3);

insert into carro (precio_total, estado, correo_usuario)
values
(44980, true, 'ana.gonzalez@gmail.com'),
(99990, true, 'elena.martinez@gmail.com'),
(300000, false, 'jorge.morales@gmail.com');

insert into producto_carro (id_producto, id_carro, cantidad_producto)
values
(1, 1, 1),
(4, 1, 1),
(7, 2, 1),
(9, 3, 1);

insert into valoracion_de_juego (puntos_valoracion, id_producto, correo_usuario)
values
(5, 1, 'ana.gonzalez@gmail.com'),
(4, 2, 'bruno.perez@gmail.com'),
(5, 3, 'daniel.rojas@gmail.com');

insert into compra (detalle, fecha, total_compra, id_carro)
values
('Compra juegos familiares', '2023-10-15', 44980, 1),
('Compra juego premium', '2023-10-16', 99990, 2);

insert into metodo_de_pago (tipo_metodo)
values
('Tarjeta de crédito'),
('Transferencia bancaria'),
('Mercado Pago');

insert into metodo_de_pago_compra (id_pago, id_compra)
values
(1, 1),
(3, 2);

insert into categoria_producto (id_categoria, id_producto)
values
(6, 1), 
(4, 1),
(5, 1),
(2, 2),
(4, 2),
(6, 2),
(6, 3),
(4, 3),
(5, 4),
(4, 4),
(3, 5),
(2, 5),
(6, 6),
(5, 6),
(1, 7),
(3, 7),
(6, 7),
(4, 8),
(6, 8),
(4, 9),
(6, 9),
(4, 10),
(6, 10);