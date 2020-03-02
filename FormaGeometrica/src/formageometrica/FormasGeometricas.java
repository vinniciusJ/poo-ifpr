package formageometrica;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FormasGeometricas {
    
    int tipo;
    double area, base, altura, lado1, lado2, raio;
    static ArrayList<FormasGeometricas> triangulos = new ArrayList<FormasGeometricas>();
    static ArrayList<FormasGeometricas> quadrados = new ArrayList<FormasGeometricas>();
    static ArrayList<FormasGeometricas> circulos = new ArrayList<FormasGeometricas>();
    
    public static DecimalFormat formatarNumero(){
        return new DecimalFormat("#,###.0000");
    }
    
    public static double calcularAreaTriangulo(double base, double altura){
        return (base * altura) / 2;
    }
    
    public static double calcularAreaQuadrado(double l1, double l2){
        return l1 * l2;
    }
    
    public static double calcularAreaCirculo(double raio){
        return Math.PI * Math.pow(raio, 2);
    }
    
    public static double lerDados(String medida){
        return Double.parseDouble(JOptionPane.showInputDialog("Valor da " + medida + ": "));
    }
    
    public static boolean verificarTipo(int tipo){
        FormasGeometricas figura = new FormasGeometricas();
        figura.tipo = tipo;
        if(tipo == 1 || tipo == 2 || tipo == 3 || tipo == 4){
            switch(tipo){
                case 1:
                    figura.base = lerDados("Base");
                    figura.altura = lerDados("Altura");
                    figura.area = calcularAreaTriangulo(figura.base, figura.altura);
                    break;
                case 2:
                    figura.lado1 = lerDados("Lado A");
                    figura.lado2 = lerDados("Lado B");
                    figura.area = calcularAreaQuadrado(figura.lado1, figura.lado2);
                    break;
                case 3:
                    figura.raio = lerDados("Raio");
                    figura.area = calcularAreaCirculo(figura.raio);
                    break;
                case 4:
                    imprimirAtributos();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                    lerOpcao();
                    break;
                	     	
            }
            armazenarFormaGeometrica(figura);
            return true;
        }
        else if(tipo == 0) {
        	return true;
        }
        return false;
    }
    public static void imprimirAtributos(){
    	if(!triangulos.isEmpty()) {
    		String conteudo = "";
    		for(int i = 0; i < triangulos.size(); i++) {
    			conteudo += "A Área do " + (i + 1) + "° triangulo =>" + "( " + triangulos.get(i).base + " + " + triangulos.get(i).altura + " )/2 = "  + formatarNumero().format(triangulos.get(i).area) + "\n"; 
    		}
    		JOptionPane.showMessageDialog(null,"Areas do triangulo criados: \n" + conteudo);
    	}
    	if(!quadrados.isEmpty()) {
    		String conteudo = "";
    		for(int i = 0; i < quadrados.size(); i++) {
    			conteudo += "A Área do " + (i + 1) + "° quadrado/retangulo => " + quadrados.get(i).lado1 + "*" + quadrados.get(i).lado2 + " = " + formatarNumero().format(quadrados.get(i).area) + "\n";
    		}
    		JOptionPane.showMessageDialog(null,"Areas dos quadrados/retangulos criados: \n" + conteudo);
    	}
    	if(!circulos.isEmpty()) {
    		String conteudo = "";
    		for(int i = 0; i < circulos.size(); i++) {
    			conteudo += "A Área do " + (i +1) + "° circulo => " + circulos.get(i).raio + " * " + formatarNumero().format(Math.PI) + " = " + formatarNumero().format(circulos.get(i).area) + "\n";
    		}
    		JOptionPane.showMessageDialog(null,"Areas dos circulos criados: \n" + conteudo);
    	}
    }
    public static void lerOpcao(){
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Informe o código \n [0]Sair \n [1] Triangulo \n [2] Quadrado/Retangulo \n [3] Circulo \n [4] Imprimir Objetos"));
        verificarTipo(opcao);
    }
    
    public static void armazenarFormaGeometrica(FormasGeometricas figura){
        switch(figura.tipo){
            case 1:
                triangulos.add(figura);
                break;
            case 2:
                quadrados.add(figura);
                break;
            case 3:
                circulos.add(figura);
        }
    }
    
    public static void main(String[] args) {
        lerOpcao();
    }
    
}
