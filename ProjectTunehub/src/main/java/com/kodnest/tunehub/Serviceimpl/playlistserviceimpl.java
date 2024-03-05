package com.kodnest.tunehub.Serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.Entity.Playlist;
import com.kodnest.tunehub.Service.PlaylistService;
import com.kodnest.tunehub.repository.PlaylistRepository;

@Service
public class playlistserviceimpl  implements PlaylistService{
	@Autowired
	PlaylistRepository playlistreposiotry;
	

	public String addplaylist(Playlist playlist) {
		playlistreposiotry.save(playlist);
		return "adminhome";
	}


	public List<Playlist> fetchAllSongs() {
		List<Playlist> playlist=playlistreposiotry.findAll();
		return playlist ;
	}


}

