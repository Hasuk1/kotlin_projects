# Hi!
Congratulations! We are almost there with our job searching client-server app - all that's left is to cover the code with tests. Testing is a necessary part of programming and no doubt you know that each project should be covered with different types of tests. There is a big variety of different kinds of tests, even there are well-known and widely spread approaches like TDD. And if you can’t imagine how useful they are, read different topics about testing and test-driven approaches in real projects, especially written by managers - they know how tests can save time and human resources for sure.
Today we’ll run through interesting and important kinds of tests: unit testing, Android UI testing and server side tests. In Android client we’ll use JUnit library to test some “Kotlin” logic (not associated with android) and a handy Espresso library for UI testing, it helps keep view logic correct in an automated way. On the server side we’ll use instruments Ktor developers politely provided us to test our API.

## Themes:
- Unit tests with JUnit in Android app
- Android UI Testing with Espresso
- Ktor server application testing

# Exercises:

**Requirement!** Please, make each exercise in a separate project. For example, `Day09/src/exercise1`, `Day09/src/exercise2`, `Day09/src/bonusexercise3`, etc. If the previous exercise is needed for the next one, just copy a project from the previous to the next folder and continue development within the next one.

**Requirement!** Please, don't commit generated files and folders, such as `.idea`, `.out`, `.build`, `.gradle`, etc. In IntelliJ IDEA and Android Studio they are usually marked with orange/brown color. Commit and push to the origin only source code of your app. Delete generated files before commit or better add them to the `.gitignore` file in your project.

## Exercise 0: Test folders 
Android projects usually have default folders for tests (they're generated on android project creation). There should be `src/test` and `src/androidTest` folders. Find out what they are for.

## Exercise 1: Write unit tests for android client application
**Let's start our unit testing with tests of utility class:**
- Remember our phone mask formatter from the Day01? Add it to our android client project. It should be placed in the Utils module, to the extensions package. 
- Apply the formatter on the "Company details" screen for the Contacts paragraph
- Write a unit test for the formatter. For example, it may assert the equivalence of the formatter result and the formatted sample

## Exercise 2: Write UI tests for the android client app using Espresso library
- Read the official Android documentation about Espresso
- Add it to the Android client app
- Write tests for vacancy details screen:
  - Check that all text fields on the screen are visible 
  - Check that all text fields show some information (if you assign to a text field "Me show u som text", it should represent it on the screen)
  - Check that the button (to a company) is visible and clickable

## Exercise 3: Write tests for the server side of the application
- Read the official Ktor documentation about testing the server app.
- Implement the Ktor testing engine to test the server application, write tests for all GET endpoints. They should check the response data.

## Bonus exercise 4: On the server side, write test for resume saving endpoint

## Bonus exercise 5: Write UI tests for other client screens
- Check text fields to be visible and to represent some information
- Check all buttons to be visible and clickable
- Take any of buttons and check that it works fine. For example, if a button should lead from screen A to screen B, you can check if (after a button click) there is some text shown on the screen B. 

💡 [Tap here](https://forms.gle/prUrPv9CuY17P2nR9) **to leave your feedback on the project**. Pedago Team really tries to make your educational experience better.
