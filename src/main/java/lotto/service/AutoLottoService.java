package lotto.service;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.Messages;
import lotto.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoService {

    public void autoLottoService() {
        Payment payment = new Payment(InputView.printConsoleMsg(Messages.ASK_PAYMENT));
        Lottos lottos = new Lottos(payment.getPurchasedLottoCnt());
        ResultView.printPurchasedLottoCnt(lottos);
        ResultView.printGeneratedLotto(lottos);
        Lotto winLotto = new Lotto(InputView.printConsoleMsg(Messages.ASK_LAST_WIN_LOTTO_NUMBERS));
        LottoNumber bonusBall = new LottoNumber(InputView.printConsoleMsg(Messages.ASK_BONUS_BALL_NUMBER));

        if (winLotto.isContained(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복되지 않아야 합니다.");
        }

        Statistics statistics = new Statistics(lottos, winLotto, bonusBall);
        ResultView.printTotalResult(payment, statistics);
    }

}
