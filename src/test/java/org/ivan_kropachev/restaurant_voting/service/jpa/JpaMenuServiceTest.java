package org.ivan_kropachev.restaurant_voting.service.jpa;

import org.ivan_kropachev.restaurant_voting.service.AbstractMenuServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static org.ivan_kropachev.restaurant_voting.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaMenuServiceTest extends AbstractMenuServiceTest {
}
