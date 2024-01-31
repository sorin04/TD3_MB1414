package fr.btsciel;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Application extends LiaisonSerie {

    public Application() {
    }

    public ArrayList<String> listeDesCom() {
        return super.listerLesPorts();
    }

    public void serialEvent(SerialPortEvent spe) {
        super.serialEvent(spe);
        float celsius;
        float kelvin;
        byte[] laTrame;
        int longueur = spe.getEventValue();
        laTrame = lireTrame(longueur);

        celsius = decodageTrameCapteur(laTrame);
        kelvin = decodageTrameCapteur(laTrame) ;

        System.out.println(String.format("""
            Réception
            Format C°:
            Format K:
            Format Chaîne de caractères : %s
            """));
    }

    private float decodageTrameCapteur(byte[] laTrame) {
        return 0;
    }


    public void initialisation(String portDeTravail) throws SerialPortException, InterruptedException {
        super.initCom(portDeTravail);
        super.configurerParametres(5800, 8, 0, 1);
        super.ecrire("test pirgari ".getBytes(StandardCharsets.US_ASCII));
        Thread.sleep(2000);
        if (super.detecteSiReception() > 0) {
            System.out.println(new String(super.lireTrame(super.detecteSiReception())));
        }
    }

    public void deconnexionCapteur(){
        super.fermerPort();
        System.out.println("Deconexion reussie: ");

    }
    public void ecrire(byte[] b)  {
        super.ecrire(b);
    }


}