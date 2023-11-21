package com.SplitwiseClone.SplitwiseClone.service.strategy;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(Strategy strategy){
        return switch (strategy){
            case HEAP_BASED -> new HeapBasedSettledUpStrategy();
        };
    }
}
