package lotto.domain;

import generator.LottoNumberGenerator;
import generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class LottoMarket {
    private final NumberGenerator numberGenerator;

    public LottoMarket() {
        this.numberGenerator = new LottoNumberGenerator();
    }

    public LottoTicket purchaseLottoTicket(Money money) {
        LottoCount lottoCount = LottoCount.calculateBy(money);
        return LottoTicket.from(generateAutoLottoNumbers(lottoCount));
    }

    private List<LottoNumbers> generateAutoLottoNumbers(LottoCount lottoCount) {
        List<LottoNumbers> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getCount(); i++) {
            lottos.add(LottoNumbers.generateBy(this.numberGenerator));
        }
        return lottos;
    }
}
