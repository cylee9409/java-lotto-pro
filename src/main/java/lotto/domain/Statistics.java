package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private static final int PERCENTAGE = 100;
    private static final int DEFAULT_COUNT = 0;
    private Map<Winning, Integer> statistics;

    public Statistics(Lottos lottos, Lotto winLotto, LottoNumber bonus) {
        statistics = new HashMap<>();
        setStatistics(lottos, winLotto, bonus);

    }

    private void setStatistics(Lottos lottos, Lotto winLotto, LottoNumber bonus) {
        lottos.getLottoList().stream().forEach(lotto -> addToMap(lotto, winLotto, bonus));
    }

    //TODO : 좋은 방법이 없을지 고민해보기 이렇게 if로 꼭 명시해줘야할까?
    private void addToMap(Lotto lotto, Lotto winLotto, LottoNumber bonus) {
        int matchCnt = lotto.countMatchNum(winLotto);
        Winning winning = Winning.getWinningByMatches(matchCnt);
        int lottoCnt = statistics.getOrDefault(winning, DEFAULT_COUNT);

        if (matchCnt == 5 && lotto.isContained(bonus)) {
            winning = Winning.getWinningByMatches(matchCnt, lotto.isContained(bonus));
        }

        statistics.put(winning, lottoCnt + 1);
    }


    public double getYield(Payment payment) {
        double rewards = getTotalWinnings();
        return Math.floor(rewards / payment.getPayment() * PERCENTAGE) / PERCENTAGE;
    }

    private double getTotalWinnings() {
        return statistics.keySet().stream().mapToDouble(this::getRewardByMatches).sum();
    }

    private double getRewardByMatches(Winning winning) {
        return winning.getReward() * statistics.get(winning);
    }

    public int getMatchedLottoCnt(Winning winning) {
        return statistics.getOrDefault(winning, DEFAULT_COUNT);
    }

}
