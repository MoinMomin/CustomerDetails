package sunbase.portal.api.mapper;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public enum SearchByEnum {
    ID("id", "id", 1),
    FIRSTNAME("firstName", "firstName", 2),
    LASTNAME("lastName", "lastName", 3),
    STREET("street", "street", 4),
    ADDRESS("address", "address", 5),
    CITY("city", "city", 6),
    STATE("state", "state", 7),
    EMAIL("email", "email", 8),
    PHONE("phone", "phone", 9);

    private String text;
    private String displayName;
    private int rank;

    SearchByEnum(String text, String displayName, int rank) {
        this.text = text;
        this.displayName = displayName;
        this.rank = rank;
    }

    @JsonIgnore
    public String getText() {
        return this.text;
    }

    @JsonCreator
    public static SearchByEnum fromString(String text) {
        for (SearchByEnum b : SearchByEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getRank() {
        return rank;
    }

    public String getValue() {
        return text;
    }
}



