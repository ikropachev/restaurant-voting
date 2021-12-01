package org.ivan_kropachev.restaurant_voting.web.vote;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminVoteController extends AbstractVoteController {
    @Override
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    public Vote get(int id) {
        return super.get(id);
    }

    @Override
    public Vote create(Vote vote) {
        return super.create(vote);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Vote vote, int id) {
        super.update(vote, id);
    }
}
