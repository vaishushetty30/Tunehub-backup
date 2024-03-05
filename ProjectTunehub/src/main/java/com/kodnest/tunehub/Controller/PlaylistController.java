package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.Entity.Playlist;
import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Service.PlaylistService;
import com.kodnest.tunehub.Serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.Serviceimpl.playlistserviceimpl;


@Controller
public class PlaylistController {
	@Autowired
	SongServiceimpl  songServices;
	@Autowired
	playlistserviceimpl playlistservice;
	
	@GetMapping("/CreatePlaylist")
	public String CreatePlaylist(Model model) {
		List<Song> songList=songServices.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylist";
}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		//UPDATING THE PLAYLIST TABLE
		playlistservice.addplaylist(playlist);
		
		//UPDATING THE SONG TABLE
		List<Song> songList = playlist.getSongs();
		for(Song s: songList) {
		s.getPlaylist().add(playlist);
		songServices.updateSong(s);
		}
		return "admin";
	}
//	@GetMapping("/updateplaylist")
//	public String updateplaylist(Model model) {
//		List<Song> songList=songServices.fetchAllSongs();
//		model.addAttribute("songs", songList);
//		return "admin";
//}
	@GetMapping("/viewplaylist")
	public String viewplaylist(Model model) {
		List<Playlist> songList=playlistservice.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displayplaylist";
	}
	
	}
