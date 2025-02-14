# 📱 Recipe Explorer

Es una aplicación Android desarrollada en Kotlin con Jetpack Compose que permite explorar recetas de cocina, ver detalles de cada receta y visualizar su país de origen en un mapa.

![Screenshot 2025-02-14 at 09 27 04](https://github.com/user-attachments/assets/482e266d-1cf5-4acb-9a4b-265883169253)


https://github.com/user-attachments/assets/9e2b3394-121e-4316-9161-bd3f75839575


---

## 🚀 **Introducción**
Este proyecto fue desarrollado como parte de una prueba técnica.
Se implementó una solución completa aplicando buenas prácticas y gestionando el tiempo disponible.

## 🎥 **Video Explicativo**
Video de 15 minutos mostrando el proyecto y las etapas de desarrollo:
- [Explicación del Proyecto Recipe Xplorer - Youtube](https://www.youtube.com/@juanjosevarqu)

---

### **Librerías y Tecnologías Utilizadas**
- **Kotlin**: Lenguaje principal para el desarrollo de apps Android.
- **Jetpack Compose**: Para la construcción de interfaces declarativas.
- **Hilt**: Para la inyección de dependencias.
- **Retrofit y Ktor**: Cliente y servicio para hacer peticiones a la API.
- **Google Maps SDK**: Para la integración de mapas.
- **Coil**: Para la carga de imágenes de URLs.
- **Junit**: Para pruebas unitarias.
- **Turbine**: Para poder testear Flows.
- **Hilt y Compose Test**: Para pruebas instrumentadas de UI.
- **Otros**: Gson, Serialization, Jetpack Navigation, Material 3.

## 🛠️ **Decisiones Técnicas**

### **Arquitectura**
Utilicé Clean Architecture para separar las capas de la aplicación:
- **core**: Clases, funciones y componentes genéricos.
- **data**: Capa de datos, incluye DTOs, servicios remotos y repositorios.
- **domain**: Capa de dominio, modelos, interfaces, casos de uso.
- **presentation**: Componentes de UI, vistas y ViewModels.
- **di**: Modulos de inyección de dependencias con Hilt.
- **navigation**: Rutas y gestion de la navegación de forma modular.

### **Patrón de presentacion MVI(derivado de MVVM)**
Implementé el patrón MVI para una gestión de estado escalable y reactiva.
En vistas complejas y/o con muchas interacciones, define un patrón unidireccional.

### **Funciones de extensión**
Las funciones de extensión son de ayuda para simplificar los archivos, mejorar la legibilidad y escalabilidad del proyecto.

### **Componentes reutilizables**
Se buscó modularizar los componentes y hacerlos mas pequeños, para poder reutilizarlos y modificarlos facilmente.

---

## ⌞ ⌝ **Accesibilidad**
Se limito la escala máxima de la fuente, proveniente de los ajustes del dispositivo.
Esto con el fin de evitar deformar la app con tamaños muy grandes
![Screenshot 2025-02-14 at 10 24 24](https://github.com/user-attachments/assets/81534bbc-7ad2-45b1-912b-48f7e53e3eba)

---

## 🗓️ Plan de Ejecución

- **Día 1**: Desarrollo de la capa de Datos y Dominio, integración en el Home (listado de recetas).
- **Día 2**: Creación de vistas Detalle de receta y Ubicación en el mapa.
- **Día 3**: Testing, correcciones, mejoras de accesibilidad y documentación.

---

## ✅ **Pruebas Implementadas**
Se realizaron unos Test y pruebas que aportaron valor y estabilidad al proyecto:
- **Pruebas de unitarias**: ViewModel y eventos con estado de la UI.
- **Pruebas de integración**: ViewModel con repositorio y casos de uso.
- **Pruebas de UI con Compose Test**: Navegacion entre pantallas de forma esperada.

---

## 📂 **Estructura del Proyecto**
```
recipexplorer/
│
├── core/
│   ├── domain/
│   └── presentation/
│
├── data/
│   ├── remote/
│   └── repository/
│
├── di/
│   ├── ApiModule.kt
│   └── RepositoryModule.kt
│
├── domain/
│   ├── constants/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
├── navigation/
│   ├── utils/
│   ├── NavGraph.kt
│   └── Routes.kt
│
├── presentation/
│   ├── detail/
│   │   ├── components/
│   │   └── navigation/
│   ├── home/
│   │   ├── components/
│   │   └── navigation/
│   └── map/
│       └── navigation/
│
└── ui/
    └── MainActivity.kt
```

### **Complicaciones presentadas**
- Pruebas de UI instrumentadas (Hilt + Compose Test). (Conflictos con dependencias)
- Debido al corto tiempo, se simplificaron algunos componentes de la arquitectura.

### **Mejoras Potenciales**
- Separar el proyecto en módulos independientes.
- Implementar un caso de uso base genérico con manejo de errores en la solicitudes a la API.
- Resolver conflictos de Pruebas de Ui instrumentadas. (Hilt + Compose)
- +Test Unitarios que aporten valor.
- +Test de Integración en puntos críticos.
- +Test de UI, asegurando la correcta interacción de las vistas.
- Rediseñar la interfaz de usuario con una guia de estilos y paleta de colores.


---

## 🧪 **Ejecución del Proyecto**
1. Clonar el repositorio desde [GitHub](https://github.com/juanjosevarqu/Recipe-Explorer.git).
2. Agregar una API Key de Google Maps en `main/res/values/strings.xml`.
   ```
   <string name="google_maps_api_key"></string><!--Aqui tu key de GoogleMaps-->
   ```
3. Compila y ejecutar el proyecto en Android Studio o IntelliJ

---

- 👨‍💻 **Autor**: Juan Jose Vargas
- 📧 **Correo**: johazdev@gmail.com
- 👤 **Linkedin**: https://www.linkedin.com/in/juanjosevarqu/

---