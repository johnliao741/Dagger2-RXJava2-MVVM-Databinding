package interview.exam.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

import static interview.exam.data.local.entity.GetItem.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class GetItem {
    final static String TABLE_NAME = "room_entity";
    @NonNull
    @PrimaryKey
    private String id;
    private String name;
    private String url;
    private int width;
    private int height;
    @Json(name = "box_count")
    @ColumnInfo(name = "box_count")
    private int boxCount;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "GetItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", boxCount=" + boxCount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
