package com.everbestlab.sms_services.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "sms_histories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SmsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "smsHistoriesIdGenerator")
    @SequenceGenerator(name = "smsHistoriesIdGenerator", sequenceName = "sms_histories_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

}
