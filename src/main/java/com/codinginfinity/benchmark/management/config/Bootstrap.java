package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.AddRepoEntityRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.GetRepoEntityByIdRequest;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesByLoginRequest;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/09/12.
 */
@Configuration
public class Bootstrap {

    @Inject
    private DatasetManagement datasetManagement;

    @Inject
    private DatasetCategoryManagement datasetCategoryManagement;

    @Inject
    private UserManagement userManagement;

    @Inject
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void setup() throws NonExistentException {
        loadEmptyDataset();
    }

    private void loadEmptyDataset() throws NonExistentException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));
        Authentication authentication = new RunAsUserToken("user", "user", null, grantedAuthorities, null);
        securityContext.setAuthentication(authentication);

        try {
            datasetManagement.getRepoEntityById(new GetRepoEntityByIdRequest<>(1L)).getRepoEntity();
        } catch (NonExistentRepoEntityException e) {

            try {
                List<Long> datasetCategories = new ArrayList<>(1);
                datasetCategories.add(1L);

                Resource resource = new ClassPathResource("Empty Dataset.tar.gz");

                MultipartFile multipartFile = new MockMultipartFile(resource.getFilename(),
                        resource.getFilename(), "application/gzip", IOUtils.toByteArray(resource.getInputStream()));

                AddRepoEntityRequest<DatasetCategory, Dataset> request = new AddRepoEntityRequest<>();
                request.setName("Blank Dataset");
                request.setDescription("Blank dataset that pipes empty dataset to user application");
                request.setCategories(datasetCategories);
                request.setFile(multipartFile);

                datasetManagement.addRepoEntity(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (NonExistentException e1) {
                e1.printStackTrace();
            } catch (NoFileUploadedException e1) {
                e1.printStackTrace();
            } catch (FileFormatNotSupportedException e1) {
                e1.printStackTrace();
            } catch (CorruptedFileException e1) {
                e1.printStackTrace();
            }

        }
    }
}
