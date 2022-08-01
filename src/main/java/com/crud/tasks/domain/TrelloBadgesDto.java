package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBadgesDto {

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentByTypeDto attachmentsByType;
}
