# 🏦 Mini Banco Extended (Java)

Sistema de gestión bancaria desarrollado en Java como proyecto guiado.  
Incluye arquitectura por capas, operaciones bancarias reales y registro de transacciones.

Versión actual: **1.0 – Consola funcional completa**

---

## 🚀 Características 
✔ Registro de clientes  
✔ Creación de cuentas bancarias (Ahorro / Corriente)  
✔ Depósitos  
✔ Retiros  
✔ Transferencias entre cuentas  
✔ Historial de transacciones por cuenta  
✔ Listado de clientes  
✔ Arquitectura en capas (Modelo / Servicio / Util / Vista)  
✔ Generador de números de cuenta  
✔ Validaciones de datos

---

## 🛠️ Tecnologías usadas

- **Java 17**
- Estructura modular por paquetes
- Programación Orientada a Objetos (POO)
- Manejo de colecciones (`ArrayList`)
- Consola estándar (Scanner)

## 📂 Estructura del Proyecto
```text
src/
└── banco/
    ├── modelo/
    │   ├── Cliente.java
    │   ├── Cuenta.java
    │   └── Transaccion.java
    ├── servicio/
    │   └── Banco.java
    ├── util/
    │   └── GeneradorNumeroCuenta.java
    └── vista/
        └── MainBanco.java
```

---

## 🧱 Arquitectura por capas

### 🟦 Modelo
Contiene las entidades principales del sistema:
- `Cliente`
- `Cuenta`
- `Transaccion`

### 🟩 Servicio
Lógica de negocio del banco:
- Registro de clientes  
- Búsqueda de cuentas  
- Creación de cuentas  
- Reportes (future)

### 🟨 Util
Herramientas auxiliares:
- Generador de números de cuenta incremental

### 🟧 Vista (Consola)
Interacción con el usuario mediante menú clásico:
- Ocho opciones principales  
- Uso del `Scanner` para leer datos  

---

## ▶️ Ejecución del programa
1. Clona este repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/MiniBanco-Extended.git
2. Importa el proyecto a tu IDE (IntelliJ IDEA recomendado)
3. Ejecuta la clase
   ```bash
   banco.vista.MainBanco
4. Usa el menu interactivo para gestionar el banco:
   ```bash
   ======== MINI BANCO EXTENDED ========
   1. Registrar cliente
   2. Crear cuentas
   3. Depositar dinero
   4. Retirar dinero
   5. Transferencia entre cuentas
   6. Consultar historial
   7. Mostrar clientes
   8. Salir del sistema

---

## 💳 Ejemplo de uso
Registro de cliente
  ```bash
  Ingrese su DNI:
  72916584
  Ingrese nombre de cliente:
  Marcelo Amaya
  Cliente agregado con éxito.
```
Creacion de cuenta: 
```bash
Ingrese el tipo de cuenta (Ahorro / Corriente):
Ahorro
Cuenta creada exitosamente.
Número: 1001
Titular: Marcelo Amaya
```

---

## 🧪 Próximas mejoras (Roadmap 1.1 - 2.0)
1.1 – Refactor y Programación Funcional
Uso de Streams
  - Optional en métodos de búsqueda
  - Lambdas forEach
  - Validaciones más robustas
  
1.2 – Mejoras de arquitectura
  - Reporte general del banco
  - Enums para tipo de cuenta
  - Separar lógica del menú en otra clase

2.0 – Versión gráfica o API
  - JavaFX / Swing
  - Migración a Spring Boot
  - Persistencia en base de datos
  - Rest API

---

## 👤 Autor
  - Marcelo Amaya Medina

Proyecto de práctica y aprendizaje de Java orientado a POO y arquitectura por capas.

---

## 📜 Licencia
MIT License – libre para estudiar, modificar y mejorar.

---
