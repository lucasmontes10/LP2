package exercicio;
import java.util.ArrayList;

public class ArrayStack implements IStackable{
    ArrayList<Integer> lista = new ArrayList<Integer>(); 
    private int index;
    //Utilizando os metodos de array, vamos implementar os metodos de IStackable
    public ArrayStack(){
        this.index = 0;
    }
    public int size(){
        return lista.size();
    }

    public void push(int v){
        lista.add(this.index, v);
        this.index += 1; 
    }

    public int pop(){
        //desimpilha o inteiro e retorna ele
        int num = lista.get(this.index); // Salvando o elemento para retorno
        lista.remove(this.index); // Removendo o elemento da lista 
        this.index -= 1;
        return num;
    }
    
    
}
