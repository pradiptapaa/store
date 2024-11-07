---

# 🛍️ FakeStoreApp

An advanced **Android e-commerce** app developed with **Clean Architecture** principles, integrating **MVVM Design Pattern**, **Jetpack Compose**, **Ktor** for networking, **Koin** for dependency injection, and **Multi-Module Architecture**. Built for scalability, maintainability, and real-world usability.

---

## 🚀 Features

- **Product Listings**: Dynamic display of products from the [Fake Store API](https://fakestoreapi.com/).
- **Product Details**: Comprehensive information and real-time updates for each product.
- **Cart Management**: Add items, adjust quantities, and manage your shopping cart.
- **User Authentication**: Secure and personalized login and registration.

> **Note:** Some features, such as the delete cart functionality and certain business flows, are currently under development and will be updated soon.

---

## 🛠️ Technologies Used

| Technology              | Description                                                                                  |
|-------------------------|----------------------------------------------------------------------------------------------|
| **Kotlin**              | Primary programming language for Android development.                                        |
| **Jetpack Compose**     | Declarative UI toolkit for flexible and maintainable UI.                                     |
| **MVVM Pattern**        | Enables clear separation between UI, logic, and data.                                        |
| **Ktor**                | Multi-platform HTTP client for efficient network operations.                                 |
| **Koin**                | Lightweight Dependency Injection framework for scalable dependency management.               |
| **Multi-Module**        | Modularized codebase, enhancing reusability and maintainability.                             |

---

## 📐 Architecture Overview

This project follows **Clean Architecture** to achieve a clear separation of concerns:

1. **Presentation Layer**: UI components built with Jetpack Compose and MVVM ViewModels.
2. **Domain Layer**: Core business logic, including use cases and repository interfaces.
3. **Data Layer**: Manages network requests using Ktor and handles data persistence.

---

## 🏗️ Project Structure

- **app**: UI components and navigation.
- **core**: Shared utilities and constants.
- **domain**: Business logic, use cases, and repository interfaces.
- **data**: Network and data management using Ktor and Koin.

---

## ⚙️ Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/pradiptapaa/store.git
   cd store
   ```
2. Open in Android Studio and sync the project.
3. Run the project on an emulator or device.

---

## 🔍 Testing

Includes unit tests for ViewModels, Use Cases, and Repository classes. Testing tools include **JUnit** and **MockK** for dependency handling.

---

## 📚 Resources

- **Fake Store API**: [https://fakestoreapi.com/](https://fakestoreapi.com/)
- **Ktor Documentation**: [https://ktor.io/docs/](https://ktor.io/docs/)
- **Koin Documentation**: [https://insert-koin.io/](https://insert-koin.io/)

---

## 👤 Author

- **Pradipta Priya Adyatmika** - [My LinkedIn](https://www.linkedin.com/in/pradiptapaa/)

---

> Built with 💻, ☕, and a commitment to clean, scalable code!

> And I need to sleep time so bad...
