# Introducton

This is JavaFX starting project with support compiling JavaFX code into multiple IDEs: VS Code, Eclipse, and IntelliJ IDEA. 
This Example project was created by Mgr. Pavle Dakić for the needs of subject OOP.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Java version

- Java version "17.0.2" 2022-01-18 LTS
- JavaFX 17.0.2-ea

## Visual Studio Code

Press `F5` to start the application in VS Code.

If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

If you need to change the path to the JavaFX library, do the following steps:

- In `.vscode/launch.json` change line #5 `"C:\\javafx-sdk-17.0.2/lib/**/*.jar"` to you location
- In `.vscode/settings.json` change `--module-path` to you location

Example:

```
"vmArgs": "--module-path C:\\javafx-sdk-17.0.2\\lib --add-modules javafx.controls,javafx.fxml",

```

## Eclipse

If you need to change the path to the JavaFX library, do the following steps:

- In `.classpath` change lines #9-16 `path="C:/javafx-sdk-17.0.2/lib/` to you location

## IntelliJ IDEA

If you need to change the path to the JavaFX library, do the following steps:

- In `.idea/workspace.xml` change lines #51 `<option name="VM_PARAMETERS" value="--module-path &quot;C:\javafx-sdk-17.0.2\lib&quot; --add-modules javafx.controls,javafx.fxml" />` to you location