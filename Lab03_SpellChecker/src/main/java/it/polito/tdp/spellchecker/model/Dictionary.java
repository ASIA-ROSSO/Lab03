package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.*;

public class Dictionary { // MODELLO

	private int paroleErrate;
	private Set<String> dizionario;
	List<RichWord> paroleInput;

	public Dictionary() {
		dizionario = new HashSet<String>();
	}

	public void loadDictionary(String language) {
		paroleErrate = 0;
		String fonte;
		if (language.equals("English")) {
			fonte = "src\\main\\resources\\English.txt";
		} else {
			fonte = "C:\\Users\\Asia Sofia\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\Italian.txt";
		}

		// Salvo il dizionario scelto nella struttura dati.
		try {
			FileReader fr = new FileReader(fonte);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		RichWord r;
		paroleInput = new ArrayList<RichWord>();
		for (String s : inputTextList) {
			r = new RichWord(s);
			if (dizionario.contains(s)) {
				r.isValid();
			} else {
				r.isNotValid();
				paroleErrate++;
			}
			paroleInput.add(r);
		}
		return paroleInput;
	}
	
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		RichWord r;
		paroleInput = new ArrayList<RichWord>();
		for (String s : inputTextList) {
			r = new RichWord(s);
			if (dizionario.contains(s)) {
				r.isValid();
			} else {
				r.isNotValid();
				paroleErrate++;
			}
			paroleInput.add(r);
		}
		return paroleInput;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		RichWord r;
		paroleInput = new ArrayList<RichWord>();
		for (String s : inputTextList) {
			r = new RichWord(s);
			if (dizionario.contains(s)) {
				r.isValid();
			} else {
				r.isNotValid();
				paroleErrate++;
			}
			paroleInput.add(r);
		}
		return paroleInput;
	}
	
	public int getParoleErrate() {
		return paroleErrate;
	}

	public void remove() {
		this.dizionario.clear();
		
	}

}
