-- Agregar un juego de mesa/carta al carrito  --

insert into producto_carro(id_producto, id_carro, cantidad_producto)
values (/*agregar valores deseados, id_producto contiene si es juego de mesa/carta*/);

/* los productos deberían quedar en esta tabla ya que es la que los relaciona con el
carro, porque carro contiene los carros de cada usuario nomas */


-- Eliminar un juego de mesa / carta del carrito de compras --

update producto_carro
set cantidad_producto = cantidad_producto - /*cantidad que quiero quitar*/
where id_producto = /*elegir*/ and id_carro = /*elegir*/;


-- Mostrar los juegos de mesa / cartas del carrito de compras --

-- Juegos de mesa:
select P.id_producto, P.descripcion_producto
from producto P
join juego_de_mesa JM on P.id_producto = JM.id_producto;

-- Juegos de carta:
select P.id_producto, P.descripcion_producto
from producto P
join juego_cartas JC on P.id_producto = JC.id_producto;


-- Mostrar el precio total a pagar por el carrito de compras --

select sum(P.precio_unitario * P.cantidad_producto) PrecioTotal
from producto_carro PC
join producto P on PC.id_producto = P.id_producto
where PC.id_carro = /*id del carro al que quiero consultar*/;


-- Mostrar todos los juegos de mesa y cartas --
-- que se vendan en una ubicacion geografica especifica --


-- Mostrar ranking de los productos con mas ventas --


-- Mostrar lista de deseos de un usuario --

select P.id_producto, P.descripcion_producto
from lista_deseos L
join lista_deseos_producto LP on L.id_lista = LP.id_lista
join producto P on LP.id_producto = P.id_producto
where L.correo_usuario = /*id del usuario que se quiere consultar*/;

/*esto para ver los productos dentro de la lista de deseos, sino es más fácil*/

select *
from lista_deseos
where correo_usuario = /*id del usuario que se quiere consultar*/;






