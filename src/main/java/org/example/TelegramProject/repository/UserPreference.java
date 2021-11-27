package org.example.TelegramProject.repository;

import lombok.Getter;
import org.springframework.lang.Nullable;

public class UserPreference {
    private static final long serialVersionUID = -2886920347632094707L;
    @Getter
    @Nullable
    private final Integer price;
    @Getter @Nullable
    private final String apartType;
    @Getter @Nullable
    private final Boolean agency;

    public UserPreference(Integer price, String apartType, Boolean agency) {
        this.price = price;
        this.apartType = apartType;

        this.agency = agency;
    }
}
