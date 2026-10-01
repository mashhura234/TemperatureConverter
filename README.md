[README.md](https://github.com/user-attachments/files/32925717/README.md)
# 🌡️ Temperature Converter

A desktop GUI application built with **Java Swing** that converts temperatures between Celsius, Fahrenheit, and Kelvin — with live unit badges, temperature-category classification, persistent conversion history, and multiple color themes.

This project was developed as an Object-Oriented Programming (OOP) coursework submission, demonstrating encapsulation, abstraction, inheritance, and polymorphism through a practical, functioning application.

---

## ✨ Features

- **Two-way conversion** between Celsius, Fahrenheit, and Kelvin
- **Swap button** to instantly flip the "From" and "To" units
- **Live unit badges** (°C / °F / K) that update as you change the dropdown
- **Temperature category classification** (Freezing, Cold, Cool, Warm, Hot) with color-coded labels
- **Persistent conversion history** — saved to `history.txt` and reloaded automatically on next launch
- **Clear History** with a confirmation prompt before deleting saved records
- **Three color themes** — White, Dark, and Green — applied across the entire UI
- **Custom-styled UI components** — rounded cards, rounded buttons with soft shadows, and a gradient-colored title
- **Splash screen** on startup before the main window loads

---

## 🖥️ Tech Stack

- **Language:** Java (JDK 17+ recommended)
- **UI Framework:** Java Swing (`javax.swing`, `java.awt`)
- **Persistence:** Plain text file I/O (`BufferedReader` / `BufferedWriter`)

No external libraries or dependencies are required.

---

## 📁 Project Structure

```
TemperatureConverter/
├── src/
│   ├── Main.java                   # Application entry point
│   ├── SplashScreen.java           # Startup splash screen (extends JWindow)
│   ├── TemperatureConverterGUI.java # Main Swing UI and event handling
│   ├── TemperatureConverter.java   # Conversion formulas (Celsius/Fahrenheit/Kelvin)
│   ├── Utils.java                  # Static helper methods (validation, rounding, categorization)
│   └── HistoryManager.java         # Reads/writes conversion history to history.txt
├── history.txt                     # Auto-generated — stores saved conversion history
└── README.md
```

---

## 🧩 Architecture Overview

Each class has a single, focused responsibility:

| Class | Responsibility |
|---|---|
| `Main` | Launches the application |
| `SplashScreen` | Displays a loading screen, then hands off to the main GUI |
| `TemperatureConverterGUI` | Builds the Swing interface and wires up all event handling |
| `TemperatureConverter` | Pure conversion math — no UI or file logic |
| `Utils` | Stateless static helpers (unit symbols, category colors, validation, rounding) |
| `HistoryManager` | Handles saving/loading conversion history to/from disk |

### OOP Concepts Demonstrated

- **Encapsulation** — private fields and a private constructor in `Utils` restrict direct instantiation and state access
- **Abstraction** — `TemperatureConverter` hides formula implementation behind simple method calls
- **Inheritance** — custom Swing components (`RoundButton`, `RoundedButton`, `RoundedPanel`) extend `JButton`/`JPanel`; `SplashScreen` extends `JWindow`
- **Polymorphism** — overridden `paintComponent()` methods render custom shapes at runtime; `HistoryCellRenderer` implements `ListCellRenderer<String>` to render each history row

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or later installed
- A terminal or IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Compile and Run (Command Line)

From the project root directory:

```bash
# Compile all source files
javac src/*.java

# Run the application
java src.Main
```

### Run from an IDE

1. Open the project folder in your IDE.
2. Mark `src` as the source root (if required).
3. Run `Main.java`.

---

## 📝 Notes

- `history.txt` is created automatically in the project's working directory the first time a conversion is made — no setup required.
- The title font falls back gracefully depending on which fonts are installed on your system.

---

## 👤 Author

**Mst. Mashhura Nasrin Monika**

B.Sc. in Computer Science and Engineering, Metropolitan University, Sylhet

---

## 📄 License

This project was created for academic coursework purposes.
