public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,10);
        r1.print();
        r1.drag(10, 20);
        System.out.println("e tem area: "+ r1.area() + " e nova posicao: " + r1.x + " e " + r1.y + "\n");
        //Incluindo outros testes
        Rect r2 = new Rect(20,50, 40,4);
        r2.print();
        r2.drag(30, 2);
        System.out.println("e tem area: "+ r2.area() + " e nova posicao: " + r2.x + " e " + r2.y + "\n");

        Rect r3 = new Rect(5,10, 10, 2);
        r3.print();
        r3.drag(12, 40);
        System.out.println("e tem area: "+ r3.area() + " e nova posicao: " + r3.x + " e " + r3.y + "\n");
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d) ",
            this.w, this.h, this.x, this.y);
    }
    int area(){
        int resultArea = this.w * this.h;
        return resultArea;
    }
    //Objeto sendo arrastado
    void drag(int x, int y){    
        this.x += x;
        this.y += y;
    }
}



