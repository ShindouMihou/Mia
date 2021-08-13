package pw.mihou.mia.core;

import pw.mihou.mia.Mia;
import pw.mihou.mia.api.LocalMia;

import java.io.File;
import java.util.Map;

public class MiaImpl implements Mia {

    private final Map<String, LocalMia> locales;

    /**
     * Creates a customized Mia instance with the
     * directory where Mia will fetch being a customized instance.
     *
     * @param path The directory where Mia will fetch from.
     */
    public MiaImpl(String path) {
        this.locales = new MiaParser(new File(path)).parse();
    }

    /**
     * Creates a new Mia instance with the default
     * setting which fetches all the locales on the <code>locales</code> folder.
     */
    public MiaImpl() {
        this("locales");
    }

    @Override
    public Map<String, LocalMia> getAllLocale() {
        return locales;
    }
}
