package com.yymonitor.common;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yy 
 * @date 2017年4月18日 下午7:16:23
 * @description TODO 系统相关 工具栏
 * @tag 
 * @company 
 */
public class SystemUtil {
	private static String currentIp=null;//当前IP地址
	private static int jvmId=0;//jvmid
	public static String getCurrentIp() {
		if(currentIp!=null)
			return currentIp;
		
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                    	currentIp=ia.getHostAddress();
                    	return ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
        	throw new RuntimeException("获取IP地址失败");
        }
        return null;
    }
	
	/**
	 * @title getJVMThreadId 
	 * @description 获取当前JVM进程号
	 * @author yy
	 * @date 2018年9月27日 上午9:20:11
	 * @return
	 * @return int
	 */
	public static int getJVMThreadId() {
		if(jvmId==0) {
		 String pid = ManagementFactory.getRuntimeMXBean().getName();
		 jvmId=Integer.parseInt(pid.split("@")[0]);
		}
		 return jvmId;
	}
	
	public static void main(String[] args) {
//		System.out.println(SystemUtil.getCurrentIp());
		System.out.println(JSONObject.toJSONString(ManagementFactory.getRuntimeMXBean()));
	}
}
