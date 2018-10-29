/** 
 * <pre>项目名称:power 
 * 文件名称:TimeUtil.java 
 * 包名:com.jk.utils 
 * 创建日期:2018年8月10日下午2:48:53 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package cc.mrbird.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * <pre>项目名称：power    
 * 类名称：TimeUtil    
 * 类描述：    
 * 创建人：赵佳佳   
 * 创建时间：2018年8月10日 下午2:48:53    
 * 修改人：赵佳佳      
 * 修改时间：2018年8月10日 下午2:48:53    
 * 修改备注：       
 * @version </pre>    
 */
public class TimeUtil {
	
	public static String fromat(Date date,String format) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		
		return simpleDateFormat.format(date);
	}
	public static String fromat(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return simpleDateFormat.format(date);
	}
}
