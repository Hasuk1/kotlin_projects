# **KOTLIN**

![Kotlin_logo](misc/Kotlin_logo.svg.png)

## **Contents**

1. [Projects](#projects)

   1.1 [Pet projects](#pet-projects)
   
   1.2 [S21 Bootcamp](#s21-bootcamp)
   
   1.3 [Google Bootcamp](#google-bootcamp)
   
   1.4 [Tinkoff Bootcamp](#tinkoff-bootcamp)

3. [Useful combinations in IntelliJ IDEA](#useful-combinations-in-intellij-idea)

    2.1 [The universal combination for all problems](#the-universal-combination-for-all-problems)

    2.2 [Code navigation](#code-navigation)

    2.3 [Refactoring/editing/code generation](#refactoringeditingcode-generation)

    2.4 [Other](#other)

4. [How to run kotlin code](#how-to-run-kotlin-code)

5. [Install gradle to other dir](#change-gradle-home-dir)

## **Projects**

### **Pet projects**

|**Project**| **Stack** | **Description** |
| ------ | ------ | ------ |
| `01` [Popular films](https://github.com/Hasuk1/Karpushov)| Kotlin/Gradle/Retrofit2/Android studio/Jetpack compose | An app with api kinopoisk, for watching popular movies |
| `02` [Simon Says Game](https://github.com/Hasuk1/Repeat-The-Sequence)| Kotlin/Gradle/Android studio/Jetpack compose | It's a game similar to Simon Says. |
| `03` [...]()| ... | ... |
| `04` [...]()| ... | ... |

### **S21 Bootcamp**

|**Project**| **Status**| **Stack** | **Description** |
| ------ | ------ | ------ | ------ |
| `Day00` [Bootcamp_00](https://github.com/Hasuk1/kotlin_projects/tree/main/s21_bootcamp/01_day_00)|6/6| Kotlin/Gradle/IntelliJ IDEA | Functional programming |
| `Day01` [Bootcamp_01](https://github.com/Hasuk1/kotlin_projects/tree/main/s21_bootcamp/02_day_01)|3/3| Kotlin/Gradle/IntelliJ IDEA | Object-oriented programming |
| `Day02` [Bootcamp_02](https://github.com/Hasuk1/kotlin_projects/tree/main/s21_bootcamp/03_day_02)|3/3| Kotlin/Gradle/IntelliJ IDEA | Collections/JSON |
| `Day03` [Bootcamp_03](https://github.com/Hasuk1/kotlin_projects/tree/main/s21_bootcamp/04_day_03)|4/5|  Kotlin/Gradle/IntelliJ IDEA | Generics/Delegates/Functional types |
| `Day04` [Bootcamp_04](https://github.com/Hasuk1/Repeat-The-Sequence)|6/6| Kotlin/Gradle/Android studio/Jetpack compose | Simons Says Game (MVVM/Navigate/...)|
| `Team00` [Bootcamp_05](https://github.com/Hasuk1/kotlin_projects/tree/main/s21_bootcamp/06_team_00)|In progress| Kotlin/Gradle/Android studio/Jetpack compose | Modules/Clean architecture |
| `Day05` [Bootcamp_06]()|Waiting to start| - | - |
| `Day06` [Bootcamp_07]()|Waiting to start| - | - |
| `Day07` [Bootcamp_08]()|Waiting to start| - | - |
| `Day08` [Bootcamp_09]()|Waiting to start| - | - |
| `Day09` [Bootcamp_10]()|Waiting to start| - | - |
| `Team01` [Bootcamp_11]()|Waiting to start| - | - |

### **Google Bootcamp**
|**Project**| **Status**| **Stack** | **Description** |
| ------ | ------ | ------ | ------ |
| `01` [Layouts, theming, and animation](https://github.com/Hasuk1/kotlin_projects/tree/main/google_course/layouts_theming_and_animation)|In progress| ... | ... |
| `02` [...]()|...| ... | ... |
| `03` [...]()|...| ... | ... |
| `04` [...]()|...| ... | ... |
| `05` [...]()|...| ... | ... |

### **Tinkoff Bootcamp**
|**Project**| **Status**| **Stack** | **Description** |
| ------ | ------ | ------ | ------ |
| `01` [Algorithm and data structure](https://github.com/Hasuk1/kotlin_projects/tree/main/algos_and_structs)|...| ... | ... |
| `02` [...]()|...| ... | ... |
| `03` [...]()|...| ... | ... |
| `04` [...]()|...| ... | ... |
| `05` [...]()|...| ... | ... |

## **Useful combinations in IntelliJ IDEA**
Keymap:

`⌘ - Command`

`⌃ - Control`

`⌥ - Option (Alt)`

`⇧ - Shift`

### **The universal combination for all problems**
|**Mac OS**|**Windows**|**Description**|**Описание**|
|---|---|---|---|
|⌥ + Enterl|Alt + Enter|show quick actions, fix the code|показать быстрые действия, исправить код|

### **Code navigation**
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

### **Refactoring/editing/code generation**
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

### **Other**
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

###### /opt/goinfre/perlabru/.gradle
