# AI Study Planner

**AI Study Planner** is an Android mobile application designed to help students organize their study activities, manage academic tasks, and monitor their study progress. The application provides subject management, task management, progress tracking, and topic-based study suggestions through a simple and user-friendly interface.

## Project Overview

Students often find it difficult to organize their subjects, manage deadlines, and maintain a consistent study schedule. AI Study Planner aims to make study planning easier by providing a single application to manage subjects, plan tasks, track completed work, and receive study suggestions.

This project is developed as part of the **Mobile Application Development (MAD)** academic coursework.

## Features

* **Subject Management:** Add and manage study subjects.
* **Task Management:** Create, view, update, and delete study tasks.
* **Task Priorities:** Organize study tasks by priority and deadline.
* **Task Completion:** Mark tasks as completed when the work is finished.
* **Progress Tracking:** Monitor study progress using the completion status of tasks.
* **Study Suggestions:** Receive topic-based study suggestions to support study planning.
* **Local Database:** Store subject and task information locally using SQLite.
* **User-Friendly Interface:** Navigate between different application screens easily.

## Technologies Used

| Technology     | Purpose                                 |
| -------------- | --------------------------------------- |
| Java           | Application logic and functionality     |
| XML            | Designing application layouts           |
| Android Studio | Application development                 |
| SQLite         | Local database management               |
| Gradle         | Project build and dependency management |

## Application Modules

1. **Home Screen:** Provides access to the application's main features.
2. **Add Subject:** Allows users to add study subjects and related details.
3. **Add Task:** Allows users to create study tasks with relevant information.
4. **View Tasks:** Displays saved tasks and provides task management options.
5. **Progress Tracking:** Shows study progress based on completed tasks.
6. **Study Suggestions:** Provides topic-based suggestions to help users plan their learning.

## How to Use the Application

1. Launch the AI Study Planner application.
2. Add the subjects you want to study.
3. Create study tasks and enter their relevant details.
4. Open the task list to view and manage your tasks.
5. Mark tasks as completed after finishing them.
6. Visit the progress screen to check your study progress.
7. Use the Study Suggestions section to receive topic-based suggestions.

## Database

The application uses **SQLite** for local data storage. Subject and task information is stored on the device so users can manage their study activities without requiring a remote database server.

## Installation and Setup

To run the project locally:

1. Install Android Studio.
2. Clone or download this repository.
3. Open Android Studio and select **Open**.
4. Select the downloaded project folder.
5. Allow Gradle synchronization to complete.
6. Connect an Android device with USB debugging enabled or start an Android emulator.
7. Click **Run** to build and launch the application.

## Project Structure

```text
AIStudyPlanner2/
├── app/
│   └── src/
│       └── main/
│           ├── java/          # Java source files
│           ├── res/           # XML layouts and resources
│           └── AndroidManifest.xml
├── gradle/                    # Gradle wrapper files
├── build.gradle.kts           # Project build configuration
├── settings.gradle.kts        # Project settings
├── gradle.properties
├── gradlew
└── gradlew.bat
```

## Future Enhancements

* Integration with an external AI API for personalized study recommendations.
* Automatic study timetable generation.
* Study reminders and notifications.
* Weekly and monthly progress reports.
* Cloud backup and synchronization.

## Current Limitations

* Study suggestions are topic-based and do not currently require an external AI API.
* Data is stored locally on the device.
* Cloud synchronization and automated notifications are planned for future versions.

## Project Information

**Project Name:** AI Study Planner
**Project Category:** Android Mobile Application
**Academic Subject:** Mobile Application Development (MAD)
**Programming Language:** Java
**Database:** SQLite
**Development Tool:** Android Studio

---

*Developed as an academic project to demonstrate Android application development, database operations, user interface design, and study progress management.*
