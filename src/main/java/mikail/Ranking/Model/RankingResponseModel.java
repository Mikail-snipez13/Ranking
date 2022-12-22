package mikail.Ranking.Model;

import lombok.AllArgsConstructor;
import mikail.Ranking.Entity.Ranking;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
public class RankingResponseModel {
    private Long id;
    private String title;
    private int votes;
    private Long userId;
    private int questions;

    public RankingResponseModel(Ranking ranking, int questions) {
        this.id = ranking.getId();
        this.title = ranking.getTitle();
        this.votes = ranking.getVotes();
        this.userId = ranking.getUserId();
        this.questions = questions;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.append("id", id);
        json.append("title", title);
        json.append("votes", votes);
        json.append("userId", userId);
        json.append("questions", questions);
        return json;
    }
}
