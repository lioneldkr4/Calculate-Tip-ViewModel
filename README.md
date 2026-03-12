# Tip Calculator – Compose Multiplatform

Aplicación de escritorio desarrollada en **Kotlin utilizando Compose Multiplatform** que permite calcular el monto de una propina a partir del total de una cuenta y un porcentaje definido por el usuario.

La aplicación utiliza una arquitectura **MVVM (Model – View – ViewModel)** para separar la lógica de negocio de la interfaz de usuario y emplea **StateFlow** para manejar el estado de forma reactiva.

---

## Características

- Cálculo automático de propinas
- Entrada del monto de la cuenta
- Configuración del porcentaje de propina
- Opción para redondear la propina
- Actualización automática del resultado
- Interfaz moderna basada en **Material Design 3**

---

## Tecnologías utilizadas

- **Kotlin**
- **Compose Multiplatform**
- **Compose for Desktop**
- **Material Design 3**
- **StateFlow**
- **ViewModel**
- **Arquitectura MVVM**

---

## Arquitectura del proyecto

La aplicación sigue el patrón **MVVM** para organizar el código:

### View
La interfaz de usuario está implementada con **Jetpack Compose**, específicamente en el composable:


---

### ViewModel
El **TipCalculatorViewModel** maneja:

- la lógica de cálculo de la propina
- los eventos de la interfaz
- la gestión del estado de la aplicación

El estado se expone mediante **StateFlow**, permitiendo que la interfaz se actualice automáticamente.

---

### UI State
El estado de la interfaz se modela con la data class:


TipUiState


Esta clase contiene:

- monto de la cuenta
- porcentaje de propina
- opción de redondeo
- resultado calculado

---

## Funcionamiento

1. El usuario introduce el **monto de la cuenta**.
2. Define el **porcentaje de propina**.
3. Opcionalmente activa el **redondeo de la propina**.
4. El **ViewModel procesa los datos**.
5. El resultado se muestra automáticamente en la interfaz.

---

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run Web Application

To build and run the development version of the web app, use the run configuration from the run widget
in your IDE's toolbar or run it directly from the terminal:

- for the Wasm target (faster, modern browsers):
    - on macOS/Linux
      ```shell
      ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
      ```
    - on Windows
      ```shell
      .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
      ```
- for the JS target (slower, supports older browsers):
    - on macOS/Linux
      ```shell
      ./gradlew :composeApp:jsBrowserDevelopmentRun
      ```
    - on Windows
      ```shell
      .\gradlew.bat :composeApp:jsBrowserDevelopmentRun
      ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).