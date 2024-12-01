
import java.io.IOException; 
import java.util.Scanner; 
import com.fazecast.jSerialComm.SerialPort;



public class Arduino {
    
    public  static  void  main (String[] args)  throws InterruptedException, IOException { 

        // Ouvrez le port série (COM3) pour la communication avec l'Arduino. 
     // Modifiez le port pour qu'il corresponde à celui auquel votre Arduino est connecté. 
     // Vous pouvez trouver le numéro de port dans l'IDE Arduino lorsque la carte est connectée. 
        SerialPort  sp  = SerialPort.getCommPort( "COM4" ); 
        // Définissez les paramètres de communication : débit en bauds, bits de données, bits d'arrêt et parité.
         sp.setComPortParameters( 9600 , 8 , 1 , 0 ); 
        // Définissez le délai d'expiration du port pour le blocage en écriture.
         sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0 , 0 ); 
        
        // Tenter d'ouvrir le port série et vérifier s'il est disponible. 
        if (!sp.openPort()) { 
            System.out.println( "\nLe port COM n'est PAS disponible\n" ); 
            return ; 
        } 

        // Objet Scanner pour lire la saisie utilisateur. 
        Scanner  input  =  new  Scanner (System.in); 
        while ( true ) { 
            System.out.println( "\nEntrez le nombre de clignotements de la LED (de 0 à quitter) : " ); 
            Integer  blinks  = input.nextInt();   // Lire le nombre de clignotements à partir de la saisie utilisateur. 
            
            // Quitter la boucle si l'utilisateur entre 0. 
            if (blinks == 0 ) { 
                break ; 
            } 
            
            // Attendre 1,5 seconde avant d'envoyer des données à l'Arduino.
             Thread.sleep( 1500 ); 
            // Envoyer le nombre de clignotements sous forme d'octet à l'Arduino via le port série.
             sp.getOutputStream().write(blinks.byteValue()); 
        } 
        
        // Fermez le scanner d'entrée pour éviter les fuites de mémoire.
         input.close(); 
    } 
}
