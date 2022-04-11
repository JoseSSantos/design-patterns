package com.kreitek.editor;

import com.kreitek.editor.Format.BadFormatException;
import com.kreitek.editor.Format.Format;
import com.kreitek.editor.Format.FormatFactory;
import com.kreitek.editor.Memento.CommandCaretaker;
import com.kreitek.editor.Memento.Memento;
import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.commands.UndoCommand;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleEditor implements Editor {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    private final CommandFactory commandFactory = new CommandFactory();
    private ArrayList<String> documentLines = new ArrayList<String>();
    CommandCaretaker commandCaretaker = new CommandCaretaker();



    @Override
    public void run(String[] args) {
        boolean exit = false;
        while (!exit) {
            String commandLine = waitForNewCommand();
            try {
                Command command = commandFactory.getCommand(commandLine);
                if (command.getClass().getSimpleName().equals("UndoCommand")) {

                    command.execute(documentLines);

                } else {

                    commandCaretaker.push(getMementoOf(documentLines));
                    command.execute(documentLines);
                }


            } catch (BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (ExitException e) {
                exit = true;
            }
            printAll(args);
            showHelp();
        }
    }

    private void printAll(String[] args){
        try {
            FormatFactory formatFactory = new FormatFactory();
            Format format = formatFactory.getFormat(args[0]);
            format.showDocumentLines(documentLines);

        } catch (BadFormatException e) {
            System.err.println("El formato que has elegido no existe, elige entre \"text\" o \"json\"");
        }catch (ArrayIndexOutOfBoundsException e){

            FormatFactory formatFactory = new FormatFactory();
            try {
                Format format = formatFactory.getFormat("text");
                format.showDocumentLines(documentLines);

            } catch (BadFormatException ex) {
                ex.printStackTrace();
            }

        }
    }
    private String waitForNewCommand() {
        printToConsole("Enter a command : ");
        Scanner scanner = new Scanner(System. in);
        return scanner.nextLine();
    }

    private void showHelp() {
        printLnToConsole("To add new line -> a \"your text\"");
        printLnToConsole("To update line  -> u [line number] \"your text\"");
        printLnToConsole("To delete line  -> d [line number]");
    }

    private void printErrorToConsole(String message) {
        setTextColor(TEXT_RED);
        printToConsole(message);
        setTextColor(TEXT_RESET);
    }

    private void setTextColor(String color) {
        System.out.println(color);
    }

    private void printLnToConsole(String message) {
        System.out.println(message);
    }

    private void printToConsole(String message) {
        System.out.print(message);
    }

    public Memento getMementoOf(ArrayList<String> documentLines){
    ArrayList<ArrayList<String>> state = new ArrayList<>();
    state.add((ArrayList<String>) documentLines.clone());
        return new Memento(state);
    }

}
