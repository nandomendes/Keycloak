package com.mendes.nando.keycloak;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataService {

    //@POST("/auth/realms/demo/protocol/openid-connect/token")
    @FormUrlEncoded
    @POST("/auth/realms/demo/protocol/openid-connect/auth")
    Call<AccessToken> getAccessToken(
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type,
            @Field("scope") String scope,
            @Field("username") String username,
            @Field("password") String password

    );
}
