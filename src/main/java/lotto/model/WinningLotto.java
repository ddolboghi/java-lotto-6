package lotto.model;

import lotto.model.validator.LottoNumbersValidator;

public class WinningLotto {
    private final int bonusNumber;
    private final Lotto lotto;

    public WinningLotto(Lotto lotto, String userInputBonusNumber) {
        LottoNumbersValidator.validateBlank(userInputBonusNumber);
        LottoNumbersValidator.validateNumeric(userInputBonusNumber);

        int bonusNumber = Integer.parseInt(userInputBonusNumber);

        LottoNumbersValidator.validateLottoNumberRange(bonusNumber);
        LottoNumbersValidator.validateContainLottoNumbers(bonusNumber, lotto);

        this.bonusNumber = bonusNumber;
        this.lotto = lotto;
    }
}
