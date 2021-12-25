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
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getJoker());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/unprepared")
    public List<String> getBestUnprepared() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getUnprepared());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/late")
    public List<String> getBestLate() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getLate());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/unreliable")
    public List<String> getBestSpoiled() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getSpoiled());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/party")
    public List<String> getBestParty() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getParty());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/smart")
    public List<String> getBestSmart() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getSmart());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/beauty")
    public List<String> getBestBeauty() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getBeauty());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/noMention")
    public List<String> getBestNoMention() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getNoMention());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/noInTime")
    public List<String> getBestNoInTime() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getNoInTime());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }

    @RequestMapping(value = "/teacher/best/dishonorable")
    public List<String> getBestDishonorable() {
        List<Teacher> teacherList = teacherRepository.findAll();
        HashMap<String, Integer> jokerMap = new HashMap<>();

        for (Teacher teacher : teacherList) {
            jokerMap.put(teacher.getId(), teacher.getDishonorable());
        }

        List<Integer> best = new ArrayList<>(jokerMap.values());
        List<String> bestName = new ArrayList<>();
        List<String> result = new ArrayList<>();

        Collections.sort(best);
        Collections.reverse(best);

        for (int n = 0; n < jokerMap.size(); n++) {
            for (String i : jokerMap.keySet()) {
                int current = jokerMap.get(i);
                if (best.get(n) == current) {
                    bestName.add(i);
                }
            }
        }
        Iterator<String> it = bestName.iterator();
        if(bestName.size() > 2) {
            for (int i = 0; i < 3; i++) {
                result.add(it.next());
            }
        }
        return result;
    }
}
