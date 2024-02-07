package fr.btsciel;



import jssc.SerialPortEvent;
import jssc.SerialPortException;


import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

public class Mb1414 extends LiaisonSerie {
    double distance=0.0;

    double derniereDistance;
    double deltaVitesse;

    double acceleration;
    double deltaDistance,deltaTemps;
    DecimalFormat dfDistance = new DecimalFormat("0.## cm");
    DecimalFormat dfDeltaT=new DecimalFormat("0.## s");
    DecimalFormat dfVitesse =new DecimalFormat("0.## s");

    Instant tInit, tFinal;
    public Mb1414() {
        tInit=Instant.now();
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        tFinal=Instant.now();
        calcul(event);
        tInit=tFinal;
        derniereDistance = distance;
        if (deltaDistance != 0) {
            System.out.printf("""
                %s
                -----------------------
                Delta d =%s
                Delta T =%s
                Vitesse =%s
                Acceleration =%s
                %n""", dfDistance.format(distance), dfDistance.format(deltaDistance), dfDeltaT.format(deltaTemps), dfVitesse.format(deltaVitesse), acceleration);
        }

        tInit = tFinal;
        derniereDistance = distance;
    }

    private void calcul(SerialPortEvent event) {
        byte[] laTrame = lireTrame(event.getEventValue());
        if(laTrame.length==8){
            Instant.now();
            distance=((laTrame[1]-0x30)*100 + (laTrame[2]-0x30)*10 + (laTrame[3]-0x30))*2.54;
            deltaTemps = (Duration.between(tInit, tFinal).toMillis()) * 0.001;
            deltaDistance = distance -derniereDistance;
            deltaVitesse = deltaDistance / deltaTemps;
            acceleration = deltaVitesse / deltaTemps;
        }

    }

    public void initialisationCapteur(String lePortCom) throws SerialPortException {
        super.initCom(lePortCom);
        super.configurerParametres(57600, 8, 0, 1);

    }
}