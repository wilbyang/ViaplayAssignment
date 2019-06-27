package com.viaplay.interview.assignment.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Artist  implements Serializable {
    private String mbid;
    private String description;
    private List<Album> albums;
}
