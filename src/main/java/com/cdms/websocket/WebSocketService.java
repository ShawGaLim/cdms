package com.cdms.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.cdms.pojo.response.WXResponse;
import com.cdms.utils.RandomJudge;

@ServerEndpoint(value = "/websocket/{role}/{cid}/{uid}", encoders = { WebSocketEncoder.class })
@Component
public class WebSocketService {
    	
	private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private static ConcurrentHashMap<String,WebSocketService> webSocketMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String uid = "";
    
    private String cid = "";
    
    private String role = "";
    
	/**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid, @PathParam("cid") String cid, @PathParam("role") String role) {
        WXResponse response = new WXResponse();
    	this.session = session;
        webSocketMap.put(uid, this);
        addOnlineCount();           //在线数加1
        this.uid = uid;
        this.cid = cid;
        this.role = role;
        System.out.println(this.role + "用户" + this.uid + "在课程" + this.cid +"与服务器建立连接，当前在线人数：" + getOnlineCount() + "人");
        try {
        	if(this.role.equals("teacher")) {
        		String str = "教师连接成功";
        		response.setDatatype("message");
        		response.setData(str);
        		this.session.getBasicRemote().sendObject(response);
        	} else {
        		String str = "学生连接成功";
        		response.setDatatype("message");
        		response.setData(str);
        		this.session.getBasicRemote().sendObject(response);
        	}
        } catch (Exception e) {
            System.out.println("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.uid);
        subOnlineCount();           //在线数减1
        System.out.println(this.role + "用户" + this.uid + "与服务器断开连接，当前在线人数：" + getOnlineCount() + "人");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自窗口" + this.uid + "的信息:" + message);
        //传回客户端消息
        try {
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    
    public static void sendToStudent(WXResponse response, String cid) {
    	for(Map.Entry<String, WebSocketService> socket: webSocketMap.entrySet()) {
    		try {
    			if(socket.getValue().cid.equals(cid) && socket.getValue().role.equals("student")) {
    				socket.getValue().session.getBasicRemote().sendObject(response);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			continue;
    		}
    	}
    }
    
    public static void sendToStudentStart(WXResponse response, String cid, Integer num) {
    	HashSet<String> keyset = new HashSet<>();
		for(Map.Entry<String, WebSocketService> socket: webSocketMap.entrySet()) {
			if(socket.getValue().cid.equals(cid) && socket.getValue().role.equals("student")) {
				keyset.add(socket.getKey());
			}
		}
		String[] keys = RandomJudge.createRandomJudge(keyset, num);
    	for(int i=0;i<keys.length;i++) {
    		try {
    			webSocketMap.get(keys[i]).session.getBasicRemote().sendObject(response);
    		} catch (Exception e) {
    			e.printStackTrace();
    			continue;
    		}
    	}
    }
    
    public static void sendToTeacher(WXResponse response, String cid) {
    	for(Map.Entry<String, WebSocketService> socket: webSocketMap.entrySet()) {
    		try {
    			if(socket.getValue().cid.equals(cid) && socket.getValue().role.equals("teacher")) {
    				socket.getValue().session.getBasicRemote().sendObject(response);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			continue;
    		}
    	}
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }
	
}
