package com.stackroute.muzixservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    private String trackId;
    private String trackName;
    private String trackComments;

    /*public Track() {
    }

    public Track(String trackId, String trackName, String trackComments) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComments = trackComments;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }*/

    @Override
    public String toString() {
        return "Track{" +
                "trackId='" + trackId + '\'' +
                ", trackName='" + trackName + '\'' +
                ", trackComments='" + trackComments + '\'' +
                '}';
    }
}
