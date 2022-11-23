package mikail.Ranking.Controller;


import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Page;
import mikail.Ranking.Repository.PageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/page")
public class PageController {

    private final PageRepository pageRepository;

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    public int[] getCount() {
        if (pageRepository.existsById("views")) {
            int[] i = new int[1];
            i[0] = pageRepository.getById("views").getViews();
            return i;
        }
        else {
            pageRepository.save(new Page("views", 1));
            int[] i = new int[1];
            i[0] = 1;
            return i;
        }
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public int[] getRankingCount() {
        if (pageRepository.existsById("ranking_views")) {
            int[] i = new int[1];
            i[0] = pageRepository.getById("ranking_views").getViews();
            return i;
        }
        else {
            pageRepository.save(new Page("ranking_views", 1));
            int[] i = new int[1];
            i[0] = 1;
            return i;
        }
    }

    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    public int[] getRequests() {
        int[] i = new int[1];
        if (pageRepository.existsById("votes")) {
            i[0] = pageRepository.getById("votes").getViews();
        }
        else {
            pageRepository.save(new Page("votes", 0));
        }
        return i;
    }

}
