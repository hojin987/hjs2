package com.spring5.mypro00.service;

import java.util.List;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;

public interface MyBoardService {
	
	//게시물 목록조회(READ)
//	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myboardPaging);
	public MyBoardPagingCreatorDTO getBoardList(MyBoardPagingDTO myboardPaging);
	
	//새 게시물 등록(CREATE)
	public long registerBoard(MyBoardVO myBoard);
	
	//특정 게시물 조회 (조회수 1 증가 고려)
	public MyBoardVO getBoard(long bno);
	
	//특정 게시물 수정(UPDATE)
	public boolean modifyBoard(MyBoardVO myBoard);
	
	//특정 게시물 삭제(DELETE)
	public boolean removeBoard(long bno);
	
	//특정 게시물 삭제 요청(UPDATE)
	public boolean modifyBdelFalg(long bno);
	
	//특정 게시물 수정 삭제 화면 호출 & 수정 후 조회페이지 호출
	public MyBoardVO getBoard2(long bno);
	
	
}
