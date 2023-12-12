package com.spring5.mypro00.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;
import com.spring5.mypro00.service.MyBoardService;


@Controller
@RequestMapping("/myboard")
public class MyBoardController {
//	@Setter(onMethod_ = @Autowired)
	private MyBoardService myBoardService;
	
	
	//(단일 생성자를 이용한 주입)
	public MyBoardController(MyBoardService myBoardService) {
		this.myBoardService = myBoardService;
	}
	
//  (Setter를 이용한 주입) 
//	public MyBoardController() {
//		System.out.println("MyBoardController기본생성자");
//	}
//  
//	@Autowired
//	public void setMyBoardService(MyBoardService myBoardService) {
//		this.myBoardService = myBoardService ;
//	}
	
	//목록조회
//	@GetMapping("/list")
//	public String showBoardList(Model model, MyBoardPagingDTO myboardPaging) {
//		List<MyBoardVO> myBoardList = myBoardService.getBoardList(myboardPaging);
//		
//		model.addAttribute("myBoardList", myBoardList);
//		
//		return "myboard/list";
//	}
	
	@GetMapping("/list")
	public String showBoardList(Model model, MyBoardPagingDTO myboardPaging) {
		MyBoardPagingCreatorDTO pagingCreator = myBoardService.getBoardList(myboardPaging);
		
		model.addAttribute("pagingCreator", pagingCreator);
		
		return "myboard/list";
	}
	
	//등록 페이지 호출 GET /myboard/register
	@GetMapping("/register")
	public String showBoardRegisterPage() {
		System.out.println("등록페이지 호출");
		
		return "myboard/register";
	}
	
//	등록 처리 POST /myboard/register
	@PostMapping("/register")
	public String registerNewBoard(MyBoardVO myBoard,
								RedirectAttributes redirectAttr) {

		long bno = myBoardService.registerBoard(myBoard);
		
		redirectAttr.addFlashAttribute("result", bno);
		System.out.println("result: " + redirectAttr.getFlashAttributes());
		//자동으로 한글, 문자를 인코딩해준다.
		
		return "redirect:/myboard/list";
//		return "redirect:/myboard/list?bno=" + bno;
	}
	
	//특정 게시물 조회 GET /myboard/detail
	@GetMapping("/detail")
	public String showBoardDetail(Long bno, Model model, String result,
								MyBoardPagingDTO myboardPaging) {
		
		MyBoardVO myboard = null;
		
		if (result == null) {
			myboard = myBoardService.getBoard(bno);	
		}else {
			myboard = myBoardService.getBoard2(bno);
		}
		 
//		System.out.println("컨트롤러 myBoard: " + myBoard);
		
		model.addAttribute("myboard" , myboard);
		model.addAttribute("result", result);
		
		return "myboard/detail";
	}
	
//	특정 게시물 수정삭제 페이지 호출 GET /myboard/modify
	@GetMapping("/modify")
	public String showBoardModify(Long bno, Model model, MyBoardPagingDTO myboardPaging) {
		
		MyBoardVO myboard = myBoardService.getBoard2(bno);
		
		model.addAttribute("myboard", myboard);
		
		return "myboard/modify";
	}
	
//	특정 게시물 수정 POST /myboard/modify 
	@PostMapping("/modify")
	public String modifyBoard(MyBoardVO myboard, 
							  Model model,
							  RedirectAttributes redirectAttr,
							  MyBoardPagingDTO myboardPaging) {
		
		boolean modifyResult = myBoardService.modifyBoard(myboard);
		
		if(modifyResult) {
			redirectAttr.addAttribute("result", "successModify");
			//addFlashAttribute는 Post 방식으로 브라우저에 값을 달고
			//addAttribute는 Get방식으로 URL에 달린다.
		} else {
			redirectAttr.addAttribute("result", "failModify");
		}
		redirectAttr.addAttribute("bno", myboard.getBno());
		redirectAttr.addAttribute("pageNum", myboardPaging.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", myboardPaging.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", myboardPaging.getScope());
		redirectAttr.addAttribute("keyword", myboardPaging.getKeyword());
		
		//flashAttribute를 먼저 달고 addAttribute 해야한다.
		
		return "redirect:/myboard/detail" ;
	}
	
//	특정 게시물 삭제 POST /myboard/remove
	@PostMapping("/remove")
	public String removeBoard(Long bno, RedirectAttributes redirectAttr,
							  MyBoardPagingDTO myboardPaging) {
		
		boolean removeResult = myBoardService.modifyBdelFalg(bno);

//		if(removeResult) {
		if(removeResult) {
			redirectAttr.addFlashAttribute("result", "successRemove");
		}else {
			redirectAttr.addFlashAttribute("result", "failRemove");
		}
		
		redirectAttr.addAttribute("pageNum", myboardPaging.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", myboardPaging.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", myboardPaging.getScope());
		redirectAttr.addAttribute("keyword", myboardPaging.getKeyword());
		
		return "redirect:/myboard/list";
	}
	
	
	

}
