package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Serviceimpl.SongServiceimpl;

@Controller
public class SongController {
	@Autowired
	SongServiceimpl songService;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {
		
		boolean songstatus =songService.songExists(song.getName());
		if(songstatus == false) {
			songService.addSong(song);
			System.out.println("song added");
			}
		else {
			System.out.println("already exists");
		}
		return "newsong";
	}
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displaysongs";
	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium=true;
		if(premium) {
			List<Song> songList=songService.fetchAllSongs();
			model.addAttribute("songs",songList);
			return "displaysongs";
			
		}
		else {
			return "subscription";
		}
		
	}

}
