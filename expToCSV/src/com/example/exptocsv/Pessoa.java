package com.example.exptocsv;

public class Pessoa {

	public String nome;
	public int idade;
	public int cpf;
	
	@Override
	public String toString() {
		return "\"Nome" + "\",\"Idade" + "\",\"cpf";
	}
	
	public String toStringAt(){
		return nome  + "," + idade + "," + cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
}

