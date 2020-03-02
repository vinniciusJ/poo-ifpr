import java.util.ArrayList;

public class Construtor {
	String nome;
	int idade;
	static ArrayList<Construtor> listas = new ArrayList<Construtor>();
	
	public Construtor(){
		System.out.println("Objeto criado");
	}
	public Construtor(Construtor c){
		c.nome = "Marcos";
		c.idade = 10;
	}
	public Construtor(String nomeParam, int idadeParam){
		this.nome = nomeParam;
		this.idade = idadeParam;
	}
	public static void main(String[] args){
		Construtor c = new Construtor();
		c.nome = "Fernanda";
		c.idade = 15;
		
		Construtor c1 = new Construtor(c);
		Construtor c2 = new Construtor("João", 40);
		
		listas.add(c);
		listas.add(c1);
		listas.add(c2);
		
		for(int i = 0; i < listas.size(); i++){
			System.out.println("O objeto " + listas.get(i) + ": \n Nome: " + listas.get(i).nome + " \n Idade: " + listas.get(i).idade);
		}
		
	}
}
