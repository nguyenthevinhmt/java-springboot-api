package rest_api_app.Common.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingResultDto<T> {
    private List<T> items;
    private long totalItems;
}
