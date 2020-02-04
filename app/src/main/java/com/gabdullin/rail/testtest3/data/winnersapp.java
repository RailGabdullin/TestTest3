package com.gabdullin.rail.testtest3.data;

import com.gabdullin.rail.testtest3.data.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface winnersapp {

    @GET("clickapi/tester.php")
    Call<DataModel> startRequest(@Query("campkey") String campkey);
}
