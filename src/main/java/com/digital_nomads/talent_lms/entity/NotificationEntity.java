package com.digital_nomads.talent_lms.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class NotificationEntity {

    private String notificationName;
    private String status;
    private String event;
    private String recipient;


    @Override
    public String toString() {
        return "notificationName: " + notificationName + '\n' +
                "status: " + status + '\n' +
                "event: " + event + '\n' +
                "recipient: " + recipient + '\n';
    }
}
