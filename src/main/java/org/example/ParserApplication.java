package org.example;


public class ParserApplication {

    public static void main(String[] args) {
        InputMenu inputMenu = new InputMenu();
        inputMenu.runInputMenu();
        OutputMenu outputMenu = new OutputMenu();
        outputMenu.runOutputMenu(inputMenu.runInputMenu());
    }
}
