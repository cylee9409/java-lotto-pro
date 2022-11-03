package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottosTest {

    @Test
    @DisplayName("로또 구매 개수 테스트")
    void purchase_lotto_cnt_test() {
        assertThat(new Lottos(new Payment("14000").getPurchasedLottoCnt()).getLottosSize()).isEqualTo(14);
    }

}
