package com.kreitek.editor.Format;

public class FormatFactory {

    public Format getFormat(String programArg) throws BadFormatException{

        switch (programArg){
            case "text":
                return new textFormat();
            case "json":
                return new jsonFormat();
            default:
                throw new BadFormatException();
        }
    }
}
