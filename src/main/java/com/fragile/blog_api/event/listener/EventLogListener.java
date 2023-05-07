package com.fragile.blog_api.event.listener;

import com.fragile.blog_api.entities.EventLog;
import com.fragile.blog_api.event.PostCreationEvent;
import com.fragile.blog_api.event.UserCreationEvent;
import com.fragile.blog_api.repositories.EventLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventLogListener {

    private final EventLogRepository eventLogRepository;

    @EventListener
    public void handleUserCreationEvent(UserCreationEvent event) {
        saveEventLog(event.getClass().getSimpleName(), event.getMessage(), null);
    }

    @EventListener
    public void handlePostCreationEvent(PostCreationEvent event) {
        saveEventLog(event.getClass().getSimpleName(),
                String.format("created a new post with title %s", event.getTitle()),
                event.getUserId());
    }

    private void saveEventLog(String eventName, String eventData, Integer userId) {
        EventLog eventLog = new EventLog();
        eventLog.setEventName(eventName);
        eventLog.setEventData(eventData);
        eventLog.setEventTime(LocalDateTime.now());
        eventLog.setUserId(userId);
        eventLogRepository.save(eventLog);
        log.info("{} event just happened", eventName);
    }
}
