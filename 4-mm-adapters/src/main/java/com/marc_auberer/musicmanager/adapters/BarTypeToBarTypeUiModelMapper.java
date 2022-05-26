package com.marc_auberer.musicmanager.adapters;

import com.marc_auberer.musicmanager.domain.bartype.BarType;

import java.util.function.Function;

public class BarTypeToBarTypeUiModelMapper implements Function<BarType, BarTypeUiModel> {
    @Override
    public BarTypeUiModel apply(BarType barType) {
        return new BarTypeUiModel(
                barType.getId(),
                barType.getBeatCount(),
                barType.getBeatValue()
        );
    }
}
