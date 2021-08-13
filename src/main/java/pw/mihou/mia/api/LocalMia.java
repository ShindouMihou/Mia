package pw.mihou.mia.api;

import pw.mihou.mia.exception.ItemNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface LocalMia {

    /**
     * Gets the item inside the category for this
     * localization, this runs on O(1) performance compared to
     * its counterpart which has to search through all the list.
     *
     * @param category The category of the item.
     * @param item The item to fetch.
     * @return The result of the item.
     */
    default String get(String category, String item) {
        return getCategories().get(category.toLowerCase()).get(item.toLowerCase());
    }

    /**
     * This searches through all items on the list and
     * fetches the item specified, contrary to {@link LocalMia#get(String)},
     * this does not run on O(1) performance.
     *
     * @param item The item to fetch.
     * @return The result of the item.
     */
    default String get(String item) {
        return getCategories().entrySet()
                .stream()
                .filter(entry -> entry.getValue().containsKey(item))
                .findFirst()
                .orElseThrow(() -> new ItemNotFound(item, getLocalization()))
                .getValue().get(item);
    }

    /**
     * Gets the localization of this LocalMia.
     *
     * @return The localization code (this is usually the file name).
     */
    String getLocalization();

    /**
     * Gets all the values inside X category for this localization of MIa.
     *
     * @param category The category to fetch.
     * @return A list of categories.
     */
    default List<String> getCategory(String category) {
        return new ArrayList<>(getCategories().get(category.toLowerCase()).values());
    }

    /**
     * Fetches all the categories that are registered in this localization
     * of {@link LocalMia} and returns them in this format: {@link String} (Category) = {@link String} (Item Name), {@link String} value.
     *
     * @return All the categories inside this {@link LocalMia} under
     * the format: {@link String} (Category) = {@link String} (Item Name), {@link String} value.
     */
    Map<String, Map<String, String>> getCategories();

}
