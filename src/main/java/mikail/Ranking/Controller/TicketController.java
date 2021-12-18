package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Page;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Factory.TicketFactory;
import mikail.Ranking.Repository.PageRepository;
import mikail.Ranking.Repository.TeacherRepository;
import mikail.Ranking.Repository.TicketRepository;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    private final TicketFactory ticketFactory = new TicketFactory();

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/tickets") //tickets?mode=create
    public String tickets(
            @RequestParam(value = "mode", defaultValue = "") String mode,
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "joker", defaultValue = "") String joker,
            @RequestParam(value = "unprepared", defaultValue = "") String unprepared,
            @RequestParam(value = "late", defaultValue = "") String late,
            @RequestParam(value = "spoiled", defaultValue = "") String spoiled,
            @RequestParam(value = "party", defaultValue = "") String party,
            @RequestParam(value = "smart", defaultValue = "") String smart,
            @RequestParam(value = "beauty", defaultValue = "") String beauty,
            @RequestParam(value = "noMention", defaultValue = "") String noMention,
            @RequestParam(value = "noInTime", defaultValue = "") String noInTime,
            @RequestParam(value = "dishonorable", defaultValue = "") String dishonorable){

        //different modes
        switch (mode) {

            //create new Table
            case "create":
                Ticket ticket = ticketFactory.createTicket(ticketRepository);
                ticketRepository.save(ticket);
                return ticket.getId();

            //returns whether it is used
            case "status":
                if (ticketRepository.existsById(id)) {
                    return Boolean.toString(ticketRepository.getById(id).getUsed());
                }

            //use Ticket
            case "use":
                if (ticketRepository.existsById(id)) {
                    Ticket tick = ticketRepository.getById(id);
                    if (!tick.getUsed()) {
                        tick.setUsed(true);
                        ticketRepository.save(tick);

                        //update joker
                        if (teacherRepository.existsById(joker)) {
                            Teacher teacher = teacherRepository.getById(joker);
                            teacher.increaseBeauty();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(joker, 1, 0, 0, 0, 0, 0, 0, 0, 0,0));
                        }

                        //update unprepared
                        if (teacherRepository.existsById(unprepared)) {
                            Teacher teacher = teacherRepository.getById(unprepared);
                            teacher.increaseSmart();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(unprepared,0, 1, 0, 0, 0, 0, 0, 0, 0,0));
                        }

                        //update late
                        if (teacherRepository.existsById(late)) {
                            Teacher teacher = teacherRepository.getById(late);
                            teacher.increaseNice();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(late,0, 0, 1, 0, 0, 0, 0, 0, 0,0));
                        }

                        //update mad
                        if (teacherRepository.existsById(spoiled)) {
                            Teacher teacher = teacherRepository.getById(spoiled);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(spoiled,0, 0, 0, 1, 0, 0, 0, 0, 0,0));
                        }

                        //update party
                        if (teacherRepository.existsById(party)) {
                            Teacher teacher = teacherRepository.getById(party);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(party,0, 0, 0, 0, 1, 0, 0, 0, 0,0));
                        }

                        //update smart
                        if (teacherRepository.existsById(smart)) {
                            Teacher teacher = teacherRepository.getById(smart);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(smart,0, 0, 0, 0, 0, 1, 0, 0, 0,0));
                        }

                        //update beauty
                        if (teacherRepository.existsById(beauty)) {
                            Teacher teacher = teacherRepository.getById(beauty);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(beauty,0, 0, 0, 0, 0, 0, 1, 0, 0,0));
                        }

                        //update noMention
                        if (teacherRepository.existsById(noMention)) {
                            Teacher teacher = teacherRepository.getById(noMention);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(noMention,0, 0, 0, 0, 0, 0, 0, 1, 0,0));
                        }

                        //update noInTime
                        if (teacherRepository.existsById(noInTime)) {
                            Teacher teacher = teacherRepository.getById(noInTime);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(noInTime,0, 0, 0, 0, 0, 0, 0, 0, 1,0));
                        }

                        //update dishonorable
                        if (teacherRepository.existsById(dishonorable)) {
                            Teacher teacher = teacherRepository.getById(dishonorable);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(dishonorable,0, 0, 0, 0, 0, 0, 0, 0, 0,1));
                        }

                        if (pageRepository.existsById("votes")) {
                            Page requests = pageRepository.getById("votes");
                            requests.increaseViews();
                            pageRepository.save(requests);
                        }
                        else {
                            pageRepository.save(new Page("votes", 1));
                        }

                        return "0";
                    }
                    else {
                        return "used";
                    }
                }
                else {
                    return "doesn't exist";
                }
        }
        return "1";
    }
}
