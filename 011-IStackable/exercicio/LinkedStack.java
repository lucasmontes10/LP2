package exercicio;
import java.util.LinkedList;

public class LinkedStack implements IStackable{

    //usando a mesma ideia do ArrayStack
    LinkedList<Integer> lista = new LinkedList<Integer>();
    private int index;

    public LinkedStack(){
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
