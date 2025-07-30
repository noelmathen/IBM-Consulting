import java.util.*;
import java.util.stream.*;

class Agent {
    private String name;
    private long generatedFund;

    public Agent(String name, long generatedFund) {
        this.name = name;
        this.generatedFund = generatedFund;
    }

    public String getName() { return name; }
    public long getGeneratedFund() { return generatedFund; }

    public void setName(String name) { this.name = name; }
    public void setGeneratedFund(long generatedFund) { this.generatedFund = generatedFund; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of agents: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Agent> agents = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name and generated fund (comma separated): ");
            String[] input = sc.nextLine().split(",");
            String name = input[0].trim();
            long fund = Long.parseLong(input[1].trim());
            agents.add(new Agent(name, fund));
        }

        // Use Stream API to create Map<String, String> of name and stars
        Map<String, String> gradedAgents = agents.stream()
                .collect(Collectors.toMap(
                        Agent::getName,
                        agent -> getStars(agent.getGeneratedFund())
                ));

        // Display each agent with stars
        gradedAgents.forEach((name, stars) -> System.out.println(name + "=" + stars));

        sc.close();
    }

    private static String getStars(long fund) {
        if (fund >= 2000000) return "*****";
        else if (fund >= 1500000) return "***";
        else if (fund >= 1000000) return "**";
        else return "*";
    }
}
