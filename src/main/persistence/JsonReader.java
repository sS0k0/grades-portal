package persistence;

import model.Account;
import model.Course;
import model.Professor;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Persistence functionality and methods are implemented from JsonSerializationDemo. Link below :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads accounts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Account> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccounts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses accounts from JSON object and returns it
    private List<Account> parseAccounts(JSONObject jsonObject) {
        List<Account> accounts = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            Account account = createAccount(nextThingy);
            accounts.add(account);
        }
        return accounts;
    }

    // EFFECTS : Creates account instance from JSON object
    private Account createAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String role = jsonObject.getString("role");
        if (role.equals("student")) {
            return new Student(name, password);
        } else {
            return new Professor(name, password);
        }
    }

}
