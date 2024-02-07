package fr.btsciel;

import fr.btsciel.Mb1414;
import jssc.SerialPortException;



public class Main {
    public static  Mb1414 monMb1414;

    public static void main(String[] args) {
        monMb1414 = new Mb1414();
        try {
            monMb1414.initialisationCapteur("com4");
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

    }
}