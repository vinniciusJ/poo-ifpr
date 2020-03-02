import javax.swing.JOptionPane;

public class Eletrodomestico {
	
	boolean status;
	String cor, marca, tarefa;
	int voltagem;
	
	public Eletrodomestico(){
		status = false;
		cor = "Preta";
		marca = "Brastemp";
		voltagem = 220;
		tarefa = "Processar";
	}
	
	public void mudarStatus(){
		this.status = this.status ? false : true;
	}
	public void imprimirStatus(){
		System.out.println(this.status ? "Aparelho Ligado" : "Aparelho Desligado");
	}
	public void processarAlimento(String alimento){
		if(this.status){
			if(alimento != null){
				if(alimento.isEmpty()){
					System.out.println("Não há nenhum alimento dentro do aparelho");
				}
				else {
					System.out.println("O(A) " + alimento + " está sendo processado");
				}
			}
			else {
				
				System.out.println("Não há nenhum alimento dentro do aparelho");
			}
		}
		else {
			System.out.println("Não é possível processar o(a) " + alimento +", o aparelho está desligado");
		}
	}
	public static void imprimirAtributos(Eletrodomestico e){
		JOptionPane.showMessageDialog(
				null,
				" Atributo do Eletrodomestico " + e
				+ "\n Marca: " + e.marca 
				+ "\n Tarefa: " + e.tarefa 
				+ "\n Cor: " + e.cor
				+ "\n Voltagem: " + e.voltagem + " volts"
				+ "\n Ligado/Desligado: " + (e.status ? " Ligado" : " Desligado")
		);

		
	}
	
	public static void main(String[] agrs){
		Eletrodomestico e = new Eletrodomestico();
		//e.mudarStatus();
		//e.processarAlimento();
		imprimirAtributos(e);
	}
}
