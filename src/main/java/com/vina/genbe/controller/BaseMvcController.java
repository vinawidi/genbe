package com.vina.genbe.controller;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.vina.genbe.model.dto.PersonBiodataDto;
// import com.vina.genbe.model.entity.PersonEntity;
// import com.vina.genbe.repository.PersonRepository;

//import com.miniproject1.group4.dto.ProvinsiDto;
//import com.miniproject1.group4.entity.ProvinsiEntity;

@Controller
@RequestMapping("/")
public class BaseMvcController {
	
	
	
//	@GetMapping("person")
//	public String get() {
//		return "dashboard/personbiodata";
//	}
	
	@GetMapping("/index2")
	public String getDetail() {
		return "dashboard/index2";
	}
	
	
	@GetMapping("pendidikan")
	public String getPend() {
		return "dashboard/pendidikanedit";
	}
	
	@GetMapping("person")
	public String getModal() {
		return "dashboard/modalbiodata";
	}

}
