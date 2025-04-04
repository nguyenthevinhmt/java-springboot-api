package rest_api_app.Shared.Common.Api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
public class PagingRequestBaseDto {
    @Setter
    @JsonProperty("page_size")
    private int pageSize;
    @Setter
    @JsonProperty("page_index")
    private int pageIndex;
    @JsonProperty("keyword")
    private String keyWord = "";

    public PagingRequestBaseDto(int pageSize, int pageIndex, String keyWord) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.setKeyWord(keyWord);
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord != null ? keyWord.trim() : "";
    }
}
