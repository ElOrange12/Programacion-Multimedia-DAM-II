# ListaCompra

App Android de lista de la compra (Kotlin, Jetpack Compose, Material 3).

- Añadir productos (no se permiten vacíos), marcarlos como comprados/pendientes y eliminarlos.
- Contador de pendientes con `<plurals>`.
- Mensaje centrado cuando la lista está vacía.
- Textos en `res/values/strings.xml` (inglés) y `res/values-es/strings.xml` (español).
- Colores solo desde `MaterialTheme.colorScheme`; modo claro y oscuro (tema naranja y negro).
- Mensajes en Logcat (etiqueta `ListaCompra`) al añadir, cambiar de estado y eliminar.

Estructura: `model/` (Product), `viewmodel/` (estado), `ui/` (pantalla), `ui/components/`, `ui/theme/`.

Abrir con Android Studio y ejecutar la configuración `app` (minSdk 26).
