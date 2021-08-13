package pw.mihou.mia.core;

import pw.mihou.mia.api.LocalMia;

import java.util.Map;

public class LocalMiaImpl implements LocalMia {

    private final String localization;
    private final Map<String, Map<String, String>> categories;

    /**
     * Creates a new Localized Mia instance where you can fetch specific categories
     * and items from the localization.
     *
     * @param localization The localization to use.
     * @param categories The categories to use.
     */
    public LocalMiaImpl(String localization, Map<String, Map<String, String>> categories) {
        this.localization = localization;
        this.categories = categories;
    }

    @Override
    public String getLocalization() {
        return localization;
    }

    @Override
    public Map<String, Map<String, String>> getCategories() {
        return categories;
    }
}
