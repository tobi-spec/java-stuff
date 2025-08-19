package com.example.springwebrtc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ice {

    private String candidate;
    private String sdpMid;
    private int sdpMLineIndex;
}
