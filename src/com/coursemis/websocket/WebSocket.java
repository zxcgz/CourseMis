package com.coursemis.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;

import com.coursemis.message.Message;
import com.coursemis.model.Student;
import com.google.gson.Gson;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/socket")
public class WebSocket {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	//private static Map<WebSocket, Message> loginMap = new HashMap<WebSocket,Message >();

	private static List<WebSocket> webSockets = new ArrayList<WebSocket>() ;
	
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	private Message message ;
	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("测试。。。onOpen...."+this.hashCode());
		this.session = session;
		webSockets.add(this) ;
		addOnlineCount(); // 在线数加1
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		//loginMap.remove(this) ;
		webSockets.remove(this) ;
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + webSockets.size());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
	//System.out.println("测试。。。onMessage...."+this.hashCode());
		try {
			Gson gson = new Gson() ;
			Message fromJson = gson.fromJson(message, Message.class) ;
			this.message = fromJson ;
			this.message.setType(Message.HEARTBEAT) ;
			this.sendMessage(gson.toJson(this.message));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		webSockets.remove(this) ;
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		//System.out.println("发送信息。。。。" + message);
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 点名的方法
	 * 
	 * @return
	 */
	public static void callTheRoll(Student student) {
		// 遍历map，获取到id
		try {
			Gson gson = new Gson();
			int sId = student.getSId();
			System.out.println("测试。。。。sid===" + sId);
			
			for (WebSocket webSocket : webSockets) {
				Message message = webSocket.message ;
				String tag = message.getTag();
				int userId = message.getUserId() ;
				if (tag.equals("student")&&userId == student.getSId()) {
					//找到了指定的学生
					System.out.println("找到了指定的学生");
					message.setType(Message.CALL_THE_ROLL) ;
					message.setMessage("call the roll") ;
					String json = gson.toJson(message) ;
					webSocket.sendMessage(json);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送随堂测验的信息
	 * @param sids		学生id集合
	 * @param cid		课程id
	 * @param periodId	课时数
	 */
	public static void classroomTest(List<Integer> sids ,int cid,int periodId){
		try {
			Gson gson = new Gson();
			System.out.println("发送随堂测验");
			for (WebSocket webSocket : webSockets) {
				Message message = webSocket.message ;
				String tag = message.getTag();
				int userId = message.getUserId() ;
				for (Integer integer : sids) {
					if (tag.equals("student")&&userId == integer) {
						//找到了指定的学生
						System.out.println("找到了指定的学生");
						message.setType(Message.TEST) ;
						message.setMessage(cid+"_"+periodId) ;
						String json = gson.toJson(message) ;
						webSocket.sendMessage(json);
						break ;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 提问的方法
	 * 
	 * @return
	 */
	public static void quiz(Student student) {
		// 遍历map，获取到id
		try {
			Gson gson = new Gson();
			int sId = student.getSId();
			System.out.println("测试。。。。sid===" + sId);
			
			for (WebSocket webSocket : webSockets) {
				Message message = webSocket.message ;
				String tag = message.getTag();
				int userId = message.getUserId() ;
				if (tag.equals("student")&&userId == student.getSId()) {
					//找到了指定的学生
					System.out.println("找到了指定的学生");
					message.setType(Message.QUIZ) ;
					message.setMessage("quiz") ;
					String json = gson.toJson(message) ;
					webSocket.sendMessage(json);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送反馈的信息
	 * @param sids		学生id集合
	 * @param cid		课程id
	 * @param periodId	课时数
	 */
	public static void callBack(List<Integer> sids ,int cid,int periodId){
		try {
			Gson gson = new Gson();
			
			for (WebSocket webSocket : webSockets) {
				Message message = webSocket.message ;
				String tag = message.getTag();
				int userId = message.getUserId() ;
				for (Integer integer : sids) {
					if (tag.equals("student")&&userId == integer) {
						//找到了指定的学生
						System.out.println("找到了指定的学生");
						message.setType(Message.CALL_BACK) ;
						message.setMessage(cid+"_"+periodId) ;
						String json = gson.toJson(message) ;
						webSocket.sendMessage(json);
						break ;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}

}
