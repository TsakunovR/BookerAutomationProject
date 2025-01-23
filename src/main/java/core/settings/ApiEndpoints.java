package core.settings;

public enum ApiEndpoints {
    PING("/ping"),
    BOOKING("/booking"), // Новый эндпоинт /booking
    AUTH("/auth");

    private final String path;

    ApiEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
