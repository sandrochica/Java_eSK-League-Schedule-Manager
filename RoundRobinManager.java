import java.util.ArrayList;

public class RoundRobinManager {
    private ArrayList<String> teams;

    public RoundRobinManager() {
        teams = new ArrayList<>();
    }

    public String addTeam(String teamName) {
        if (teamName != null && !teamName.isEmpty() && !teams.contains(teamName)) {
            teams.add(teamName);
            return "Team \"" + teamName + "\" added successfully!";
        }
        return "Team \"" + teamName + "\" already exists or invalid name.";
    }

    public String updateTeam(int index, String newName) {
        if (index >= 0 && index < teams.size() && newName != null && !newName.isEmpty()) {
            String oldName = teams.get(index);
            teams.set(index, newName);
            return "Team \"" + oldName + "\" updated to \"" + newName + "\".";
        }
        return "Invalid index or name.";
    }

    public String deleteTeam(int index) {
        if (index >= 0 && index < teams.size()) {
            String removedTeam = teams.remove(index);
            return "Team \"" + removedTeam + "\" deleted successfully!";
        }
        return "Invalid index.";
    }

    public String generateSchedule() {
        if (teams.size() < 3) {
            return "Not enough teams to generate a schedule. Minimum 3 teams required.";
        }
        if (teams.size() > 10) {
            return "Too many teams. Maximum 10 teams allowed.";
        }

        ArrayList<String> tempTeams = new ArrayList<>(teams);
        if (tempTeams.size() % 2 != 0) {
            tempTeams.add("Bye");
        }

        StringBuilder schedule = new StringBuilder();
        int numRounds = tempTeams.size() - 1;
        int numMatchesPerRound = tempTeams.size() / 2;

        for (int round = 0; round < numRounds; round++) {
            schedule.append("Round ").append(round + 1).append(":\n");
            for (int match = 0; match < numMatchesPerRound; match++) {
                String team1 = tempTeams.get(match);
                String team2 = tempTeams.get(tempTeams.size() - 1 - match);
                if (!team1.equals("Bye") && !team2.equals("Bye")) {
                    schedule.append(team1).append(" vs ").append(team2).append("\n");
                }
            }
            schedule.append("\n");
            tempTeams.add(1, tempTeams.remove(tempTeams.size() - 1));
        }

        return schedule.toString();
    }

    public ArrayList<String> getTeams() {
        return teams;
    }
}