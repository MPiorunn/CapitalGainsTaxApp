package com.capital.gains.tax.app.core.infrastructure.boundary.inbound.rest;

import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTax;
import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTaxFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CapitalGainsTaxService {

    private final CapitalGainsTaxFacade capitalGainsTaxFacade;

    public CapitalGainsTax calculateCapitalGainsTax(CapitalGainsTaxRequest request) {
        return capitalGainsTaxFacade.calculateCapitalGainsTax(request.getStock(), request.getAmount());
    }
}
