package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Winning {

    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    private final int matches;
    private final int reward;
    private final boolean bonusBallMatched;

    Winning(int matches, int reward, boolean bonusBallMatched) {
        this.matches = matches;
        this.reward = reward;
        this.bonusBallMatched = bonusBallMatched;
    }

    public int getMatches() {
        return matches;
    }

    public int getReward() {
        return reward;
    }

    public boolean getBonusBallMatched() {
        return bonusBallMatched;
    }

    //TODO : 꼭 이렇게 나눠야만 할까?
    public static Winning getWinningByMatches(int matches) {

        return getWinningInfo().stream()
                .filter(winning -> winning.getMatches() == matches && winning.getBonusBallMatched() == false)
                .findFirst()
                .orElse(MISS);

    }

    public static Winning getWinningByMatches(int matches, boolean bonusBallMatched) {

        return getWinningInfo().stream()
                .filter(winning -> winning.getMatches() == matches && winning.getBonusBallMatched() == bonusBallMatched)
                .findFirst()
                .orElse(MISS);

    }

    public static List<Winning> getWinningInfo() {
        return Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH);
    }

    public static void main(String[] args) {
        System.out.println(Winning.getWinningByMatches(4, true));
    }
}
