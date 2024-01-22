# Hello!  

Today we are going to develop a project using the object-oriented paradigm features of the language.  
Kotlin implements the classic single-parent inheritance model and the ability to implement multiple behavior interfaces, but with an interesting caveat: by default, all classes are closed to inheritance.    
A very important, one of the "killer" features of Kotlin, is Null-safety feature. This system divides all types into two parts - nullable and not null, and adds some operators to interact with nullable or not null variables. This feature allows to write null-safe code and almost to avoid NullPointer exceptions.  
Also, language developers offer several handy tools from this area, for example:
- Data/sealed classes
- The concept of a property (field + default getter/setter)
- Objects
- Extensions

Extensions deserve special attention, allowing you to extend a class or interface with new functionality without the need to inherit or use patterns such as the Decorator.

# Themes:
- OOP, classes, interfaces, files, getter/setter
- Nullable variables
- Extensions
- Exceptions

**Advice!** Continue reading the official documentation and articles about these Kotlin features. If you need, find more information about OOP principles, OOP in Kotlin.

**Advice!** There is an example of using the Kotlin "Object" feature in the `codesamples` folder. Compare realisations of the Singleton pattern in Kotlin and in Java. As you can see, the Kotlin Object is a kind of Singleton itself.

### Project: Emergency service
The city is divided into several zones, each one has its own rescue service unit. Today we are developing features for a mobile application for emergency assistance. It will handle 2 main requests: receive full information about a specific zone and understand whether there are any incidents

**Advice!** If you've read anything about OOP in Kotlin, you're already acquainted with `data class` feature and its advantages. It's a very popular construction to describe pieces of data model, you should use it for "model" classes (with data) in our projects.

# Exercises

**Requirement!** Please, make each exercise in a separate project. For example, `Day01/src/exercise1`, `Day01/src/exercise2`, `Day01/src/bonusexercise3`, etc. If the previous exercise is needed for the next one, just copy a project from the previous to the next folder and continue development within the next one.

**Requirement!** Please, don't commit generated files and folders, such as `.idea`, `.out`, `.build`, `.gradle`, etc. In IntelliJ IDEA and Android Studio they are usually marked with orange/brown color. Commit and push to the origin only source code of your app. Delete generated files before commit or better add them to the `.gitignore` file in your project.

**Requirement!** There is no need to download gradle. Please, use the IDE embedded one.

## Exercise 1: Emergency department
You must determine if an incident happened in a specific zone.  

Here, we'll do things in OOP style (classes, fields and methods, etc.). 

**Advice!** This exercise looks huge, but don't panic, it's only at first glance. In general, you just need to describe some entities (zone, incident, incident types, etc.), organize input and use some mathematics to solve the "Is incident inside" problem.

**Advice!** In the `src/exercise1` folder, there is some code you can start with. In the `Incident.kt` file, there are two classes, united by meaning. Note, that in Kotlin you can place different entities (classes, functions, etc.) in one file.

**Advice!** The incident and zone entities are described below. You should use classes for them. As we said earlier, there is a `data class` feature, which you can use to describe incidents. But data classes were designed as lightweight classes, they have problems with inheritance. You should read where is better to use `data classe` or a classic class. Think about the zone and its types in the way of `inheritance`.

**Advice!** Use `enum` classes to make enumerations, for example - types of incidents.

### Incident description:
- An incident is determined by two integer coordinates on the coordinate plane (in `Incident.kt` they are described as `x` and `y`)
- An incident also has:
  - Description
  - Applicant's phone number - can be null (read documentation about the `nullable types`)
  - A field corresponding to one of the 3 types of incidents (e.g. fire, gas leak, cat on the tree) - can be null

### Zone description:
- There is a base Zone class
- There are three particular zones: with a tetragon, triangular or round shape (representing figures on the coordinate plane). As said, it should be classes
- Use inheritance to connect shaped zones with a base zone class
- Each zone has a phone number. The base zone has a base phone number (with operator code 800)
- Zones have a method to determine if an incident is within it. As zones have different shapes, think how to use inheritance to implement for each zone different calculations

**Input:**
- All coordinates are entered in the form of two integer numbers separated by a semicolon. Example: 5;4
- Enter zone params: the program automatically determines the shape of the zone according to the entered data:
  - For a circle, the two things are entered divided by space: a center coordinate and radius. Example: 5;4 6
  - For a triangle three coordinates are entered divided by spaces. Example: 5;4 3;6 2;5
  - For a tetragon four coordinates are entered divided by spaces. Example: 5;4 3;6 2;5 5;9
- Enter the coordinates of the point of the incident. When the program creates an Incident object, it must randomly take a description and a phone number from corresponding arrays in `utils` folder 
- Wrong input causes an error

**Output:**
- The program prints the full information about the zone and the incident. If the incident's phone is null, it shouldn't be printed (you can read about `?.let` construction to work with nullable types and use it)
- The program prints if the incident is within the zone. If not, it advises to switch the applicant to the common number

_Example:_
```
Enter zone parameters:
3;4 2

The zone info:
  The shape of zone: circle 
  Phone number: 89347362826

Enter an incident coordinates:
9;9

The incident info:
  Description: My cat can't get off the tree
  Phone number: 74831743929
  Type: cat on the tree

An incident is not in the zone
Switch the applicant to the common number: 88008473824
```


## Exercise 2: The Phone mask
A phone mask is a useful UX feature that improves readability of phone numbers.  
Read about extensions in Kotlin. Write an extension function to the String class that applies two different masks to a phone number:
- It works with 11-digits numbers starting with 7 or 8, or with 12-digits numbers starting with +7:
  - If the operator code is 800, the number is converted to the form "8 (800) xxx xx xx"
  - For another operator, the mask is "+7 xxx xxx-xx-xx"
  - Note, that besides brackets, spaces and hyphens are added, the first (country) digit also changes according to the mask. Example: 84352835724 converts to +7 435 283-57-24 
- Other numbers are bypassed  

**Check the result:** apply this extension in the previous exercise to display information about zone's and applicant's phones, then repeat the exercise check.

_Example:_
```
...

The zone info:
... 
  Phone number: +7 934 736-28-26
...
The incident info:
...
  Phone number: +7 483 264-85-73

An incident is not in the zone
Switch the applicant to the common number: 8 (800) 847 38 24
```

## Exercise 3: Processing server responses
Assume, that we have a server for our app, and it returns a response to our requests. The response contains a response code and a message. There are a lot of different response codes - check the HTTP codes description.  
In our app, according to the code, the answer can be classified into two types: Success and Error. It's a common case, where a sealed class can be used. Implement this logic with help of sealed class:
- Success, if codes are 200 or 201. It has a message - string "The request processed successfully"
- Error, codes from 400 and more, the message is JSON with the title and description of the error

Also, errors from the server are different. Our app has classes for several errors. It's also a good case to be implemented with sealed class. Implement errors hierarchy:
- There are 4 types of known internal errors (1000-1003). They have a code, a header and a description (for desc - think of it):
  - 1000, "The user is not identified", desc
  - 1001, "The session is expired", desc 
  - 1002, "No connection", desc
  - 1003, "The device has failed the verification", desc
- The rest of the response codes (other than 200, 201, 1000-1003) are handled with a common "Unknown" error, which has the title "Error code: $code" and the description "Unknown error. Please, try again later"

Finally, let's combine our responses and errors. The application needs a handler to recognize responses and types of errors. As you guessed, you should use Error sealed class as our Error response type.  
The program must fail on non-integer input.

**Input:** the server response code is entered into the console  
**Output:** the program prints a type of the response (if an error, a concrete type of this error) and its information: code and message for Success or code, title and description for Error

_Example:_
```
Type a response code:
1003

NoConnectionError: 
  Code: 1003
  Title: No connection
  Description: There is no internet connection. Try later.
```

💡 [Tap here](https://forms.gle/EmMtUu47HGP7kf7q6) **to leave your feedback on the project**. Product Team really tries to make your educational experience better.
