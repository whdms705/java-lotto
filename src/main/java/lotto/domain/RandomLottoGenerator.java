package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoNumber.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoNumber.LOTTO_MIN_NUMBER;

public class RandomLottoGenerator implements LottoGenerator {
    private final long automaticIssueQuantity;

    public RandomLottoGenerator(long automaticIssueQuantity) {
        this.automaticIssueQuantity = automaticIssueQuantity;
    }

    @Override
    public List<Lotto> generate() {

        ArrayList<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < automaticIssueQuantity; i++) {
            lottos.add(getRandomLotto());
        }

        return lottos;
    }

    private Lotto getRandomLotto() {
        List<Integer> lottoNumberList = Arrays.stream(IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER).toArray()).boxed().collect(Collectors.toList());
        Collections.shuffle(lottoNumberList);
        int[] numbers = lottoNumberList.subList(0, LOTTO_NUMBER_COUNT).stream().mapToInt(i -> i).toArray();
        return new Lotto(numbers);
    }
}