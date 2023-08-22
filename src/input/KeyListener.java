package input;

import java.util.HashSet;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyListener implements EventHandler<KeyEvent> {
    public Set<KeyCode> keyList = new HashSet<>();

    public KeyListener(Scene scene) {
        scene.setOnKeyPressed(this);
        scene.setOnKeyReleased(this);
    }

    @Override
    public void handle(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            keyList.add(event.getCode());
        } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            keyList.remove(event.getCode());
        }
    }

    public boolean isPressed(KeyCode keyCode) {
        return keyList.contains(keyCode);
    }

    public boolean isReleased() {
       return keyList.isEmpty();
    }

    public void printPressedKeys() {
        System.out.println(keyList);
    }
}
