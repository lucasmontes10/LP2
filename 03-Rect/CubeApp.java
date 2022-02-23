import java.util.Scanner;

public class CubeApp {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe o comprimento do cubo:\n");
        float c = ler.nextFloat();
        System.out.printf("Informe a largura do cubo:\n");
        float l = ler.nextFloat();
        System.out.printf("Informe a altura do cubo:\n");
        float a = ler.nextFloat();
		Cube c1 = new Cube(c, l, a);
        c1.print();
        ler.close();
	}
}

class Cube{
    float comprimento, largura, altura;
    float rotacao; // Grau de rotação no próprio eixo
    int r,g, b; //RGB para indicar a cor do cubo
    Cube(float c, float l, float a){
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
    }
    void print(){
        System.out.format("Desenhando um cubo com %.2f de comprimento, %.2f de largura e %.2f de altura\n", this.comprimento, this.largura, this.altura);
    }
}