package com.my.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private int ID;

    private String NAME;

    private String AUTHOR;

    private int PRICE;

    private String PUBLISHDATE;

    private String TYPE;

    private String ADDRESS;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}

	public int getPRICE() {
		return PRICE;
	}

	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}

	public String getPUBLISHDATE() {
		return PUBLISHDATE;
	}

	public void setPUBLISHDATE(String pUBLISHDATE) {
		PUBLISHDATE = pUBLISHDATE;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	@Override
	public String toString() {
		return "Book [ID=" + ID + ", NAME=" + NAME + ", AUTHOR=" + AUTHOR + ", PRICE=" + PRICE + ", PUBLISHDATE="
				+ PUBLISHDATE + ", TYPE=" + TYPE + ", ADDRESS=" + ADDRESS + "]";
	}

	

	
}