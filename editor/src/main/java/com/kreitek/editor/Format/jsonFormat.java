package com.kreitek.editor.Format;

import java.util.ArrayList;

public class jsonFormat implements Format {


    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    @Override
    public void showDocumentLines(ArrayList<String> textLines) {


        if (textLines.size() > 0){
            setTextColor(TEXT_YELLOW);
            printLnToConsole("{");
            printLnToConsole("\"doc\":[");
            for (int index = 0; index < textLines.size(); index++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\t{\"line\":");
                stringBuilder.append("\""+index+"\",\"text\":");
                stringBuilder.append("\""+textLines.get(index)+"\"},");
                printLnToConsole(stringBuilder.toString());
            }
            printLnToConsole(" ]");
            printLnToConsole("}");
            setTextColor(TEXT_RESET);
        }

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
}
