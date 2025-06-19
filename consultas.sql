-- Agregar un juego de mesa/carta al carrito  --
INSERT INTO producto_carro (id_producto, id_carro, cantidad_producto)
VALUES (2, 1, 1); -- Ejemplo: producto con id=2, carro con id=1, cantidad=1

/* los productos deberían quedar en esta tabla ya que es la que los relaciona con el
carro, porque carro contiene los carros de cada usuario nomas */


-- Eliminar un juego de mesa / carta del carrito de compras --
update producto_carro
set cantidad_producto = cantidad_producto - /*cantidad que quiero quitar*/
where id_producto = /*elegir*/ and id_carro = /*elegir*/;
/* hacer la seleccion mediante los id */


-- Mostrar los juegos de mesa / cartas del carrito de compras -
-- Juegos de mesa:
select P.nombre_producto, PC.cantidad_producto
from producto_carro PC
join producto P on P.id_producto = PC.id_producto
join juego_mesa JM on P.id_producto = JM.id_producto
where PC.id_carro =  1 /*id del carro de quien se quiera consultar*/

-- Juegos de carta:
select P.nombre_producto, PC.cantidad_producto
from producto_carro PC
join producto P on PC.id_producto = P.id_producto
join carta_coleccionable C on P.id_producto = C.id_producto
where PC.id_carro = 3 /*id del carro de quien se quiera consultar*/;


-- Mostrar el precio total a pagar por el carrito de compras --
SELECT SUM(p.precio_unitario * pc.cantidad_producto) AS total_a_pagar
FROM producto_carro pc
JOIN producto p ON pc.id_producto = p.id_producto
WHERE pc.id_carro = 1;



-- Mostrar todos los juegos de mesa y cartas --
-- que se vendan en una ubicacion geografica especifica --
SELECT p.nombre_producto, p.tipo_producto, u.ubicacion
FROM producto p
JOIN tienda t ON p.id_tienda = t.id_tienda
JOIN usuario u ON t.id_usuario = u.id_usuario
WHERE u.ubicacion = 'Iquique'; -- Cambia por la ubicación que necesites



-- Mostrar ranking de los productos con mas ventas --
SELECT p.nombre_producto, COUNT(*) AS cantidad_vendida
FROM compra c
JOIN producto_carro pc ON c.id_carro = pc.id_carro
JOIN producto p ON pc.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre_producto
ORDER BY cantidad_vendida DESC;


-- Mostrar lista de deseos de un usuario --
SELECT p.nombre_producto, p.tipo_producto
FROM lista_deseos ld
JOIN lista_deseos_producto ldp ON ld.id_lista = ldp.id_lista
JOIN producto p ON ldp.id_producto = p.id_producto
WHERE ld.correo_usuario = 'ana.gonzalez@gmail.com'; 
