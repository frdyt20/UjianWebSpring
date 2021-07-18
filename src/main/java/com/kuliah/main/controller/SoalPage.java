package com.kuliah.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Soal;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class SoalPage {
	
	@Autowired
	SoalRepository soalRepository;
	
	@Autowired
	PertanyaanRepository pertanyaanRepository;
	
	@GetMapping("/soal/view")
	public String viewSoal(Model model) {
		
		model.addAttribute("listSoal", soalRepository.findAll());
		
		return "view_soal.html";
	}
	
	@GetMapping("/soal/add")
	public String addSoal(Model model) {
		
		model.addAttribute("soal", new Soal());
		model.addAttribute("listPertanyaan", this.pertanyaanRepository.findAll());
		
		return "add_soal.html";
	}
	
	@PostMapping("/soal/add")
	public String addSoal(@ModelAttribute Soal soal) {
		
		soalRepository.save(soal);
		
		return "redirect:/soal/view";
	}
	
	@GetMapping("soal/delete/{id}")
	public String deleteSoal(@PathVariable Long id, Model model) {
		
		this.soalRepository.deleteById(id);
		model.getAttribute("listSoal");
		
		return"redirect:/soal/view";
	}
	
	@GetMapping("/soal/update/{id}")
	public String updateSoal(@PathVariable Long id, Model model) {
		
		Optional<Soal> soal = soalRepository.findById(id);
		model.addAttribute("soal", soal);
		
		return "add_soal";
		
	}

}
