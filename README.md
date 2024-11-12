# Trabajo Práctico Integrador
## Programación Avanzada I (Backend)
### Ing. y Lic. en Sistemas

### Objetivos
- Desarrollar un microservicio simple, que permita llenar una base de datos, con datos de Pilotos de F1 obtenidos desde una API pública

### Evaluacion
- Fecha de Entrega: 23/11/2024
- El proyecto debe compilar sin errores en cualquier entorno de programación en el que se abra
- Todos los test unitarios deben pasar en verde

### Punto de partida
- Se deberá crear el proyecto desde cero, siguiendo las recomendaciones vertidas durante la cursada.
- Se deberá consumir de la API https://openf1.org/

## Consigna
#### Módulo F1
_Se desea implementar un backend para un microservicio que permita capturar la información de Pilotos, desde una API externa._

#### Restricciones:
- No puede existir dos Pilotos con el mismo nombre completo (ej. "Franco Colapinto", "Fernando Alonso", "Sergio Perez")
- No puede existir dos Pilotos con el mismo nombre Abreviado (ej. "COL", "ALO", "PER")
- Todos los atributos de Piloto son obligatorios

#### Estructura de la clase Piloto a persistir:

 ```json
    {
      "id": "cabbd417-1841-4b25-8798-e8d54df1416e",
      "name": "Max",
      "surname": "Verstappen",
      "full_name": "Max Verstappen",
      "short_name": "VER",
      "picture_url": "https://www.formula1.com/..."
    }
```

#### Funcionalidad
- Cargar Pilotos
  - Endpoint: POST http://localhost:8080/drivers
  
- Obtener Pilotos
  - Endpoint: GET http://localhost:8080/drivers

## Etapas

### Alumnos con parcial aprobado
#### Etapa 1 - Deadline 23-11-24
- Creación de casos de uso que cubran la funcionalidad, aplicando patrones y principios de diseño SOLID
#### Etapa 2 - Hasta el momento de presentarse a Examen final
- Creación de infraestructura para obtener pilotos de la api y persistir en base local, y endpoints para comunicarse con la aplicación

### Alumnos con parcial NO aprobado
#### Etapa 1 - Deadline 23-11-24
- Creación de casos de uso que cubran la funcionalidad, aplicando patrones y principios de diseño SOLID
- Implementación de Controller/s (endpoints) con test que dejen establecido el entry-point de la aplicación
#### Etapa 2 - Hasta el momento de presentarse a Examen final
- Creación de infraestructura para obtener pilotos de la api y persistir en base local


### Buenas prácticas y conceptos a considerar
- La nomenclatura de paquetes será en minúsculas
- La nomenclatura de clases será en UpperCamelCase
- La nomenclatura de métodos será en lowerCamelCase
- La organización de paquetes será por modelo->aspecto, tanto a nivel src/main como a nivel src/test. Ejemplo:
  ```
  drivers
  └─ excepciones
  └─ modelo
  └─ repositorio
  └─ casodeuso
  ```
- Usar Excepciones personalizadas
- Se debe usar método factory/instancia para crear objetos
- Nomenclatura representativa de clases, métodos, etc.
