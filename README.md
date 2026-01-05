# BACKEND-VEHICULES

Guía rápida de consumo de los endpoints REST del servicio de vehículos usando `fetch` desde un frontend separado.

## Base URL

- Local: `http://localhost:8080/v1/vehicules`

## Modelo de datos

- Request (`VehiculeDtoRequest`):
	- `brand` (string)
	- `model` (string)
	- `plate` (string)
	- `year` (number)
	- `fuelType` (string)
	- `owner` (string)
- Response (`VehiculeDtoResponse`): mismos campos + `id` (UUID).

## Endpoints principales

- `POST /v1/vehicules` — crea un vehículo. Devuelve 201 con el vehículo creado.
- `GET /v1/vehicules` — lista todos.
- `GET /v1/vehicules/{id}` — obtiene por `id` (UUID).
- `GET /v1/vehicules?plate=ABC123` — obtiene por placa exacta.
- `GET /v1/vehicules?brand=Toyota` — lista por marca.
- `PUT /v1/vehicules/{id}` — actualiza por `id`.
- `DELETE /v1/vehicules/{id}` — elimina por `id` (204 sin cuerpo).

## Ejemplos con fetch

### Crear

```javascript
const baseUrl = 'http://localhost:8080/v1/vehicules';

async function crearVehiculo() {
	const res = await fetch(baseUrl, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			brand: 'Toyota',
			model: 'Corolla',
			plate: 'ABC123',
			year: 2022,
			fuelType: 'Gasolina',
			owner: 'Juan Perez'
		})
	});

	if (!res.ok) throw new Error('Error al crear');
	const data = await res.json();
	console.log('Creado:', data);
}
```

### Obtener por id

```javascript
async function obtenerPorId(id) {
	const res = await fetch(`${baseUrl}/${id}`);
	if (!res.ok) throw new Error('No encontrado');
	return res.json();
}
```

### Buscar por placa

```javascript
async function obtenerPorPlaca(plate) {
	const res = await fetch(`${baseUrl}?plate=${encodeURIComponent(plate)}`);
	if (!res.ok) throw new Error('No encontrado');
	return res.json();
}
```

### Listar por marca

```javascript
async function listarPorMarca(brand) {
	const res = await fetch(`${baseUrl}?brand=${encodeURIComponent(brand)}`);
	if (!res.ok) throw new Error('Error al listar');
	return res.json();
}
```

### Actualizar

```javascript
async function actualizarVehiculo(id, payload) {
	const res = await fetch(`${baseUrl}/${id}`, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(payload)
	});
	if (!res.ok) throw new Error('Error al actualizar');
	return res.json();
}
```

### Listar todos

```javascript
async function listarTodos() {
	const res = await fetch(baseUrl);
	if (!res.ok) throw new Error('Error al listar');
	return res.json();
}
```

### Eliminar

```javascript
async function eliminarVehiculo(id) {
	const res = await fetch(`${baseUrl}/${id}`, { method: 'DELETE' });
	if (!res.ok && res.status !== 204) throw new Error('Error al eliminar');
}
```

## Notas rápidas

- Todas las peticiones que envían cuerpo usan `Content-Type: application/json`.
- El servicio devuelve 400/404 en errores de validación o no encontrado; maneja `res.ok` y `res.status` en el frontend.
- **CORS configurado**: el backend acepta peticiones desde `http://localhost:8081`, `http://localhost:3000` y `http://localhost:5173`. Si usas otro puerto, edita `CorsConfig.java`.
