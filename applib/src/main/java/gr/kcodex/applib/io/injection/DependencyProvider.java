package gr.kcodex.applib.io.injection;

import okhttp3.OkHttpClient;

public interface DependencyProvider {

    String getBaseUrl();

    OkHttpClient getOkHttpClient();
}
