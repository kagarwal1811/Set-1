package com.bookreview.data.api;

import android.content.Context;
import com.bookreview.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FakeInterceptor implements Interceptor {
    private final Context ctx;
    public FakeInterceptor(Context ctx) { this.ctx = ctx; }

    @Override public Response intercept(Chain chain) throws IOException {
        if (chain.request().url().encodedPath().equals("/books")) {
            InputStream is = ctx.getResources().openRawResource(R.raw.booklist);
            String json = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining());
            return new Response.Builder()
                    .code(200).message(json)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .body(ResponseBody.create(json, MediaType.parse("application/json")))
                    .build();
        }
        return chain.proceed(chain.request());
    }
}
