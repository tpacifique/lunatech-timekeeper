/*
 * Copyright 2020 Lunatech S.A.S
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.lunatech.timekeeper.services.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.lunatech.timekeeper.models.time.EventTemplate;
import fr.lunatech.timekeeper.models.time.EventType;
import fr.lunatech.timekeeper.models.time.UserEvent;
import fr.lunatech.timekeeper.timeutils.TimeKeeperDateFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventTemplateResponse {

    @NotNull
    private final Long id;

    @NotBlank
    private final String name;

    @NotNull
    private final String description;

    @NotNull
    @JsonFormat(pattern = TimeKeeperDateFormat.DEFAULT_DATE_TIME_PATTERN)
    private final LocalDateTime startDateTime;

    @Null
    @JsonFormat(pattern = TimeKeeperDateFormat.DEFAULT_DATE_TIME_PATTERN)
    private final LocalDateTime endDateTime;

    @Null
    private final List<UserEventResponse> attendees;


    public EventTemplateResponse(
            @NotNull Long id,
            @NotBlank String name,
            @NotNull String description,
            @NotNull LocalDateTime startDateTime,
            @Null LocalDateTime endDateTime,
            @Null List<UserEventResponse> attendees
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.attendees = attendees;
    }


    public static EventTemplateResponse bind(@NotNull EventTemplate eventTemplate) {
        List<UserEventResponse> attendees = eventTemplate.attendees
                .stream()
                .map(UserEventResponse::bind)
                .collect(Collectors.toList());
        return new EventTemplateResponse(
                eventTemplate.id,
                eventTemplate.name,
                eventTemplate.description,
                eventTemplate.startDateTime,
                eventTemplate.endDateTime,
                attendees
        );
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public LocalDateTime getStartDateTime() { return startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }

    public List<UserEventResponse> getAttendees() { return attendees; }

    public static final class UserEventResponse {

        @NotNull
        private final Long id;

        @NotNull
        private final String name;

        @NotNull
        private final String description;

        @NotNull
        @Enumerated(EnumType.STRING)
        private final EventType eventType;

        @NotNull
        private final LocalDateTime startDateTime;

        @NotNull
        private final LocalDateTime endDateTime;

        @NotNull
        private final Long userId;

        public UserEventResponse(
                @NotNull Long id,
                @NotNull String name,
                @NotNull String description,
                @NotNull EventType eventType,
                @NotNull LocalDateTime startDateTime,
                @NotNull LocalDateTime endDateTime,
                @NotNull Long userId) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.eventType = eventType;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.userId = userId;
        }

        public static UserEventResponse bind(@NotNull UserEvent userEvent) {
            return new UserEventResponse(
                    userEvent.id,
                    userEvent.name,
                    userEvent.description,
                    userEvent.eventType,
                    userEvent.startDateTime,
                    userEvent.endDateTime,
                    userEvent.owner.id
            );
        }


        public Long getId() { return id; }

        public String getName() { return name; }

        public String getDescription() { return description; }

        public EventType getEventType() { return eventType; }

        public LocalDateTime getStartDateTime() { return startDateTime; }

        public LocalDateTime getEndDateTime() { return endDateTime; }

        public Long getUserId() { return userId; }

        @Override
        public String toString() {
            return "UserEventResponse{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", eventType=" + eventType +
                    ", userId=" + userId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EventTemplateResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", attendees=" + attendees +
                '}';
    }

}
