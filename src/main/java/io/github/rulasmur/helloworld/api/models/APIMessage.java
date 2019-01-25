package io.github.rulasmur.helloworld.api.models;

import lombok.Data;

@Data
public class APIMessage {

    private String message;

    public APIMessage(String message) {
        this.message = message;
    }
}
