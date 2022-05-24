package com.shreks.onboarding.util;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

import static org.joda.time.DateTimeZone.UTC;

@Component
public class DateTimeUtil {

    /**
     * This method returns current utc date time.
     *
     * @return UTC Date
     */
    public Timestamp getCurrentUTCTimestamp() {
        return new Timestamp(LocalDateTime.now(UTC).toDateTime(UTC).getMillis());
    }

    /**
     * This method returns current UTC DateTime based on provided offset time
     *
     * @param time OffsetDateTime
     * @return UTC Date Timestamp of offset Time
     */
    public Timestamp getCurrentUTCTimestampFromOffset(OffsetDateTime time) {
        return new Timestamp(time.toInstant().toEpochMilli());
    }
}
