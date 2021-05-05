package hu.dlaszlo.natura2remek.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private Map<String, String> fokonyviSzamok = new LinkedHashMap<>();

    private String naturaDir = null;

    private String remekDir = null;

    @PostConstruct
    public void init() {
        LOGGER.info("Beállítások betöltése: kontir.yml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        InputStream is = ConfigServiceImpl.class.getResourceAsStream("/kontir.yml");
        try (Reader in = new InputStreamReader(is, Charset.forName("UTF-8"))) {
            JsonNode rootNode = mapper.readTree(in);

            JsonNode naturaDirNode = rootNode.get("NaturaSoft könyvtár");
            if (naturaDirNode != null) {
                naturaDir = naturaDirNode.asText(null);
                LOGGER.info("NaturaSoft könyvtár: {}", naturaDir);
            }

            JsonNode remekDirNode = rootNode.get("Remek könyvtár");
            if (remekDirNode != null) {
                remekDir = remekDirNode.asText(null);
                LOGGER.info("Remek könyvtár: {}", remekDir);
            }

            JsonNode termekListNode = rootNode.get("Termékek");
            LOGGER.info("Termékek betöltése");
            for (JsonNode termekNode : termekListNode) {
                String fokonyviSzam = termekNode.get("Főkönyvi szám").asText();
                JsonNode cikkszamokNode = termekNode.get("Cikkszámok");
                List<String> cikkszamList = new ArrayList<>();
                for (JsonNode cikkszamNode : cikkszamokNode) {
                    String cikkszam = cikkszamNode.asText();
                    cikkszamList.add(cikkszam);
                    fokonyviSzamok.put(cikkszam, fokonyviSzam);
                }
                LOGGER.info("Főkönyvi szám: {}, Cikkszámok: {}",
                        fokonyviSzam,
                        cikkszamList.stream().collect(Collectors.joining(", ")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("A beállítások betöltése végetért.");
    }

    /**
     * @return Főkönyvi számok szótár
     */
    public Map<String, String> getFokonyviSzamok() {
        return Collections.unmodifiableMap(fokonyviSzamok);
    }

    /**
     * @return Natura default könyvtár
     */
    public String getNaturaDir() {
        return naturaDir;
    }

    /**
     * @return Remek default könyvtár
     */
    public String getRemekDir() {
        return remekDir;
    }

}
