package com.viaplay.interview.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: boyang, created on Jun
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscogProfile  implements Serializable {
    private String profile;

}
