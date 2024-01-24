# **KOTLIN**

![Kotlin_logo](misc/Kotlin_logo.svg.png)

## **Contents**

1. [Projects](#project)

2. [Useful combinations in IntelliJ IDEA](#useful-combinations-in-intellij-idea)

    2.0 [0. The universal combination for all problems](#0-the-universal-combination-for-all-problems)

    2.1 [1. Code navigation](#1-code-navigation)

    2.2 [2. Refactoring/editing/code generation](#2-refactoringeditingcode-generation)

    2.3 [3. Other](#3-other)

3. [How to run kotlin code](#how-to-run-kotlin-code)

4. [Install gradle to other dir](#change-gradle-home-dir)

## **Projects**

|**Project**| **Status**| **Stack** | **Description** |
| ------ | ------ | ------ | ------ |
| `Day00` [Bootcamp_00](https://github.com/Hasuk1/Kotlin_bootcamp/tree/main/Bootcamp_00)|6/6| Kotlin/Gradle/IntelliJ IDEA | Functional programming |
| `Day01` [Bootcamp_01](https://github.com/Hasuk1/Kotlin_bootcamp/tree/main/Bootcamp_01)|3/3| Kotlin/Gradle/IntelliJ IDEA | Object-oriented programming |
| `Day02` [Bootcamp_02](https://github.com/Hasuk1/Kotlin_bootcamp/tree/main/Bootcamp_02)|In progress| Kotlin/Gradle/IntelliJ IDEA | Collections/JSON |
| `Day03` [Bootcamp_03]()|Waiting to start| - | - |
| `Day04` [Bootcamp_04]()|Waiting to start| - | - |
| `Day05` [Bootcamp_05]()|Waiting to start| - | - |
| `Day06` [Bootcamp_06]()|Waiting to start| - | - |
| `Day07` [Bootcamp_07]()|Waiting to start| - | - |
| `Day08` [Bootcamp_08]()|Waiting to start| - | - |
| `Day09` [Bootcamp_09]()|Waiting to start| - | - |
| `Day10` [Bootcamp_10]()|Waiting to start| - | - |
| `Day11` [Bootcamp_11]()|Waiting to start| - | - |
| `Day12` [Bootcamp_12]()|Waiting to start| - | - |
| `Day13` [Bootcamp_13]()|Waiting to start| - | - |
| `Day14` [Bootcamp_14]()|Waiting to start| - | - |
| `Day15` [Bootcamp_15]()|Waiting to start| - | - |
| `Kotlin01` [Project_01]()|Waiting to start| - | - |
| `Kotlin02` [Project_02]()|Waiting to start| - | - |
| `Kotlin03` [Project_03]()|Waiting to start| - | - |
| `Kotlin04` [Project_04]()|Waiting to start| - | - |
| `Kotlin05` [Project_05]()|Waiting to start| - | - |

## **Useful combinations in IntelliJ IDEA**
Keymap:

`⌘ - Command`

`⌃ - Control`

`⌥ - Option (Alt)`

`⇧ - Shift`

### **0. The universal combination for all problems**
|**Mac OS**|**Windows**|**Description**|**Описание**|
|---|---|---|---|
|⌥ + Enterl|Alt + Enter|show quick actions, fix the code|показать быстрые действия, исправить код|

### **1. Code navigation**
|**Mac OS**|**Windows**|**Description**|**Описание**|
|---|---|---|---|
|⌘ + O|Ctrl + N|open class|открыть класс|
|⌘ + ⇧ + O|Ctrl + Shift + N|open file|открыть файл|
|⌘ + ⌥ + Left/Right|Ctrl + Alt + Left/Right|backward/forward through the navigation history|назад/вперед по истории навигации|
|⌘ + B|Ctrl + B|go to the declaration (variable/method), show the use of the variable/method|перейти к декларации (переменной/метода), показать использование переменной/метода|
|⌘ + ⌥ + B|Ctrl + Alt + B|move on to method implementation|перейти к имплементации метода|
|⌘ + E|Ctrl + E|recent open files|последние открытые файлы|
|⌘ + ⇧ + E|Ctrl + Shift + E|recent edits|последние редактированные файлы|
|⌘ + F12|Ctrl + F12|list of methods of the current class|start typing the name of the method to filter|список методов текущего класса|начать набирать имя метода для фильтрации|

### **2. Refactoring/editing/code generation**
|**Mac OS**|**Windows**|**Description**|**Описание**|
|---|---|---|---|
|⌃ + ⌥ + O|Ctrl + Alt + O|optimize the import section|оптимизировать раздел import|
|⌘ + ⌥ + L|Ctrl + Alt + L|auto-format the code|автоматически отформатировать код|
|⌘ + N|Alt + Insert|generate code (constructor, getter, setter, equals, hashCode)|сгенерировать код (конструктор, геттер, сеттер, equals, hashCode)|
|⇧ + F6|Shift + F6|Rename (class, method, field, variable), including all uses and references|переименовать (класс, метод, поле, переменную), включая все использования и референсы|
|⌘ + ⌥ + V|Ctrl + Alt + V|variableize the expression|вынести выражение в переменную|
|⌃ + O|Ctrl + O|override method|переопределить метод|
|⌃ + I|Ctrl + I|implement the method|имплементировать метод|
|⌃ + G|Alt + J|highlight the next occurrence of text (next occurence)|выделить следующее появление текста (next occurence)|
|⌘ +|Ctrl + /|comment with a one-line comment|закомментировать однострочным комментарием|
|⌘ + ⇧ + /|Ctrl + Shift + /|comment with a multi-line comment|закомментировать многострочным комментарием|
|⌘ + ⇧ + 8|Shift + Alt + Insert|switch editing mode (normal/column)|переключить режим редактирования (обычный/колонка)|
|⌥ + ⌥(зажать) + Up/Down|Ctrl + Ctrl(зажать) + Up/Down|highlight the next/previous row in column mode|выделить следующую/предыдущую строку в режиме колонки|

### **3. Other**
|**Mac OS**|**Windows**|**Description**|**Описание**|
|---|---|---|---|
|⌃ + R/D|Shift + F10/F9|run/debug the currently selected configuration|запуск/дебаг текущей выбранной конфигурации|
|⌃ + ⇧ + R/D|Ctrl + Shift + F10/F9|run/debug configuration from context|запуск/дебаг конфигурации из контекста|
|⌃ + ⌥ + R/D|Alt + Shift + F10/F9|configuration selection and startup/debug|выбор конфигурации и запуск/дебаг|
|⌘ + ⇧ + F8|Ctrl + Shift + F8|breakpoint window|открыть окно брейкпоинтов|
|⇧ + ⇧|Shift + Shift|search everywhere|поиск везде|
|⌘ + ⇧ + A|Alt + `|action search|поиск действий|
|⌃ + V|Content Cell|quick menu of the version control system|быстрое меню системы контроля версий|
|⌘ + K|Ctrl + K|commit|сделать коммит|

## **How to run kotlin code**

First you need to compile the code using the kotlin compiler:
```shell
kotlinc Main.kt -include-runtime -d Main.jar
```

You can then run the compiled byte code on the JVM:
```shell
java -jar Main.jar
```

```shell
kotlinc Main.kt -include-runtime -d Main.jar && java -jar Main.jar && rm  Main.jar
```
## **Change gradle home dir**

```zsh
export GRADLE_USER_HOME=/other/dir
source ~/.zshrc
```
Preferences..->Appearance & Behavior->Path Variables [+]GRADLE_USER_HONE - Value(/other/dir)

#### /Users/perlabru/goinfre/.gradle
