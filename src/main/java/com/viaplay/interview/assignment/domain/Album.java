package com.viaplay.interview.assignment.domain;

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
public class Album  implements Serializable {

    private String id;
    private String title;
    private String image;
}
