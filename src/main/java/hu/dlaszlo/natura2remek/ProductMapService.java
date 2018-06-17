package hu.dlaszlo.natura2remek;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductMapService
{
    public static Map<String, String> getDictionary(String filename)
    {
        Map<String, String> fokonyviSzamok = new LinkedHashMap<>();

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Path p = Paths.get(filename);
        try (Reader in = Files.newBufferedReader(p, Charset.defaultCharset()))
        {
            JsonNode rootNode = mapper.readTree(in);
            JsonNode termekListNode = rootNode.get("Termékek");
            for (JsonNode termekNode : termekListNode )
            {
                String fokonyviSzam = termekNode.get("Főkönyvi szám").asText();
                JsonNode cikkszamokNode = termekNode.get("Cikkszámok");
                for (JsonNode cikkszamNode : cikkszamokNode)
                {
                    String cikkszam = cikkszamNode.asText();
                    fokonyviSzamok.put(cikkszam, fokonyviSzam);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return fokonyviSzamok;
    }

    public static void main(String[] args) throws Exception
    {
        Map<String, String> fokonyviSzamok = getDictionary("C:\\Users\\dlasz\\IdeaProjects\\natura2remek\\src\\main\\resources\\kontir.yml");
        System.out.println(fokonyviSzamok);

    }


}
