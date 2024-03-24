# Currency Exchange App

This project implements a currency exchange application using Spring Boot.

## API Documentation

### Conversion Controller

#### `POST /currency`

Converts currency based on provided parameters.

**Request:**

- Method: `POST`
- Path: `/currency`
- Request Parameters:
  - `base` (String): Base currency code.
  - `symbol` (String): Currency code to convert to.
  - `amount` (Double): Amount of base currency to convert.

**Response:**

- Status Code: `201 CREATED`
- Body: JSON object representing the converted currency details.

#### `GET /conversionHistory`

Retrieves conversion history for a specific date.

**Request:**

- Method: `GET`
- Path: `/conversionHistory`
- Request Parameters:
  - `date` (LocalDate): Date to filter conversion history.

**Response:**

- Status Code: `200 OK`
- Body: JSON object representing a paginated list of conversion history.

### Exchange Controller

#### `GET /exchangeRate`

Retrieves the exchange rate between two currencies.

**Request:**

- Method: `GET`
- Path: `/exchangeRate`
- Request Parameters:
  - `base` (String): Base currency code.
  - `symbol` (String): Currency code to convert to.

**Response:**

- Status Code: `200 OK`
- Body: Exchange rate as a Double value.
