package com.spring5.mypro00.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.service.MyReplyService;

@RestController
@RequestMapping(value= {"/replies", "/myreplies"})
public class MyReplyController {
	
	private MyReplyService myReplyService;
	
	public MyReplyController (MyReplyService myReplyService) {
		this.myReplyService = myReplyService;
	}
	
//	게시물에 대한 댓글 목록 조회 GET /replies/pages/{bno}/{page}
	@GetMapping(value="/{bno}/page/{pageNum}", 
				produces = {"application/json;charset=utf-8", 
							"application/xml;charset=utf-8"})
	public ResponseEntity<MyReplyPagingCreatorDTO> showReplyList(@PathVariable("bno") long bno,
																 @PathVariable("pageNum") Integer pageNum){
		
		MyReplyPagingCreatorDTO myreplyPagingCreator = 
				myReplyService.getReplyList(new MyReplyPagingDTO(bno, pageNum));
		
		ResponseEntity<MyReplyPagingCreatorDTO> myResponseEntity = 
				new ResponseEntity<MyReplyPagingCreatorDTO>(myreplyPagingCreator, HttpStatus.OK);
		
		return myResponseEntity;
	}
	
//	게시물에 대한 댓글 등록(rno 반환) POST /replies/{bno}/new
	@PostMapping(value = "/{bno}/new", consumes= {"application/json;charset=utf-8"}, 
									   produces= {"text/plain;charset=utf-8"})
	public ResponseEntity<String> registerReplyForBoard(@PathVariable("bno") long bno,
														@RequestBody MyReplyVO myreply) {
		
		Long registeredRno = myReplyService.registerReplyForBoard(myreply);
		String _registeredRno = null;
		
		if(registeredRno != null) {
			_registeredRno = String.valueOf(registeredRno);
		}else {
			_registeredRno = String.valueOf(registeredRno);
		}
		
		return registeredRno != null ? new ResponseEntity<String>(_registeredRno, HttpStatus.OK)
								     : new ResponseEntity<String>(_registeredRno, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
//	게시물에 대한 댓글의 답글 등록(rno 반환) POST /replies/{bno}/{prno}/new
	@PostMapping(value = "/{bno}/{prno}/new", consumes= {"application/json;charset=utf-8"}, 
					   produces= {"text/plain;charset=utf-8"})
	public ResponseEntity<String> registerReplyForReply(@PathVariable("bno") long bno,
														@PathVariable("prno") long prno,
														@RequestBody MyReplyVO myreply) {
		
		Long registeredRno = myReplyService.registerReplyForReply(myreply);
		String _registeredRno = null;
		
		if(registeredRno != null) {
			_registeredRno = String.valueOf(registeredRno);
		}else {
			_registeredRno = String.valueOf(registeredRno);
		}
		
		return registeredRno != null ? new ResponseEntity<String>(_registeredRno, HttpStatus.OK)
				     : new ResponseEntity<String>(_registeredRno, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	게시물에 대한 특정 댓글 조회 GET /replies/{bno}/{rno}
	@GetMapping(value = "/{bno}/{rno}", 
				produces = "application/json;charset=utf-8")
	public MyReplyVO showReply(@PathVariable("bno") Long bno,
							   @PathVariable("rno") Long rno){
		
		return myReplyService.getMyReply(bno, rno) ;
		
	}
//	게시물에 대한 특정 댓글 수정 PUT 또는 PATCH /replies/{bno}/{rno}
//	Ajax에서의 요청 URI: /mypro00/replies/234567/3, PUT:PATCH	
	@RequestMapping(value="/{bno}/{rno}", 
					method= {RequestMethod.PUT, RequestMethod.PATCH} ,
					consumes = "aplication/json;charset=utf-8" ,
					produces = "text/plain;charset=utf-8")
	public String modifyReply(@PathVariable("bno") Long bno,
											  @PathVariable("rno") Long rno,
											  @RequestBody MyReplyVO myreply){
		if(myReplyService.modifyMyReply(myreply)) {
			return "mdoifySuceess" ;
		}else {
			return "modifyfail";
		}
		
	}
	
//	특정 게시물에 대한 특정 댓글/답글 삭제(rdelFlag 1로 업데이트)
	@DeleteMapping(value="/{bno}/{rno}", produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> removeReply(@PathVariable("bno") Long bno,
											  @PathVariable("rno") Long rno){
		
		return myReplyService.modifyRdelFlag(bno, rno)
				? new ResponseEntity<String>("removeSuccess", HttpStatus.OK) 
				: new ResponseEntity<String>("removeFail", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
//	게시물에 대한 모든 댓글 삭제 DELETE
	@DeleteMapping(value="/{bno}", produces="text/plain;charset=utf-8") 
	public ResponseEntity<String> removeAllReply(@PathVariable("bno") Long bno){
		
		int deleteRows = myReplyService.removeAllMyReply(bno);
		
		return new ResponseEntity<String>(String.valueOf(deleteRows), HttpStatus.OK);
	}

}
