package advanced.tryWithRessouces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class DebugBufferedReader extends BufferedReader {
    private boolean closed = false;

    public DebugBufferedReader(Reader in) {
        super(in);
    }

    @Override
    public void close() throws IOException {
        super.close();
        closed = true;
        System.out.println("BufferedReader was closed.");
    }

    public boolean isClosed() {
        return closed;
    }

}
