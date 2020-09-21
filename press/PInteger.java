package press;


class PInteger {
	static protected boolean isInt(String tmp) {
		if(tmp.toCharArray()[0] == '-') tmp = tmp.substring(1);
		for(String i : tmp.split("")) {
			if(!i.matches("[0-9]"))return false;
		}
		return true;
	}
	static protected String toInt(String n)throws InvalidExpressException {
		if(!Pressure.check(n))throw new InvalidExpressException(n);
		if(isInt(n))return n;
		return n.split("\\.")[0];
	}
}
