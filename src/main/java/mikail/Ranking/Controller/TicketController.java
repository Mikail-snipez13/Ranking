package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Factory.TicketFactory;
import mikail.Ranking.Repository.TeacherRepository;
import mikail.Ranking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    private final TicketFactory ticketFactory = new TicketFactory();

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/tickets") //tickets?mode=create
    public String tickets(
            @RequestParam(value = "mode", defaultValue = "") String mode,
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "beauty", defaultValue = "") String beauty,
            @RequestParam(value = "smart", defaultValue = "") String smart,
            @RequestParam(value = "nice", defaultValue = "") String nice,
            @RequestParam(value = "severe", defaultValue = "") String severe) {

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

                        //update beauty
                        if (teacherRepository.existsById(beauty)) {
                            Teacher teacher = teacherRepository.getById(beauty);
                            teacher.increaseBeauty();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(beauty, 1, 0, 0, 0));
                        }

                        //update smart
                        if (teacherRepository.existsById(smart)) {
                            Teacher teacher = teacherRepository.getById(smart);
                            teacher.increaseSmart();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(smart, 0, 1, 0, 0));
                        }

                        //update nice
                        if (teacherRepository.existsById(nice)) {
                            Teacher teacher = teacherRepository.getById(nice);
                            teacher.increaseNice();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(nice, 0, 0, 1, 0));
                        }

                        //update mad
                        if (teacherRepository.existsById(severe)) {
                            Teacher teacher = teacherRepository.getById(severe);
                            teacher.increaseSevere();
                            teacherRepository.save(teacher);
                        }
                        else {
                            teacherRepository.save(new Teacher(severe, 0, 0, 0, 1));
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
