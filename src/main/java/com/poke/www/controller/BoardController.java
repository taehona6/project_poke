package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poke.www.domain.BoardDTO;
import com.poke.www.domain.BoardVO;
import com.poke.www.domain.FileVO;
import com.poke.www.handler.FileHandler;
import com.poke.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
@Slf4j
public class BoardController {

	
	private final BoardService boardService;
	
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo >>> {}", bvo);
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		if(bvo.getTitle() == null || bvo.getTitle() == "" || bvo.getWriter() == null || bvo.getWriter() == "" ||
				bvo.getContent() == null || bvo.getContent() == "") {
			log.info(">>> register bvo >>> {}", bvo);
			return "redirect:/board/list";
		}else {			
			boardService.register(new BoardDTO(bvo, flist));
			return "redirect:/board/list";
		}
		
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		
		List<BoardVO> list = boardService.getList();
		m.addAttribute("list", list);
		
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") long bno, Model m) {
		
		BoardDTO bdto = boardService.getDetail(bno);
	
		m.addAttribute("bdto", bdto);
		
		return "/board/detail";	
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files
			,@RequestParam("uuids")String uuids) {
		
		String [] uuidsArr = uuids.split(",");
		for(String uuid : uuidsArr) {
			log.info(uuid);
			boardService.deleteFile(uuid);
		}
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		boardService.modify(new BoardDTO(bvo, flist));
		
		return "redirect:/board/list";
	}
	
	
}
