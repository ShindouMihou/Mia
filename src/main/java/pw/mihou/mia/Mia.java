package pw.mihou.mia;

import pw.mihou.mia.api.LocalMia;
import pw.mihou.mia.core.MiaImpl;

import java.util.List;
import java.util.Map;

public interface Mia {

    /**
     * Gets the locale of Mia that can be used to
     * fetch the items from the <code>.mia</code> files.
     *
     * @param locale The locale to fetch.
     * @return The locale which you can query localizations.
     */
    default LocalMia getLocale(String locale) {
        return getAllLocale().get(locale.toLowerCase());
    }

    /**
     * Gets all the locale that is registered with Mia.
     *
     * @return All the locales registered, in format: {@link String} - {@link LocalMia}.
     */
    Map<String, LocalMia> getAllLocale();

    /**
     * Creates a customized Mia instance with the
     * directory where Mia will fetch being a customized instance.
     *
     * @param path The directory where Mia will fetch from.
     * @return A new Mia instance.
     */
    static Mia from(String path) {
        return new MiaImpl(path);
    }

    /**
     * Creates a new Mia instance with the default
     * setting which fetches all the locales on the <code>locales</code> folder.
     *
     * @return A new Mia instance.
     */
    static Mia ofDefault() {
        return new MiaImpl();
    }

}
