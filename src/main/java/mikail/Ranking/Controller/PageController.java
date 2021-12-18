package mikail.Ranking.Controller;

import ch.qos.logback.classic.spi.EventArgUtil;
import mikail.Ranking.Entity.Page;
import mikail.Ranking.Repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
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

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public int[] getRequests() {
        int[] i = new int[1];
        if (pageRepository.existsById("votes")) {
            i[0] = pageRepository.getById("votes").getViews();
            return i;
        }
        else {
            pageRepository.save(new Page("votes", 0));
            return i;
        }
    }

}
