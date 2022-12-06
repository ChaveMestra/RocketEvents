package com.rocketmc.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.rocketmc.events.util.MessageUtil.f;

@AllArgsConstructor
@Getter
public enum EventStatus {
    WAITING(f("&bINICIANDO")), ACTIVE(f("&2ATIVO")), ENDED(f("&cFINALIZADO"));

    public String string;

    @Override
    public String toString() {
        return string;
    }

    public static EventStatus getNext(EventStatus eventStatus) {
        if (eventStatus.equals(WAITING)) {
            return ACTIVE;
        }
        return ENDED;
    }
}
