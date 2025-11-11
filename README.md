# 🎮 Arkanoid Game – Object-Oriented Programming Project

### 👥 Group 2: AntiK – Class INT2204 1

| Đặng Minh Quân | 24020280 |
| Trần Thiệu Hưng | 24020154 |
| Đinh Văn Dương | 24020100 |
| Nguyễn Thị Phương Ngọc | 24020253 |

**Instructor:** Kiều Văn Tuyên / Vũ Đức Hiếu  
**Semester:** HK1 - 2025

---

## 📝 Description

**Arkanoid Game** is a classic brick-breaker game developed in **Java** as the **final project** for the *Object-Oriented Programming (OOP)* course.  
The project demonstrates the practical application of **OOP principles** and **design patterns** in a real-world interactive game.

---

## 🚀 Key Features

- Developed using **Java 17+** with **JavaFX/Swing** for GUI  
- Implements all major **OOP principles**:
  - Encapsulation
  - Inheritance
  - Polymorphism
  - Abstraction
- Applies multiple **Design Patterns**:
  - Singleton
  - Factory Method
  - Strategy
  - Observer
  - State
- Supports **Multithreading** for smooth gameplay and responsive UI  
- Includes **sound effects**, **animations**, and **power-up systems**  
- Features **Save/Load Game** functionality and **Leaderboard System**

---

## ⚙️ Game Mechanics

- Control the **paddle** to bounce the **ball** and destroy bricks  
- **Collect power-ups** for temporary abilities  
- Progress through **multiple levels** with increasing difficulty  
- Score points and **compete on the leaderboard**

---

## 🧩 UML Diagram

### 📘 Class Diagram
![UML Diagram](https://github.com/user-attachments/assets/2f62f3e7-9d68-47d8-84cc-332afcc8bdce)

---

## 🧠 Design Patterns Implementation

### 🟢 Singleton Pattern
**Used in:** `MainMedia`, `MainImage`  
**Purpose:** Ensure only one instance exists throughout the application.

### 🟡 Factory Pattern
**Used in:** `Factory (GameObject)`, `Product (Ball, Paddle, Brick)`  
**Purpose:** Dynamically create different game objects.

---

## 🧵 Multithreading Implementation

The game utilizes multiple threads to ensure smooth and stable performance:

| Thread | Purpose |
|--------|----------|
| Game Loop Thread | Updates game logic at 60 FPS |
| Audio Thread Pool | Plays sound effects asynchronously |
| PowerUpManager Thread | Manages and stops power-up effects |

---

## 🕹️ Controls

| Key | Action |
|-----|---------|
| ← or A | Move paddle left |
| → or D | Move paddle right |
| SPACE | Launch ball |
| P | Pause/Resume game |

---

## 🎯 How to Play

1. **Start the game**: Click **"New Game"** from the main menu  
2. **Control the paddle**: Move using arrow keys or **A/D**  
3. **Launch the ball**: Press **SPACE**  
4. **Destroy bricks**: Bounce the ball to hit and break bricks  
5. **Collect power-ups**: Catch falling power-ups to gain abilities  
6. **Avoid losing the ball**: Prevent it from falling below the paddle  
7. **Complete the level**: Break all destructible bricks to advance  

---

## ⚡ Power-ups

| Name | Effect |
|------|--------|
| 🧱 Long Paddle | Increases paddle width for 10 seconds |
| 🔫 Gun Paddle | Shoot bullets to destroy bricks for 10 seconds |
| 💥 Explosive Ball | Explodes in a 3×3 area for 10 seconds |
| 🔮 Infinity Ball | Makes the ball indestructible for 5 seconds |
| ⚽ Multi Ball | Spawns 1 additional ball |
| 💰 Bonus Point | Adds 50 bonus points |

---

## 🧮 Scoring System

| Action | Points |
|---------|--------|
| Destroy a brick | +10 |
| Collect power-up | +50 |
| Each remaining life | +20 bonus |

---

## 📸 Demo

### 🏠 Main Menu  
<img width="677" height="876" alt="Main Menu" src="https://github.com/user-attachments/assets/07075e74-6d44-41a2-9fd5-1d87a2958484" />

### 🎮 Gameplay  
<img width="679" height="879" alt="Gameplay" src="https://github.com/user-attachments/assets/fc234dcd-53fd-4d1f-b5a5-0b7b22fbe593" />

### ⚡ Power-ups in Action  
<img width="220" height="313" alt="Power-up 1" src="https://github.com/user-attachments/assets/6942aab7-722b-4017-b064-501a3bd7c604" />
<img width="524" height="451" alt="Power-up 2" src="https://github.com/user-attachments/assets/8d9814de-09c5-4230-8ab3-cf692014a892" />
<img width="449" height="446" alt="Power-up 3" src="https://github.com/user-attachments/assets/6f5aa804-379b-4a9b-9078-0ae536b9296f" />
<img width="419" height="165" alt="Power-up 4" src="https://github.com/user-attachments/assets/6a1fc401-d2b3-457d-bddb-3b7da5c275dc" />
<img width="369" height="546" alt="Power-up 5" src="https://github.com/user-attachments/assets/a251b325-d81b-4a16-a0f4-6f95c36fa970" />
<img width="197" height="140" alt="Power-up 6" src="https://github.com/user-attachments/assets/a58f99a2-0edc-4bac-9abe-995898f76a1f" />

### 🏆 Leaderboard  
<img width="676" height="870" alt="Leaderboard" src="https://github.com/user-attachments/assets/070e3458-0fb3-4ef2-a968-39a6105cb75f" />

---

## 🔮 Future Improvements

### 🕹️ Additional Game Modes
- Time Attack mode  
- Endless Survival mode  
- Co-op Multiplayer mode  

### 🧱 Enhanced Gameplay
- Boss battles  
- New power-ups (Freeze Time, Shield Wall, etc.)  
- Achievements system  

### 💻 Technical Upgrades
- Migrate to **LibGDX** or **JavaFX** for improved graphics  
- Add particle effects & animations  
- Implement **AI Opponent Mode**  
- Online leaderboard with **database backend**

---

## 🧰 Technologies Used

| Technology | Version | Purpose |
|-------------|----------|----------|
| Java | 17+ | Core programming language |
| JavaFX | 21.0.2 | GUI framework |
| Maven | 3.9+ | Build and dependency management |

---

## 🪪 License & Academic Note

This project was developed **for educational purposes only** as part of the **Object-Oriented Programming with Java** course.  
All code is written by group members under instructor guidance.  

Some assets (images, sounds) are used under **fair use** for educational demonstration.

> ⚠️ **Academic Integrity:**  
> Please use this project responsibly and adhere to your institution’s policies.

---

### 💡 Summary
This project demonstrates a **complete application** of OOP principles, design patterns, and multithreading in Java — packaged into a fun and interactive **Arkanoid-style game** 🎮.
