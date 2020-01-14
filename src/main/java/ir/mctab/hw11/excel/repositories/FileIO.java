package ir.mctab.hw11.excel.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface FileIO<V> {

    V getFile();

    void outPut(V v , Path path) throws Exception;
}
