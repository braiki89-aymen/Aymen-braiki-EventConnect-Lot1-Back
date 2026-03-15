package tn.esprit.tic.se.spr01.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import tn.esprit.tic.se.spr01.TrainingSession.Entities.WeatherData;

public class SessionEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionEventListener.class);

    @EventListener
    public void handleWeatherCheckedEvent(WeatherCheckedEvent event) {
        WeatherData weatherData = event.getWeatherData();
        logger.info("Weather checked for session ID: {}. Conditions: {}°, {}, Recommendation: {}",
                event.getSessionId(), weatherData.getTemperature(), weatherData.getCondition(), weatherData.getRecommendation());
        // In a real application, notify the coach (e.g., via email or notification system)
    }
}
