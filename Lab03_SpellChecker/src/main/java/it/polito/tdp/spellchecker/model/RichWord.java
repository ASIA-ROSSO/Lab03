package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String parola;
	private boolean isValid;
	
	public RichWord(String parola) {
		this.parola = parola;
	}

	public String getParola() {
		return parola;
	}

	public void isValid() {
		this.isValid = true;
	}
	
	public void isNotValid() {
		this.isValid = false;
	}
	
	public boolean getIsValid() {
		return this.isValid;
	}

	@Override
	public String toString() {
			return parola;
	}

	
	

}
