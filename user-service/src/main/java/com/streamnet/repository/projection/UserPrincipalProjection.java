package com.streamnet.repository.projection;

public interface UserPrincipalProjection {
    Long getId();
    String getEmail();
    String getActivationCode();
}
