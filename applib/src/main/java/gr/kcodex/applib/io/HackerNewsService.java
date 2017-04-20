package gr.kcodex.applib.io;

import java.util.List;

import gr.kcodex.applib.io.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsService {

    //https://hacker-news.firebaseio.com/
    @GET("v0/topstories.json")
    Call<List<String>> topStoryIds();

    //https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty
    @GET("v0/item/{itemId}.json")
    Call<Item> getItem(@Path("itemId") int itemId);
}
