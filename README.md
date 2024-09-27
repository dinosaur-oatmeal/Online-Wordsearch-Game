# Real-time Multiplayer Word Game in Java

## Overview
This project is a **real-time multiplayer word search game** developed in Java. Designed to support multiple, simultaneous 2-4-player games, the game provides a seamless and interactive word search experience through real-time communication using WebSockets. Built with both frontend and backend technologies, the application leverages a responsive interface while ensuring efficient server-side handling of multiplayer interactions.

## Features
- **Real-time Multiplayer:** Uses Java WebSockets to handle live, synchronous gameplay between 2 to 4 players.
- **Dynamic Interface:** Employs HTML and CSS for a user-friendly interface that adjusts to various screen sizes, enhancing the gameplay experience.
- **Server Deployment:** Deployed on an online server to allow easy access and real-time connectivity for multiple players.
- **Automated Testing:** Implements a suite of JUnit tests to validate game logic, ensuring consistent functionality and reliability during gameplay.
- **Detailed Architecture:** System design and data flow are mapped out using UML diagrams, providing a clear blueprint of the game's structure.
  
## Technologies Used
- **Programming Language:** Java
- **Backend:** Java WebSockets for real-time communication
- **Frontend:** HTML, CSS
- **Project Management:** Maven
- **Testing:** JUnit
- **IDE:** VS Code
- **Server Environment:** Unix VM
- **Data Management:** JSON for message parsing and data exchange

## Running the Project
```bash
% export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.18.0.9-0.3.ea.el8.x86_64
% mvn clean
% mvn compile
% mvn package
% mvn exec:java -Dexec.mainClass=uta.cse3310.App
```
