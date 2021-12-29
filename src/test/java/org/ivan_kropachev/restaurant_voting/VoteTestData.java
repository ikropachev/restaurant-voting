package org.ivan_kropachev.restaurant_voting;

import org.ivan_kropachev.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.ivan_kropachev.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    //https://stackoverflow.com/questions/8746084/string-to-localdate
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final int NOT_FOUND = 100000;
    public static final int VOTE1_ID = START_SEQ + 23;
    public static final int USER_ID = 100005;
    public static final int RESTAURANT_ID = 100001;

    public static final Vote vote1 = new Vote(VOTE1_ID, 100005, 100001, LocalDate.parse("2021-12-02", PATTERN));
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, 100005, 100002, LocalDate.parse("2021-12-03", PATTERN));
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, 100006, 100000, LocalDate.parse("2021-11-16", PATTERN));
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, 100006, 100003, LocalDate.parse("2021-11-17", PATTERN));
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, 100007, 100002, LocalDate.parse("2021-11-17", PATTERN));
    public static final Vote vote6 = new Vote(VOTE1_ID + 5, 100006, 100001, LocalDate.now());
    public static final Vote vote7 = new Vote(VOTE1_ID + 6, 100007, 100002, LocalDate.now());

    //Votes must be sorted by date DESC
    public static final List<Vote> votes = List.of(vote6, vote7, vote2, vote1, vote4, vote5, vote3);

    public static Vote getNew() {
        return new Vote(null, USER_ID, RESTAURANT_ID, LocalDate.now());
    }
}
