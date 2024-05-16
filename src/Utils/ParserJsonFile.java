package Utils;

import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ParserJsonFile {

    public static JSONArray parseJsonFile(Path path) throws ParseException {
        StringBuilder sb = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonData = (JSONArray) parser.parse(String.valueOf(sb));

        return jsonData;
    }

}
