# Parcial Biblioteca – Java (POO)
---

###  Gestión de libros
- Registro de: ISBN, título, autor, año, ejemplares totales y disponibles.  
- Validaciones: ISBN (13 dígitos) y año de publicación válido.  
- Métodos clave:
  - `prestar()` — disminuye disponibles si hay ejemplares.
  - `devolver()` — aumenta ejemplares disponibles.
  - `estaDisponible()` — devuelve si hay ejemplares libres.  
- Excepción personalizada: `LibroNoDisponibleException`.

###  Gestión de usuarios
- Usuario con ID autogenerado, nombre y correo validado.  
- Reglas:
  - Máximo **3 libros** prestados por usuario.
  - Multas acumuladas hasta **$5000** (límite de bloque).  
- Métodos clave:
  - `puedePedirPrestado()` — verifica cupo y multas.
  - `agregarMulta()` — suma multas al usuario.
  - `pagarMultas()` — permite abonar saldo de multas.  
- Excepción personalizada: `UsuarioSinCupoException`.

---
