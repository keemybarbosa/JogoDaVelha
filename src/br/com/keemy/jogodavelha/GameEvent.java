package br.com.keemy.jogodavelha;

public class GameEvent {
    private String message;
    private GameEventType eventType;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GameEventType getEventType() {
        return eventType;
    }

    public void setEventType(GameEventType eventType) {
        this.eventType = eventType;
    }

    public enum GameEventType{
        GAME_FINISH,
    }
}
