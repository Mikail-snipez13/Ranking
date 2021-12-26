package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/teacher/all")
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @RequestMapping(value = "/teacher/best/joker")
    public List<String> getBestJoker() {
        return getBest(Category.JOKER);
    }

    @RequestMapping(value = "/teacher/best/unprepared")
    public List<String> getBestUnprepared() {
        return getBest(Category.UNPREPARED);
    }

    @RequestMapping(value = "/teacher/best/late")
    public List<String> getBestLate() {
        return getBest(Category.LATE);
    }

    @RequestMapping(value = "/teacher/best/unreliable")
    public List<String> getBestSpoiled() {
        return getBest(Category.UNRELIABLE);
    }

    @RequestMapping(value = "/teacher/best/party")
    public List<String> getBestParty() {
        return getBest(Category.PARTY);
    }

    @RequestMapping(value = "/teacher/best/smart")
    public List<String> getBestSmart() {
        return getBest(Category.SMART);
    }

    @RequestMapping(value = "/teacher/best/beauty")
    public List<String> getBestBeauty() {
        return getBest(Category.BEAUTY);
    }

    @RequestMapping(value = "/teacher/best/noMention")
    public List<String> getBestNoMention() {
        return getBest(Category.NOMENTION);
    }

    @RequestMapping(value = "/teacher/best/noInTime")
    public List<String> getBestNoInTime() {
        return getBest(Category.NOINTIME);
    }

    @RequestMapping(value = "/teacher/best/dishonorable")
    public List<String> getBestDishonorable() {
        return getBest(Category.DISHONORABLE);
    }

    private List<String> getBest(Category category){
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> bestMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            bestMap.put(teacher.getId(), callCategory(category, teacher));
        }

        List<Integer> best = new ArrayList<>(bestMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < bestMap.size(); n++) {
            for (String i : bestMap.keySet()) {
                int current = bestMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                //Capitalize name
                String s = it.next();
                result.add(s.substring(0,1).toUpperCase() + s.substring(1));
            }
        }
        return result;
    }

    private int callCategory(Category category, Teacher teacher) {

        return switch (category) {
            case JOKER -> teacher.getJoker();
            case UNPREPARED -> teacher.getUnprepared();
            case LATE -> teacher.getLate();
            case UNRELIABLE -> teacher.getSpoiled();
            case PARTY -> teacher.getParty();
            case SMART -> teacher.getSmart();
            case BEAUTY -> teacher.getBeauty();
            case NOMENTION -> teacher.getNoMention();
            case NOINTIME -> teacher.getNoInTime();
            case DISHONORABLE -> teacher.getDishonorable();
        };
    }

    private enum Category {
        JOKER,
        UNPREPARED,
        LATE,
        UNRELIABLE,
        PARTY,
        SMART,
        BEAUTY,
        NOMENTION,
        NOINTIME,
        DISHONORABLE
    }

}
