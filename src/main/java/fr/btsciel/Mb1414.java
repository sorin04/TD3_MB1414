package fr.btsciel;


import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mb1414 extends LiaisonSerie{
    private double Distance;

    public void calcul(SerialPortEvent spe){

    }

    public void deconnexionCapteur(){
        fermerPort();
    }


    public void initialisationPort(String Port) throws SerialPortException {
        initCom(Port);
        super.configurerParametres(5800,8,0,1);

    }

    public double getDistance(){
        return Distance;
    }

    public ArrayList<String> listerlesport(){
        return super.listerLesPorts();
    }

    public void SerialEvent(SerialPortEvent spe){

    }
}