package com.jcr.itunessearcher.data.network;


import android.arch.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceiTunes {
    @GET("search")
    LiveData<ApiResponse<ResponseiTunesSearch>> searchItunes(@Query("term") String search);
}
