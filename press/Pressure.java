package Pressure.press;

import Pressure.press.cal.*;

public class Pressure {
	private String Number = "";
	
	@Override 
	public String toString() {
		return Number;
	}
	
	public Pressure() {
		Number = "0";
	}
	
	public Pressure(String s) {
		s=s.replaceAll(" ", "");
		Number = s;
	}
	
	public Pressure(Pressure p) {
		Number = p.getNumber().replaceAll(" ", "");;
	}
	
	protected static boolean check(String s) {
		s=s.replaceAll(" ", "");
		return PFloat.isFloat(s) || PInteger.isInt(s);
	}
	
	protected String getNumber() {
		return Number;
	}
	
	
	public Pressure add(String s) throws InvalidExpressException {
		s=s.replaceAll(" ", "");
		if(!check(s))throw new InvalidExpressException(s);
		if(PInteger.isInt(s) && PInteger.isInt(Number)) {
			Number = Add.add(Number, s);
		}else {
			String NumT = PFloat.toFloat(Number);
			String sT = PFloat.toFloat(s);
			int bi = (NumT.split("\\.")[1].length() > sT.split("\\.")[1].length()? NumT.split("\\.")[1] : sT.split("\\.")[1]).length();
			Number = hush(Add.addFloat(PFloat.toFloat(NumT, bi), PFloat.toFloat(sT, bi)));
		}
		return this;
	}
	
	public Pressure substract(String s) {
		
		return this;
	}
	
	public Pressure multiply(String s) {
		
		return this;
	}
	
	public Pressure divide(String s) {
		
		return this;
	}
	
	public static boolean isPositive(String s) throws InvalidExpressException {
		s=s.replaceAll(" ", "");
		if(!check(s))throw new InvalidExpressException(s);
		return !(s.charAt(0) == '-');
	}
	public boolean isInteger() {
		return !PFloat.isFloat(Number);
	}
	
	protected static String hush(String s) throws InvalidExpressException{
		boolean test = isPositive(s);
		if(!test)s = s.substring(1);
		if(PInteger.isInt(s)) {
			String[] srr = s.split("");
			int i = 0;
			for(; i < s.length(); i ++) {
				if(!srr[i].contentEquals("0")) {
					break;
				}
			}
			
			return (!test? "-": "") + s.substring(i);
		}else if(PFloat.isFloat(s)){
			if(!test)s = s.substring(1);
			String ipart = s.split("\\.")[0];
			String fpart = s.split("\\.")[1];
			String[] srr = ipart.split("");
			String[] frr = fpart.split("");
			
			int i = 0;
			for(; i < srr.length - 1; i ++) {
				if(!srr[i].contentEquals("0")) {
					break;
				}
			}
			ipart = ipart.substring(i);
			int j = fpart.length() - 1;
			for(; j > 0; j --) {
				if(!frr[j].contentEquals("0")) {
					break;
				}
			}
			
			if(fpart.substring(0, j + 1).contentEquals("0")) {
				return ipart;
			}
			return (!test? "-": "") +ipart + "." + fpart.substring(0, j + 1);
		}
		
		return "";
	}
}
