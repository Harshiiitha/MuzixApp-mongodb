package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MuzixRepository extends JpaRepository<Track,String> {

    @Query("select t from Track t where LOWER(t.trackName)=Lower(:name)")
    public Track findTrackByName(String name);


}
