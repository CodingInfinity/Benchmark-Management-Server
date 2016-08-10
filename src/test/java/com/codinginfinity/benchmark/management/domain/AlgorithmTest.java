package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.repository.AlgorithmRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;
import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/26.
 */
public class AlgorithmTest extends RepoManagementTest<Algorithm, AlgorithmRepository,AlgorithmCategory> {

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(Algorithm.class);
    }

    @Override
    Algorithm makeObjectOfType() {
        return new Algorithm();
    }

    @Override
    void setCategoriesForType(Algorithm obj) {
        List<AlgorithmCategory> categories = new ArrayList<AlgorithmCategory>();
        AlgorithmCategory sorting = new AlgorithmCategory(new Long(1), "Sorting");
        AlgorithmCategory ai = new AlgorithmCategory(new Long(2), "Artificial Intelligence");
        categories.add(sorting);
        categories.add(ai);
        obj.setCategories(categories);
    }
}
