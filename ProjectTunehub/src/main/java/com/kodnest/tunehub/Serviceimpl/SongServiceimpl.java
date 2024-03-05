package com.kodnest.tunehub.Serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Service.SongService;
import com.kodnest.tunehub.repository.SongRepository;

@Service
public class SongServiceimpl implements SongService {
	@Autowired
	SongRepository songrepository;
	
	

	public String addSong(Song song) {
		songrepository.save(song);
		return "song added successfully";
		
	}



	public boolean songExists(String name) {
		Song song=songrepository.findByName(name);
		if(song==null) {
			return false;
			
		}
		else {
			return true;
			
		}
	}



	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs=songrepository.findAll();
		return songs;
	}


@Override
	public void updateSong(Song s) {
	songrepository.save(s);
		
	}



	



	

}
