package ru.kaznacheev.authservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class UserProviderPrimaryKey implements Serializable {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "provider_id", nullable = false)
    private Integer providerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProviderPrimaryKey that = (UserProviderPrimaryKey) o;
        return Objects.equals(getUserId(), that.getUserId())
                && Objects.equals(getProviderId(), that.getProviderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getProviderId());
    }

}
