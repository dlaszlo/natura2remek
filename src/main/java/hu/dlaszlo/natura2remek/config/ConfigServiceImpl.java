package hu.dlaszlo.natura2remek.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class ConfigServiceImpl implements ConfigService
{
    private final static String CONFIG_FILE = System.getProperty("config.file");

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private Map<String, String> fokonyviSzamok = new LinkedHashMap<>();

    private String naturaDir = null;

    private String remekDir = null;

    @Inject
    public void init()
    {
        LOGGER.info("Beállítások betöltése: {}", CONFIG_FILE);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Path p = Paths.get(CONFIG_FILE);
        try (Reader in = Files.newBufferedReader(p, Charset.forName("UTF-8")))
        {
            JsonNode rootNode = mapper.readTree(in);

            JsonNode naturaDirNode = rootNode.get("NaturaSoft könyvtár");
            if (naturaDirNode != null)
            {
                naturaDir = naturaDirNode.asText(null);
                LOGGER.info("NaturaSoft könyvtár: {}", naturaDir);
            }

            JsonNode remekDirNode = rootNode.get("Remek könyvtár");
            if (remekDirNode != null)
            {
                remekDir = remekDirNode.asText(null);
                LOGGER.info("Remek könyvtár: {}", remekDir);
            }

            JsonNode termekListNode = rootNode.get("Termékek");
            LOGGER.info("Termékek betöltése");
            for (JsonNode termekNode : termekListNode)
            {
                String fokonyviSzam = termekNode.get("Főkönyvi szám").asText();
                JsonNode cikkszamokNode = termekNode.get("Cikkszámok");
                List<String> cikkszamList = new ArrayList<>();
                for (JsonNode cikkszamNode : cikkszamokNode)
                {
                    String cikkszam = cikkszamNode.asText();
                    cikkszamList.add(cikkszam);
                    fokonyviSzamok.put(cikkszam, fokonyviSzam);
                }
                LOGGER.info("Főkönyvi szám: {}, Cikkszámok: {}",
                            fokonyviSzam,
                            cikkszamList.stream().collect(Collectors.joining(", ")));
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        LOGGER.info("A beállítások betöltése végetért.");
    }

    /**
     * @return Főkönyvi számok szótár
     */
    public Map<String, String> getFokonyviSzamok()
    {
        return Collections.unmodifiableMap(fokonyviSzamok);
    }

    /**
     * @return Natura default könyvtár
     */
    public String getNaturaDir()
    {
        return naturaDir;
    }

    /**
     * @return Remek default könyvtár
     */
    public String getRemekDir()
    {
        return remekDir;
    }

}
