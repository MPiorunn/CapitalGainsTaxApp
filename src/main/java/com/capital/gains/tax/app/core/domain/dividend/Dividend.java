package com.capital.gains.tax.app.core.domain.dividend;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Dividend {

    private final Double amount;
    private final LocalDate declaredDate;
    private final LocalDate paymentDate;
    private final LocalDate recordDate;
}
