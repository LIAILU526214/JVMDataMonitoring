package com.i5lu.utils;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class JavaProcessIDUtils {
	/**
	 * 获取java pid 和 程序名
	 * @return
	 * @throws MonitorException
	 * @throws URISyntaxException
	 */
	public static Object[][] getAllJavaProcessId() throws MonitorException, URISyntaxException{
//		Map<Object,String> PIDMap = new HashMap<Object,String>();
		// 获取监控主机
	     MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
	     // 取得所有在活动的虚拟机集合
	     Set<?> vmlist = new HashSet<Object>(local.activeVms());
	     Object[][] pids = new Object[vmlist.size()][3];
	     int i = 0;
	     // 遍历集合，输出PID和进程名
	     for(Object process : vmlist) {
	       MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
	       // 获取类名
	       String processname = MonitoredVmUtil.mainClass(vm, true);
//	       System.out.println(process + " ------> " + processname);
	       if ((process == null || "".equals(process)) && (processname == null || "".equals(processname))) {
	    	   break;
	       }
//	       PIDMap.put(process, processname);
	       pids[i][0] = process + " -- "+processname;
	       pids[i][1] = process;
	       pids[i][2] = processname;
	       i++;
	     }
	     return pids;
	}
}
