package Pressure.press;


class PFloat {
	static protected boolean isFloat(String tmp) {
		boolean flag = true;
		if(tmp.toCharArray()[0] == '-') tmp = tmp.substring(1);
		if(!tmp.contains("."))return false;
		if(tmp.startsWith("."))return false;
		for(String i : tmp.split("")) {
			if(!i.matches("[0-9]")) {
				if(i.contentEquals(".") && flag) {
					flag = false;
					continue;
				}
				return false;
			}
		}
		return true;
	}
	protected static String toFloat(String s) {
		if(isFloat(s))return s;
		return s + ".0";
	}
	
	protected static String toFloat(String s, int fix) {
		if(isFloat(s)) {
			String fpart = s.split("\\.")[1];
			String ipart = s.split("\\.")[0];
			if(fpart.length() > fix) {
				fpart = fpart.substring(fpart.length() - fix, fpart.length());
			}else if(fpart.length() < fix) {
				int tmp = fpart.length();
				for(int i = 0; i < fix - tmp; i ++)fpart = fpart + "0";
			}
			
			return ipart + "." + fpart;
		}else {
			String rep = "";
			for(int i = 0; i < fix; i ++)rep += "0";
			return s + "." + rep;
		}
	}
}
