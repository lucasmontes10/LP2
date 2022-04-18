//CONTRATO

//"Autenticavel" (diretor, gerente, cliente)
//Metodo: autentica, esse metodo irá receber um inteiro que será a senha, e ele devolve
//um booleano permitindo ou não a entrada no sistema
//Metodo: opções, esse metodo retonará o conjunto de opções, ações para aquele determinado
//objeto

public interface autentic{
    boolean autenticar(int senha); 
    String menu();
}

//Exemplo de uso
if (usuario == "diretor"){
    if (metodo == "autenticar"){
        diretor.autenticar(senha);
    }else if (method == "menu"){
        diretor.menu();
    }
}

//CONTRATO

//"Comível" (salada, fruta, proteínas)
//Metodo: informacoes, retorna uma string com as informações nutricionais de um determinado alimento
//Metodo: cozinhar, retorna o modo de prepara do alimento comível, por exemplo: assado ou frito

public interface comivel{
    String informacoes();
    String cozinhar();
}

//Exemplo de uso
//Criar um array de alimentos

ArrayList<Alimentos> alimento = new ArrayList<Alimentos>();
//pegar o nome do alimento desejado
String alimentoDesejado = "Carne";
for (Alimentos alimento: ali){
	if(ali == alimentoDesejado){
		ali.informacoes();
		ali.cozinhar();
	}
}