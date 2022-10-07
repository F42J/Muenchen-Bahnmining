package config;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private Map<String, Session> session = new HashMap<>();

    public Map<String, Session> getSession() {
        return session;
    }

    public void setSession(Map<String, Session> session) {
        this.session = session;
    }
}
