<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@include file="../myinclude/myheader.jsp" %>


<style>
 th {text-align:center;}
</style>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Board - List</h3>
        </div>
        <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
            	<div class="row">
					<div class="col-md-6" style="font-size:20px; height: 45px; padding-top:10px;">게시글 목록</div>
					<div class="col-md-6" style="padding-top:8px;">
						<button type="button" id="btnToRegister" class="btn btn-primary btn-sm pull-right">새글 등록</button>
					</div>
				</div>
           	</div>
            <!-- /.panel-heading -->
            <div class="panel-body">
            
            <form class="form-inline" id="frmSendValue" name="frmSendValue" action="${contextPath }/myboard/list" method="get">
            	<div class="form-group">
            		<label class="sr-only">frmSendValues</label>
            		<select class="form-control" id="selectAmount" name="rowAmountPerPage">
            			<option value="10" ${(pagingCreator.myboardPaging.rowAmountPerPage == 10) ? "selected" : ""}>10</option>
            			<option value="50" ${(pagingCreator.myboardPaging.rowAmountPerPage == 50) ? "selected" : ""}>50</option>
            			<option value="100" ${(pagingCreator.myboardPaging.rowAmountPerPage == 100) ? "selected" : ""}>100</option>
            		</select>
            		
            		<select class="form-control" id="selectScope" name="scope">
            			<option value="" ${(pagingCreator.myboardPaging.scope == null) ? "selected" : ""}>선택</option>
            			<option value="T" ${(pagingCreator.myboardPaging.scope == "T") ? "selected" : ""}>제목</option>
            			<option value="C" ${(pagingCreator.myboardPaging.scope == "C") ? "selected" : ""}>내용</option>
            			<option value="W" ${(pagingCreator.myboardPaging.scope == "W") ? "selected" : ""}>작성자</option>
            			<option value="TC"${(pagingCreator.myboardPaging.scope == "TC") ? "selected" : ""}>제목+내용</option>
            			<option value="TCW"${(pagingCreator.myboardPaging.scope == "TCW") ? "selected" : ""}>제목+내용+작성자</option>
            		</select>
            		
            		<div class="input-group"><!-- 검색어 입력 -->
					   <input class="form-control" id="keyword" name="keyword" type="text" placeholder="검색어를 입력하세요"
					         value='<c:out value="${pagingCreator.myboardPaging.keyword}" />' />
					   <span class="input-group-btn"><!-- 전송버튼 -->
					      <button class="btn btn-warning" type="button" id="btnSearchGo"
					            ><i class="fa fa-search"></i>
					      </button>
					   </span>
					</div>
					
					<div class="input-group"><!-- 검색 초기화 버튼 -->
			         	<button id="btnReset" class="btn btn-info" type="button">Reset</button>
			      	</div> 
					
            		
					<input type="hidden" id="pageNum" name="pageNum" value="${pagingCreator.myboardPaging.pageNum}"><%-- 
					<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${pagingCreator.myboardPaging.rowAmountPerPage}"> --%>
					<input type="hidden" id="lastPageNum" name="lastPageNum" value="${pagingCreator.lastPageNum}">
				</div>
			</form>
            <hr>
                <table class="table table-striped table-bordered table-hover" 
                	   style="width:100; text-align:center;">
                    <thead>
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:choose>
                    	<c:when test="${not empty pagingCreator.myboardList}">
	                    	<c:forEach var="myboard" items="${pagingCreator.myboardList}">
	                        <tr class="moveDetail" data-bno='<c:out value="${myboard.bno}"/>' >
	                            <td><c:out value="${myboard.bno }"/></td>
	                       <%-- <td style="text-align:left"><a href="${contextPath}/myboard/detail?bno=${myboard.bno}">${myboard.btitle }</a></td> --%>
	                            <td style="text-align:left">${myboard.btitle}</td>
	                            <td>${myboard.bwriter }</td>
	                            <td class="center"><fmt:formatDate value="${myboard.bregDate }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                            <td class="center"><fmt:formatDate value="${myboard.bmodDate }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
	                            <td class="center"><c:out value="${myboard.bviewCnt }"/></td>
	                        </tr>
	                        </c:forEach>
                        </c:when>
                        <c:otherwise>
                        	 <tr class="odd gradeX">
	                            <td colspan="6">등록된 게시물이 없습니다.</td>
	                        </tr>
                        </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
                <!-- /.table-responsive -->
                
                <div style="text-align:center; ">
					<ul class="pagination pagination-sm ">
					<c:if test="${pagingCreator.prev}">
						<li class="pagination-button">
						  <a href="1" aria-label="Previous">
						  	<span aria-hidden="true">&laquo</span>
						  </a>
						</li>
					</c:if>
					<c:if test="${pagingCreator.prev}">
						<li class="pagination-button">
						  <a href="${pagingCreator.startPagingNum - 1}" aria-label="Previous">
						  	<span aria-hidden="true">이전</span>
						  </a>
						</li>
					</c:if>
					
					<c:forEach begin="${pagingCreator.startPagingNum}" end="${pagingCreator.endPagingNum}" var="pagingNum">
						<li class='pagination-button ${(pagingCreator.myboardPaging.pageNum == pagingNum) ? "active" : ""}'>
					  		<a href="${pagingNum}">${pagingNum}</a>
					  	</li>
					</c:forEach>
					<c:if test="${pagingCreator.next}">
						<li class="pagination-button">
						  <a href="${pagingCreator.endPagingNum + 1}" aria-label="Next">
						  	<span aria-hidden="true">다음</span>
						  </a>
						</li>
					</c:if>
					<c:if test="${pagingCreator.next}">
						<li class="pagination-button">
						  <a href="${pagingCreator.lastPageNum}" aria-label="Next">
						  	<span aria-hidden="true">&raquo</span>
						  </a>
						</li>
					</c:if>
					  	
					</ul>
                </div>
                
            </div><!-- /.panel-body -->
        </div><!-- /.panel -->
    </div><!-- /.col-lg-12 -->
</div><!-- /.row -->
    
    
</div>
<!-- /#page-wrapper -->

<!-- Modal -->
<div class="modal fade" id="yourModal" tabindex="-1" role="dialog" aria-labelledby="yourModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="yourModalLabel">Modal title</h4>
            </div>
            <div class="modal-body" id="yourModal-body">메세지</div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>

var frmSendValue = $("#frmSendValue")
var result = '<c:out value="${result}"/>';

//등록페이지 이동
$("#btnToRegister").on("click", function(){
	window.location.href="${contextPath}/myboard/register"
})

//세부사항 보기 이동
$(".moveDetail").on("click", function(){
	
	var bno = $(this).data("bno");
//	window.location.href="${contextPath}/myboard/detail?bno=" + bno;
	
	frmSendValue.append("<input type='hidden' name='bno' value=' " + bno + " '/>");
	frmSendValue.attr("action", "${contextPath}/myboard/detail").attr("method", "get");
	frmSendValue.submit();

})

function runModal(result){
	if(result == 0){
		return;
	} else if(parseInt(result) > 0 ){
		var myMsg = result + "번 게시물이 등록되었습니다."
	} else if(result =="successRemove"){
		var myMsg = "게시물이 삭제되었습니다."
	} else if(reuslt =="successModify"){
		var myMsg = "게시물이 수정되었습니다."
	}
	$("#yourModal-body").html(myMsg);
	
	$("#yourModal").modal("show");
	
	myMsg = "";
}

$(document).ready(function(){
	runModal(result);
	
	window.addEventListener("popstate", function(event){
		history.pushState(null, null, location.href);
	})
	
	history.pushState(null, null, location.href);
	
	
})

//페이징 기능 처리
$(".pagination-button a").on("click", function(e){
	e.preventDefault();
	
	frmSendValue.find("input[name='pageNum']").val($(this).attr("href")) ;
	frmSendValue.attr("action", "${contextPath}/myboard/list")
	frmSendValue.attr("method", "get");
	
	frmSendValue.submit();
	
})

//표시 행수 선택 이벤트 처리
/*
<select class="form-control" id="selectAmount" name="rowAmountPerPage">
            			<option value="10" ${(pagingCreator.myboardPaging.rowAmountPerPage == 10) ? "selected" : ""}>10</option>
            			<option value="50" ${(pagingCreator.myboardPaging.rowAmountPerPage == 50) ? "selected" : ""}>50</option>
            			<option value="100" ${(pagingCreator.myboardPaging.rowAmountPerPage == 100) ? "selected" : ""}>100</option>
            		</select>  
 
*/
<%--검색 관련 요소의 이벤트 처리--%>
<%--표시행수 변경 이벤트 처리--%>
$("#selectAmount").on("change",function(){
	frmSendValue.find("input[name='pageNum']").val(1);
	frmSendValue.submit();
})

<%--검색버튼 클릭 이벤트 처리 --%>
$("#btnSearchGo").on("click", function(){
	
	var scope = $("#selectScope").find("option:selected").val();
	
	if (!scope || scope == ""){
		alert("검색 범위를 설정하세요");
		return false;
	}
	
	var keyword = $("#keyword").val();
	
	if(!keyword || keyword.length == 0){
		alert("검색어를 입력하세요");
		return false;
	}
	
	frmSendValue.find("input[name='pageNum']").val(1);
	frmSendValue.submit();
	
})

<%--검색초기화 버튼 이벤트처리, 버튼 초기화 시, 1페이지에 목록 정보 다시 표시 --%>

$("#btnReset").on("click", function(){
	$("#selectAmount").val(10);
	$("#selectScope").val("") ;
	$("#keyword").val("");
	$("#pageNum").val(1);
})

$("#selectScope").on("change", function(){
	$("#pageNum").val(1);
	frmSendValue.submit();
})


</script>


<%@include file="../myinclude/myfooter.jsp" %>    
