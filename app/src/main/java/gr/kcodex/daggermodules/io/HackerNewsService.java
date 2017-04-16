package gr.kcodex.daggermodules.io;

import java.util.List;

import gr.kcodex.daggermodules.io.model.Story;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsService {

    //https://hacker-news.firebaseio.com/
    @GET("v0/topstories.json")
    Call<List<String>> topStoryIds();

    //https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty
    @GET("v0/item/{itemId}.json")
    Call<Story> getStory(@Path("itemId") String itemId);
}
