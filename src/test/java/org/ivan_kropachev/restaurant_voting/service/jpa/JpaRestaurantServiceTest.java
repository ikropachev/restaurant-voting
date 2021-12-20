package org.ivan_kropachev.restaurant_voting.service.jpa;

import org.ivan_kropachev.restaurant_voting.service.AbstractRestaurantServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static org.ivan_kropachev.restaurant_voting.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
