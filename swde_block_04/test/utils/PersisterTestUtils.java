package utils;

import ch.hslu.informatik.swde.vereinverwaltung.persister.api.Persister;
import ch.hslu.informatik.swde.vereinverwaltung.persister.ser.PersisterSer;
import ch.hslu.informatik.swde.vereinverwaltung.persister.txt.PersisterTxt;

import java.io.File;
import java.util.UUID;

public final class PersisterTestUtils {

    private static Persister p;

    private PersisterTestUtils(){}

    public static Persister create_PersisterTxt() {
        try {
            File tmpTxt = File.createTempFile("test", "txt");
            tmpTxt.deleteOnExit();
            p = new PersisterTxt(tmpTxt);
        } catch (Exception msg){
            System.out.println("Exception occured: " + msg);
        }
        return p;
    }

    public static Persister create_PersisterSer() throws Exception {
        return new PersisterSer(createTmpFile());
    }

    public static File createTmpFile () {
        UUID testId = UUID.randomUUID();
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        File file = new File(tempDirectoryPath + File.separator + testId);

        file.deleteOnExit();

        if (file.exists()) {
            file.delete();
        }
        return file;
    }
}
