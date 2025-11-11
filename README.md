Arkanoid Game - Object-Oriented Programming Project
Group 2:AntiK - Class INT2204 1

Đặng Minh Quân         - 24020280
Trần Thiệu Hưng        - 24020154
Đinh Văn Dương         - 24020100
Nguyễn Thị Phương Ngọc - 24020253
Instructor: Kiều Văn Tuyên / Vũ Đức Hiếu
Semester: HK1 - 2025

Description
This is a classic Arkanoid game developed in Java as a final project for Object-Oriented Programming course. The project demonstrates the implementation of OOP principles and design patterns.

Key features:

The game is developed using Java 17+ with JavaFX/Swing for GUI.
Implements core OOP principles: Encapsulation, Inheritance, Polymorphism, and Abstraction.
Applies multiple design patterns: Singleton, Factory Method, Strategy, Observer, and State.
Features multithreading for smooth gameplay and responsive UI.
Includes sound effects, animations, and power-up systems.
Supports save/load game functionality and leaderboard system.
Game mechanics:

Control a paddle to bounce a ball and destroy bricks
Collect power-ups for special abilities
Progress through multiple levels with increasing difficulty
Score points and compete on the leaderboard

UML Diagram
Class Diagram

<img width="11964" height="7818" alt="UML" src="https://github.com/user-attachments/assets/2f62f3e7-9d68-47d8-84cc-332afcc8bdce" />

Design Patterns Implementation

1. Singleton Pattern
Used in: MainMedia, MainImage.

Purpose: Ensure only one instance exists throughout the application.

2. Factory Pattern
Used in: Factory(GameObject), Product(Ball, Paddle, Brick).

Purpose: Create objects in the game.


Multithreading Implementation

The game uses multiple threads to ensure smooth performance:

Game Loop Thread: Updates game logic at 60 FPS
Audio Thread Pool: Plays sound effects asynchronously
PowerUpManager Thread: Stop power up

Installation
Clone the project from the repository.
Open the project in the IDE.
Run the project.

Usage

Controls

Key	Action

← or A	Move paddle left

→ or D	Move paddle right

SPACE	Launch ball

P	Pause game/Resume game

How to Play

Start the game: Click "New Game" from the main menu.

Control the paddle: Use arrow keys or A/D to move left and right.

Launch the ball: Press SPACE to launch the ball from the paddle.

Destroy bricks: Bounce the ball to hit and destroy bricks.

Collect power-ups: Catch falling power-ups for special abilities.

Avoid losing the ball: Keep the ball from falling below the paddle.

Complete the level: Destroy all destructible bricks to advance.


Power-ups

Name	Effect

Long Paddle	Increases paddle width for 10 seconds

Gun Paddle	Shoot bullet to destroy bricks for 10 seconds

Explosive Ball	Explodes in a 3x3 area for 10 seconds

Infinity Ball	Indestructible and immortal ball for 5 seconds

Multi Ball	Spawns 1 additional balls

Bonus Point Bunus 50 Points


Scoring System

Brick: 10 points

Power-up Collection: 50 points

Each remaining lives corresponds to 20 bonus points.


Demo

Screenshots

Main Menu

<img width="677" height="876" alt="image" src="https://github.com/user-attachments/assets/07075e74-6d44-41a2-9fd5-1d87a2958484" />

Gameplay

<img width="679" height="879" alt="image" src="https://github.com/user-attachments/assets/fc234dcd-53fd-4d1f-b5a5-0b7b22fbe593" />

Power-ups in Action

<img width="220" height="313" alt="image" src="https://github.com/user-attachments/assets/6942aab7-722b-4017-b064-501a3bd7c604" />

<img width="524" height="451" alt="image" src="https://github.com/user-attachments/assets/8d9814de-09c5-4230-8ab3-cf692014a892" />

<img width="449" height="446" alt="image" src="https://github.com/user-attachments/assets/6f5aa804-379b-4a9b-9078-0ae536b9296f" />

<img width="419" height="165" alt="image" src="https://github.com/user-attachments/assets/6a1fc401-d2b3-457d-bddb-3b7da5c275dc" />

<img width="369" height="546" alt="image" src="https://github.com/user-attachments/assets/a251b325-d81b-4a16-a0f4-6f95c36fa970" />

<img width="197" height="140" alt="image" src="https://github.com/user-attachments/assets/a58f99a2-0edc-4bac-9abe-995898f76a1f" />


Leaderboard

<img width="676" height="870" alt="image" src="https://github.com/user-attachments/assets/070e3458-0fb3-4ef2-a968-39a6105cb75f" />


Future Improvements

Planned Features


1.Additional game modes

Time attack mode

Survival mode with endless levels

Co-op multiplayer mode

Enhanced gameplay


2.Boss battles at end of worlds

More power-up varieties (freeze time, shield wall, etc.)

Achievements system

Technical improvements


3.Migrate to LibGDX or JavaFX for better graphics

Add particle effects and advanced animations

Implement AI opponent mode

Add online leaderboard with database backend


Technologies Used

Technology	Version	Purpose

Java	17+	Core language

JavaFX	21.0.2	GUI framework

Maven	3.9+	Build tool


License

This project is developed for educational purposes only.

Academic Integrity: This code is provided as a reference. Please follow your institution's academic integrity policies.


Notes

The game was developed as part of the Object-Oriented Programming with Java course curriculum.

All code is written by group members with guidance from the instructor.

Some assets (images, sounds) may be used for educational purposes under fair use.

The project demonstrates practical application of OOP concepts and design patterns.


