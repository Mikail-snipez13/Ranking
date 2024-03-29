package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Page;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Repository.PageRepository;
import mikail.Ranking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageController pageController;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index.htm");
        if (pageRepository.existsById("views")) {
            Page page = pageRepository.getById("views");
            page.increaseViews();
            pageRepository.save(page);
        }
        return view;
    }

    @RequestMapping(value = "/response", method = RequestMethod.GET)
    public ModelAndView response() {
        ModelAndView view = new ModelAndView();
        view.setViewName("thanks.htm");
        return view;
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {
        ModelAndView view = new ModelAndView();
        view.setViewName("failed.htm");
        return view;
    }

    @RequestMapping(value = "/used", method = RequestMethod.GET)
    public ModelAndView used() {
        ModelAndView view = new ModelAndView();
        view.setViewName("used.htm");
        return view;
    }

    @RequestMapping(value = "/privacy", method = RequestMethod.GET)
    public ModelAndView privacy() {
        ModelAndView view = new ModelAndView();
        view.setViewName("privacy.htm");
        return view;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public ModelAndView ranking() {
        ModelAndView view = new ModelAndView();
        view.setViewName("ranking.htm");
        if (pageRepository.existsById("ranking_views")) {
            Page page = pageRepository.getById("ranking_views");
            page.increaseViews();
            pageRepository.save(page);
        }
        return view;
    }
}
