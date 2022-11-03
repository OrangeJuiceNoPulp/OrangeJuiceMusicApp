package piano;

import javax.sound.midi.Receiver;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import music.Notes;

public class PianoScene extends Scene {
    


    public PianoScene(double width, double height, Receiver receiver) {
        super(new PianoPane(Notes.A, 3, receiver), width, height);

        PianoPane piano = (PianoPane) this.getRoot();
        this.setOnKeyPressed(e -> { 
            
            for (Node key :  piano.getChildren()) {
              PianoKey key1 = (PianoKey) key;
              if (key1.getKeyboardKey() == e.getCode()){
  
                if (key1.getIsPressed() != true) {
                  key1.setIsPressed(true);
                key1.startNote(receiver);
  
                switch (key1.getKeyType()) {
                  case B_E_KEY:
                  case C_F_KEY:
                  case STANDARD:
                  key1.setFill(Color.WHITE.darker());
                  break;
                  case SHARP_FLAT:
                  key1.setFill(Color.DARKGRAY.darker().darker());
                  break;
                }
              }
              }
              
            }
          
          });
          this.setOnKeyReleased(e -> { 
            
            for (Node key :  piano.getChildren()) {
              PianoKey key1 = (PianoKey) key;
              if (key1.getKeyboardKey() == e.getCode()){
                if (key1.getIsPressed() == true) {
                key1.stopNote(receiver);
  
                switch (key1.getKeyType()) {
                  case B_E_KEY:
                  case C_F_KEY:
                  case STANDARD:
                  key1.setFill(Color.WHITE);
                  break;
                  case SHARP_FLAT:
                  key1.setFill(Color.DARKGRAY.darker().darker().darker().darker());
                  break;
                }
                key1.setIsPressed(false);
              }
            }
            }
          
          });


    }
}
