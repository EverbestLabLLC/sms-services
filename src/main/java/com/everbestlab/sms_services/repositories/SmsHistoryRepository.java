package com.everbestlab.sms_services.repositories;

import com.everbestlab.sms_services.domain.entities.SmsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHistoryRepository extends JpaRepository<SmsHistory, Long> {
}
