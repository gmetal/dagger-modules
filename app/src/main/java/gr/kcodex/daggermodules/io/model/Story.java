package gr.kcodex.daggermodules.io.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import gr.kcodex.daggermodules.io.util.HtmlTextConverter;

@JsonObject
public class Story implements Parcelable {

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

    public Story() {
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

    protected Story(Parcel in) {
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

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
