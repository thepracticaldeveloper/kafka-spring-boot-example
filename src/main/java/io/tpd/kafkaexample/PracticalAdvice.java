package io.tpd.kafkaexample;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PracticalAdvice {
    private final String message;
    private final int importance;

    public PracticalAdvice(@JsonProperty("message") final String message, @JsonProperty("importance") final int importance) {
        this.message = message;
        this.importance = importance;
    }

    public String getMessage() {
        return message;
    }

    public int getImportance() {
        return importance;
    }
}
