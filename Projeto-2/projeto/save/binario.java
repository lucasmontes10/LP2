package save;
import ivisible.*;
import save.Isave;
import java.io.*;
import java.util.*;
import figurePack.*;
public class binario implements Isave {

    public boolean salvarArquivo(String filePath, ArrayList<Figures> figList) {
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(figList);
            o.flush();
            o.close();
            return true;
        } catch (Exception error) {
            System.out.println("Não foi possível salvar o projeto atual.\nError: " + error);
        }

        return false;
    }

    public ArrayList<Figures> carregarArquivo(String filePath) {
        try {
            FileInputStream f = new FileInputStream(filePath);
            ObjectInputStream o = new ObjectInputStream(f);
            Object obj = o.readObject();
            o.close();
            if (obj instanceof ArrayList) {
                ArrayList<Figures> test = (ArrayList<Figures>) obj;
                if (test.get(0) instanceof Figures)
                    return (ArrayList<Figures>) obj;
                else
                    throw new Exception("Error: o arquivo proj.bin não é um arquivo válido! O arquivo pode estar corrompido ou alterado.");
            }
            else
                throw new Exception("Error: o arquivo proj.bin não é um arquivo válido! O arquivo pode estar corrompido ou alterado.");
        } catch (Exception error) {
            System.out.println("Não foi possível carregar um projeto salvo.\nError: " + error);
        }

        return null;
    }

    public boolean salvarArquivo(String filePath, ArrayList<Figures> figList, int width, int height) {
        return salvarArquivo(filePath, figList);
    }
}
