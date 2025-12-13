# FinFlow -> Control de Finanzas Personales

FinFlow es una aplicación móvil para Android diseñada para ofrecer una solución práctica, intuitiva y completa para la gestión de finanzas personales, del hogar o de pequeñas empresas. Su principal objetivo es facilitar el registro y seguimiento de ingresos y gastos, permitiendo al usuario tener un panorama claro y detallado de su situación financiera en todo momento.

## Funcionalidades principales

La aplicación permite tener un control financiero mediante las siguientes funcionalidades:

* Registro de ingresos y gastos: Los usuarios pueden añadir transacciones manualmente, detallando la cantidad, la fecha, la descripción y otros datos relevantes.

* Clasificación por categorías y etiquetas: Para facilitar el análisis, cada movimiento puede organizarse en categorías (por ejemplo, alimentación, transporte, ocio, etc.) y etiquetarse de forma personalizada.

* Soporte para múltiples monedas.

* Pagos recurrentes: Es posible registrar transacciones periódicas como suscripciones, pagos de créditos o ingresos fijos.

* Multicuentas: Los usuarios pueden administrar diversas cuentas financieras dentro de la misma aplicación (diferentes cuentas ). Esto incluye cuentas corrientes, tarjetas de crédito, billeteras digitales, entre otras.

## Visión a futuro: Interacción inteligente 

En una segunda fase de desarrollo, FinFlow incorporará funcionalidades de automatización:
* Lectura de notificaciones bancarias por correo electrónico, obteniendo información sobre movimientos como pagos, transferencias y otros.

## Mockups

* Login

<img width="434" height="735" alt="image" src="https://github.com/user-attachments/assets/b5fafdec-1ff0-4646-b103-3d5e86c62dc1" />

* Configuración

<img width="323" height="781" alt="image" src="https://github.com/user-attachments/assets/fc6d938b-a775-4eec-a29d-5a20ad8bee1c" />
<img width="319" height="584" alt="image" src="https://github.com/user-attachments/assets/e7423728-8e05-4354-bb20-e2f603eaccfc" />

* Funcionalidades báscias

<img width="322" height="866" alt="image" src="https://github.com/user-attachments/assets/050b9945-57ab-43bc-8760-4db7e6f5c744" />

# API Documentation

* Base URL http://delicias-001-site4.rtempurl.com/api | REST | JSON

## Endpoints

### Account 

* GET /api/Account

Get all accounts

* POST /api/Account

Creates a new account.

Body
```json
{
  "name": "Main Account",
  "balance": 1500.50,
  "currency": { "id": 1 },
  "accountType": "Saving"
}
```

* GET /api/Account/{id}

Retrieves a specific account.

id	int	required

* PUT /api/Account/{id}

Updates an existing account.

id	int	required

* DELETE /api/Account/{id}

Deletes an account by ID.

id	int	required

### Category API
* GET /api/Category

Retrieves all categories.

* POST /api/Category

Creates a new category.

Body
```json
{
  "type": "Expense",
  "name": "Food"
}
```

* GET /api/Category/{id}

Retrieves a category by ID.

id	int	required

* PUT /api/Category/{id}

Updates an existing category.

id	int	required

* DELETE /api/Category/{id}

Deletes a category.

id	int	required

### Currency API

* GET /api/Currency

Lists all currencies.

* POST /api/Currency

Creates a new currency.

Body
```json
{
  "code": "USD",
  "name": "US Dollar"
}
```

* GET /api/Currency/{id}

Retrieves a currency by ID.

id	int	required

* PUT /api/Currency/{id}

Updates a currency.

id	int	required

* DELETE /api/Currency/{id}

Deletes a currency.

id	int	required

### Transaction API

* GET /api/Transaction

Retrieves all transactions.

* POST /api/Transaction

Creates a new transaction.

Body
```json
{
  "amount": 50.75,
  "description": "Dinner",
  "date": "2025-01-01T18:30:00Z",
  "category": { "id": 3 },
  "account": { "id": 1 }
}
```

* GET /api/Transaction/{id}

Retrieves a transaction by ID.

id	int	required

* PUT /api/Transaction/{id}

Updates an existing transaction.

id	int	required

* DELETE /api/Transaction/{id}

Deletes a transaction by ID.

id	int	required

## Models

### Account
```json
{
  "id": 0,
  "name": "string",
  "balance": 0.0,
  "currency": { Currency },
  "accountType": "string"
}

```

### Category
```json
{
  "id": 0,
  "type": "string",
  "name": "string"
}
```

### Currency
```json
{
  "id": 0,
  "code": "string",
  "name": "string"
}
```

### Transaction
```json
{
  "id": 0,
  "amount": 0.0,
  "description": "string",
  "date": "YYYY-MM-DDTHH:MM:SSZ",
  "category": { Category },
  "account": { Account }
}
```
