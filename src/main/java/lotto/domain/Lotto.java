package lotto.domain;

import static lotto.exception.ExceptionMessage.ERROR_INVALID_LOTTO_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int DIGIT = 6;
    private static final String SEPARATOR = ",";

    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(List<Integer> values) {
        validateCount(values);
        values.forEach(value -> {
            this.numbers.add(new LottoNumber(value));
        });
        Collections.sort(this.numbers);
    }

    private void validateCount(final List<Integer> values) {
        if (values.size() != DIGIT) {
            throw new IllegalArgumentException(ERROR_INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    public Lotto(final String value) {
        String[] splited = value.split(SEPARATOR);
        validateCount(splited);
        Arrays.stream(splited)
            .map(LottoNumber::from)
            .sorted()
            .forEach(this.numbers::add);
    }

    private void validateCount(final String[] splited) {
        if (splited.length != DIGIT) {
            throw new IllegalArgumentException(ERROR_INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    public WinningInformation matchWithWinningLotto(final Lotto lotto, final LottoNumber bonusNumber) {
        int countOfSameNumber = (int) lotto.numbers.stream()
            .filter(this.numbers::contains)
            .count();

        boolean isContainedBonus = false;
        if (countOfSameNumber == 5) {
            isContainedBonus = this.numbers.contains(bonusNumber);
        }

        return WinningInformation.findByMatchingResult(new MatchingResult(countOfSameNumber, isContainedBonus));
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return this.numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }
}
