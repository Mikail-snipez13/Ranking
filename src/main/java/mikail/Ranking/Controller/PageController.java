package mikail.Ranking.Controller;

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
        if (pageRepository.existsById(1L)) {
            int[] i = new int[1];
            i[0] = pageRepository.getById(1L).getViews();
            return i;
        }
        else {
            pageRepository.save(new Page(1L, 1));
            int[] i = new int[1];
            i[0] = 1;
            return i;
        }
    }

}
