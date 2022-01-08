package org.ikropachev.voting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//https://stackoverflow.com/questions/479565/how-do-you-define-a-class-of-constants-in-java
public final class Constants {
    public static final int NOT_FOUND = 0;
    public static final int START_SEQ = 100000;
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 4;
    public static final int USER_ID = START_SEQ + 5;
    public static final int MENU1_ID = START_SEQ + 8;
    public static final int DISH1_ID = START_SEQ + 14;
    public static final int VOTE1_ID = START_SEQ + 23;

    //https://stackoverflow.com/questions/8746084/string-to-localdate
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final LocalDate DATE = LocalDate.parse("2021-12-03", PATTERN);

    //String constants is used only like API examples
    public static final String RESTAURANT1_ID_STR = "100000";
    public static final String USER_ID_STR = "100005";
    public static final String USER_FOR_DELETE_ID_STR = "100006";
    public static final String USER_FOR_UPDATE_ID_STR = "100007";
    public static final String MENU1_ID_STR = "100008";
    public static final String DISH1_ID_STR = "100014";
    public static final String VOTE_ID_STR = "100027";
    public static final String DATE_STR = "2021-12-03";
    public static final String DISH_LIST_STR = "[\n{\n\"id\": \"null\",\n\"name\": \"dish1\",\n\"price\": 10\n},\n" +
            "    {\n\"id\": \"null\",\n\"name\": \"dish2\",\n\"price\": 20\n}\n]";
}

