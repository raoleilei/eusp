package cn.mldn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if(arg1 == null) {
			return "" ;
		}else if(arg1 instanceof Date) {
			return new SimpleDateFormat("yyyy-MM-dd").format((Date)arg1) ;
		}else {
			return arg1.toString() ;
		}
	}

}
