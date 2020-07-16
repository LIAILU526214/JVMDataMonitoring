package com.i5lu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class StaticVar {
	/**
	 * 项目路径
	 */
	public static String PROGRAME_PATH = null;
	public static Integer nettyPort = 8081;

	public static Integer serverPort = 8080;
	public static Integer mysqlPort = 3309;
	public static String username = "root";
	public static String pwd = "root";
	public static Integer imbRefreshTime = 200;
	public static Integer a664RefreshTime = 200;
	public static Integer chartRefrshTime = 200;
	public static Integer timeChartRefreshTime = 200;
	public static Integer imbPayloadRefreshTime = 500;
	public static Integer a664PayloadRefreshTime = 500;

	public static String configPath = "jre/lib/ext/conf/config.properties";
	public static String applicationPath = "jre/lib/ext/application.properties";
	public static String indexGlobalPath = "jre/lib/ext/static/dist/index.04d65067.js";
	public static String clientMainPath = "client/resources/app/main.js";
	public static String clientName = "Data_Analysis_Client.exe";

	static {
		String path = System.getProperty("user.dir");
		if (path.startsWith("file:")) {
			path = path.substring("file:/".length());
		}
		// path = "D://Data_Analysis";
		PROGRAME_PATH = path;
		try {
			InputStream resourceAsStream = StaticVar.class.getClassLoader()
					.getResourceAsStream("resources/sconf/config.properties");
			if (resourceAsStream != null) {
				Properties pro2 = new Properties();
				pro2.load(resourceAsStream);
				resourceAsStream.close();

				configPath = pro2.getProperty("configPath");
				applicationPath = pro2.getProperty("applicationPath");
				indexGlobalPath = pro2.getProperty("indexGlobalPath");
				clientMainPath = pro2.getProperty("clientMainPath");
				clientName = pro2.getProperty("clientName");
			}

			if (new File(PROGRAME_PATH + "/" + applicationPath).exists()) {
				Properties pro = new Properties();
				FileInputStream in = new FileInputStream(PROGRAME_PATH + "/" + applicationPath);
				pro.load(in);
				in.close();
				String netty = pro.getProperty("netty.server.port");
				String server = pro.getProperty("server.port");
				String mysql = pro.getProperty("jdbc.port");
				String user = pro.getProperty("spring.datasource.username");// 用户名
				String password = pro.getProperty("spring.datasource.password");// 密码
				if (netty != null && !"".equals(netty)) {
					nettyPort = Integer.parseInt(netty);
				}
				if (mysql != null && !"".equals(mysql)) {
					mysqlPort = Integer.parseInt(mysql);
				}
				if (server != null && !"".equals(server)) {
					serverPort = Integer.parseInt(server);
				}
				if (user != null && !"".equals(user)) {
					username = user;
				}
				if (password != null && !"".equals(password)) {
					pwd = password;
				}
			} else {
				if (new File(PROGRAME_PATH + "/conf/" + applicationPath).exists()) {
					Properties pro = new Properties();
					FileInputStream in = new FileInputStream(PROGRAME_PATH + "/conf/" + applicationPath);
					pro.load(in);
					in.close();
					String netty = pro.getProperty("netty.server.port");
					String server = pro.getProperty("server.port");
					String mysql = pro.getProperty("jdbc.port");
					String user = pro.getProperty("spring.datasource.username");// 用户名
					String password = pro.getProperty("spring.datasource.password");// 密码
					if (netty != null && !"".equals(netty)) {
						nettyPort = Integer.parseInt(netty);
					}
					if (mysql != null && !"".equals(mysql)) {
						mysqlPort = Integer.parseInt(mysql);
					}
					if (server != null && !"".equals(server)) {
						serverPort = Integer.parseInt(server);
					}
					if (user != null && !"".equals(user)) {
						username = user;
					}
					if (password != null && !"".equals(password)) {
						pwd = password;
					}
				}
			}
			if (new File(PROGRAME_PATH + "/" + configPath).exists()) {
				Properties pro1 = new Properties();
				FileInputStream in1 = new FileInputStream(PROGRAME_PATH + "/" + configPath);
				pro1.load(in1);
				in1.close();

				String imbRefreshTimeStr = pro1.getProperty("config.server.imbRefreshTime");
				String a664RefreshStr = pro1.getProperty("config.server.a664RefreshTime");
				String chartRefrshTimeStr = pro1.getProperty("config.server.chartRefrshTime");
				String timeChartRefreshTimeStr = pro1.getProperty("config.server.timeChartRefreshTime");// 用户名
				String a664PayloadRefreshTimeStr = pro1.getProperty("config.server.a664PayloadRefreshTime");// 密码
				String imbPayloadRefreshTimeStr = pro1.getProperty("config.server.imbPayloadRefreshTime");// 密码
				String clientNameStr = pro1.getProperty("config.server.client");// 密码
				if (imbRefreshTimeStr != null && !"".equals(imbRefreshTimeStr)) {
					imbRefreshTime = 1000 / Integer.parseInt(imbRefreshTimeStr);
				}
				if (a664RefreshStr != null && !"".equals(a664RefreshStr)) {
					a664RefreshTime = 1000 / Integer.parseInt(a664RefreshStr);
				}
				if (chartRefrshTimeStr != null && !"".equals(chartRefrshTimeStr)) {
					chartRefrshTime = 1000 / Integer.parseInt(chartRefrshTimeStr);
				}
				if (timeChartRefreshTimeStr != null && !"".equals(timeChartRefreshTimeStr)) {
					timeChartRefreshTime = 1000 / Integer.parseInt(timeChartRefreshTimeStr);
				}
				if (a664PayloadRefreshTimeStr != null && !"".equals(a664PayloadRefreshTimeStr)) {
					a664PayloadRefreshTime = 1000 / Integer.parseInt(a664PayloadRefreshTimeStr);
				}
				if (imbPayloadRefreshTimeStr != null && !"".equals(imbPayloadRefreshTimeStr)) {
					imbPayloadRefreshTime = 1000 / Integer.parseInt(imbPayloadRefreshTimeStr);
				}
				if (clientNameStr != null && !"".equals(clientNameStr)) {
					clientName = clientNameStr;
				}
			} else {
				if (new File(PROGRAME_PATH + "/conf/" + configPath).exists()) {
					Properties pro1 = new Properties();
					FileInputStream in1 = new FileInputStream(PROGRAME_PATH + "/conf/" + configPath);
					pro1.load(in1);
					in1.close();

					String imbRefreshTimeStr = pro1.getProperty("config.server.imbRefreshTime");
					String a664RefreshStr = pro1.getProperty("config.server.a664RefreshTime");
					String chartRefrshTimeStr = pro1.getProperty("config.server.chartRefrshTime");
					String timeChartRefreshTimeStr = pro1.getProperty("config.server.timeChartRefreshTime");// 用户名
					String a664PayloadRefreshTimeStr = pro1.getProperty("config.server.a664PayloadRefreshTime");// 密码
					String imbPayloadRefreshTimeStr = pro1.getProperty("config.server.imbPayloadRefreshTime");// 密码
					String clientNameStr = pro1.getProperty("config.server.client");// 密码
					if (imbRefreshTimeStr != null && !"".equals(imbRefreshTimeStr)) {
						imbRefreshTime = 1000 / Integer.parseInt(imbRefreshTimeStr);
					}
					if (a664RefreshStr != null && !"".equals(a664RefreshStr)) {
						a664RefreshTime = 1000 / Integer.parseInt(a664RefreshStr);
					}
					if (chartRefrshTimeStr != null && !"".equals(chartRefrshTimeStr)) {
						chartRefrshTime = 1000 / Integer.parseInt(chartRefrshTimeStr);
					}
					if (timeChartRefreshTimeStr != null && !"".equals(timeChartRefreshTimeStr)) {
						timeChartRefreshTime = 1000 / Integer.parseInt(timeChartRefreshTimeStr);
					}
					if (a664PayloadRefreshTimeStr != null && !"".equals(a664PayloadRefreshTimeStr)) {
						a664PayloadRefreshTime = 1000 / Integer.parseInt(a664PayloadRefreshTimeStr);
					}
					if (imbPayloadRefreshTimeStr != null && !"".equals(imbPayloadRefreshTimeStr)) {
						imbPayloadRefreshTime = 1000 / Integer.parseInt(imbPayloadRefreshTimeStr);
					}
					if (clientNameStr != null && !"".equals(clientNameStr)) {
						clientName = clientNameStr;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param imbRefreshTime
	 * @param a664RefreshTime
	 * @param chartRefrshTime
	 * @param timeChartRefreshTime
	 * @param imbPayloadRefreshTime
	 * @param a664PayloadRefreshTime
	 * @return boolean
	 */
	public static boolean changeRefreshTime(String imbRefreshTime, String a664RefreshTime, String chartRefrshTime,
			String timeChartRefreshTime, String imbPayloadRefreshTime, String a664PayloadRefreshTime) {
		// TODO Auto-generated method stub
		try {
			Properties pro1 = new LinkedProperties();
			if (new File(PROGRAME_PATH + "/" + configPath).exists()) {
				FileInputStream in1 = new FileInputStream(PROGRAME_PATH + "/" + configPath);
				pro1.load(in1);
				in1.close();
				Integer imb = 1000 / Integer.parseInt(imbRefreshTime);
				Integer a664 = 1000 / Integer.parseInt(a664RefreshTime);
				Integer chart = 1000 / Integer.parseInt(chartRefrshTime);
				Integer time = 1000 / Integer.parseInt(timeChartRefreshTime);
				Integer imbPayload = 1000 / Integer.parseInt(imbPayloadRefreshTime);
				Integer a664Payload = 1000 / Integer.parseInt(a664PayloadRefreshTime);
				pro1.setProperty("config.server.imbRefreshTime", String.valueOf(imb));
				pro1.setProperty("config.server.a664RefreshTime", String.valueOf(a664));
				pro1.setProperty("config.server.chartRefrshTime", String.valueOf(chart));
				pro1.setProperty("config.server.timeChartRefreshTime", String.valueOf(time));
				pro1.setProperty("config.server.imbPayloadRefreshTime", String.valueOf(imbPayload));
				pro1.setProperty("config.server.a664PayloadRefreshTime", String.valueOf(a664Payload));
				OutputStream fos = new FileOutputStream(PROGRAME_PATH + "/" + configPath);
				pro1.store(fos, "Update value" + new Date());
				fos.close();
				if (new File(PROGRAME_PATH + "/conf/" + configPath).exists()) {
					OutputStream fos2 = new FileOutputStream(PROGRAME_PATH + "/conf/" + configPath);
					pro1.store(fos2, "Update value" + new Date());
					fos2.close();
				}
				StaticVar.a664PayloadRefreshTime = Integer.parseInt(a664PayloadRefreshTime);
				StaticVar.imbPayloadRefreshTime = Integer.parseInt(imbPayloadRefreshTime);
				StaticVar.timeChartRefreshTime = Integer.parseInt(timeChartRefreshTime);
				StaticVar.chartRefrshTime = Integer.parseInt(chartRefrshTime);
				StaticVar.a664RefreshTime = Integer.parseInt(a664RefreshTime);
				StaticVar.imbRefreshTime = Integer.parseInt(imbRefreshTime);
			} else {
				if (new File(PROGRAME_PATH + "/conf/" + configPath).exists()) {
					FileInputStream in1 = new FileInputStream(PROGRAME_PATH + "/conf/" + configPath);
					pro1.load(in1);
					in1.close();
					Integer imb = 1000 / Integer.parseInt(imbRefreshTime);
					Integer a664 = 1000 / Integer.parseInt(a664RefreshTime);
					Integer chart = 1000 / Integer.parseInt(chartRefrshTime);
					Integer time = 1000 / Integer.parseInt(timeChartRefreshTime);
					Integer imbPayload = 1000 / Integer.parseInt(imbPayloadRefreshTime);
					Integer a664Payload = 1000 / Integer.parseInt(a664PayloadRefreshTime);
					pro1.setProperty("config.server.imbRefreshTime", String.valueOf(imb));
					pro1.setProperty("config.server.a664RefreshTime", String.valueOf(a664));
					pro1.setProperty("config.server.chartRefrshTime", String.valueOf(chart));
					pro1.setProperty("config.server.timeChartRefreshTime", String.valueOf(time));
					pro1.setProperty("config.server.imbPayloadRefreshTime", String.valueOf(imbPayload));
					pro1.setProperty("config.server.a664PayloadRefreshTime", String.valueOf(a664Payload));
					OutputStream fos = new FileOutputStream(PROGRAME_PATH + "/conf/" + configPath);
					pro1.store(fos, "Update value" + new Date());
					fos.close();
					StaticVar.a664PayloadRefreshTime = Integer.parseInt(a664PayloadRefreshTime);
					StaticVar.imbPayloadRefreshTime = Integer.parseInt(imbPayloadRefreshTime);
					StaticVar.timeChartRefreshTime = Integer.parseInt(timeChartRefreshTime);
					StaticVar.chartRefrshTime = Integer.parseInt(chartRefrshTime);
					StaticVar.a664RefreshTime = Integer.parseInt(a664RefreshTime);
					StaticVar.imbRefreshTime = Integer.parseInt(imbRefreshTime);
				}
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @param mysql
	 * @param netty
	 * @param server
	 * @throws IOException
	 */
	public static void changePort(String mysql, String netty, String server) throws IOException {
		if (new File(PROGRAME_PATH + "/" + applicationPath).exists()) {
			Properties pro = new LinkedProperties();
			FileInputStream in = new FileInputStream(PROGRAME_PATH + "/" + applicationPath);
			pro.load(in);
			in.close();
			String nettySrc = pro.getProperty("netty.server.port");
			String serverSrc = pro.getProperty("server.port");
			String mysqlSrc = pro.getProperty("jdbc.port");
			//stopMysql(mysqlSrc);
			if (new File(PROGRAME_PATH + "/" + indexGlobalPath).exists()) {
				ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/" + indexGlobalPath, "wsPort:" + nettySrc,
						"wsPort:" + netty);
				ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/" + indexGlobalPath, "serverPort:" + serverSrc,
						"serverPort:" + server);
			}
			if (new File(PROGRAME_PATH + "/conf/" + indexGlobalPath).exists()) {
				ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/conf/" + indexGlobalPath, "wsPort:" + nettySrc,
						"wsPort:" + netty);
				ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/conf/" + indexGlobalPath, "serverPort:" + serverSrc,
						"serverPort:" + server);
			}
			if (new File(PROGRAME_PATH + "/" + clientMainPath).exists()) {
				ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/" + clientMainPath, "localhost:" + serverSrc,
						"localhost:" + server);
			}
			pro.setProperty("netty.server.port", netty);
			pro.setProperty("server.port", server);
			pro.setProperty("jdbc.port", mysql);
			pro.setProperty("spring.datasource.url", "jdbc:mysql://127.0.0.1:" + mysql
					+ "/data_analysis?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&autoReconnect=true");
			OutputStream fos2 = new FileOutputStream(PROGRAME_PATH + "/" + applicationPath);
			pro.store(fos2, "Update value" + new Date());
			fos2.close();

			if (new File(PROGRAME_PATH + "/conf/" + applicationPath).exists()) {
				OutputStream fos3 = new FileOutputStream(PROGRAME_PATH + "/conf/" + applicationPath);
				pro.store(fos3, "Update value" + new Date());
				fos3.close();
			}

			File shutdown = new File(PROGRAME_PATH + "/shutdown.bat");
			if (!shutdown.exists()) {
				shutdown.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(shutdown);
			
			String shutDownBat = "wmic process where name='"+clientName+"'  delete\r\n"
					+ "mysql5.5.6\\bin\\mysqladmin.exe --port=" + mysql + " --user=" + username + " --password=" + pwd
					+ " shutdown\r\n"
					+ "ping -n 2 127.0>nul \r\n"
					+ "wmic process where name='java_isource.exe' delete\r\n"
					+ "wmic process where name='isource_mysqld.exe' delete\r\n"
					+ "ping -n 1 127.0>nul \r\n"
					+ "set port=" + server + "\r\n"
					+ "for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m\r\n"
					+ "set port=" + netty + "\r\n"
					+ "for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m\r\n"
					+"set port="+mysql+"\r\n"  
					+"for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m";
			out.write(shutDownBat.getBytes(), 0, shutDownBat.getBytes().length);
			out.flush();
			out.close();
			StaticVar.mysqlPort = Integer.parseInt(mysql);
			StaticVar.nettyPort = Integer.parseInt(netty);
			StaticVar.serverPort = Integer.parseInt(server);
		} else {
			if (new File(PROGRAME_PATH + "/conf/" + applicationPath).exists()) {
				Properties pro = new LinkedProperties();
				FileInputStream in = new FileInputStream(PROGRAME_PATH + "/conf/" + applicationPath);
				pro.load(in);
				in.close();
				String nettySrc = pro.getProperty("netty.server.port");
				String serverSrc = pro.getProperty("server.port");
				String mysqlSrc = pro.getProperty("jdbc.port");
				if (new File(PROGRAME_PATH + "/" + indexGlobalPath).exists()) {
					ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/" + indexGlobalPath, "wsPort:" + nettySrc,
							"wsPort:" + netty);
				}
				if (new File(PROGRAME_PATH + "/conf/" + indexGlobalPath).exists()) {
					ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/conf/" + indexGlobalPath, "wsPort:" + nettySrc,
							"wsPort:" + netty);
				}
				if (new File(PROGRAME_PATH + "/" + clientMainPath).exists()) {
					ReplaceFileFiled.replacTextContent(PROGRAME_PATH + "/" + clientMainPath, "localhost:" + serverSrc,
							"localhost:" + server);
				}
				pro.setProperty("netty.server.port", netty);
				pro.setProperty("server.port", server);
				pro.setProperty("jdbc.port", mysql);
				pro.setProperty("spring.datasource.url", "jdbc:mysql://127.0.0.1:" + mysql
						+ "/data_analysis?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&autoReconnect=true");
				OutputStream fos2 = new FileOutputStream(PROGRAME_PATH + "/conf/" + applicationPath);
				pro.store(fos2, "Update value" + new Date());
				fos2.close();

				File shutdown = new File(PROGRAME_PATH + "/shutdown.bat");
				if (!shutdown.exists()) {
					shutdown.createNewFile();
				}
				FileOutputStream out = new FileOutputStream(shutdown);
				String shutDownBat = "wmic process where name='"+clientName+"'  delete\r\n"
						+ "mysql5.5.6\\bin\\mysqladmin.exe --port=" + mysql + " --user=" + username + " --password=" + pwd
						+ " shutdown\r\n"
						+ "ping -n 2 127.0>nul \r\n"
						+ "wmic process where name='java_isource.exe' delete\r\n"
						+ "wmic process where name='isource_mysqld.exe' delete\r\n"
						+ "ping -n 1 127.0>nul \r\n"
						+ "set port=" + server + "\r\n"
						+ "for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m\r\n"
						+ "set port=" + netty + "\r\n"
						+ "for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m\r\n"
						+"set port="+mysql+"\r\n"  
						+"for /f \"tokens=1-5\" %%i in ('netstat -ano |findstr \":%port%\"') do taskkill /f /pid %%m";
				out.write(shutDownBat.getBytes(), 0, shutDownBat.getBytes().length);
				out.flush();
				out.close();
				StaticVar.mysqlPort = Integer.parseInt(mysql);
				StaticVar.nettyPort = Integer.parseInt(netty);
				StaticVar.serverPort = Integer.parseInt(server);
			}
		}
	}
}
