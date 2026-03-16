package tn.esprit.tic.se.spr01.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import tn.esprit.tic.se.spr01.TrainingSession.Entities.WeatherData;
@Getter
@Setter
public class WeatherCheckedEvent extends ApplicationEvent {
    private final Long sessionId;
    private final WeatherData weatherData;

    public WeatherCheckedEvent(Object source, Long sessionId, WeatherData weatherData) {
        super(source);
        this.sessionId = sessionId;
        this.weatherData = weatherData;
    }

    public Long getSessionId() { return sessionId; }
    public WeatherData getWeatherData() { return weatherData; }
}
