package com.kreitek.editor.Memento;

import java.util.ArrayList;

public class Memento {
    private ArrayList<ArrayList<String>> state;


    public Memento(ArrayList<ArrayList<String>> state){
        this.state=state;
    }

    public ArrayList<ArrayList<String>> getState() {
        return state;
    }
}
