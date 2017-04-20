package gr.kcodex.applib.io.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import gr.kcodex.applib.io.util.HtmlTextConverter;

@JsonObject
public class Item implements Parcelable {

    @JsonField
    public String by;
    @JsonField
    public int descendants;
    @JsonField
    public int id;
    @JsonField
    public int[] kids;
    @JsonField
    public int score;
    @JsonField(typeConverter = HtmlTextConverter.class)
    public String text;
    @JsonField
    public long time;
    @JsonField
    public String title;
    @JsonField
    public String type;
    @JsonField
    public String url;

    public Item() {
        this.by = "";
        this.descendants = 0;
        this.id = -1;
        this.kids = new int[]{};
        this.score = 0;
        this.time = 0;
        this.title = "";
        this.type = "";
        this.text = "";
        this.url = "";
    }

    protected Item(Parcel in) {
        this.by = in.readString();
        this.descendants = in.readInt();
        this.id = in.readInt();
        this.kids = in.createIntArray();
        this.score = in.readInt();
        this.text = in.readString();
        this.time = in.readLong();
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(by);
        dest.writeInt(descendants);
        dest.writeInt(id);
        dest.writeIntArray(kids);
        dest.writeInt(score);
        dest.writeString(text);
        dest.writeLong(time);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
