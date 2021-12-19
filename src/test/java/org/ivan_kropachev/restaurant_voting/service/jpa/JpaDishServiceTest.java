package org.ivan_kropachev.restaurant_voting.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import org.ivan_kropachev.restaurant_voting.service.AbstractDishServiceTest;

import static org.ivan_kropachev.restaurant_voting.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaDishServiceTest extends AbstractDishServiceTest {
}
