package Pressure.press.cal;

import Pressure.press.*;

public class Add {
	
	protected static String[] reverse(String s) {
		String tmp = "";
		for(String i : s.split("")) {
			tmp = i + tmp;
		}
		return tmp.split("");
	}
	protected static String reverse1(String s) {
		String tmp = "";
		for(String i : s.split("")) {
			tmp = i + tmp;
		}
		return tmp;
	}
	
	protected static String join(String[] sa) {
		String res = "";
		for(String s : sa) res += s;
		return res;
	}
	
	public static String add(String a, String b) throws NumberFormatException, InvalidExpressException { // 23, 456 (479)
		if(Pressure.isPositive(a) && Pressure.isPositive(b)) { // a > 0, b > 0
		
			String[] s = reverse(a); // 32
			String[] k = reverse(b); // 654
			String result = "";
			boolean bo = false;
			int smaller = a.length() > b.length()? b.length() : a.length();
			int bigger = a.length() < b.length()? b.length() : a.length();
			String[] biggern = a.length() < b.length()? k : s;
			String cache = "";
			
			for(int i = 0; i < bigger; i ++) {
				if(i >= smaller) {
					int ri = Integer.parseInt(biggern[i]);
					if(bo) {
						ri ++;
						bo = false;
					}
					if(ri >= 10) {
						cache += ri%10 + "";
						bo = true;
						continue;
					}
					result += ri%10;
					continue;
				}
				
				int r = Integer.parseInt(s[i]) + Integer.parseInt(k[i]);
				if(bo) {bo = false; r ++; }
				if(r >= 10) {
					bo = true;
					result += r%10;
					continue;
				}
				result += r;
			} // 4
			return (bo ? 1 : "") + cache + reverse1(result); 
			
		}else{
			
			if(Pressure.isPositive(a) && !Pressure.isPositive(b)) { // b < 0
				return Subtract.su(a, b);
			}else if(!Pressure.isPositive(a) && Pressure.isPositive(b)){ //a < 0
				return Subtract.su(b, a);
			}else { // a < 0, b < 0
				a = a.substring(1);
				b = b.substring(1);
				String[] s = reverse(a); // 32
				String[] k = reverse(b); // 654
				String result = "";
				boolean bo = false;
				int smaller = a.length() > b.length()? b.length() : a.length();
				int bigger = a.length() < b.length()? b.length() : a.length();
				String[] biggern = a.length() < b.length()? k : s;
				String cache = "";
				
				for(int i = 0; i < bigger; i ++) {
					if(i >= smaller) {
						int ri = Integer.parseInt(biggern[i]);
						if(bo) {
							ri ++;
							bo = false;
						}
						if(ri >= 10) {
							cache += ri%10 + "";
							bo = true;
							continue;
						}
						result += ri%10;
						continue;
					}
					
					int r = Integer.parseInt(s[i]) + Integer.parseInt(k[i]);
					if(bo) {bo = false; r ++; }
					if(r >= 10) {
						bo = true;
						result += r%10;
						continue;
					}
					result += r;
				} // 4
				return "-" + (bo ? 1 : 0) + cache + reverse1(result); 
			}
		}
	} 
	
	public static String addFloat(String a, String b) throws NumberFormatException, InvalidExpressException {
		
		if(Pressure.isPositive(a) && Pressure.isPositive(b)) { // a > 0, b > 0
			boolean bo = false;
			String result = "";
			String intParta = a.split("\\.")[0];
			String intPartb = b.split("\\.")[0];
			String fParta = a.split("\\.")[1];
			String fPartb = b.split("\\.")[1];
			String[] s = reverse(intParta); 
			String[] k = reverse(intPartb);
			String[] ss = reverse(fParta); 
			String[] kk = reverse(fPartb);
			int smaller = intParta.length() > intPartb.length()? intPartb.length() : intParta.length();
			int bigger = intParta.length() < intPartb.length()? intPartb.length() : intParta.length();
			int fsmaller = fParta.length() > fPartb.length()? fPartb.length() : fParta.length();
			int fbigger = fParta.length() < fPartb.length()? fPartb.length() : fParta.length();
			String[] biggern = reverse(intParta.length() < intPartb.length()? intPartb : intParta);
			String[] fbiggern = reverse(fParta.length() < fPartb.length()? fPartb : fParta);
			boolean tmpbo = false;
			
			for(int i = 0; i < fbigger; i ++) { // float
				if(i >= fsmaller) {
					int r = Integer.parseInt(fbiggern[i]);
					if(bo) {
						r ++;
						bo = false;
					}
					result += r;
					continue;
				}
				
				int r = Integer.parseInt(ss[i]) + Integer.parseInt(kk[i]);
				if(bo) {bo = false; r ++; }
				if(r >= 10) {
					bo = true;
					if(i == 0)tmpbo = true;
					result += r%10;
					continue;
				}
				result += r;
			}
			result = "." + reverse1(result);
			
			String result1 = "";
			String cache = "";
			for(int i = 0; i < bigger; i ++) { // integer
				if(i >= smaller) {
					int ri = Integer.parseInt(biggern[i]);
					if(bo) {
						ri ++;
						bo = false;
					}
					if(ri >= 10) {
						cache += ri%10 + "";
						bo = true;
						continue;
					}
					result1 += ri%10;
					continue;
				}
				
				int r = Integer.parseInt(s[i]) + Integer.parseInt(k[i]) + (tmpbo && i == bigger - 1? 1 : 0);
				tmpbo = false;
				if(bo) {bo = false; r ++; }
				if(r >= 10) {
					bo = true;
					result1 += r%10;
					continue;
				}
				result1 += r;
			} 
			result1 = reverse1(result1);
			
			return (bo ? 1 : 0) + cache + result1 + result; 
			
		}else{
			
			if(Pressure.isPositive(a) && !Pressure.isPositive(b)) { // b < 0
				return Subtract.fsu(a, b);
			}else if((!Pressure.isPositive(a)) && Pressure.isPositive(b)){ //a < 0
				return Subtract.fsu(b, a);
			}else { // a < 0, b < 0
				a = a.substring(1);
				b = b.substring(1);
				boolean bo = false;
				String result = "";
				String intParta = a.split("\\.")[0];
				String intPartb = b.split("\\.")[0];
				String fParta = a.split("\\.")[1];
				String fPartb = b.split("\\.")[1];
				String[] s = reverse(intParta); 
				String[] k = reverse(intPartb);
				String[] ss = reverse(fParta); 
				String[] kk = reverse(fPartb);
				int smaller = intParta.length() > intPartb.length()? intPartb.length() : intParta.length();
				int bigger = intParta.length() < intPartb.length()? intPartb.length() : intParta.length();
				int fsmaller = fParta.length() > fPartb.length()? fPartb.length() : fParta.length();
				int fbigger = fParta.length() < fPartb.length()? fPartb.length() : fParta.length();
				String[] biggern = reverse(intParta.length() < intPartb.length()? intPartb : intParta);
				String[] fbiggern = reverse(fParta.length() < fPartb.length()? fPartb : fParta);
				boolean tmpbo = false;
				
				for(int i = 0; i < fbigger; i ++) { // float
					if(i >= fsmaller) {
						int r = Integer.parseInt(fbiggern[i]);
						if(bo) {
							r ++;
							bo = false;
						}
						result += r;
						continue;
					}
					
					int r = Integer.parseInt(ss[i]) + Integer.parseInt(kk[i]);
					if(bo) {bo = false; r ++; }
					if(r >= 10) {
						bo = true;
						if(i == 0)tmpbo = true;
						result += r%10;
						continue;
					}
					result += r;
				}
				result = "." + reverse1(result);
				
				String result1 = "", cache = "";
				for(int i = 0; i < bigger; i ++) { // integer
					if(i >= smaller) {
						int ri = Integer.parseInt(biggern[i]);
						if(bo) {
							ri ++;
							bo = false;
						}
						if(ri >= 10) {
							cache += ri%10 + "";
							bo = true;
							continue;
						}
						result1 += ri%10;
						continue;
					}
					
					int r = Integer.parseInt(s[i]) + Integer.parseInt(k[i]) + (tmpbo && i == bigger - 1? 1 : 0);
					tmpbo = false;
					if(bo) {bo = false; r ++; }
					if(r >= 10) {
						bo = true;
						result1 += r%10;
						continue;
					}
					result1 += r;
				} 
				result1 = reverse1(result1);
				
				return "-" + (bo ? 1 : 0) + cache +result1 + result; 
			}
		}
	}
}
