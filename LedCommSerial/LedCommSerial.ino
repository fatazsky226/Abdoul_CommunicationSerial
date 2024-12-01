// Broche où la LED est connectée 
int ledPin = 13 ; 

void  setup ()  { 
  Serial. begin ( 9600 ); // Initialise la communication série à un débit de 9600 bauds 
  pinMode (ledPin, OUTPUT); // Définit la broche LED comme sortie
 } 

void  loop ()  { 
  byte blinkCount; // Variable pour stocker le nombre de clignotements reçus du port série 
  if (Serial. available ()) { // Vérifie si les données sont disponibles pour la lecture depuis le port série
     blinkCount = Serial. read (); // Lit l'octet entrant depuis le port série 
    for (byte i = 1 ; i <= blinkCount * 2 ; i++) { // Boucle pour faire clignoter la LED 
      digitalWrite (ledPin, ! digitalRead (ledPin)); // Bascule l'état de la LED 
      delay ( 200 ); // Attends 200 millisecondes
     } 
  } 
}
