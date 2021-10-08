package io.tpd.kafkaexample;

import com.fasterxml.jackson.annotation.JsonProperty;

record PracticalAdvice(@JsonProperty("message") String message,
                       @JsonProperty("identifier") int identifier) {
}
