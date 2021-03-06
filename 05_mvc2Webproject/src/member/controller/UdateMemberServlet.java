package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UdateMemberServlet
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember" })
public class UdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		Member m = new Member();
		m.setMemberPw(request.getParameter("memberPw"));
		m.setAddress(request.getParameter("address"));
		m.setPhone(request.getParameter("phone"));
		m.setMemberId(request.getParameter("memberId"));
		//3.비즈니스 로직
		int result = new MemberService().updateMember(m);
		
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		//페이지에서 사용할 데이터 등록
		if(result>0) {
			request.setAttribute("msg", "정보변경 성공!");
			//정보변경에 성공한경우
			Member member = new MemberService().selectOneMember(m.getMemberId()); //중복확인용 셀렉트 원 멤버 사용
			HttpSession session = request.getSession(false);
			session.setAttribute("m", member);
		}else {
			request.setAttribute("msg", "정보변경 실패!");
		}
		request.setAttribute("loc", "/mypage");//마이페이지로 이동시키기위해 서블릿 매핑값을 등록
		rd.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
