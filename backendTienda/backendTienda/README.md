
# Backend Tienda de Juegos de Mesa y Cartas

## Requisitos
- Java 17+
- Maven
- (Opcional) Postman para pruebas

## Instalación y ejecución

1. Clona el repositorio y entra a la carpeta `backendTienda`.
2. Compila y ejecuta:
   ```bash
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```
   El backend quedará disponible en `http://localhost:8080`

---

## Pruebas de ejemplo con Postman

### 1. Registro de usuario
**Endpoint:** `POST /api/usuarios/crear`
**Repetir para crear un jefe, admin y vendedor**

**Body:**
```json
{
  "correo": "cliente@ejemplo.com",
  "contrasena": "123456",
  "rol": "cliente",
  "nombreUsuario": "Juan",
  "apellido": "Pérez",
  "direccion": "Calle Falsa 123",
  "ubicacion": "Santiago"
}
```

---

### 2. Crear producto (jefe, vendedor o admin)
**Endpoint:** `POST /api/productos?usuarioId=ID_USUARIO`

**Body:**
```json
{
  "nombreProducto": "Catan",
  "tipoProducto": "JuegoMesa",
  "precioUnitario": 25000,
  "stock": 10,
  "urlImagen": "https://ejemplo.com/catan.jpg",
  "direccionComercial": "Santiago",
  "categoria": { "idCategoria": 1 }
}
```

---

### 3. Actualizar producto (jefe o admin)
**Endpoint:** `PUT /api/productos/{id}?usuarioId=ID_USUARIO`

**Body:** igual a crear producto

---

### 4. Eliminar producto (jefe o admin)
**Endpoint:** `DELETE /api/productos/{id}?usuarioId=ID_USUARIO`

---

### 5. Actualizar usuario (propio o admin)
**Endpoint:** `PUT /api/usuarios/{id}?usuarioId=ID_USUARIO`

**Body:** igual a registro

---

### 6. Eliminar usuario (propio o admin)
**Endpoint:** `DELETE /api/usuarios/{id}?usuarioId=ID_USUARIO`

---

### 7. Filtrar productos por ubicación
**Endpoint:** `GET /api/productos/filtrar-ubicacion?ubicacion=Santiago`

---

### 8. Ranking productos más vendidos
**Endpoint:** `GET /api/productos/ranking-mas-vendidos`

---

### 9. Ranking cartas más deseadas
**Endpoint:** `GET /api/listas-deseos/ranking-mas-deseados`

---

### 10. Carro de compras
- **Crear carro:**
  - `POST /api/carro`
  - **Body:**
```json
{
  "precioTotal": 0,
  "estado": false,
  "correoUsuario": "cliente@ejemplo.com"
}
```
- **Agregar producto al carro:**
  - `POST /api/producto-carro`
  - **Body:**
```json
{
  "idCarro": 1,
  "idProducto": 1,
  "cantidadProducto": 2
}
```
- **Simular compra:**
  - `POST /api/compras/{idCarro}`

---

### 11. Lista de deseos
- **Crear lista:**
  - `POST /api/listas-deseos/usuario/{idUsuario}`
- **Agregar producto a lista:**
  - `POST /api/listas-deseos/{idLista}/producto/{idProducto}`

---

### 12. Valoración de producto
- **Endpoint:** `POST /api/valoracion-producto`
- **Body:**
```json
{
  "idProducto": 1,
  "idUsuario": 1,
  "valoracion": 5,
  "comentario": "Excelente juego!"
}
```

---

## Instrucciones generales para probar

- Cambia los valores de ejemplo (`ID_USUARIO`, `id`, `idCarro`, `idLista`, `idProducto`) por los que correspondan en tu base de datos.
- El campo `urlImagen` es solo una URL de ejemplo, no requiere imagen real.
- Los endpoints protegidos requieren el parámetro `usuarioId` en la URL.
- Usa el registro de usuario para crear usuarios con distintos roles (`admin`, `jefe`, `vendedor`, `cliente`).
- Para probar restricciones de rol, usa el `usuarioId` de un usuario con el rol adecuado.
- Puedes importar estos ejemplos como colecciones en Postman para probar fácilmente.

---

¿Dudas? Revisa los controladores para ver la estructura exacta de cada endpoint y body.
