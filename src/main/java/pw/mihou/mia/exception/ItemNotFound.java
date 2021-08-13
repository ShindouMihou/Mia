package pw.mihou.mia.exception;

public class ItemNotFound extends RuntimeException {

    public ItemNotFound(String item, String localization) {
        super("The item " + item + " was not found in " + localization + " localization!");
    }

}
