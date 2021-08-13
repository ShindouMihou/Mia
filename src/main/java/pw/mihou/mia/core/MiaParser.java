package pw.mihou.mia.core;

import pw.mihou.mia.api.LocalMia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class MiaParser {

    private final File path;
    private static final FileFilter filter = pathname -> pathname.isFile() && pathname.canRead() && pathname.getName().endsWith(".mia");

    public MiaParser(File path) {
        this.path = path;
    }

    public Map<String, LocalMia> parse() {
        Map<String, LocalMia> map = new HashMap<>();
        Arrays.stream(Objects.requireNonNull(path.listFiles(filter)))
                .forEachOrdered(file -> {
                    try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                        AtomicReference<String> currentCategory = new AtomicReference<>("");

                        // This is to keep the file name without an extension.
                        String fileName = file.getName();

                        if(fileName.contains(".")) {
                            fileName = fileName.substring(0, fileName.lastIndexOf("."));
                        }

                        AtomicReference<String> name = new AtomicReference<>(fileName);

                        Map<String, Map<String, String>> categories = new HashMap<>();
                        reader.lines().forEachOrdered(s -> {
                            // Special syntax either category or customized locale name.
                            if(s.startsWith("#")) {

                                if(s.startsWith("#(") && s.endsWith(")")) {
                                    name.set(s.substring("#(".length(), s.lastIndexOf(")")));
                                }

                                if(s.startsWith("#[") && s.endsWith("]")) {
                                    currentCategory.set(s.substring("#[".length(), s.lastIndexOf("]")));
                                }

                            } else {
                                if(s.contains("=")) {
                                    String key = s.substring(0, s.indexOf("="));
                                    String value = s.substring(s.indexOf("=") + 1);

                                    String category = currentCategory.get().isEmpty() ? "no_category" : currentCategory.get();
                                    if(!categories.containsKey(category))
                                        categories.put(category, new HashMap<>());

                                    categories.get(category).put(key, value);
                                }
                            }
                        });

                        map.put(name.get(), new LocalMiaImpl(name.get(), categories));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return map;
    }

}
