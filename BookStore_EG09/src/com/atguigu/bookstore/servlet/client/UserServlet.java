package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.util.WEBUtils;

/**
 * 处理用户相关请求的Servlet
 * 
 * @author lilichao
 * 
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 创建一个UserService
	private UserService userService = new UserServiceImpl();
	
	/**
	 * 检查用户名是否存在
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户输入的用户名
		String username = request.getParameter("username");
		
		//调用service检查用户名
		boolean checkUsername = userService.checkUsername(username);
		
		//如果用户名可用，则返回一个1，不可用返回一个0
		if(checkUsername){
			//如果用户名可用，则返回一个1
			response.getWriter().print("1");
		}else{
			//不可用，返回一个0
			response.getWriter().print("0");
		}
		
		
	}
	

	
	/**
	 * 用户登出的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取HttpSession
		HttpSession session = request.getSession();
		//直接使session失效
		session.invalidate();
		//重定向回首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		
	}
	

	/**
	 * 处理用户登录请求的方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取HttpSession
		HttpSession session = request.getSession();
		
		//将请求参数封装为User对象
		User user = WEBUtils.param2Bean(request , new User());
		

		// 调用Service验证用户名和密码是否正确
		User loginUser = userService.login(user);

		// 判断loginUser是否null
		if (loginUser == null) {

			// 在request域对象中设置一个错误消息
			request.setAttribute("msg", "用户名或密码错误！");

			// 登录失败，用户名或密码错误。转发到login.html重新输入
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);

		} else {
			
			//将user对象放入到session
			session.setAttribute("loginUser", loginUser);
			
			// 登录成功 重定向到login-success.html
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");
		}
	}

	/**
	 * 处理注册请求的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取Session对象
		HttpSession session = request.getSession();
		//获取session中code
		String sessCode = (String) session.getAttribute("code");
		//获取请求参数中的code
		String reqCode = request.getParameter("code");
		//移除session中code
		session.removeAttribute("code");
		//判断验证码是否正确
		if(reqCode!=null && reqCode.equals(sessCode)){
			//验证码正确，正常处理请求
			//将请求参数封装为User对象
			User user = WEBUtils.param2Bean(request, new User());
			
			// 调用Service将User插入进数据库
			boolean regist = userService.regist(user);
			
			// 判断注册是否成功
			if (regist) {
				// 注册成功，重定向到regist-success.html
				response.sendRedirect(request.getContextPath()
						+ "/pages/user/regist_success.jsp");
			} else {
				
				// 注册失败，设置一个错误消息
				request.setAttribute("msg", "用户名已存在！");
				
				// 注册失败，转发到regist.html
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			}
		}else{
			//验证码错误，设置一个错误消息，并转发到regist.jsp
			request.setAttribute("msg", "请输入正确的验证码！");
			//转发到regist.jsp
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}

		
	}

}
