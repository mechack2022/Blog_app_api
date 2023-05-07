package com.fragile.blog_api.repositories;

import com.fragile.blog_api.entities.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {

}
