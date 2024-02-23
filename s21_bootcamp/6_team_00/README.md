# Hi, people!
Today we’ll work in small teams developing a multi-module application.  
By the multi-module architecture we'll understand a project with multiply Gradle modules. The idea implies separation by a purpose of the application code into loosely coupled and self-sufficient parts called modules. It helps to make a code base more clear, maintainable and reduce its complexity.  
As an example we'll take an Android platform.

**Advice!** Read about modularization in android on the official android documentation resource.

In the documentation about modules you can find a mark telling us that before you start it's important to get familiar with the app architecture principles and guidelines. You may read some articles about SOLID and Clean Architecture principles, and then about Android architecture principles to be familiar with, but don't spend much time on it, we'll touch these topics later.  

**Advice!** There are different ways to divide an app into modules: by feature and by architecture layer (you can read about it with reference to architecture principles). Sometimes, the mixed variant is used. Today we'll develop different feature modules and combine them into an Android app.

In the Day00 project, we wrote some features for the Smart Calculator project. Now it's time to move our developments in a real application that a user can interact with. Also, it can be easily decomposed into the feature modules and developed in parallel.

## Themes:
- Modularization of the application

### Project: The Smart Calculator on the android platform.

# Exercises:

**Requirement!** Please, don't commit generated files and folders, such as `.idea`, `.out`, `.build`, `.gradle`, etc. In IntelliJ IDEA and Android Studio they are usually marked with orange/brown color. Commit and push to the origin only source code of your app. Delete generated files before commit or better add them to the `.gitignore` file in your project.

**Requirement!** There is no need to download gradle. Please, use the IDE embedded one.

**Advice!** Read the documentation about navigation between modules on the Android developers resource.

## Exercise 0. Create a new android project

**Requirement!** The project with modules should look like: an Android project, containing packages for each module. Each package should contain source code (and resources if needed, like strings, layouts, etc.) of a module.

## Exercise 1. Develop the feature modules

- The app must have feature modules for the tools: Circles (or Circles-2), Prime numbers, Thermometer (or Thermohydrometer) - from the Day00 project
- For each module consider UI input and output. It must be implemented using standard android components (input fields, checkboxes, text fields, buttons). Each tool/module should have its own screen.
- Declare these modules in Gradle

**Advice!** Read the official documentation about declaration of modules in Gradle (e.g. using `include` in the root settings.gradle)

### Exercise 2. Develop core modules
Create a logger module
- It should be a small lightweight module, that contains class/classes with logic for logging
- The easy-to-implement way is to create a Kotlin `object`, that will be an abstraction layer over the standard `Log` class methods. The idea is to hide the concrete realisation (it can be `Log` or `Timber` or whatever) behind the facade of your own methods, so if we want to change concrete realisation, we have no need to change it in the bunch of our project modules. Also, in our facade we can declare some methods and constants specific for our app (e.g. default logging TAG or some enhanced exception info logging)
- Declare this module in Gradle

## Exercise 3. Come together! Develop the app module

**Advice!** Read the official documentation about creating dependencies between modules with Gradle. Be careful with the dependencies between modules. For example, feature modules shouldn't depend on each other, if there is no need (cross-logic), but each of modules using logger should have dependency on it.

- In the app module we have our main app logic. The app must have a main screen with buttons navigate to the tools. It must be an entry point in our Application.
- Connect all modules. There should be the app module, feature modules and core modules
- Setup navigation between the app module and feature modules
- Use the logging module to log any operations in the feature modules. Add some "analytics": log button taps and screen opening. Usually, it's a good practice to use logging where an exception may occur and when you're obtaining info (e.g. input) or preparing it before some logic (e.g. before calculations). Also, you can add logs to the Android framework events to get better acquainted with it (e.g. activity/fragment lifecycle) 

## Bonus exercise 4: More features
- The Smart Calculator now is a very-easy-to-extend application. Decide with you team, what functionality do you want to add next and write a new module/a couple of modules
- New modules can be of different types: a new feature, a new module with core functionality

💡 [Tap here](https://docs.google.com/forms/d/e/1FAIpQLSeuGXhSvF8aA8PBnEUx04iyh0gcnZaqPODXilAatmDK5C2sGA/viewform) **to leave your feedback on the project**. Product Team really tries to make your educational experience better.
