package pico.chall.math;

public class Pressure {
	public String pointNum;
	private String index(String s, int i) {
		return ""+s.toCharArray()[i];
	}
	private String rev(String r) { //			reverse
		String s = "";
		for(int i = r.length() - 1; i >= 0; i --) {
			s += r.charAt(i);
		}
		return s;
	}
	private void trim() {
		if(!index(pointNum, 0).contentEquals("-")) {
			int count = 0, le = pointNum.length();
			while(le > count) {
				if(index(pointNum, count).equals("0")) {
					count++;
				}else {
					break;
				}
			}
			if(count == 0)return;
			pointNum = pointNum.substring(count-1).substring(1);
		}else {
			int count = 1, le = pointNum.length();
			while(le > count) {
				if(index(pointNum, count).equals("0")) {
					count++;
				}else {
					break;
				}
			}
			if(count == 0)return;
			pointNum = "-" + pointNum.substring(count-1).substring(1);
		}
	}
	
	
	public Pressure(String n) {
		pointNum = n;
	}
	public Pressure(int n) {
		pointNum = ""+n;
	}
	public Pressure(float n) {
		pointNum = ""+n;
	}
	public Pressure(double n) {
		pointNum = ""+n;
	}
	public boolean equal(String s) {
		if(s.equals(pointNum))return true;
		return false;
	}

										// 				compare
	public boolean bigger(String s) {
		if(s.indexOf("-") != -1 && pointNum.indexOf("-") == -1)return true;
		if(s == pointNum) return false;
		else if(s.indexOf("-") != -1 && pointNum.indexOf("-") != -1) {
			s = s.replace("-", "");
			pointNum = pointNum.replace("-", "");
			if(s.length() < pointNum.length())return false;
			else if(s.length() > pointNum.length())return true;
			pointNum = pointNum.replace("-", "");
			for(int i = 0; i < s.length(); i ++) {
				if(s.charAt(i) < pointNum.charAt(i)) {
					return false;
				}else if(s.charAt(i) > pointNum.charAt(i)) {
					return true;
				}
			}
		}
		if(s.indexOf("-") == -1 && pointNum.indexOf("-") != -1)return false;
		if(s.length() > pointNum.length())return false;
		else if(s.length() < pointNum.length())return true;
		else {
			for(int i = 0; i < s.length(); i ++) {
				if(s.charAt(i) > pointNum.charAt(i)) {
					return false;
				}else if(s.charAt(i) < pointNum.charAt(i)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean smaller(String s) {
		if(s.indexOf("-") != -1 && pointNum.indexOf("-") == -1)return false;
		if(s == pointNum) return false;
		else if(s.indexOf("-") != -1 && pointNum.indexOf("-") != -1) {
			s = s.replace("-", "");
			pointNum = pointNum.replace("-", "");
			if(s.length() < pointNum.length())return true;
			else if(s.length() > pointNum.length())return false;
			pointNum = pointNum.replace("-", "");
			for(int i = 0; i < s.length(); i ++) {
				if(s.charAt(i) < pointNum.charAt(i)) {
					return true;
				}else if(s.charAt(i) > pointNum.charAt(i)) {
					return false;
				}
			}
		}
		if(s.indexOf("-") == -1 && pointNum.indexOf("-") != -1)return true;
		if(s.length() > pointNum.length())return true;
		else if(s.length() < pointNum.length())return false;
		else {
			for(int i = 0; i < s.length(); i ++) {
				if(s.charAt(i) > pointNum.charAt(i)) {
					return true;
				}else if(s.charAt(i) < pointNum.charAt(i)) {
					return false;
				}
			}
		}
		return false;
	}

	
	public String get() {
		return pointNum;
	}
	public String get(Pressure pr) {
		return pr.pointNum;
	}

	
	public Pressure subtract(String sub) {				//	minus
		if(sub == "")return this;
		if(index(sub, 0).contentEquals("-"))return add(sub.replace("-", ""));
		if(!index(pointNum, 0).contentEquals("-")) {
			if(sub.contentEquals(pointNum)) {
				pointNum = "0";
				return this;
			}
			sub = rev(sub);
			String rp = rev(pointNum);
			int[] comp = new int[2];
			if(sub.length() < rp.length()) comp[1] = rp.split("").length;
			else comp[1] = sub.split("").length;
			int[] tmp = new int[comp[1] + 2];
			boolean mod = false;
			int tt;
			byte by = 0;
			String[] rpa = rp.split(""), suba = sub.split("");
			for(int co = 0; co < comp[1]; co ++) {
				if(bigger(rev(sub))) {
					tt =  -Integer.parseInt(co < sub.length()? suba[co] : "0") + Integer.parseInt(co < rp.length()? rpa[co] : "0");
				}else {
					tt = Integer.parseInt(co < sub.length()? suba[co] : "0") - Integer.parseInt(co < rp.length()? rpa[co] : "0");
					by = 1;
				}
				if(mod) {
					tt -= 1;
					mod =! mod;
				}
				if(tt < 0) {
					mod = !mod;
					tt += 10;
				}
				tmp[co] = tt;
			}
			String res = "";
			for(int nnn : tmp) {
				res += nnn;
			}
			pointNum = (by == 1? "-" : "") + rev(res);
			trim();
			return this;
		}else {
			pointNum = pointNum.replace("-", "");
			System.out.println(sub);
			pointNum = "-" + add(sub).get();
			trim();
			return this;
		}
	}
	public Pressure add(String pn) {		//	plus
		if(pn == "")return this;
		if(index(pn, 0).contentEquals("-"))return subtract(pn.replace("-", ""));
		pn = rev(pn);
		String rp = rev(pointNum);
		int[] comp = new int[2];
		if(pn.length() < rp.length()) {
			comp[0] = pn.length();
			comp[1] = rp.length();
		}else {
			comp[1] = pn.length();
			comp[0] = rp.length();
		}
		int[] tmp = new int[comp[1] + 2];
		boolean mod = false;
		int tt = 0;
		String[] rpa = rp.split(""), pna = pn.split("");
		if(!index(pointNum, 0).contentEquals("-")) {
			for(int co = 0; co < comp[1]+2; co ++) {
				tt =  Integer.parseInt(co < rpa.length? rpa[co] : "0") + Integer.parseInt(co < pna.length? pna[co] : "0");
				if(mod) {
					tt += 1;
					mod = !mod;
				}
				if(tt >= 10) {
					mod = !mod;
					tt -= 10;
				}
				tmp[co] = tt;
			}
			String res = "";
			for(int nnn : tmp) {
				res += nnn;
			}
			pointNum = rev(res);
			trim();
			return this;
		}else {
			if(pn.indexOf("-") == -1 && pointNum.indexOf("-") != -1) {
				String tempV = pointNum;
				pointNum = rev(pn);
				pn = tempV;
				return subtract(pn.replace("-", ""));
			}else {
				return this;
			}
		}
	}
}
