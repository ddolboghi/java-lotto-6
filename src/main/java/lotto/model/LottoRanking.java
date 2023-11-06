package lotto.model;

import java.util.HashMap;
import java.util.Map;
import lotto.common.config.LottoGameRule;
import lotto.common.config.LottoWinningRule;

public class LottoRanking {
    private final LottoBucket lottoBucket;
    private final Lotto winningLotto;
    private final int bonusNumber;

    private final Map<LottoWinningRule, Integer> winningDetails = new HashMap<>() {{
        put(LottoWinningRule.RANK_FIVE, LottoGameRule.INITIAL_WINNING_NUMBER.getConstant());
        put(LottoWinningRule.RANK_FOUR, LottoGameRule.INITIAL_WINNING_NUMBER.getConstant());
        put(LottoWinningRule.RANK_THREE, LottoGameRule.INITIAL_WINNING_NUMBER.getConstant());
        put(LottoWinningRule.RANK_TWO, LottoGameRule.INITIAL_WINNING_NUMBER.getConstant());
        put(LottoWinningRule.RANK_ONE, LottoGameRule.INITIAL_WINNING_NUMBER.getConstant());
    }};

    public LottoRanking(LottoBucket lottoBucket, Lotto winningLotto, int bonusNumber) {
        this.bonusNumber = bonusNumber;
        this.winningLotto = winningLotto;
        this.lottoBucket = lottoBucket;
    }

    public Map<LottoWinningRule, Integer> getWinningDetails() {
        checkWinning();
        return winningDetails;
    }

    private void checkWinning() {
        for (int i = 0; i < lottoBucket.getLottoAmount(); i++) {
            addWinningCount(lottoBucket.getLotto(i));
        }
    }

    private void addWinningCount(Lotto randomLotto) {
        LottoWinningRule lottoWinningRule = LottoWinningRule.getRank(winningLotto.countMatchOtherLotto(randomLotto),
                randomLotto.contains(bonusNumber));
        if (winningDetails.containsKey(lottoWinningRule)) {
            winningDetails.put(lottoWinningRule, winningDetails.get(lottoWinningRule) + 1);
        }
    }
}
