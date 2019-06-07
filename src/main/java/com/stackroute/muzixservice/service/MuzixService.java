package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import java.util.List;

public interface MuzixService {


    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public Track updateTrackComments(String id, String comment)throws TrackNotFoundException;

    public Track deleteTrack(String id) throws TrackNotFoundException;

}
