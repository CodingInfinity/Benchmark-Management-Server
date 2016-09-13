package com.codinginfinity.benchmark.management.service.repositoryManagement.category.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/28.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetCategoryByIdRequest<T> extends Request {

    private static final long serialVersionUID = -1949786225976747097L;

    private Long id;
}
