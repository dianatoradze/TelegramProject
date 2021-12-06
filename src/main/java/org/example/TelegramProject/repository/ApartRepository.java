package org.example.TelegramProject.repository;

import org.example.TelegramProject.model.ApartEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

    @RepositoryRestResource(collectionResourceRel = "apart", path = "apart")
    public interface ApartRepository extends PagingAndSortingRepository<ApartEntity, String> {

        List<ApartEntity> findApartEntityByApartTypeContains(@Param("1к") String apartType);

    }

