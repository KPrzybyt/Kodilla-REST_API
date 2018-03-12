package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Badges {

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
