package study.lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import study.lotto.domain.lottomachine.LottoGenerator;
import study.lotto.domain.sorter.IntegerAscendingSorter;
import study.lotto.domain.sorter.IntegerSorter;

public class AutomaticLottoGenerator implements LottoGenerator {
    private final IntegerSorter integerSorter;

    public AutomaticLottoGenerator() {
        this(new IntegerAscendingSorter());
    }

    public AutomaticLottoGenerator(IntegerSorter integerSorter) {
        this.integerSorter = integerSorter;
    }

    @Override
    public List<LottoNumber> generate() {
        return generateRandomLottoNumbers();
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        List<Integer> lottoNumbers = shuffledLottoNumbers().subList(0, Lotto.LOTTO_NUMBER_SIZE);
        sortLottoNumbers(lottoNumbers);
        return lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void sortLottoNumbers(List<Integer> lottoNumbers) {
        integerSorter.sort(lottoNumbers);
    }

    private List<Integer> shuffledLottoNumbers() {
        List<Integer> lottoNumbers = possibleLottoNumber();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    private List<Integer> possibleLottoNumber() {
        return IntStream.range(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
