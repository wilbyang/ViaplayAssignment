package com.viaplay.interview.assignment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Author: boyang, created on Jun
 */


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicBrainz implements Serializable {

    @JsonProperty("sort-name")
    private String sortName;
    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;

    private List<Relation> relations;

}
