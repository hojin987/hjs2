package com.spring5.mypro00.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;
import com.spring5.mypro00.mapper.MyBoardMapper;

@Service //서비스 클래스는 DAO 또는 mapper 인터페이스의 메서드를 호출합니다.
public class MyBoardServiceImpl implements MyBoardService {
	
	private MyBoardMapper myBoardMapper;
	
	//위의 필드에 MyBoardMapper 인터페이스 주입
	//방법1: setter방식
//	public MyBoardServiceImpl() {
//		System.out.println("MyBoardServiceImpl의 기본생성자입니다.");
//	}
//	
//	@Autowired
//	public void setMyBoardMapper(MyBoardMapper myBoardMapper) {
//		this.myBoardMapper = myBoardMapper;
//		System.out.println("MyBoardServiceImpl의 Setter입니다.");
//	}
	
	//방법2: 모든필드 생성자
	public MyBoardServiceImpl(MyBoardMapper myBoardMapper) {
		this.myBoardMapper = myBoardMapper;
//		System.out.println("MyBoardServiceImpl의 모든 필드 초기화 생성자입니다.");
//		System.out.println("myBoardMapper" + myBoardMapper);
	}
	
	//게시물 목록조회
//	@Override
//	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myboardPaging) {
//		List<MyBoardVO> myBoardList = myBoardMapper.selectMyBoardList(myboardPaging);
//		
//		return myBoardList;
//	}
	
	@Override
	public MyBoardPagingCreatorDTO getBoardList(MyBoardPagingDTO myboardPaging) {
		String startDate = myboardPaging.getStartDate();
		String endDate = myboardPaging.getEndDate();
		
		if((startDate != null && startDate.length() != 0) && (endDate != null && endDate.length() != 0)) {
//			myboardPaging.set_endDate(endDate);
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = myFormat.parse(endDate);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, 1);
				
				endDate = myFormat.format(cal.getTime());
				System.out.println("endDate: " + endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			myboardPaging.setEndDate(endDate);
			
		}
		
		List<MyBoardVO> myboardList = myBoardMapper.selectMyBoardList(myboardPaging);
		
		long rowTotal = myBoardMapper.selectRowTotal(myboardPaging);
		
		MyBoardPagingCreatorDTO pagingCreator = new MyBoardPagingCreatorDTO(rowTotal, myboardPaging, myboardList);

		return pagingCreator;
	}
	
	//게시물 등록
	@Override //저장된 게시물의 bno 값을 반환
	public long registerBoard(MyBoardVO myBoard) {
		
		int myRegBoard = myBoardMapper.insertMyBoard(myBoard);
		
//		return (myRegBoard ==1)? myBoard.getBno();
		return myBoard.getBno() ;
	}

	//특정 게시물 조회: 특정 게시물 하나의 데이터를 가져옴
	@Override
	public MyBoardVO getBoard(long bno) {
		
		int rows = myBoardMapper.updateBviewCnt(bno);
		
		MyBoardVO myBoard = myBoardMapper.selectMyBoard(bno);
		
		return (rows ==1) ? myBoard : null;
	}
	
	//게시물 수정
	@Override
	public boolean modifyBoard(MyBoardVO myBoard) {
		
		int rows = myBoardMapper.updateMyBoard(myBoard);
		return rows == 1;
		
//		return myBoardMapper.updateMyBoard(myBoard) == 1;
		
	}
	
	//게시물 삭제
	@Override
	public boolean removeBoard(long bno) {
		
		int rows = myBoardMapper.deleteMyboard(bno);
		
		return (rows == 1) ;
	}
	
	@Override
	public boolean modifyBdelFalg(long bno) {
		
		int rows = myBoardMapper.updateBdelFlag(bno);
		
		return (rows == 1) ;
	}
	
	//특정 게시물 수정 삭제 화면 호출 & 수정 후 조회페이지 호출
	@Override
	public MyBoardVO getBoard2(long bno) {
		
		MyBoardVO myBoard = myBoardMapper.selectMyBoard(bno);
		
		return myBoard ;
	}
	
}
