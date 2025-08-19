package com.example.springwebrtc.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignalMessage {
    private Type type;
    private String room;
    private String from;
    private String to;
    private String sdp;
    private String sdpType;
    private Ice ice;
}
