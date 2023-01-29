
package Modelo;
//Importo los paquetes que necesito
import java.awt.event.KeyEvent;
import javax.swing.JTextField;


public class Eventos {
    
    //Solo permite caracteres
    public void textKeyPress(KeyEvent evt) {
        // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }
    //Solo permite numeros
    public void numberKeyPress(KeyEvent evt) {
        // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }
    //Solo permite numeros decimales
    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
        // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    
}
