package com.kreitek.editor.Memento;

import java.util.ArrayList;
import java.util.List;

public class CommandCaretaker {
    static List<Memento> mementos = new ArrayList<>();

    public void push(Memento memento){
        mementos.add(memento);
    }

    public Memento pop(){
        if(mementos.size() > 0){
            try {
                Memento memento = mementos.get(mementos.size() - 1);
                mementos.remove(mementos.size() - 1);
                return memento;
            }
                catch(Exception e){
                System.out.println("No se puede volver atras");
                return null;
                }
        }
        return null;
    }
}
