package lista1;
import java.util.Scanner;
public class exercicios {
	
	public static Scanner entrada = new Scanner(System.in);
	public static int A;
	public static int B;
	
	public static void inserirValores() {
		System.out.println("Inserir A e B respectivamente:\n");
		A = entrada.nextInt();
		B = entrada.nextInt();
	}
	public static void exercicio1() {
		inserirValores();
		if(A>10) {
			System.out.println("A > 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("numero não válido.");
		}
	}
	public static void exercicio2() {
		inserirValores();
		if(A<10) {
			System.out.println("A < 10\n");
		}else if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else{
			System.out.println("numero não válido\n");
		}
	}
	public static void exercicio3() {
		inserirValores();
		if(A==10) {
			System.out.println("A == 10\n");
		}
		if(A+B==20) {
			System.out.println("A+B == 20\n");
		}
		if(B==20) {
			System.out.println("B == 10\n");
		}
	}
	public static void exercicio4() {
		inserirValores();
		if(A<10||A+B==20) {
			System.out.println("numero válido\n");
		}else if(A==B){
			System.out.println("A é igual B\n A e B são diferentes de 10\n");
		}else {
			System.out.println("numero inválido.");
		}
	}
	public static void exercicio5() {
		inserirValores();
		if(A>10||A+B==20) {
			System.out.println("numero válido\n");
		}else {
			System.out.println("numero não válido\n");
		}
	}
	
	public static void exercicio6() {
		inserirValores();
		if(A>10) {
			System.out.println("A > 10\n");
		}else{
			System.out.println("A <= 10\n");
		}
		
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("A + B != 20\n");
		}
	}
	public static void exercicio7() {
		inserirValores();
		if(A>10||A+B==20) {
			System.out.println("numeros válidos\n");
		}else {
			System.out.println("numero não válido\n");
		}
	}
	public static void exercicio8() {
		inserirValores();
		if(A>10) {
			System.out.println("A > 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("numero não valido\n");
		}
	}
	public static void exercicio9() {
		inserirValores();
		if(A>10&&A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("numero não valido\n");
		}
	}
	public static void exercicio10() {
		inserirValores();
		if(!(A>10)) {
			System.out.println("numero menor que 10\n");
		}
		if(A+B==20) {
			System.out.println("numero diferente de 20\n");
		}
	}
	
	public static void exercicio11() {
		inserirValores();
		if(!(A>10)) {
			if(A+B==20) {
				System.out.println("A + B == 20\n");
			}else {
				System.out.println("numero não válido\n");
			}
		}
	}
	public static void exercicio12() {
		inserirValores();
		if(A>10) {
			System.out.println("A>10\n");
		}else if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("numeros não valido\n");
		}
		System.out.println("Sejam bem-vindos à disciplina de Técnicas de Programação");
	}
	public static void exercicio13() {
		if(A>10) {
			System.out.println("A > 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}
		if(A<=10&&A+B==20) {
			System.out.println("numeros não válidos\n");
		}
	}
	public static void exercicio14() {
		inserirValores();
		if(A>10) {
			System.out.println("A > 10\n");
			if(A+B==20) {
				System.out.println("A + B == 20\n");
			}else {
				System.out.println("numero não valido\n");
			}
		}
	}
	public static void exercicio15() {
		inserirValores();
		if(A<10) {
			System.out.println("A < 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}
		if(A>=10&&A+B!=20) {
			System.out.println("numero não valido\n");
		}
	}

	public static void exercicio16() {
		inserirValores();
		if(A==10) {
			System.out.println("A == 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}
		if(B==10) {
			System.out.println("B == 10\n");
		}
	}
	public static void exercicio17() {
		inserirValores();
		if(A>10||A+B==20) {
			System.out.println("numero válido.");
		}else if(A==B) {
			System.out.println("A é igual a B.");
		}else if(A!=10&&B!=10&&A<10){
			System.out.println("A é menor que 10");
		}else {
			System.out.println("numero não valido");
		}
	}
	public static void exercicio18() {
		inserirValores();
		if(A>10||A+B==20) {
			System.out.println("numero válido\n");
		}else {
			System.out.println("numero não válido\n");
		}
	}
	public static void exercicio19() {
		inserirValores();
		if(A>10) {
			System.out.println("A > 10\n");
		}else {
			System.out.println("A <= 10\n");
		}
		if(A+B==20) {
			System.out.println("A + B == 20\n");
		}else {
			System.out.println("A + B != 20\n");
		}
	}
	public static void exercicio20() {
		inserirValores();
		if(A>10||A+B==20) {
			System.out.println("numeros válidos\n");
		}else {
			System.out.println("numero não válido\n");
		}
		System.out.println("Sejam bem-vindos à disciplina de Técnicas de Programação");
	}
	
	public static void Main(String args[]) {
		//exercicioX();
	}
}
