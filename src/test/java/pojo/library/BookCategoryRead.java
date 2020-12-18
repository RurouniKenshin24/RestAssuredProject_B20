package pojo.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookCategoryRead {
    @JsonProperty("id")
    private int category_id;
    @JsonProperty("name")
    private String category_name;

    public BookCategoryRead() {}

    public BookCategoryRead(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "BookCategoryRead{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
