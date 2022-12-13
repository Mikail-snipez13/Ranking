package mikail.Ranking.Factory;

import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TicketFactory {

    public Ticket createTicket(TicketRepository repo, Long rankingId) {
        String random = randomString();
        do {random = randomString();}
        while (repo.getByKeyAndRankingId(random, rankingId) != null);

        return new Ticket(random, rankingId);
    }

    public String randomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int RANDOM_STRING_LENGTH = 6;
        for(int i = 0; i < RANDOM_STRING_LENGTH; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
