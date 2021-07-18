package com.kuliah.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.repository.DosenRepository;
import com.kuliah.main.repository.MahasiswaRepository;
import com.kuliah.main.repository.MataKuliahRepository;
import com.kuliah.main.repository.PlotMataKuliahRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class PlotMataKuliahPage {
	
	@Autowired
	PlotMataKuliahRepository plotMataKuliahRepository;
	
	@Autowired
	MataKuliahRepository mataKuliahRepository;
	
	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	@Autowired
	DosenRepository dosenRepository;
	
	@Autowired
	SoalRepository soalRepository;
	
	
	@GetMapping("/plotmatakuliah/view")
	public String viewPlotMataKuliah(Model model) {
		
		model.addAttribute("listPlotMataKuliah", plotMataKuliahRepository.findAll());
		
		return "view_plotmatakuliah.html";
	}
	
	@GetMapping("/plotmatakuliah/add")
	public String addPlotMataKuliah(Model model) {
		
		model.addAttribute("plotMataKuliah", new PlotMataKuliah());
		model.addAttribute("listMataKuliah", mataKuliahRepository.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		model.addAttribute("listDosen", dosenRepository.findAll());
		model.addAttribute("listSoal", soalRepository.findAll());
		
		return "add_plotmatakuliah.html";
	}
	
	@PostMapping("/plotmatakuliah/view")
	public String addPlotmatakuliah(@ModelAttribute PlotMataKuliah plotMatkul) {
		
		plotMataKuliahRepository.save(plotMatkul);
		
		return "redirect:/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/delete/{id}")
	public String deleteplotmatkul(@PathVariable Long id, Model model) {
		
		this.plotMataKuliahRepository.deleteById(id);
		model.getAttribute("listPlotMataKuliah");
		
		return "/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/update/{id}")
	public String updatePlotmatkul(@PathVariable Long id, Model model) {
		
		Optional<PlotMataKuliah> plotMataKuliah = plotMataKuliahRepository.findById(id);
		model.addAttribute("plotmatkul", plotMataKuliah);
		model.addAttribute("listMataKuliah", mataKuliahRepository.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		model.addAttribute("listDosen", dosenRepository.findAll());
		model.addAttribute("listSoal", soalRepository.findAll());
		
		return "add_plotmatakuliah";
		
	}
	
}
