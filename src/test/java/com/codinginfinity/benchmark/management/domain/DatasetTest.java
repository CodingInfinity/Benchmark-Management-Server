package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.repository.DatasetRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;

/**
 * Created by reinhardt on 2016/06/26.
 */
public class DatasetTest extends RepoManagementTest<Dataset, DatasetRepository,DatasetCategory> {
    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(Dataset.class);
    }

    @Override
    Dataset makeObjectOfType() {
        return new Dataset();
    }

    @Override
    void setCategoriesForType(Dataset obj) {
        List<DatasetCategory> categories = new ArrayList<DatasetCategory>();
        DatasetCategory sorting = new DatasetCategory(new Long(1), "Sorting");
        DatasetCategory ai = new DatasetCategory(new Long(2), "Artificial Intelligence");
        categories.add(sorting);
        categories.add(ai);
        obj.setCategories(categories);
    }
}
