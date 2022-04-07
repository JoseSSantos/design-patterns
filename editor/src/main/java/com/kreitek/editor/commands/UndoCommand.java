package com.kreitek.editor.commands;

import com.kreitek.editor.Command;
import com.kreitek.editor.Memento.CommandCaretaker;
import com.kreitek.editor.Memento.Memento;

import java.util.ArrayList;
import java.util.Collection;

public class UndoCommand implements Command {

    CommandCaretaker commandCaretaker = new CommandCaretaker();

    @Override
    public void execute(ArrayList<String> documentLines) {

        try {
            documentLines.clear();
            Memento undo = commandCaretaker.pop();
            documentLines.addAll(undo.getState().get(0));
        }catch (Exception e){
            System.out.println("No se puede volver a atras");
        }
    }
}
