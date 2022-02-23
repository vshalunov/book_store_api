package com.github.zlwqa.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDataForAdd {

    private CollectionOfIsbns[] collectionOfIsbns;
    private String userId;
}
