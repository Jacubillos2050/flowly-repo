# Lenguaje Ubicuo – Flowly_MC OMS

Este documento define el **lenguaje común** entre desarrolladores y expertos del negocio.  
Todos los términos deben usarse **tal cual** en código, documentación, APIs y reuniones.

---

## Términos del Dominio

| Término en Español         | Término en Inglés         | Definición                                                                                                   | Contexto Principal     |
|----------------------------|---------------------------|---------------------------------------------------------------------------------------------------------------|------------------------|
| Pedido                     | Order                     | Solicitud de compra originada en un canal (web, marketplace, tienda). Tiene un ciclo de vida y estado.        | Orders                 |
| Realizar Pedido            | Place Order               | Acción de crear un pedido válido tras confirmación del cliente y verificación de disponibilidad.              | Orders                 |
| Cancelar Pedido            | Cancel Order              | Anulación de un pedido antes de su envío. No aplica una vez está "Enviado".                                   | Orders                 |
| Actualizar Estado          | Update Status             | Cambio controlado del estado del pedido (ej: "Pendiente" → "Confirmado" → "Enviado" → "Entregado").           | Orders                 |
| Item de Pedido             | Order Item                | Línea dentro de un pedido que representa un producto, cantidad, precio unitario y SKU.                        | Orders                 |
| Cliente                    | Customer                  | Persona o empresa que realiza pedidos. Identificado por `customerId` único.                                   | Customers              |
| Canal                      | Channel                   | Origen del pedido: "WEB", "AMAZON", "MERCADOLIBRE", "TIENDA_FISICA".                                         | Orders                 |
| Dirección de Envío         | Shipping Address          | Lugar donde se entrega el pedido. Es un **objeto de valor** (inmutable, sin ID).                              | Orders                 |
| Dirección de Facturación   | Billing Address           | Dirección usada para facturación. Puede ser igual o distinta a la de envío.                                  | Orders                 |
| Dinero                     | Money                     | Representación de un monto con moneda (ej: `{"amount": 149.90, "currency": "USD"}`). **Objeto de valor**.      | Orders, Payments       |
| Producto                   | Product                   | Artículo vendido. Identificado por SKU. Incluye nombre, descripción y precio base.                           | Products               |
| Inventario Disponible      | Available Inventory       | Cantidad de unidades físicas listas para ser vendidas en un almacén.                                         | Inventory              |
| Reservar Stock             | Reserve Stock             | Acción de bloquear unidades de inventario para un pedido específico.                                         | Inventory              |
| Liberar Stock              | Release Stock             | Devolver unidades reservadas al inventario disponible (ej: por cancelación).                                 | Inventory              |
| Pago                       | Payment                   | Transacción financiera asociada a un pedido. Puede estar en estado "Pendiente", "Aprobado" o "Rechazado".     | Payments               |
| Confirmar Pago             | Confirm Payment           | Validación exitosa del pago por la pasarela (ej: Stripe, PayPal).                                            | Payments               |