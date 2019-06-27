package com.viaplay.interview.assignment.domain;

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
public class ReleaseArtCover implements Serializable {
    private List<Cover> images;
    private String release;
}
