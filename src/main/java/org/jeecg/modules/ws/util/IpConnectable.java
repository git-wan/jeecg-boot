package org.jeecg.modules.ws.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class IpConnectable {
	
	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	public static boolean isHostReachable(String host, Integer timeOut) {
		try {
			return InetAddress.getByName(host).isReachable(timeOut);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean ping(String ipAddress){
		int timeOut =1000;
	    try {
			return	InetAddress.getByName(ipAddress).isReachable(timeOut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/*e.printStackTrace();*/
			System.err.println("----------------------------"+ipAddress+"ip地址异常-------------------------------");
			return false;
		} 
	}

}
