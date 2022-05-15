package save;

import java.io.*;
import java.util.*;

import figurePack.Figures;

public interface Isave {
    public boolean salvarArquivo(String filePath, ArrayList<Figures> figList, int width, int height);
}
