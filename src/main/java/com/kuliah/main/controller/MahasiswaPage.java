package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Mahasiswa;
import com.kuliah.main.repository.MahasiswaRepository;

@Controller
public class MahasiswaPage {
	
	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	@GetMapping("/mahasiswa/view")
	public String viewIndexMahasiswa(Model model) {
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		return "view_mahasiswa.html";
	}
	
	@GetMapping("/mahasiswa/add/")
	public String addMahasiswa(Model model) {
		model.addAttribute("mahasiswa", new Mahasiswa());
		return "add_mahasiswa.html";
	}
	
	@PostMapping("/mahasiswa/add")
	public String addMahasiswa(@ModelAttribute Mahasiswa mahasiswa, Model model) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = mahasiswa.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		mahasiswa.setPassword(encodedPassword);
	
		mahasiswaRepository.save(mahasiswa);
		
		return "redirect:/mahasiswa/view";
	}
	
	
	@GetMapping("/mahasiswa/delete/{id}")
	public String deleteMahasiswa(@PathVariable Long id, Model model) {
		
		this.mahasiswaRepository.deleteById(id);
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll())
		;
		return "redirect:/mahasiswa/view";
	}
	
	@GetMapping("/mahasiswa/update/{id}")
	public String viewUpdateMahasiswa(@PathVariable Long id, Model model) {
		
		this.mahasiswaRepository.findById(id);
		
		model.addAttribute("mahasiswa", mahasiswaRepository.findAll());
		
		return"/mahasiswa/add";
	}
	
	
}
