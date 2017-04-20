package gr.kcodex.applib.io.injection;

import dagger.BindsInstance;
import dagger.Component;
import gr.kcodex.applib.io.HackerNewsServiceClient;

@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder hackerNewsServiceClient(HackerNewsServiceClient client);

        NetworkComponent build();
    }

    void inject(HackerNewsServiceClient client);
}
