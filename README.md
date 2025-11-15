# Mini Social Media App with Hybrid DBMS (MySQL & MongoDB)

## Overview
This is a mini social media application built in Java demonstrating the use of a hybrid database management system combining MySQL (via JDBC) for structured user and authentication data, and MongoDB (via the MongoDB Java Driver) for unstructured social content such as posts, comments, and likes. The app features console-based interaction with proper layering using Model, DAO, Service, and UI layers.

## Features
- User registration with username, full name, email, and password (hashed using BCrypt)
- Secure user login and authentication using MySQL
- Display user profile with personal info and their posts including likes and comments
- Social interaction:
  - Create text posts stored in MongoDB
  - View feed of recent posts from all users (login required)
  - Like and comment on posts with simple numeric post selection to avoid complex ObjectId usage
- Implements connection pooling (JDBC), prepared statements, error handling, and logging basics

## Technologies Used
- Java 17+
- MySQL with JDBC Connector 8+
- MongoDB with MongoDB Java Driver (sync)
- BCrypt for password hashing
- Maven for project and dependency management

## Project Structure
- src/
- model/ - Java classes defining entities (User, Post, Comment)
- dao/ - Data Access Objects for MySQL & MongoDB interaction
- service/ - Business logic layer (UserService, PostService)
- util/ - Utility classes for DB connections, hashing, logging
- ui/ - Console-based user interface (MainMenu.java)


## Prerequisites
- MySQL Server (local or remote) installed with database and `users` table created:

`CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) UNIQUE,
full_name VARCHAR(100),
email VARCHAR(100) UNIQUE,
password_hash VARCHAR(100)
);`

- MongoDB server running locally or remotely with access permissions
- Java JDK and Maven installed

## Setup Instructions
1. Clone this repository:
`git clone <repository-url>`
2. Update DB connection details in `JDBCUtils.java` and `MongoDBUtil.java` to match your environment.
3. Build the project with Maven:
4. Run the console app from your IDE or using:
`mvn exec:java -Dexec.mainClass="ui.MainMenu"`

## Usage
- Register a new user.
- Login with your credentials.
- View your profile and posts with all likes and comments.
- View the feed (requires login).
- Create new posts.
- Like or comment on posts by selecting the post number shown in the feed (user-friendly numeric selection replaces object ID input).

## Notes
- Passwords are hashed securely using BCrypt.
- Posts and related social interactions are stored in MongoDB.
- All sensitive actions are protected by login checks.
- Sequential numbering of posts is implemented for easy liking/commenting.
