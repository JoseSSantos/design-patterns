
package com.kreitek.pets.logger;


public class Logger {

    private static volatile Logger instance = null;
    private static String cont;
    private static int contador=0;


    private void Logger(){
        if (instance!=null){
            throw new RuntimeException("Usage getInstance()");
        }
    }

    public static Logger getInstance(){
        if (instance == null){
            synchronized (Logger.class){
                if(instance==null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    public static void debug(String cont){

        System.out.println("[debug]["+contador+"] - "+cont);
        contador++;
    }

}
