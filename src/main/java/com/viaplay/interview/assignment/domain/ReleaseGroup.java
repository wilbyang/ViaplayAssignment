package com.viaplay.interview.assignment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author: boyang, created on Jun
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseGroup implements Serializable {
    private String title;
    @JsonProperty("primary-type")
    private String primaryType;
    private String id;
}
